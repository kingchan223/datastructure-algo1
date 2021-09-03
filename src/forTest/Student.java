package forTest;

public class Student implements Comparable<Student>{
	
	int id;
	String name;
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public int compareTo(Student that) {
		return name.compareTo(that.name);
	}
	
	public String toString() {
		return name+"["+id+"]";
	}
	
	public boolean equals(Student that) {
		return (id == that.id) ? true : false;
	}

}
