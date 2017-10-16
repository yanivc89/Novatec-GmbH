package rest;

import java.io.IOException;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.exceptions.BulkheadException;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;

import dao.DepartmentDao;
import entity.Department;
import exceptions.ConnectException;
import microservices.DepartmentFaultTolerance;
import microservices.DepartmentFaultToleranceBulkhead;
import microservices.DepartmentFaultToleranceRetry;
import microservices.DepartmentFaultToleranceTimeout;
import microservices.DepartmentFaultToleranceWithFallback;
import microservices.DepartmentMicroProfileConfig;

@ApplicationScoped
@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class DepartmentService{
	
	
	@Inject
	DepartmentDao departmentDao;
	
	@Inject
	DepartmentFaultTolerance departmentFaultTolerance;
	
	@Inject
	DepartmentFaultToleranceWithFallback departmentFaultToleranceWithFallback;
	
	@Inject
	DepartmentMicroProfileConfig deptConfig;
	
	@Inject
	DepartmentFaultToleranceRetry departmentFaultToleranceRetry;
	
	@Inject	
	DepartmentFaultToleranceTimeout departmentFaultToleranceTimeout;
	
	@Inject
	DepartmentFaultToleranceBulkhead departmentFaultToleranceBulkhead;
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/countWithFaultTolerance")
	public String viewDepartmentCount(){
		
		try {
			return departmentFaultTolerance.viewDepartmentCount();
		} catch (ConnectException e) {
			return e.getMessage();
		}catch (CircuitBreakerOpenException ex) {
			return "The Circuit breaker is open and thus every request from now on gets failed without queuing them for processing.";
		}
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/countWithFaultToleranceWithFallBack")
	public String viewDepartmentCountWithFallback(){
		
		try {
			return departmentFaultToleranceWithFallback.viewDepartmentCount();
		} catch (ConnectException e) {
			return e.getMessage();
		}
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/faultToleranceRetry")
	public String faultToleranceRetry(){
		
		try {
			departmentFaultToleranceRetry.faultToleranceRetry();
		} catch (ConnectException e) {
			return e.getMessage();
		}
		
		return "This is a success message shown once the retries are done and the service is up and running";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/faultToleranceTimeout")
	public String faultToleranceTimeout(){	
		
		try {
			departmentFaultToleranceTimeout.faultToleranceTimeout();
		}catch( TimeoutException ex ) {
			return "Time out exception occurred because of the timeout configuration done over the method."
					+ " This prevents the request from going into indefinite processing state due to underlying exception ";
		}
		
		return "This will never be returned as the timeout exception occurrs";
					
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/faultToleranceBulkhead")
	public String faultToleranceBulkhead(){	
		
		try{
			departmentFaultToleranceBulkhead.faultToleranceBulkhead();
		}catch(BulkheadException ex) {
			return "Bulk head exception occurred as the service cannot process more than 5 requests concurrently. "
					+ "Bulk head annotation limits the number of concurrent requests";
		}
		
		return "Success response";
					
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/configProperties")
	public String getConfigProperties(){
		
		return deptConfig.getAllProperties();
	}
	
	@GET
	@Path("/allDepartments")
	public JsonArray getAllDepartment() throws IOException{
		
		
		JsonArrayBuilder jb = Json.createArrayBuilder();
		
		Set<Department> departmentList = departmentDao.getAllDepartment();		
		
		for( Department dept : departmentList ) {
			JsonObjectBuilder departments = Json.createObjectBuilder();
			departments.add("id", dept.getDepartmentId());
			departments.add("name", dept.getDepartmentName());
			departments.add("hod", dept.getHod());
			jb.add(departments);
		}			
		
		
		return jb.build();
		
	}
	
	@POST
	@Path("/insertOrUpdateDepartment")
	public String insertDepartment(Department department){		
		return departmentDao.addDepartment(department);		
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteEmployee/{id}")
	public String deleteDepartmentByPathParam(@PathParam("id") int deptId) {
		return departmentDao.deleteDepartment(deptId);	
	}
	
	@GET
	@Path("/deleteDepartment")
	public String deleteDepartmentByQueryParam(@QueryParam("id") int deptId) {
		return departmentDao.deleteDepartment(deptId);
	}
	
	
}
