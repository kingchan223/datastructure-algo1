package week06;

public class MyArrayList {
	private String[] array;
	private int arrSize;
	private int numOfData;

	public MyArrayList(int initSize) {
		arrSize = initSize;
		array = new String[arrSize];
		numOfData = 0;
	}

	// 원하는 인덱스의 데이터를 반환해준다.
	public String get(int index) {
		// 인덱스가 데이터의 최대 크기보다 크다면 불가
		if (index >= numOfData) {
			System.out.println("해당 index에는 데이터가 없습니다.");
			return null;
		}
		return array[index];
	}

	public void set(int index, String data) {
		if (index < 0)
			return;
		array[index] = data;
	}

	public void addFirst(String data) {
		add(0, data);
	}

	public void addLast(String data) {
		add(numOfData, data);
	}

	public void add(int index, String data){
		// 데이터를 추가할 때 array의 크기가 이미 최대라면
		// 해당 array의 크기를 두배로 늘려준다.
		if (numOfData >= arrSize){
			String[] bigArray = new String[arrSize+5];
			// +5 크기의 배열 만들고
			// bigArray에 array에 있던 요소들을 넣어주고
			for (int i = 0; i < arrSize; i++) {
				bigArray[i] = array[i];
			}
			arrSize += 5;
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
	public String remove(int index) {
		String retVal = array[index];
		// 유효성 검사
		if (index < 0)
			return null;
		// 뒤에 있는 데이터를 앞으로 한칸씩 당긴다.
		for (int i = index; i < numOfData; i++) {
			array[i] = array[i + 1];
		}
		// 데이터 하나가 사라졌으니 크기도 줄여준다.
		numOfData--;
		return retVal;
	}

	// 데이터를 삭제하고 싶다면
	public int remove(String data) {
		// 해당 데이터릐 인덱스를 찾고
		int index = indexOf(data);
		// 그 인덱스에 해당하는 데이터를 삭제하기 위해 remove(index)를 호출
		remove(index);
		return index;
	}

	// 내부에서만 쓰이는 private메소드 찾고자하는 데이터의 index반환
	private int indexOf(String data) {
		int index = 0;
		for (int i = 0; i < numOfData; i++) {
			if (array[i].equals(data)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public int sizeOf() {
		return numOfData;
	}

	public int arrSize() {
		return arrSize;
	}
	
	public void sort() {
		for(int i=0; i<numOfData; i++){
			for(int j=i+1; j<numOfData; j++){
				if(0 < array[i].compareTo(array[j])) {
					String temp = array[i];
					array[i] = array[j];
					array[j] = temp;					
				}
			}
		}
	}

	public String toString() {
		String retVal = "";
		for (int i = 0; i < numOfData; i++)
			retVal += "/" + array[i];
		return retVal;
	}

	public static void main(String[] args) {
		MyArrayList list = new MyArrayList(3);

		list.add(list.sizeOf(), "a");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "b");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "c");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "d");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "e");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "f");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "g");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "h");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.add(list.sizeOf(), "i");
		System.out.println(list);
		System.out.println("arrSize:"+list.arrSize());
		System.out.println("numOfData:"+list.sizeOf());
		System.out.println();
		list.remove(6);
		System.out.println("get(5):" + list.get(5));
		System.out.println(list.toString());
		list.sort();
		System.out.println(list.toString());
		System.out.println("current Max Size :" + list.arrSize());

	}

}
