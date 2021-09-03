package Practice;

public class Prac1 {
	public static void main(String[] args) {
		Box<String> box = new Box<String>();
	}
}

class Box<T> {
	T item;
}