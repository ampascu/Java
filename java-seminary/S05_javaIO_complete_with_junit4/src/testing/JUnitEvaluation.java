package testing;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;

import org.junit.Test;

import eu.ase.io.Order;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitEvaluation {

	@Test
	public void _310testInfoClassOrder_mark3() throws Exception {
		Class<?> t = Class.forName("eu.ase.io.Order");
		if(t.getDeclaredFields().length < 3)
			fail("Not proper number of the fields");
		for (Field f : t.getDeclaredFields()) {
			try {
	            //assertNotNull(f);
				System.out.println("Order field: " + f.toString());
	            assertTrue("The field " + f.toString() + " is private",  Modifier.isPrivate(f.getModifiers()));
	            if (f.getName().compareTo("prices") == 0)
	            	assertEquals("The 'prices' is type double[]", double[].class, f.getType());
	            if (f.getName().compareTo("units") == 0)
	            	assertEquals("The 'units' is type int[]", int[].class, f.getType());
	            if (f.getName().compareTo("productNames") == 0)
	            	assertEquals("The 'descs' is type String[]", String[].class, f.getType());
	        } catch (Exception nsfe) {
	            fail("The field "+f.toString()+" has problems in class Order.");
	        }
		}
	}
	
	@Test
	public void _313testOrderDownload_mark3() throws Exception {
		double[] prices = new double[] {10, 11, 9};
		int[] units = new int[] {9, 10, 9};
		String[] productNames = new String[] {"T-Shirt", "Mug", "Pen"};
		
		Order order = new Order(units, prices, productNames);
		System.out.println("Order test313 display = " + order.toString());
		
		order.downloadOrder("test3.txt");
		
		double total = 0.0;
		DataInputStream in = null;
		
			in = new DataInputStream(new BufferedInputStream(new FileInputStream("test3.txt")));
			double price; int unit; String productName;
			
			try {
				while (true) {
					price = in.readDouble();
					unit = in.readInt();
					productName = in.readUTF();
					total += (unit * price);
					//System.out.printf("\n Read record: %s, unit = %d, price = %f", desc, unit, price);
				}
			} catch(EOFException eofe) {
				try {
					assertEquals(281, total, 0.5);
					in.close();
					File f = new File("test3.txt");
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	@Test
	public void _313testReadOrderFromFile_mark3() throws Exception {
		double[] prices = new double[] {10, 11, 8};
		int[] units = new int[] {9, 10, 8};
		String[] productNames = new String[] {"T-Shirt", "Mug", "Pen"};
		
		Order order = new Order(units, prices, productNames);
		System.out.println("Order test313 display = " + order.toString());
		
		DataOutputStream out = null;
		
		try {
			FileOutputStream fos = new FileOutputStream("test4.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			out = new DataOutputStream(bos);
			
			for (int i = 0; i < prices.length; i++) {
				out.writeDouble(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(productNames[i]);
			}
			
			out.close();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		double total = order.readOrderFromFileAndComputeTotal("test4.txt");
		
		try {
			File f = new File("test4.txt");
			f.delete();
			assertEquals(264, total, 0.5);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void _315testOrderClone_mark3() throws Exception {
		double[] prices = new double[] {10, 11, 9};
		int[] units = new int[] {9, 10, 9};
		String[] productNames = new String[] {"T-Shirt", "Mug", "Pen"};
		
		Order order1 = new Order(units, prices, productNames);
		Order order2 = (Order) order1.clone();

		assertNotSame(order1, order2);
		if (order1.getPrices() == order2.getPrices()) {
			fail("clone not correct implemented");
		}
		if (order1.getUnits() == order2.getUnits()) {
			fail("clone not correct implemented");
		}
		if (order1.getProductNames() == order2.getProductNames()) {
			fail("clone not correct implemented");
		}
		if (! Arrays.equals(order1.getPrices(), order2.getPrices()) ) {
			fail("clone not correct implemented");
		}
		if (! Arrays.equals(order1.getUnits(), order2.getUnits()) ) {
			fail("clone not correct implemented");
		}
		
	}

}
