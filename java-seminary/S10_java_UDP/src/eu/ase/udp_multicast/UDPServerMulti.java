package eu.ase.udp_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Instant;

public class UDPServerMulti {

	public static void main(String[] args) {
		DatagramSocket socket = null;
		boolean running = true;
		byte[] buf = null;

		try {
			socket = new DatagramSocket(4447);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	    System.out.println("Multicast udp server started and bind on port 4447");
	    while (running) {
	    	try {
	    		// construct the message
	            String dString = Instant.now().toString();
	            buf = dString.getBytes();

			    // send it
	            InetAddress group = InetAddress.getByName("230.0.0.0");
	            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4448);
	            socket.send(packet);

			    // sleep for a while
			    try {
			      //Thread.currentThread();
				  Thread.sleep(5000); //five seconds
			    } catch (InterruptedException e) { 
			    	e.printStackTrace();
			    }
	        } catch (IOException ioe) {
	        	ioe.printStackTrace();
	        	running = false;
	        } catch (Exception ge) {
	            ge.printStackTrace();
	            running = false;
		    }
	    } 
	    socket.close();

	}

}
