package eu.ase.poly;


// test the polymorphism, class-cast exception, interface as type and try-catch mechnism, example:
/*
		//--------------------------------------
		//Polymorphism demo
		//--------------------------------------
		Animal animal = null;
		Lion lion = null;
		Snake snake = null;
		try {
			lion = new Lion(130, 50);
			snake = new Snake(5, 1.0f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		animal = lion;
		System.out.println(animal.display());
		
		animal = snake;
		System.out.println(animal.display());
		

		// --------------------------------------
		// try-catch mechanism ClassCastException
		// --------------------------------------
		try {
			Animal animal_test = null;
			animal_test = lion;
			
			snake = (Snake) animal_test; //down-casting polymorphically // Lion cannot be cast to Snake
			snake = (Snake) new Animal(23); //down-casting polymorphically // Animal cannot be cast to Snake
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}       
        
		//--------------------------------------
        //interface as type
		//--------------------------------------
		try {
			Action action = new Lion(160, 30); //upcasting polymorphically
			 action.eat();
			 action = new Snake(160, 30.0f); //upcasting polymorphically
			 action.eat();
		     System.out.println("No Lions = " + Lion.getNoLions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
*/
public class ProgMain {

	public static void main(String[] args) {
		
	}

}
