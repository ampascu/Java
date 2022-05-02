package eu.ase.multi;

class MyClassNonSync extends Thread {

	private static int a = 0;
	private static int b = 0;
	
	public MyClassNonSync(String threadName) {
		super(threadName);
	}
	
	public void myMethod() {
		System.out.println("Thread " + this.getName() + 
		": a = " + a + ", b = " + b);
		a++;
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b++;
	}
	
	@Override
	public void run() {
		System.out.println("Running thread " + this.getName());
		for(int i=0;i<3;i++) {
			this.myMethod();
		}
	}
}

class MyClassSync extends Thread {

	private static int a = 0;
	private static int b = 0;
	
	private Object myObj;
	
	public MyClassSync(String threadName) {
		super(threadName);
		myObj = new Object();
	}
	
	//public synchronized void myMethod() {
	public void myMethod() {
		synchronized(myObj) {
			System.out.println("Thread " + this.getName() + 
					": a = " + a + ", b = " + b);
			a++;
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b++;
		}
	}
	
	@Override
	public void run() {
		System.out.println("Running thread " + this.getName());
		for(int i=0;i<3;i++) {
			this.myMethod();
		}
	}
}

public class ProgMain {

	public static void main(String[] args) {
//		MyClassNonSync t1 = new MyClassNonSync("t1");
//		MyClassNonSync t2 = new MyClassNonSync("t2");
//		
//		t1.start();
//		t2.start();
//		
//		System.out.println("-- Prog main end--");
		
		MyClassSync t1 = new MyClassSync("t1");
		MyClassSync t2 = new MyClassSync("t2");
		
		t1.start();
		t2.start();
		
		System.out.println("-- Prog main end--");
	}

}
