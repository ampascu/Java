package eu.ase.multi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ProgMain {

	private static final int NTHREADS = 4;

	public static void main(String[] args) {

		int dimVect = 80_000_000; // "_" is used only for readability

		int[] v = new int[dimVect];
		long sum = 0L;

		for (int i = 0; i < dimVect; i++)
			v[i] = 1 + i;

		int startIdx = 0, stopIdx = 0;
		long startTime = 0, stopTime = 0;
		long[] vectSum = new long[NTHREADS];

		// -----------
		// 1. Sequential
		
		sum = 0L;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < dimVect; i++) {
			sum += v[i];
		}
		stopTime = System.currentTimeMillis();
		System.out.println("1. Seq time = " + (stopTime - startTime) + " , sum = " + sum);


		// 2. Multi-threading standard
		
		sum = 0L;
		startTime = System.currentTimeMillis();

		Thread[] vectThreads = new Thread[NTHREADS];
		MyMultiThreadArray[] vectRThreads = new MyMultiThreadArray[NTHREADS];

		for (int it = 0; it < NTHREADS; it++) {
			startIdx = it * (dimVect / NTHREADS);
			stopIdx = (it + 1) * (dimVect / NTHREADS);
			vectSum[it] = 0L;
			vectRThreads[it] = new MyMultiThreadArray(v, startIdx, stopIdx);
			vectThreads[it] = new Thread(vectRThreads[it]);
		}

		for (int it = 0; it < NTHREADS; it++) {
			vectThreads[it].start();
		}

		for (int it = 0; it < NTHREADS; it++) {
			try {
				// inter-thread synchronization
				// the calling thread goes into a waiting state and
				// it remains in a waiting state until the referenced thread terminates
				vectThreads[it].join();
			} catch (InterruptedException ie) {
				// Thrown when a thread is waiting, sleeping, or otherwise occupied,
				// and the thread is interrupted, either before or during the activity
				ie.printStackTrace();
			}
		}

		for (int it = 0; it < NTHREADS; it++) {
			sum += vectRThreads[it].getSum();
		}

		stopTime = System.currentTimeMillis();
		System.out.println("2. MultiThread Standard Time = " + (stopTime - startTime) + " , sum = " + sum);

		
		
		
		// 3. Multi-threading executor-service
		// ExecutorService automatically provides a pool of threads and an API for
		// assigning tasks to it.
		// If additional tasks are submitted when all threads are active,
		// they will wait in the queue until a thread is available.
		
		sum = 0L;
		startTime = System.currentTimeMillis();
		ExecutorService execThreadPool = Executors.newFixedThreadPool(NTHREADS);
		MyMultiThreadArray[] workerTask = new MyMultiThreadArray[NTHREADS];

		for (int it = 0; it < NTHREADS; it++) {
			startIdx = it * (dimVect / NTHREADS);
			stopIdx = (it + 1) * (dimVect / NTHREADS);
			vectSum[it] = 0L;
			workerTask[it] = new MyMultiThreadArray(v, startIdx, stopIdx);
		}

		for (int it = 0; it < NTHREADS; it++) {
			execThreadPool.execute(workerTask[it]);
		}

		// Initiates a shutdown in which previously submitted tasks are executed,
		// but no new tasks will be accepted.
		execThreadPool.shutdown();
		try {
			// Blocks until all tasks have completed execution after a shutdown request
			// Similar to join() method
			execThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		for (int it = 0; it < NTHREADS; it++) {
			sum += workerTask[it].getSum();
		}

		stopTime = System.currentTimeMillis();
		System.out.println("3. MultiThread Executor-Service time = " + (stopTime - startTime) + " , sum = " + sum);

		
		
		
		// 4. Future - Callable mechanism
		
		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
		List<Future<Long>> list = new ArrayList<Future<Long>>();

		sum = new Long(0);
		int slot = 0;

		startTime = System.currentTimeMillis();
		for (int it = 0; it < NTHREADS; it++) {
			startIdx = it * (dimVect / NTHREADS);
			stopIdx = (it + 1) * (dimVect / NTHREADS);

			Callable<Long> worker = new MyCallableArray(v, startIdx, stopIdx);
			
			//Future object (submit) is used to check the result 
			//of the Callable (worker)
			Future<Long> submit = executor.submit(worker);
			list.add(submit);
		}

		for (Future<Long> future : list) {
			try {
				vectSum[slot] = future.get();
				slot++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException ee) {
				ee.printStackTrace();
			}
		}

		for (int it = 0; it < NTHREADS; it++) {
			sum += vectSum[it];
		}

		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		stopTime = System.currentTimeMillis();
		System.out.println(
				"4. MultiThread Callable & Future Executor-Service Time = "
						+ (stopTime - startTime) + " , sum = " + sum);

		
		
		// 5 - Fork-Join 
		sum = new Long(0); 
		startTime = System.currentTimeMillis();
		sum = SumForkJoin.sumArrays(v);
		stopTime = System.currentTimeMillis();
		System.out.println("5. Fork-Join Parallel Array Time = " + (stopTime - startTime) + " , sum = " + sum);
	}

}
