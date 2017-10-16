package microservices;

import org.eclipse.microprofile.faulttolerance.Retry;

import exceptions.ConnectException;

/** Try other retry mechanisms by calling the respective method from the DepartmentService.faultToleranceRetry() method **/
public class DepartmentFaultToleranceRetry {

	private int counterForInvokingViewDepartments = 0;


	/**
	* The configured max retries is 90 but the max duration is 10000ms.
	* Once the duration is reached, no more retries should be performed,
	* even through it has not reached the max retries.
	*/
	@Retry(maxRetries = 90, maxDuration= 10000)
	public void faultToleranceRetry() throws ConnectException {
		counterForInvokingViewDepartments++;
		faultToleranceRetryService();
	}
	
	
	/**
	* There should be 0-800ms (jitter is -400ms - 400ms) delays
	* between each invocation.
	* there should be at least 4 retries but no more than 10 retries.
	*/
	@Retry(delay = 400, maxDuration= 3200, jitter= 400, maxRetries = 10)
	public void faultToleranceRetryWithDelays() throws ConnectException {
		counterForInvokingViewDepartments++;
		faultToleranceRetryService();
	}
	/**
	* Sets retry condition, which means Retry will be performed on
	* IOException.
	*/
	@Retry(retryOn = {ConnectException.class})
	public void faultToleranceRetryWithConnectException() throws ConnectException {
		counterForInvokingViewDepartments++;
		faultToleranceRetryService();
	}


	private void faultToleranceRetryService() throws ConnectException {
		
		System.out.println(" This method is being invoked " + counterForInvokingViewDepartments + " times.");
		
		if (counterForInvokingViewDepartments <= 5) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ie) {
				throw new ConnectException("System threw an exception. Check console.logs in MicroprofileTest\\target\\liberty\\wlp\\usr\\servers\\defaultServer\\logs to see the number of retries performed");
			}
			throw new ConnectException("System threw an exception. Check console.logs in MicroprofileTest\\target\\liberty\\wlp\\usr\\servers\\defaultServer\\logs to see the number of retries performed");
		}
	}

}
