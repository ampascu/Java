package eu.ase.poly;


// Create the Animal class which implements Action and Cloneable interface 
// and it has a private int non-static field weight with 
// default constructor and constructor with one parameter, 
// plus get and set methods
// implement public String display() method for returning a string which contain the Animal weight
public class Animal implements Action, Cloneable {
	
	private int weight;
	
	public Animal() {
		
	}
	
	public Animal(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String display() {
		return "Animal - weight: " + this.weight;
	}

	@Override
	public void eat() {
		System.out.println("Animal is eating!");		
	}

	@Override
	public void sleep() {
		System.out.println("Animal is sleeping!");		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Animal a = (Animal) super.clone();
		a.weight = this.weight;
		
		return a;
	}

}
