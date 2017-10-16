package microservices;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.exceptions.BulkheadException;

public class DepartmentFaultToleranceBulkhead {

	/** Annotating a method or a class with 
	 * @Bulkhead applies a semaphore style bulkhead, which allows the specified concurrent number of requests.
	 * 
	 */
	
	@Bulkhead(5) 
	public void faultToleranceBulkhead() throws BulkheadException{
		faultToleranceBulkheadService();
	}

//	/**When @Bulkhead is used with @Asynchronous, 
//	 * the thread pool isolation approach will be used. 
//	 * The thread pool approach allows to configure the maximum concurrent requests together with the waiting queue size. 
//	 * The semaphore approach only allows the concurrent number of requests configuration. 
//	 * @Asynchronous causes an invocation to be executed by a different thread.
//	 * @throws BulkheadException
//	 */
//	@Asynchronous
//	@Bulkhead(value = 5, waitingTaskQueue = 8)
//	public void faultToleranceBulkheadThreadpoolStyle() throws BulkheadException{
//		faultToleranceBulkheadService();
//	}
	
	private void faultToleranceBulkheadService() throws BulkheadException{
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
