package eu.ase.poly;

// Create the Lion class which is inheriting Animal and it is adding the following private fields:
// - speed: int
// - noLions: int static
// - Create default constructor and constructor with parameters
// - create get/set methods with eventual throw Exception statement
// - overwrite display method from Animal class and clone (Cloneable interface) methods
public class Lion extends Animal implements Cloneable{
	
	private int speed;
	private static int noLions;
	
	public Lion() {
		Lion.noLions++;
	}
	
	public Lion(int weight, int speed) throws Exception{
		//call the Animal constructor with weight parameter
		super(weight);
		
		if(speed < 0) {
			throw new Exception("Speed cannot be less than 0");
		}
		this.speed = speed;
		Lion.noLions ++;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) throws Exception{
		if(speed < 0) {
			throw new Exception("Speed cannot be less than 0");
		}
		this.speed = speed;
	}

	public static int getNoLions() {
		return noLions;
	}
	
	
	@Override
	public void eat() {
		System.out.println("Lion is eating!");
	}

	@Override
	public String display() {
		return "Lion - weight: " + this.getWeight() + ", speed - " + this.speed;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		try {
			Lion l = (Lion)super.clone();
			l.setSpeed(this.speed);
			Lion.noLions ++;
			
			return l;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
