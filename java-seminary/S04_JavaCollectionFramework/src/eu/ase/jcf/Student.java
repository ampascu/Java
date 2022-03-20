package eu.ase.jcf;

public class Student implements Comparable<Student>{

	private String name;
	private int groupNo;
	
	public Student(String name, int groupNo) {
		this.name = name;
		this.groupNo = groupNo;
	}
	
	public void print() {
		System.out.println("Student " + this.name  + " is in group " + groupNo);
	}

	//if we want to use sort we have to implement the compareTo method
	@Override
	public int compareTo(Student std) {
		int currentGroup = this.groupNo;
		int compareToGroup = ((Student)std).groupNo;

		if(currentGroup < compareToGroup) {
			return -1;
		} else if(currentGroup > compareToGroup){
			return 1;
		} else {
			//groups are the same, compare student names
			String currentName = this.name.toLowerCase();
			String compareToName = ((Student)std).name.toLowerCase();
			
			int nameCompare = currentName.compareTo(compareToName);  
			if (nameCompare < 0) {
				return nameCompare;
			}
			else if (nameCompare > 0) {
				return nameCompare;
			}
			else {  
			    //names are equal
				return 0;
			} 
		}
	}

	//you have to implement both equals and hashCode when using hash maps/lists
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Student other = (Student) obj;
		if (this.groupNo != other.groupNo)
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
        return 31 * this.groupNo + this.name.hashCode();
	}
}
