package eu.ase.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// create Order class which contains:
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
public class Order {

	private double[] prices;
	private int[] units;
	private String[] productNames;

	public Order(int[] units, double[] prices, String[] productNames) {
		this.prices = prices;
		this.units = units;
		this.productNames = productNames;
	}

	public double[] getPrices() {
		return prices;
	}

	public void setPrices(double[] prices) {
		this.prices = prices;
	}

	public int[] getUnits() {
		return units;
	}

	public void setUnits(int[] units) {
		this.units = units;
	}

	public String[] getProductNames() {
		return productNames;
	}

	public void setProductNames(String[] productNames) {
		this.productNames = productNames;
	}

	public void downloadOrder(String orderFileName) {
		try {
			FileOutputStream fos = new FileOutputStream(orderFileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			DataOutputStream dos = new DataOutputStream(bos);

			for(int i=0;i<this.prices.length;i++) {
				dos.writeDouble(prices[i]);
				dos.writeInt(units[i]);
				dos.writeUTF(productNames[i]);
			}
			
			dos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ioException) {
			// TODO Auto-generated catch block
			ioException.printStackTrace();
		}
	}

	public double readOrderFromFileAndComputeTotal(String orderFileName) {
		double orderTotal = 0;
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(orderFileName)));
			while(true) {
				double price = dis.readDouble();
				int unit = dis.readInt();
				
				//trick: read product name but do nothing with it, so that you can continue with next product
				dis.readUTF();
				orderTotal += (price * unit);
			}
		} catch(EOFException eofException) {
			//exception might occur if EOF, then do nothing, just return orderTotal
			//eofException.printStackTrace();
		} catch (IOException ioException) {
			// TODO Auto-generated catch block
			ioException.printStackTrace();
		}
		try {
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderTotal;
	}

	// remember to make clone() public
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		double[] prices = new double[this.prices.length];
		int[] units = new int[this.units.length];
		String[] productNames = new String[this.productNames.length];

		for (int i = 0; i < this.prices.length; i++) {
			prices[i] = this.prices[i];
		}
		for (int i = 0; i < this.units.length; i++) {
			units[i] = this.units[i];
		}
		for (int i = 0; i < this.productNames.length; i++) {
			productNames[i] = this.productNames[i];
		}

		Order orderCopy = new Order(units, prices, productNames);

		return orderCopy;
	}

}
