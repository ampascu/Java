package eu.ase.io.serialization;

/**
 * 1. Create the Order class which inherits Serializable with the following
 * parameters: 
 * - long serialVersionUID 
 * - URL orderURL 
 * - String clientName 
 * - float orderTotal
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
	
}
