package microProfileService.rest;

import java.util.List;

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

import microProfileService.dao.DepartmentDao;
import microProfileService.dao.EmployeeDao;
import microProfileService.entity.Department;
import microProfileService.entity.Employee;

@ApplicationScoped
@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class DepartmentEmployeeService {

	@Inject
	DepartmentDao departmentDao;
	
	@Inject
	EmployeeDao employeeDao;
	
	@GET
	@Path("/allDepartments")
	public JsonArray getAllDepartment(){
		
		
		JsonArrayBuilder jb = Json.createArrayBuilder();
		
		JsonArrayBuilder jb_emp = null;
		
		List<Department> departmentList = departmentDao.getDepartmentList();		
		
		for( Department dept : departmentList ) {
			JsonObjectBuilder departments = Json.createObjectBuilder();
			departments.add("id", dept.getDepartmentId());
			departments.add("name", dept.getDepartmentName());
			departments.add("hod", dept.getHod());
			if( dept.getEmployees().size() > 0 ) {
				jb_emp = Json.createArrayBuilder();
				for( Employee emp : dept.getEmployees() ) {					
					JsonObjectBuilder employee = Json.createObjectBuilder();
					employee.add("id", emp.getEmpId());
					employee.add("name", emp.getEmpName());
					employee.add("email", emp.getEmail());
					employee.add("phone", emp.getPhone());
					jb_emp.add(employee);
				}
			}
			departments.add("employeeList", jb_emp);
			jb.add(departments);
		}
		
		
		return jb.build();
		
	}
	
	@POST
	@Path("/insertOrUpdateDepartment")
	public void insertDepartment(Department department){		
		departmentDao.saveDepartment(department);		
	}
	
	@POST
	@Path("/insertOrUpdateEmployee")
	public void insertEmployee(Employee employee){		
		employeeDao.saveEmployee(employee);		
	}
	
	@GET
	@Path("/deleteEmployee/{id}")
	public void deleteEmployee(@PathParam("id") int empId) {
		employeeDao.deleteEmployee(empId);	
	}
	
	@GET
	@Path("/deleteDepartment")
	public void deleteDepartment(@QueryParam("id") int deptId) {
		departmentDao.deleteDepartment(deptId);	
	}
	
	
}
