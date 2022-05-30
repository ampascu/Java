package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpServerThread extends Thread {
	
	private Socket socket;
	
	public HttpServerThread(Socket client) {
		this.socket = client;
	}

	
	@Override
	public void run() {
		System.out.println("Executing run method...");
		
		OutputStream outputStream = null; 
		InputStream inputStream = null; 
		
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		
		try {
			inputStream = this.socket.getInputStream();
			outputStream = this.socket.getOutputStream();
			
			bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream)
					);
			
			printWriter = new PrintWriter(outputStream, true);
			
			String inputLine = ""; 
			String outputLine = "";
			String processLine = "";
			
			while( 
					((inputLine = bufferedReader.readLine()) != null ) 
					&& (inputLine.length() > 1)
				){
				processLine += inputLine;
			}
			
			HttpService myHttpService = new HttpService();
			outputLine = myHttpService.processInput(processLine);
			printWriter.println(outputLine);
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(printWriter != null)
				printWriter.close();
		}
	}
}
