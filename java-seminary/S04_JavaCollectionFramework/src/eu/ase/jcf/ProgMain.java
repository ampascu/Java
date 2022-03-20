package eu.ase.jcf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ProgMain {

	public static void main(String[] args) {

		// 1. Array list and sort
		List<Student> listStudents = new ArrayList<Student>();
		listStudents.add(new Student("Maria", 1066));

		listStudents.add(new Student("Elena", 1061));
		listStudents.add(new Student("Elena", 1061));
		listStudents.add(new Student("George", 1066));

		// to sort the list we have to implement compareTo method for Students
		Collections.sort(listStudents);

		// 2. Printing lists
		System.out.println("--- For loop iterator ---");
		for (int i = 0; i < listStudents.size(); i++) {
			listStudents.get(i).print();
		}

		System.out.println("--- Java Iterator for loop---");
		for (Iterator<Student> stdIt = listStudents.iterator(); stdIt.hasNext();) {
			stdIt.next().print();
		}

		System.out.println("--- Java Iterator while loop---");
		Iterator<Student> studentsIterator = listStudents.iterator();
		while (studentsIterator.hasNext()) {
			studentsIterator.next().print();
		}

		// 3. List iterator
		// Does nothing because we have reached the end of the list, Java no such element exception:
		// studentsIterator.next().print();

		ListIterator<Student> listIterator = listStudents.listIterator();
		// printing ASC order
		System.out.println("--- Java ListIterator ASC---");
		while (listIterator.hasNext()) {
			listIterator.next().print();
		}
		// printing DESC order
		System.out.println("--- Java ListIterator DESC---");
		while (listIterator.hasPrevious()) {
			listIterator.previous().print();
		}

		// 4. LinkedList
		/*
		 * List<Student> linkedListStudents = new LinkedList<Student>();
		 * listStudents.add(new Student("Ioana", 1066)); listStudents.add(new
		 * Student("Mihai", 1066)); listStudents.add(new Student("Andreea", 1061));
		 * listStudents.add(new Student("Florian", 1061));
		 */

		// 5. Convert Lists to sets
		// HashSet does not maintain the order of elements
		// to make this work and remove duplicates, we have to implement equals and hashcode in Student
		System.out.println("--- Java Set---");
//		Set<Student> studentsSet = new HashSet<Student>(listStudents);
//		Iterator<Student> setIterator = studentsSet.iterator();
//		while(setIterator.hasNext()) {
//			setIterator.next().print();
//		}

		// 6. TreeSet maintains list order (ASC)
		Set<Student> studentsSet = new TreeSet<Student>(listStudents);
		Iterator<Student> setIterator = studentsSet.iterator();
		while (setIterator.hasNext()) {
			setIterator.next().print();
		}

		// 7. HashMap (key - value) - maintains no order in data
		Map<Faculty, Student> studentsMap = new HashMap<Faculty, Student>();
		studentsMap.put(new Faculty("ASE"), new Student("Ana", 1067));
		studentsMap.put(new Faculty("Poli"), new Student("Mihai", 1066));
		studentsMap.put(new Faculty("ASE"), new Student("Alex", 1066));
		studentsMap.put(new Faculty("Poli"), new Student("Ana", 1068));

		System.out.println("Printing HashMaps");
		Set<Faculty> keySet = studentsMap.keySet();
		Iterator<Faculty> it = keySet.iterator();
		while (it.hasNext()) {
			Faculty f = it.next();
			Student s = studentsMap.get(f);
			
			f.print();
			s.print();
		}
		
		//8. TreeMap (key - value) - maintains ASC order
		Map<Faculty, Student> studentsTreeMap = new TreeMap<Faculty, Student>();
		studentsTreeMap.put(new Faculty("ASE"), new Student("Ana", 1067));
		studentsTreeMap.put(new Faculty("Poli"), new Student("Mihai", 1066));
		studentsTreeMap.put(new Faculty("ASE"), new Student("Alex", 1066));
		studentsTreeMap.put(new Faculty("Poli"), new Student("Ana", 1068));
		
		System.out.println("Printing TreeMap");
		Set<Faculty> keySet2 = studentsTreeMap.keySet();
		Iterator<Faculty> it2 = keySet2.iterator();
		while (it2.hasNext()) {
			Faculty f = it2.next();
			Student s = studentsTreeMap.get(f);
			
			f.print();
			s.print();
		}
	}

}
