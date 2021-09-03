package forTest;

public class MArrayList<T> {
	T[] array;
	int arrSize;
	int numOfData;

	public MArrayList(int initSize) {
		arrSize = initSize;
		array = (T[]) new Object [initSize];
		numOfData = 0;
	}

	private int indexOf(T data) {
		for(int i=0; i<numOfData; i++)
			if(compare(array[i], data)==0)
				return i;
		return -1;
	}
	
	//A.compareTo(B) = 1 : A가 크다
	//A.compareTo(B) = 0 : A와 B가 같다.
	//A.compareTo(B) = -1 : A가 작다.
	public int compare(T o1, T o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable) {
			Comparable c1 = (Comparable) o1;
			Comparable c2 = (Comparable) o2;
			return c1.compareTo(c2);
		}
		else
			return -1;
	}
	
	public void sort() {
		for(int i=0; i<numOfData; i++){
			for(int j=i+1; j<numOfData; j++){
				if(compare(array[i], array[j]) > 0) {
					T temp = array[i];
					array[i] = array[j];
					array[j] = temp;					
				}
			}
		}
	}
	
	public T get(int index) {
		return array[index];
	}

	public void set(int index, T data) {
		if (index < 0)
			return;
		array[index] = data;
	}

	public void addFirst(T data) {
		add(0, data);
	}

	public void addLast(T data) {
		add(numOfData, data);
	}

	public void add(int index, T data){
		if (numOfData == arrSize){
			T[] bigArray = (T[]) new Object[arrSize+5];
			for (int i = 0; i < arrSize; i++){
				bigArray[i] = array[i];
			}
			arrSize += 5;
			bigArray[numOfData] = data;
			array = bigArray;
		}
		for (int j = numOfData - 1; j >= index; j--){
			array[j + 1] = array[j];
		}
		array[index] = data;
		numOfData++;
	}

	public T remove(int index) {
		T retVal = array[index];
		if (index < 0)
			return null;

		for (int i = index; i < numOfData; i++) {
			array[i] = array[i + 1];
		}
		numOfData--;
		return retVal;
	}

	public int remove(T data) {
		int index = indexOf(data);
		remove(index);
		return index;
	}

	public int sizeOf() {
		return numOfData;
	}

	public int arrSize() {
		return arrSize;
	}

	public String toString() {
		String retVal = "";
		for (int i = 0; i < numOfData; i++)
			retVal += " / " + array[i];
		return retVal;
	}

	public static void main(String[] args) {
		MArrayList<Student> list = new MArrayList<>(3);
		int idNum = 1;
//		list.addLast(new Student(idNum++, "lee"));
//		list.addLast(new Student(idNum++, "kim"));
//
//		list.addLast(new Student(idNum++, "kang"));
//
//		list.addLast(new Student(idNum++, "park"));
//
//		list.addLast(new Student(idNum++, "jung"));
//
//		list.addLast(new Student(idNum++, "joo"));
//
//		list.addLast(new Student(idNum++, "paek"));
//
//		list.addLast(new Student(idNum++, "ha"));
//
//		System.out.println(list.toString());
//		System.out.println("Current number of data:"+list.sizeOf());
//		System.out.println("current Max Size :" + list.arrSize());
//		
//		System.out.println("Remove 1, lee:" + list.remove(new Student(1, "lee")));//새로운 객체를 생성하여 삭제
//		System.out.println(list.toString());
//		list.remove(1);//인덱스로 삭제
//		System.out.println(list.toString());
//		list.remove(list.get(2));// 이미 list에 담겨진 객체를 불러와서 삭제
//		System.out.println(list.toString());
//		
//		list.sort();
//		System.out.println(list.toString());
		System.out.println(list.compare(new Student(idNum++, "lee"), new Student(idNum++, "kim")));

	}
}