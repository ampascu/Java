package http;

import java.io.FileInputStream;
import java.io.IOException;

public class HttpService {
	
	public String processInput(String theInput) {
		
        String theOutput = ""; 
       
        byte[] buffResp = new byte[4096];
        
        if(theInput.indexOf("GET") != 0) {
        	String message = new String("{\"msg\":\"NU STIU COMANDA\"}");
            theOutput = "HTTP/1.1 400\r\nContent-Length: " + message.length() + "\r\n\r\n" + message + "\r\n";
        } else {
            String fileName = theInput
            		.substring(theInput.indexOf("/")+1, theInput.indexOf(" HTTP/"));
            String fileExt = fileName
            		.substring(fileName.indexOf(".")+1);
            
            String contentType = ""; 
            String fileContent = "";
            
            if(fileExt.compareToIgnoreCase("html") == 0) contentType = "text/html";
            
//            if(fileExt.compareToIgnoreCase("txt") == 0) contentType = "text/html";
//            if(fileExt.compareToIgnoreCase("htm") == 0) contentType = "text/html";
//            if(fileExt.compareToIgnoreCase("gif") == 0) contentType = "image/gif";
            
            
            try {
                int bread = 0;
                FileInputStream fis = new FileInputStream(fileName);
                while((bread = fis.read(buffResp)) != -1) {
                    fileContent += new String(buffResp, 0, bread);
                }  
                fis.close();
                theOutput = "HTTP/1.1 200 OK\r\nContent-Type: "+ contentType +"\r\nContent-Length: "
                        +(fileContent.length()+2)+"\r\n\r\n"+fileContent+"\r\n";
            } catch(IOException ioec) {
                ioec.printStackTrace();
                theOutput = "HTTP/1.1 404\r\n\r\n";
            }
            
        }
        return theOutput;
    }
}
