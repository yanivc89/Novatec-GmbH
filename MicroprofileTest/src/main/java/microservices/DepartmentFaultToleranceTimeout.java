package microservices;

import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;

public class DepartmentFaultToleranceTimeout {
	
	//Timeout occurs if the request processing time goes beyond 5 seconds
	@Timeout(5000)
	public void faultToleranceTimeout() throws TimeoutException{
		faultToleranceTimeoutService();
	}

	private void faultToleranceTimeoutService() throws TimeoutException{
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
