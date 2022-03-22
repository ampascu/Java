package eu.ase.io;

//create Order class which contains:
//1 - prices: double[]
//2 - units: int[]
//3 - productNames: String[] - product names within order

//Methods:
//1 - constructor: public Order(int[] units, double[] prices, String[] productNames)
//2 - get and set methods
//3 - public void downloadOrder(String orderFileName) - save the order data( in the order of the described fields) 
//into a file
//4 - public double readOrderFromFileAndComputeTotal(String orderFileName) - read from the file and calculate 
//the total of the order
//5 - clone method for deep copy
public class Order implements Cloneable{

}
