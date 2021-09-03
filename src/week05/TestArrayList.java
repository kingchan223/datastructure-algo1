package week05;

import java.awt.List;
import java.util.ArrayList;

class MyClass{
	int x;
	String y;
	public MyClass(int x, String y) {
		this.x = x;
		this.y = y;
	}
}

public class TestArrayList {

	public static void main(String[] args) {
		
		//ArrayList는 object형 리스트이다. 그래서 모든 타입을 다 받을 수 있다. 
		ArrayList  ar = new ArrayList();
		ar.add(1);
		ar.add("asd");
		System.out.println(ar);
		
		//근데 오브젝트 형이라서 안에있는 요소를 꺼내려면 모두 그에 맞게 형변환을 해줘야하는 불편함이 있다.
		int x = (int) ar.get(0);
		String y= (String) ar.get(1);
		
		
		//이를 피하기 위해 ArrayList에 넣을 요소의 타입을 지정할 수 있다.
		ArrayList<Integer> ari = new ArrayList<>();
		//이렇게 되면 각 지정한 타입의 요소만 넣을 수 있다.
		ari.add(1);
		ArrayList<String> ars = new ArrayList();
		ars.add("asd");
		
		//근데 2가지 이상의 타입을 다 넣고 싶다면? 내가 직접 클래스를 만들고 그 클래스타입의 ArrayList를 만들면 된다.
		ArrayList<MyClass> arm = new ArrayList<>();
		arm.add(new MyClass(1, "asd"));
		MyClass mc = arm.get(0);
	}

}
