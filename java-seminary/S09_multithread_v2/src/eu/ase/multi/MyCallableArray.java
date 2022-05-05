package eu.ase.multi;

import java.util.concurrent.Callable;

/**
 * The Callable interface is similar to Runnable, 
 * in that both are designed for classes 
 * whose instances are potentially executed by another thread.
 *  
 * A Runnable, however, does not return a result and cannot throw a checked exception.
 * 
 * Runnable tasks can be run using the Thread class or ExecutorService 
 * whereas Callables can be run only using the ExecutorService.
 *
 */
public class MyCallableArray implements Callable<Long> {

	private int[] vector;
	private int startPos;
	private int endPos;
	
	private long sum;
	
	public MyCallableArray(int []vector, int startPos, 
			int endPos) {
		this.vector = vector;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	@Override
	public Long call() throws Exception {
		sum = 0L;
		for(int i=startPos; i<endPos; i++) {
			sum += vector[i];
		}
		return sum;
	}

}
