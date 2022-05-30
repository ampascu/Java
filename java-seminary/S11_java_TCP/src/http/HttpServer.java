package http;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = 
				new ServerSocket(8008)){
			System.out.println("Server listening on port: " + 8008);
			
			while(true) {
				Socket client = serverSocket.accept();
				HttpServerThread objClient = new HttpServerThread(client);
				objClient.start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
