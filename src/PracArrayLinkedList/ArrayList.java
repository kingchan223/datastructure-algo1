package PracArrayLinkedList;

public class ArrayList<T extends Comparable<T>>{
	
	T[] array;
	int arrSize;
	int numOfData = 0;
	//constructor
	public ArrayList(int initSize) {
		array = (T[])new Object[initSize];
		arrSize = initSize;
	}
		
	//add
	public void add(int index, T data) {
		if(index<0 || index > numOfData) {
			return;
		}
		//arrSize가 numOfData보다 작다면 더 큰 어레이를 만들어준다.
		if(arrSize <= numOfData) {
			T[] bigArray = (T[]) new Object[arrSize+5];
			for(int i=0; i<numOfData; i++) {
				bigArray[i] = array[i];
			}
			array = bigArray;
		}
		for(int i=index; i<numOfData; i++) {
			array[i+1] = array[i];
		}
		array[index] = data;
		numOfData ++;
	}
	
	//addFirst
	public void addFirst(T data) {
		add(0, data);
	}
	
	//addLst
	public void addLast(T data) {
		add(numOfData, data);
	}
	
	//indexOf
	private int indexOf(T data) {
		int index = 0;
		for(int i=0; i<numOfData; i++) {
			if(array[i] == data) {
				index = i;
			}
		}
		return index;
	}
	
	//remove(index)
	public T remove(int index) {
		if(index<0 || index > numOfData)
			return null;
		T retVal = array[index];
		for(int i=index; i<numOfData; i++){
			array[i] = array[i+1];
		}
		numOfData--;
		return retVal;
		
	}
	//remove
	public T remove(T data) {
		int index = indexOf(data);
		return remove(index);
	}
	//removeFirst
	public T removeFirst() {
		return remove(0);
	}
	
	//removeLast
	public T removeLast() {
		return remove(numOfData);
	}
	
	//sort
	
	//get(index)
	public T get(int index) {
		return array[index];
	}
	//set(index)
	public void set(int index, T data) {
		array[index] = data;
	}
	//arrSize
	public int arrSize() {
		return arrSize;
	}
	
	//sizeOF
	public int sizeOf() {
		return numOfData;
	}

	public static void main(String[] args) {
		

	}

}
