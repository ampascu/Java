package eu.ase.poly;

//Create the Snake class which is inheriting Animal and it is adding the following private field:
//- length: float
//- Create default constructor and constructor with parameters - using super
//- create get/set methods with eventual throw Exception statement
//- overwrite display method from Animal class
public class Snake extends Animal{
	private float length;
	
	public Snake() {
		
	}
	
	public Snake(int weight, float length) throws Exception{
		//call the Animal constructor with weight parameter
		super(weight);
		
		if(length < 0) {
			throw new Exception("Lenght cannot be negative!");
		}
		this.length = length;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) throws Exception {
		if(length < 0) {
			throw new Exception("Lenght cannot be negative!");
		}
		this.length = length;
	}
}
