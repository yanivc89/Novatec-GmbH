package microProfileService.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EMP_ID")
	private int empId;

	private String email;

	@Column(name="EMP_NAME")
	private String empName;

	private String phone;

	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Department department;

	public Employee() {
	}

	public int getEmpId() {
		return this.empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmail() {
		return this.email == null ? "" : this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPhone() {
		return this.phone == null ? "" : this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}