package entity;

import java.io.Serializable;


public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private int departmentId;

	private String departmentName;

	private String hod;
	
	private int numberOfEmployees;
	

	public Department() {
		
	}
	
	public Department(int departmentId, String departmentName, String hod, int numberOfEmployees) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.hod = hod;
		this.numberOfEmployees = numberOfEmployees;
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

}