package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) throws IOException {
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] buf = new String("Hello Java!").getBytes();
		
		InetAddress address = InetAddress.getByName("127.0.0.1");
		int port = 7778;
		
		// send data
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		clientSocket.send(packet);
		
		// get the response
		byte[] bufResp = new byte[256];
		packet = new DatagramPacket(bufResp, bufResp.length);
		clientSocket.receive(packet);
		
		// display the response
		String received = new String(packet.getData());
		System.out.println("Client from server received = " + received);
		
		// close socket
		clientSocket.close();
	}
}
