package eu.ase.multi;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool is an implementation of the ExecutorService that 
 * manages worker threads and provides us with tools 
 * to get information about the thread pool state and performance
 * 
 *
 */
public class SumForkJoin extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;

	static final int SEQUENTIAL_THRESHOLD =100_000;

	int[] array;
	
	int low;
	int high;

	SumForkJoin(int[] arrayInput, int low, int high) {
		this.array = arrayInput;
		this.low = low;
		this.high = high;
	}

	protected Long compute() {
		if (high - low <= SEQUENTIAL_THRESHOLD) {
			long sum = 0;
			for (int i = low; i < high; ++i)
				sum += array[i];
			return sum;
		} else {
			int mid = low + (high - low) / 2;
			
			SumForkJoin left = new SumForkJoin(array, low, mid);
			SumForkJoin right = new SumForkJoin(array, mid, high);
			
			left.fork();
			right.fork();
			
			long leftAns = left.join();
			long rightAns = right.join();
			return leftAns + rightAns;
		}
	}

	static long sumArrays(int[] array) {
		return ForkJoinPool.commonPool().invoke(new SumForkJoin(array, 0, array.length));
	}
}
