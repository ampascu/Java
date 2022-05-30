package eu.ase.json;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProgMainJSONTest {

	public static void writeJsonObject(String fileName) throws JSONException, IOException {
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("name", "Andreea");
		jsonUser.put("isActive", true);
		jsonUser.put("age", 300);
		
		jsonUser.put("roles", new JSONArray());
		JSONObject role1 = new JSONObject("{\"name\":\"admin\"}"); // {"name": "admin"}
		JSONObject role2 = new JSONObject("{\"name\":\"guest\"}"); // {"name": "guest"}
		
		jsonUser.append("roles", role1);
		jsonUser.append("roles", role2);
		
		// {"name":"Andreea", "isActive":true, "age":300, "roles" :
		//[{"name":"admin"},{"name":"guest"}]}
		
		FileWriter fw = new FileWriter(fileName);
		fw.write(jsonUser.toString());
		fw.close();
		
	}
	
	public static void readJsonObject(String fileName) throws IOException, JSONException {
		FileInputStream fis = new FileInputStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		
		byte[] data = dis.readAllBytes();
		String stringData = new String(data);
		
		JSONObject myObject = new JSONObject(stringData);
		
		System.out.println("My JSON: " + 
		JSONObject.quote(myObject.toString()));
		
		dis.close();
	}
	
	public static void main(String[] args) {
		try {
			writeJsonObject("myFile.json");
			readJsonObject("myFile.json");
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
