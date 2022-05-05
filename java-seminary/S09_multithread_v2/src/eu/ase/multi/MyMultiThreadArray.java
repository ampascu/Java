package eu.ase.multi;

/**
 * We implement Runnable because we intend on running the inside processes
 * on a thread.
 *
 */
public class MyMultiThreadArray implements Runnable {

	private int[] vector;
	private int startPos;
	private int endPos;
	
	private long sum;
	
	public MyMultiThreadArray(int[] vector, int startPos, int endPos) {
		this.vector = vector;
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	@Override
	public void run() {
		sum = 0L;
		for(int i=startPos; i<endPos; i++) {
			sum += vector[i];
		}
	}

	public long getSum() {
		return this.sum;
	}
}
