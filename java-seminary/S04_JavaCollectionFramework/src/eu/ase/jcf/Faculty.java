package eu.ase.jcf;

public class Faculty implements Comparable<Faculty>{

	private String name;
	
	public Faculty(String name) {
		this.name = name;
	}
	
	
	public void print() {
		System.out.println("Faculty " + name);
	}


	@Override
	public int compareTo(Faculty arg0) {
		// TODO Auto-generated method stub
		return this.name.compareTo(arg0.name);
	}
}
