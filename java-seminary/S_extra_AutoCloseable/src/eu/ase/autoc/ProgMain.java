package eu.ase.autoc;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class ProgMain {

	public static void main(String[] args) throws Exception {
		Student s = new Student();
		System.out.println("The number of students = " + Student.noStud);
		
		s.close();
		
		System.out.println("The number of students = " + Student.noStud);
		
		//try catch block
		try{
			
		} catch(Exception exc){
			
		}
		
		//try with resources block
		try(Student s2 = new Student()){
			//code logic
			System.out.println("The number of students = " + Student.noStud);
		} catch(Exception exc){
			
		}
		
		try(
				FileOutputStream fos = new FileOutputStream("text.txt");
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				DataOutputStream dos = new DataOutputStream(bos)
				
				){
			//code logic
		} catch(Exception exc) {
			
		}
		
		System.out.println("The number of students = " + Student.noStud);
	}

}
