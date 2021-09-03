package q3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
//Comparable 인터페이스를 구현하여 비교할 수 있도록 한다.
public class MyClass implements Comparable<MyClass>{
	
	int idNumber;
	String name;
	
	public MyClass(int i, String s) {
		idNumber = i;
		name = s;
	}
	//Comparable 인터페이스의 compareTo메소드 사용
	//구현은 String클래스에서 구현됨
	public int compareTo(MyClass that) {
		return this.name.compareTo(that.name);
		
	}

	@Override
	public String toString() {
		return " " + idNumber + "," + name + "";
	}

	public boolean equals(MyClass that) {
		return this.idNumber == that.idNumber;
	}
}
