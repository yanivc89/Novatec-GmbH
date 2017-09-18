package microProfileService.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPARTMENT_ID")
	private int departmentId;

	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	private String hod;

	@Column(name="NUMBER_OF_EMPLOYEES")
	private int numberOfEmployees;
	
	@OneToMany(mappedBy="department")
	private List<Employee> employees;

	public Department() {
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getHod() {
		return this.hod == null ? "" : this.hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public int getNumberOfEmployees() {
		return this.numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}