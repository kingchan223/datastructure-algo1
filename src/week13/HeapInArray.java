package week13;

public class HeapInArray {
	private char[] heap = null;
	private int realSize;
	
	
	public HeapInArray(){
		heap = new char[5];
		realSize = 1;
	}
	
	public void makeMoreBig(){
		char[] newHeap = new char[heap.length+5];
		for(int i=0; i<heap.length; i++)
			newHeap[i] = heap[i];
		this.heap = newHeap;
	}
	
	public void heapSort(char[] input){
		buildHeap(input);
		System.out.println();
		sortOut();
	}
	
	private void buildHeap(char[] input) {
		System.out.println("<<    insert     >>");
		for (int i = 0; i < input.length; i++) {
			insertHeap(input[i]);
		}
	}
	
	//힙트리 채워가기
	private void insertHeap(char c){
		//System.out.println(realSize);
		if(realSize == heap.length){
			makeMoreBig();
		}
		int k = realSize;
		heap[realSize]=c; // heap.add(k, c);//노드가 k-1개인 트리에서 k번째에 노드 자리 확장
		realSize++;
		System.out.print(c + "  ");
		int parentIndex = k / 2;
		heapifyUpward(parentIndex);//heapify시작
		System.out.println(toString());
	}
	
	private void heapifyUpward(int parentIndex){
		// left child ->  heap트리에서 왼쪽자식 먼저 들어오므로, 왼쪽자식부터.
		int k = 2 * parentIndex;
		//트리보다 범위를 벗어나거나, 부모가 null인 경우에는 return
		if (k >= realSize || parentIndex < 1)
			return;
		
		// right child -> right가 트리 범위에 있고,이미 정렬된 left보다 크다면 right로 heapify
		if (k + 1 < realSize && heap[k + 1] > heap[k])
			k++;
		//자식이 부모보다 크다면 위치 바꾸고, 부모의 부모와도 heapify진행
		if (heap[k] > heap[parentIndex]){
			swap(heap, k, parentIndex);
			heapifyUpward(parentIndex / 2);
		}
	}                                                                  
	
	private void sortOut(){
		System.out.println("<<    delete   >>");
		while (realSize > 1){
			System.out.println(deleteHeap() + "/" + toString());
		}
	}

	//제일 큰 노드. 즉 root노드를 반환한다.
	private Character deleteHeap() {
		//heap의 크기가 범위 이하면 끝냄
		if (realSize <= 1)
			return null;
		
		//1. 제일 큰 노드. root노드 삭제
		char retVal = heap[1];
		for(int i=1; i<realSize-1; i++){
			heap[i] = heap[i+1];
		}
		realSize--;
		//System.out.println(" ---  realSize:"+realSize);
		//트리크기가 2이상. 즉 null제외하고 노드가 2개 이상 있다면
		if (realSize > 2) {
			//2. 제일 뒤에있는 노드를 root의 자리로 옮긴다.
			//heap.add(1, heap.remove(heap.size() - 1));
			char end = heap[realSize-1];
			for(int i= realSize-1; i>1; i--){
				heap[i] = heap[i-1];
			}
			heap[1] = end;
			heapifyDownward(1);
		}
		return retVal;
	}
	
	//노드의 자리를 찾아기는 과정의 메소드
	private void heapifyDownward(int i){
		// left child
		int k = 2 * i;
		//k가 범위 밖이거나, root노드 인덱스가 null이면 끝내기
		if (k >= realSize || i < 1){
			return;
		}
		// right child ->k+1이 범위에 있고, k+1(right)가 left보다 크다면 right와 비교해주는 것이 맞다.
		if (k + 1 < realSize && heap[k+1] > heap[k])
			k++;
		//자식과 부모 크기비교해서 자식이 더 크다면 바꿔준다.
		if (heap[k] > heap[i]){
			swap(heap, k, i);
			heapifyDownward(k);//Upwnward라면 부모를 부르지만 Downward에선 자식을 부른다.
		}
	}
	
	@Override
	public String toString(){
		String retVal = "";
		for(int i=0; i<realSize; i++) {
			retVal += heap[i]+"  ";
		}
		return retVal;
		
	}


	private void swap(char[] heap, int k, int i){
		char temp = heap[k];
		heap[k] = heap[i];
		heap[i] = temp;
	}

	public static void main(String[] args) {
		char[] data = { 'M', 'Y', 'U', 't','N', 'G','a' ,'I', 'S', 'W','P', 'e','5'};
		HeapInArray h = new HeapInArray();
		h.heapSort(data);
	}

}
