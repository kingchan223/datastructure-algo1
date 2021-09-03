package week05;

public class MyArrayList1 {
	int initSize = 3;
	String[] array;
	int arrSize;
	int numOfData;
	
	public MyArrayList1() {
		arrSize = initSize;
		array = new String[arrSize];
		numOfData = 0;
	}
	
	//원하는 인덱스의 데이터를 반환해준다.
	public String get(int index) {
		//인덱스가 데이터의 최대 크기보다 크다면 불가
		if(index >= numOfData){
			System.out.println("해당 index에는 데이터가 없습니다.");
			return null;
		}
		return array[index];
	}
	public void add(int index, String data) {
		//데이터를 추가할 때 array의 크기가 이미 최대라면 
		//해당 array의 크기를 두배로 늘려준다.
		if(numOfData >= arrSize) { 
			String[] bigArray = new String[arrSize*2];
			//2배 크기의 배열 만들고
			//bigArray에 array에 있던 요소들을 넣어주고
			for(int i=0; i<arrSize; i++) {
				bigArray[i] = array[i];
			}
			//추가할 데이터를 마지막에 넣기
			bigArray[numOfData] = data;
			arrSize *= 2;
			//array가 bogArray를 참조하게 만들기
			array = bigArray;
		}
		//데이터를 한 칸씩 뒤로 밀기
		for(int j=numOfData-1; j>=index; j--) {
			array[j+1] = array[j];
		}
		array[index] = data;
		numOfData++;
	}
	//해당 인덱스의 요소를 삭제하는 메서드
	public String remove(int index) {
		//위에 있는 데이터를 앞으로 한칸씩 당긴다.
		for(int i=index; i<numOfData;i++) {
			array[i] = array[i+1];
		}
		//데이터 하나가 사라졌으니 크기도 줄여준다.
		numOfData--;
		return null;
	}
	//데이터를 삭제하고 싶다면
	public int remove(String data) {
		// 해당 데이터릐 인덱스를 찾고
		int index = indexOf(data);
		//그 인덱스에 해당하는 데이터를 삭제하기 위해 remove(index)를 호출
		remove(index);
		return index;
	}
	//내부에서만 쓰이는 private메소드 찾고자하는 데이터의 index반환
	private int indexOf(String data) {
		int index=0;
		for(int i=0; i<numOfData; i++) {
			if(array[i].equals(data)) {
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
	public String toString() {
		String retVal = "";
		for(int i=0; i<numOfData; i++)
			retVal += "/"+array[i];
		return retVal;
	}
	public static void main(String[] args) {
		MyArrayList1 list = new MyArrayList1();
		
		list.add(list.sizeOf(), "ㄱ");
		list.add(list.sizeOf(), "ㄴ");
		list.add(list.sizeOf(), "ㄷ");
		list.add(list.sizeOf(), "ㄹ");
		list.add(list.sizeOf(), "ㅁ");
		list.add(list.sizeOf(), "ㅂ");
		list.add(list.sizeOf(), "ㅅ");
		list.add(list.sizeOf(), "ㅇ");
		list.add(list.sizeOf(), "ㅈ");
		list.remove(6);
		System.out.println("get(5):"+list.get(5));
		System.out.println(list.toString());
		System.out.println("current Max Size :"+list.arrSize());

	}

}
