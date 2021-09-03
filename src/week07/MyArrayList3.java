package week07;

public class MyArrayList3<T> {
	T[] array;
	int arrSize;
	int numOfData;

	public MyArrayList3(int initSize) {
		arrSize = initSize;
		array = (T[]) new Object [arrSize];//형변환은 런타임시에 일어나므로 이때는 T의 정체를 알 수 있다. 
//		array = new T[];//이렇게 하면 컴파일러가 T가 어떤 타입인지 몰라 메모리 할당을 얼마나 해야 하는지 알 수 없다.
		numOfData = 0;
	}

	// 내부에서만 쓰이는 private메소드. 찾고자하는 데이터의 index반환
	private int indexOf(T data) {
		for(int i=0; i<numOfData; i++)
			if(compare(array[i], data)==0)
				return i;
		return -1;
	}
	//equals는 모든 클래스의 조상인 Object의 메소드이므로 어느 메소드에서사용하던 에러가 안나지만,
	//compareTo는 Onject의 메서드가 아니다.
	//Comparalbe 인터페이스를 구현한 클래스만 compareTo를 사용할 수 있도록 하기 위해
	//Comparable를 구현했는지 확인하는  if문을 사용한다.
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
//				if(0 < array[i].compareTo(array[j])) {
				if(compare(array[i], array[j]) > 0) {
					T temp = array[i];
					array[i] = array[j];
					array[j] = temp;					
				}
			}
		}
	}
	
	// 원하는 인덱스의 데이터를 반환해준다.
	public T get(int index) {
		// 인덱스가 데이터의 최대 크기보다 크다면 불가
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
		// 데이터를 추가할 때 array의 크기가 이미 최대라면
		// 해당 array의 크기를 두배로 늘려준다.
		if (numOfData >= arrSize){
			T[] bigArray = (T[]) new Object[arrSize*2];
			// 2배 크기의 배열 만들고
			// bigArray에 array에 있던 요소들을 넣어주고
			for (int i = 0; i < arrSize; i++){
				bigArray[i] = array[i];
			}
			arrSize *= 2;
			// 추가할 데이터를 마지막에 넣기
			bigArray[numOfData] = data;
			// array가 bogArray를 참조하게 만들기
			array = bigArray;
		}
		// 추가하려는 데이터의 인덱스부터 데이터를 한 칸씩 뒤로 밀기
		for (int j = numOfData - 1; j >= index; j--){
			array[j + 1] = array[j];
		}
		array[index] = data;
		numOfData++;
	}

	// 해당 인덱스의 요소를 삭제하는 메서드
	public T remove(int index) {
		T retVal = array[index];
//		System.out.println("adad2:"+array[0]);
		// 유효성 검사
		if (index < 0)
			return null;
		// 뒤에 있는 데이터를 앞으로 한칸씩 당긴다.
//		System.out.println("numOfData:"+numOfData);
		for (int i = index; i < numOfData; i++) {
			array[i] = array[i + 1];
		}
		// 데이터 하나가 사라졌으니 크기도 줄여준다.
		numOfData--;
		return retVal;
	}
	
	// 데이터를 삭제하고 싶다면
	public int remove(T data) {
		// 해당 데이터의 인덱스를 찾고
		int index = indexOf(data);
		// 그 인덱스에 해당하는 데이터를 삭제하기 위해 remove(index)를 호출
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
			retVal += "/" + array[i];
		return retVal;
	}

	public static void main(String[] args) {
		MyArrayList3<MyClass> list = new MyArrayList3<>(3);
		int idNum = 1;
		list.addLast(new MyClass(idNum++, "lee"));

		list.addLast(new MyClass(idNum++, "kim"));

		list.addLast(new MyClass(idNum++, "kang"));

		list.addLast(new MyClass(idNum++, "park"));

		list.addLast(new MyClass(idNum++, "jung"));

		list.addLast(new MyClass(idNum++, "joo"));

		list.addLast(new MyClass(idNum++, "paek"));

		list.addLast(new MyClass(idNum++, "ha"));


		System.out.println(list.toString());
		System.out.println("Current number of data:"+list.sizeOf());
		System.out.println("current Max Size :" + list.arrSize());
		
		System.out.println("Remove 1, lee:" + list.remove(new MyClass(1, "lee")));//새로운 객체를 생성하여 삭제
		System.out.println(list.toString());
		list.remove(1);//인덱스로 삭제
		System.out.println(list.toString());
		list.remove(list.get(2));// 이미 list에 담겨진 객체를 불러와서 삭제
		System.out.println(list.toString());
		
		list.sort();
		System.out.println(list.toString());

	}
}
