package microProfileService.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import microProfileService.entity.Employee;

@LocalBean
@Stateless
public class EmployeeDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void saveEmployee(Employee employee) {
		em.merge(employee);
	}
	
	public void deleteEmployee(int empId) {
		
		Query query = em.createQuery("DELETE from Employee e where e.empId = :empId");
		query.setParameter("empId", empId);
		
		query.executeUpdate();
	}
	
	
	public List<Employee> getAllEmployees(){
		
		Query query = em.createQuery("SELECT employee from Employee employee");
		
		List<Employee> employeeList = (List<Employee>)query.getResultList();		
		
		return employeeList;
		
	}
	

}
