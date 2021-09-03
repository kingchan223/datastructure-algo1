package week10;

public class Search {

	String[] data;
	int dataSize;
	static int seqCnt = 0;
	static int binCnt = 0;

	public Search() {}

	public Search(String[] input) {
		this.data = input;
		dataSize = data.length;
	}

	//순차검색:맨 처음부터 하나씩 검사한다.
	public int seqSearch1(String[] input, String key) {
		
		int n = input.length;
		int i = 0;
		//i가 input의 길이보다 작은 동안, 그리고 찾는 데이터가 일치하지 않는다
		while (i < n && input[i].compareTo(key) != 0)
			i++;//i를 증가시켜 while문을 반복한다.
		//while문을 빠져나왔는데 i < n이라면 데이터를 찾은 경우
		if (i < n)
			return i;
		//반대의 경우라면 못찾은 경우다.
		else
			return -1;
	}
	
	//seq2는 위와 같은데, 클래스 내부의 데이터를 사용한다는 것만 다르다.
	public int seqSearch2(String key) {
		int i = 0;

		while (i < dataSize && data[i].compareTo(key) != 0)
			i++;

		if (i < dataSize)
			return i;
		else
			return -1;
	}
	
	//오버로드 메소드
	public int seqSearchRecursion(String key) {
		return seqSearchRecursion(0, key);
	}
	//재귀호출을 사용한 순차검색
	public int seqSearchRecursion(int index, String key){
		//기저조건:증가시킨 index가 dataSize와 같으면 
		//모든 곳을 다 찾아본 것이므로 검색을 종료한다.(데이터가 없는 경우)
		if(index==dataSize) return -1;
		
		//만약 data[index]와 key(찾고자하는 데이터)가 같다면
		//해당 index반환
		if(data[index].compareTo(key)==0) return index;
		//다음 인덱스 검사를 위해 index+1하여 재귀호출
		else return seqSearchRecursion(index+1, key);
		
	}
	
	//오버로드 메소드
	public int seqSearchRecursion2(String key) {
		return seqSearchRecursion2(0, key);
	}
	
	//정렬된 어레이에서 찾기
	public int seqSearchRecursion2(int index, String key){
		//정렬된 경우는 data[index].compareTo(key) > 0 의 기저조건이 추가됨
		//이미 정렬되었으므로 data[index]가 key보다 크다면 데이터가 없는 경우로 볼 수 있음
		if(index==dataSize || data[index].compareTo(key) > 0) return -1;
		
		//찾았다면 index반환
		if(data[index].compareTo(key)==0) return index;
		//다음 인덱스 검사를 위해 index+1하여 재귀호출
		else return seqSearchRecursion(index+1, key);
		
	}
	
	//선택정렬
	public void selectionSort() {
		for(int i=0; i<dataSize-1;i++) {
			for(int j=i+1; j<dataSize; j++){
				if(data[i].compareTo(data[j]) > 0) {
					String temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
	}
	
	//정렬된 어레이에서 찾기
	public int seqSearch3(String key){
		int i = 0;
		//정렬된 상태이므로 data[i].compareTo(key) < 0 의 조건이 추가됨
		while (i < dataSize && data[i].compareTo(key) < 0) {
			seqCnt++;
			i++;
		}
		//찾았다면 i리턴
		if (data[i].compareTo(key) == 0)
			return i;
		else
			return -1;
	}
	
	//오버로드 메소드
	public int binarySearchIter(String key){ return binarySearchIter(0, dataSize,key);}
	//정렬된 어레이에서 반복적으로 찾기
	public int binarySearchIter(int start, int end, String key){
		//start<=end인 동안 반복
		while(start<=end) {
			//이진탐색을 위해 반으로 나눠서 검사한다.
			int middle = (start+end)/2;
			// data[middle]이라면 인덱스인 middle반환
			if(data[middle].compareTo(key)==0) return middle;
			//data[middle]이 key보다 크다면 start~middle-1 사이를 검사
			else if(data[middle].compareTo(key) > 0) end = middle-1;
			//data[middle]이 key보다 작다면 middle+1 ~ end 사이를 검사
			else start = middle+1;
		}
		return -1;
	}
	//오버로드 메소드
	public int binarySearchRecur(String key) { return binarySearchRecur(0, dataSize,key);}
	//정렬된 어레이에서 재귀적으로 찾기
	public int binarySearchRecur(int start, int end, String key){
		binCnt++;
		// 기저조건: start > end이면 끝내기
		if(start>end) return -1;
		//이진탐색을 위해 반으로 나눠서 검사한다.
		int middle = (start+end)/2;
		// data[middle]이라면 인덱스인 middle반환하고 종료
		if(data[middle].compareTo(key)==0) return middle;
		//data[middle]이 key보다 크다면 start~middle-1 사이를 검사하기 위해 재귀호출
		else if(data[middle].compareTo(key) > 0) return binarySearchRecur(start, middle-1, key);
		//data[middle]이 key보다 작다면 middle+1 ~ end 사이를 검사하기 위해 재귀호출
		else return  binarySearchRecur(middle+1, end, key);
	}
	

	public static void main(String[] args) {
		String[] input = { "lee", "kim", "park", "jo", "choi", "baek", "kang", "lim", "hwang", "yun", "ha" };
		Search test1 = new Search();
		System.out.println("seqSearch1: " + test1.seqSearch1(input, "lim"));
		Search test2 = new Search(input);
		System.out.println("seqSearch2: "+test2.seqSearch2("lim"));
		System.out.println("seqSearchRecursion: "+test2.seqSearchRecursion("lim"));
		
		System.out.println();
		System.out.println("-----After sorting-----");
		System.out.println();
		
		test2.selectionSort();
		System.out.println("seqSearch3: "+test2.seqSearch3("lim"));
		System.out.println("seqSearchRecursion2: "+test2.seqSearchRecursion2("lim"));
		System.out.println("binarySearchIter: "+test2.binarySearchIter("lim"));
		System.out.println("binarySearchRecur: "+test2.binarySearchRecur("lim"));
		
		System.out.println("seqCnt:"+seqCnt+" vs "+"bicCnt:"+binCnt);

	}
}
