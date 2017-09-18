package microProfileService.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import microProfileService.entity.Department;

@LocalBean
@Stateless
public class DepartmentDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void saveDepartment(Department department) {
		em.merge(department);
	}
	
	public void deleteDepartment(Department department) {
		em.remove(department);
	}
	
	public List<Department> getDepartmentList(){
		
		Query query = em.createQuery("SELECT department FROM Department department");
		
		return (List<Department>) query.getResultList();
		
	}
	
	public void deleteDepartment( int deptId ) {
		
		Query query = em.createQuery("DELETE from Employee e where e.department = (SELECT d from Department d where d.departmentId = :deptId)");
		query.setParameter("deptId", deptId);
		query.executeUpdate();
		
		query = em.createQuery("DELETE from Department d where d.departmentId = :deptId");
		query.setParameter("deptId", deptId);
		query.executeUpdate();
	}

}
