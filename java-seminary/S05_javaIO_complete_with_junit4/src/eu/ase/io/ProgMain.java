package eu.ase.io;

/*
double[] prices = new double[] {10, 11, 9};
int[] units = new int[] {12, 8, 9};
String[] productNames = new String[] {"T-Shirt", "Mug", "Pen"};
	
Order order = new Order(units, prices, productNames);
order.downloadOrder("test2.txt");
order.readOrderFromFileAndComputeTotal("test2.txt");
*/

public class ProgMain {

	public static void main(String[] args) {
		double[] prices = new double[] {10, 11, 9};
		int[] units = new int[] {12, 8, 9};
		String[] productNames = new String[] {"T-Shirt", "Mug", "Pen"};
			
		Order order = new Order(units, prices, productNames);
		order.downloadOrder("test2.txt");
		double total = order.readOrderFromFileAndComputeTotal("test2.txt");
		System.out.println("Order total is " + total);

	}

}
