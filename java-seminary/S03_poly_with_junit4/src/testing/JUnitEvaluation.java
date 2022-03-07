package testing;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import java.lang.reflect.*;
import org.junit.Test;

import eu.ase.poly.Lion;
import eu.ase.poly.Snake;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitEvaluation {

	@Test
	public void _310testInfoClassAnimal() throws Exception {
		Class<?> t = Class.forName("eu.ase.poly.Animal");
		if(t.getDeclaredFields().length < 1)
			fail("Not proper number of the fields");
		for (Field f : t.getDeclaredFields()) {
			try {
	            //assertNotNull(f);
				System.out.println("Animal field: " + f.toString());
	            assertTrue("The field " + f.toString() + " is private",  Modifier.isPrivate(f.getModifiers()));
	            if (f.getName().compareTo("weight") == 0)
	            	assertEquals("The 'weight' is type int", int.class, f.getType());
	        } catch (Exception e) {
	            fail("The field "+f.toString()+" has problems in class Animal.");
	        }
		}
	}
	
	@Test
	public void _311testInfoClassLion() throws Exception {
		Class<?> t = Class.forName("eu.ase.poly.Lion");
		if(t.getDeclaredFields().length < 2)
			fail("Not proper number of the fields");
		for (Field f : t.getDeclaredFields()) {
			try {
	            //assertNotNull(f);
				System.out.println("Lion field: " + f.toString());
	            assertTrue("The field " + f.toString() + " is private",  Modifier.isPrivate(f.getModifiers()));
	            if (f.getName().compareTo("weight") == 0)
	            	assertEquals("The 'weight' is type int", int.class, f.getType());
	            if (f.getName().compareTo("speed") == 0)
	            	assertEquals("The 'speed' is type int", int.class, f.getType());
	            if (f.getName().compareTo("noLions") == 0)
	            	assertEquals("The field 'noLions' is int", int.class, f.getType());
	        } catch (Exception e) {
	            fail("The field "+f.toString()+" has problems in class Lion.");
	        }
		}
	}
	
	@Test
	public void _312testInfoClassSnake() throws Exception {
		Class<?> t = Class.forName("eu.ase.poly.Snake");
		if(t.getDeclaredFields().length < 1)
			fail("Not proper number of the fields");
		for (Field f : t.getDeclaredFields()) {
			try {
	            //assertNotNull(f);
				System.out.println("Snake field: " + f.toString());
	            assertTrue("The field " + f.toString() + " is private",  Modifier.isPrivate(f.getModifiers()));
	            if (f.getName().compareTo("weight") == 0)
	            	assertEquals("The 'weight' is type int", int.class, f.getType());
	            if (f.getName().compareTo("length") == 0)
	            	assertEquals("The 'length' is type float", float.class, f.getType());
	        } catch (Exception e) {
	            fail("The field "+f.toString()+" has problems in class Snake.");
	        }
		}
	}
	
	@Test
	public void _313testLionSetSpeedLt0() throws Exception {
		Lion a = new Lion();
		System.out.println("Lion test313 display = " + a.display());
		
		try {
			a.setSpeed(-5);
			fail("setSpeed accepts negative values - it MUST NOT");
		} catch (Exception e) {
		}
	}
	
	@Test
	public void _314testSnakeSetLengthLt0_mark3() throws Exception {
		Snake p = new Snake();
		System.out.println("Snake test314 display = " + p.display());
		
		try {
			p.setLength(-7);
			fail("setLength accepts negative values - it MUST NOT");
		} catch (Exception e) {
		}
	}

	@Test
	public void _315testLionClone() throws Exception {
		Lion a1 = new Lion();
		a1.setSpeed(4);
		Lion a2 = (Lion) a1.clone();
		a2.setSpeed(4);

		assertNotSame(a1, a2);
		if (a1.getWeight() != a2.getWeight()) {
			fail("clone not correct implemented");
		}
		if (a1.getSpeed() != a2.getSpeed()) {
			fail("clone not correct implemented");
		}
		
	}

}
