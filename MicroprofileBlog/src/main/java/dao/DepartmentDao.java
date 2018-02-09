package dao;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entity.Department;


@Stateless
@LocalBean
@ManagedBean(value = "departmentDao")
public class DepartmentDao {

	private static Set<Department> departmentList;


	static {

		departmentList = new HashSet<>();
		Department java = new Department(1, "Java", "James Gosling", 1000);
		Department c = new Department(2, "C", "Dennis Ritchie", 1000);
		Department cplus = new Department(3, "C++", "Bjarne Stroustrup", 1000);
		Department  python = new Department(4, "Python", "Guido van Rossum", 1000);
		Department javascript = new Department(5, "Javascript", "Brendan Eich", 1000);

		departmentList.add(java);
		departmentList.add(python);
		departmentList.add(c);

	}

	public Set<Department> getAllDepartment(){

		return departmentList;
	}

	public Department getDepartmentById(int id) {

		for( Department dept : departmentList ) {

			if( dept.getDepartmentId() == id ) {
				return dept;
			}
		}

		return null;
	}

	public String addDepartment( Department dept ) {

		return departmentList.add(dept) ? "Successfully Added" : "Error occurred while adding";

	}

	public String updateDepartment( Department dept ) {

		Department department = getDepartmentById( dept.getDepartmentId() );

		if( department != null ) {
			return departmentList.add(dept) ? "Successfully Added" : "Error occurred while updating";
		}

		return "Department with this id not found";
	}

	public String deleteDepartment( int deptId ) {

		Department department = getDepartmentById( deptId );

		if( department != null ) {
			return departmentList.remove(department) ? "Successfully deleted" : "Error occurred while deleting";
		}

		return "Department with this id not found";
	}

}
