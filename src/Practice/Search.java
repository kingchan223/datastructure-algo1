package Practice;

import java.util.ArrayList;

public class Search <T extends Comparable<T>>{

	ArrayList<T> data;
	int dataSize;
	static int seqCnt;
	static int binCnt;
	
	public Search() {}
	
	public Search(ArrayList<T> data) {
		this.data = data;
		this.dataSize = data.size();
	}
	
	//외부 데이터 순차 검색
	public int seqSearch(ArrayList<T> input, T key){
		int i=0;
		while(i<input.size()) {
			if(input.get(i).compareTo(key)==0)
				return i;
			else
				i++;
		}
		return -1;
	}
	
	//내부 데이터 순차검색
	public int seqSearh2(T key){
		int i=0;
		while(i<dataSize) {
			if(this.data.get(i).compareTo(key)==0)
				return i;
			else 
				i++;
		}
		return -1;
	}
	
	//재귀 순차 검색(내부 데이터)
	public int seqSearchRecursion(T key) {
		return seqSearchRecursion(0, key);
	}
	private int seqSearchRecursion(int index, T key) {
		if(this.data.get(index).compareTo(key)==0)
			return index;
		else
			return seqSearchRecursion(index+1, key);
	}
	
	//선택정렬
	public void selectionSort(){
		for(int i=0; i<dataSize-1; i++) {
			for(int j=i+1; j<dataSize;j++){
				if(data.get(i).compareTo(data.get(j))>0){
					T temp = data.get(i);
					data.set(i, data.get(j));
					data.set(j, temp);
				}
			}
		}
	}
	
	//정렬된 어레이에서의 search
	public int sortedSearch(T key){
		int i=0;
		while(i<dataSize && data.get(i).compareTo(key)<0){
			i++;
		}
		if(data.get(i).compareTo(key)==0)
			return i;
		else 
			return -1;
	}
	
	public int binarySearchIter(T key){
		return binarySearchIter(0, dataSize, key);
	}
	
	private int binarySearchIter(int start, int end, T key) {
		while(start<=end) {
			int middle = (start+end)/2;
			T val = data.get(middle);
			
			if(val.compareTo(key)==0) return middle;
			else if(val.compareTo(key)>0) return end = middle-1;
			else return start = middle+1;
		}
		return -1;
	}
	
	public int binarySearchRecur(T key){
		return binarySearchRecur(0, dataSize, key);
	}

	private int binarySearchRecur(int start, int end, T key) {
		
		if(start>end) return -1;
		int middle = (start+end)/2;
		T val = data.get(middle);
		if(val.compareTo(key)==0) return middle;
		else if(val.compareTo(key)>0) return binarySearchRecur(start, middle-1, key);
		else return binarySearchRecur(middle+1, end, key);
	}

	public String toString(){
		String retVal = "";
		int i=0;
		while(i<dataSize){
			retVal += data.get(i) + " / ";
			i++;
		}
		return retVal;
	}

	public static void main(String[] args) {
		ArrayList<String> data = new ArrayList<String>();
		data.add("bang");
		data.add("aark");
		data.add("eee");
		data.add("co");
		data.add("dim");
		Search<String> test = new Search<String>(data);
		System.out.println(test.seqSearch(data, "eee"));
		System.out.println(test.seqSearh2("eee"));
		
		System.out.println(test.seqSearchRecursion("eee"));
		
		/* After Sorting */
		
		test.selectionSort();
		System.out.println(test.toString());
		System.out.println(test.sortedSearch("dim"));
		System.out.println(test.binarySearchIter("dim"));
		System.out.println(test.binarySearchRecur("dim"));
		
		
	}
}
