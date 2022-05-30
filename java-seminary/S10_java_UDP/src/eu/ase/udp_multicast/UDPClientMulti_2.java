package eu.ase.udp_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPClientMulti_2 {

	public static void main(String[] args) throws IOException {
		MulticastSocket socket = new MulticastSocket(4448);

		InetAddress address = InetAddress.getByName("230.0.0.0");
		socket.joinGroup(address);

		DatagramPacket packet;

		// get once
		byte[] buf = new byte[256];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);

		String received = new String(packet.getData());
		System.out.println("[Multi_2] Received the Moment multicast: " + received);

		socket.leaveGroup(address);
		socket.close();
	}

}
