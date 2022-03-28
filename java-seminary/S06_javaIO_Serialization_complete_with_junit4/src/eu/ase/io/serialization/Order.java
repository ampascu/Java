package eu.ase.io.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

/**
 * 1. Create the Order class which inherits Serializable with the following
 * parameters: - long serialVersionUID - URL orderURL - String clientName -
 * float orderTotal
 *
 * 2. Create the constructor with parameters in the order described above and
 * constructor without parameters
 * 3. Create getters only for orderURL, clientName, orderTotal
 * 3. Override the toString method 
 * 4. Create the method saveOrder which returns void
 * and receives the file name to which the Order object will be written 
 * 5. Create the method restoreOrder which returns the Order object read from the file
 * and receives the file name as parameter
 */
public class Order implements Serializable {
	/**
	 * Used to keep track of the Object that has been serialized. It is a best
	 * practice to change this value if objects have been already serialized and the
	 * class has changed. If serial versions do not match, the InvalidClassException
	 * is thrown
	 */
	private static final long serialVersionUID = -806571991288912011L;

	private URL orderURL;
	private transient String clientName;
	private float orderTotal;

	public Order(URL orderURL, String clientName, float orderTotal) {
		this.orderURL = orderURL;
		this.clientName = clientName;
		this.orderTotal = orderTotal;
	}

	public Order() {
	}

	public URL getOrderURL() {
		return orderURL;
	}

	public String getClientName() {
		return clientName;
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	@Override
	public String toString() {
		return "orderURL: " + orderURL + ", clientName: " + clientName + ", orderTotal: " + orderTotal;
	}
	
	public void saveOrder(String fileName) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(fileName));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Order restoreOrder(String fileName) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			Order order = (Order) ois.readObject();
			ois.close();
			return order;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
