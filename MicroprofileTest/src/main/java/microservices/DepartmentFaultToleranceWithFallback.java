package microservices;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;

import dao.DepartmentDao;
import exceptions.ConnectException;

@ApplicationScoped
public class DepartmentFaultToleranceWithFallback {

	private int counterForInvokingViewDepartments = 0;
	
	private int departmentCount = 0;

	@Inject
	private DepartmentDao departmentDao;

	 /*
     * Fallback is called whenever a request fails and when the circuit is in open state.
     */
	@Fallback(fallbackMethod="fallbackDepartment")
	/*
     * If one request fails in a rolling window of 2 requests, the circuit will be opened. 
     * The circuit will remain in the open state for 5 seconds and then switch to half-open state.
     * During the half-open state, if a request fails, the circuit will switch back to open state. 
     * Otherwise 2 successful requests will switch the circuit back to closed state.
     */
	@CircuitBreaker(requestVolumeThreshold=2, failureRatio=0.50, delay=5000, successThreshold=2)
	public String viewDepartmentCount() throws ConnectException {
		counterForInvokingViewDepartments++;
		return viewDepartmentCountService();
	}

	private String viewDepartmentCountService() throws ConnectException {
		// Simulating 2 failures to trip the circuit
		if (counterForInvokingViewDepartments <= 2) {
			try {
				departmentCount = departmentDao.getAllDepartment().size();
				Thread.sleep(2000);
			} catch (InterruptedException ie) {
				throw new ConnectException("The system is down. Try again later.");
			}
			//System.out.println("returning connectException");
			throw new ConnectException("The system is down. Try again later.");
		}
		return "Total number of Departments : " + departmentCount;
	}

	public String fallbackDepartment() throws ConnectException {
		return "This is the response from fallback method. Gets invoked whenever the main service fails."
				+ " Last viewed total number of Departments : " + departmentCount;
	}

}
