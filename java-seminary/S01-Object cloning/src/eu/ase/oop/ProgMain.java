package eu.ase.oop;


/**
 * This is the entry of the appplication
 * @author ampascu
 *
 */
public class ProgMain {

	public static void main(String[] args) {
//		Apartment ap12 = new Apartment();
		String wallColour = "white";
		Apartment ap1 = new Apartment(1, wallColour);
		Apartment ap2 = new Apartment(2, wallColour);
		
		//need of getters
		System.out.println(ap1);
		
		System.out.println("Apartment #" + ap1.getApartmentNumber() + " has " + ap1.getWallColour() + " walls!");
		System.out.println("Apartment #" + ap2.getApartmentNumber() + " has " + ap2.getWallColour() + " walls!");

		//shallow copy
		ap1 = ap2;
		System.out.println("Apartment #" + ap1.getApartmentNumber() + " has " + ap1.getWallColour() + " walls!");
		System.out.println("Apartment #" + ap2.getApartmentNumber() + " has " + ap2.getWallColour() + " walls!");
		
		//shallow copy effect
		ap2.setWallColour("green");
		
		System.out.println("Apartment #" + ap1.getApartmentNumber() + " has " + ap1.getWallColour() + " walls!");
		System.out.println("Apartment #" + ap2.getApartmentNumber() + " has " + ap2.getWallColour() + " walls!");
		
		//deep copy
		Apartment ap3 = ap1.myClone();
		ap1.setWallColour("red");
		System.out.println("Apartment #" + ap1.getApartmentNumber() + " has " + ap1.getWallColour() + " walls!");
		System.out.println("Apartment #" + ap2.getApartmentNumber() + " has " + ap2.getWallColour() + " walls!");
		System.out.println("Apartment #" + ap3.getApartmentNumber() + " has " + ap3.getWallColour() + " walls!");
	}

}
