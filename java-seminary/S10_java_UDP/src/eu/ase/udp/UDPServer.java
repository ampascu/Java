package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	public static void main(String[] args) {
		byte[] bufRecv = null;
		byte[] bufResp = null;

		try (DatagramSocket socket = new DatagramSocket(7778)) {
			System.out.println("UDP Server bind on 7778 port.");
			
			while (true) {
				bufRecv = new byte[256];

				// receive request
				DatagramPacket packet = new DatagramPacket(bufRecv, bufRecv.length);
				socket.receive(packet);

				// bufRecv = packet.getData();
				System.out.println("Client says: " + new String(bufRecv));

				// figure out the response
				bufResp = new String("OK").getBytes();

				// send the response at the client address and port
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				
				DatagramPacket packetResp = new DatagramPacket(bufResp, bufResp.length, address, port);
				socket.send(packetResp);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
