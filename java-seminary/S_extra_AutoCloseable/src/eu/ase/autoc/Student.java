package eu.ase.autoc;

public class Student implements AutoCloseable {

	public static int noStud;
	
	public Student() {
		noStud++;
	}
	
	@Override
	public void close() throws Exception {
		noStud--;
		
	}

}
