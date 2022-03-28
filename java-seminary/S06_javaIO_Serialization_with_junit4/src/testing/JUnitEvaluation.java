package testing;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import eu.ase.io.serialization.Order;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitEvaluation {

	@Test
	public void _310testInfoClassOrder() throws Exception {
		Class<?> t = Class.forName("eu.ase.io.serialization.Order");
		if(t.getDeclaredFields().length < 4)
			fail("Not proper number of the fields");
		for (Field f : t.getDeclaredFields()) {
			try {
				System.out.println("Order field: " + f.toString());
	            assertTrue("The field " + f.toString() + " is private",  Modifier.isPrivate(f.getModifiers()));
	            if (f.getName().compareTo("serialVersionUID") == 0)
	            	assertEquals("The 'serialVersionUID' is type static final long", long.class, f.getType());
	            if (f.getName().compareTo("orderURL") == 0)
	            	assertEquals("The 'orderURL' is type URL", URL.class, f.getType());
	            if (f.getName().compareTo("clientName") == 0)
	            	assertEquals("The 'clientName' is type String", String.class, f.getType());
	            if (f.getName().compareTo("orderTotal") == 0)
	            	assertEquals("The 'orderTotal' is type float", float.class, f.getType());
	        } catch (Exception nsfe) {
	            fail("The field "+f.toString()+" has problems in class Order.");
	        }
		}
	}
	
	@Test
	public void _313testOrderSerialize() throws Exception {
		URL url = new URL("https://www.google.ro/");
		Order order = new Order(url, "Gina", 123.0f);
		
		System.out.println("Order test313 display = " + order.toString());
		
		order.saveOrder("test.txt");
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
		Order testOrder = (Order)ois.readObject();
		ois.close();
		assertEquals(url, testOrder.getOrderURL());
		assertEquals("Gina", testOrder.getClientName());
		assertEquals(123.0, testOrder.getOrderTotal(), 0.5);
	}
	
	@Test
	public void _313testOrderRestore() throws Exception {
		URL url = new URL("https://www.google.ro/");
		Order order = new Order(url, "Gina", 123.0f);
		
		System.out.println("Order test313 display = " + order.toString());
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.txt"));
		oos.writeObject(order);
		oos.close();
		
		Order testOrder = new Order();
		testOrder = testOrder.restoreOrder("test.txt");
				
		assertEquals(url, testOrder.getOrderURL());
		assertEquals("Gina", testOrder.getClientName());
		assertEquals(123.0, testOrder.getOrderTotal(), 0.5);
	}

}
