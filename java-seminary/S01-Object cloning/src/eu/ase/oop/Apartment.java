package eu.ase.oop;

public class Apartment {
	private int apartmentNumber;
	private String wallColour;
	
//	public Apartment() {
//		this.apartmentNumber = 0;
//		this.wallColour = "";
//	}
	
	public Apartment(int apartmentNumber, String wallColour) {
		//apartmentNumber = apartmentNumber;
		this.apartmentNumber = apartmentNumber;
		this.wallColour = wallColour;		
	}
	
	public int getApartmentNumber() {
		return this.apartmentNumber;
	}
	
	public String getWallColour() {
		return this.wallColour;
	}
	
	public void setWallColour(String newWallColour) {
		this.wallColour = newWallColour;
	}
	
	public Apartment myClone() {
		Apartment ap = new Apartment(this.apartmentNumber, this.wallColour);
		return ap;
	}
}
