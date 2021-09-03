package week13;

import java.util.ArrayList;

public class HeapInArrayList {

	private ArrayList<Character> heap = new ArrayList<>();

	public HeapInArrayList() {
		heap.add(null);
	}

	public void heapSort(char[] input){
		buildHeap(input);
		sortOut();
	}
	
	private void buildHeap(char[] input){
		System.out.println("<< Heap implemented in ArrayList >>");
		for (int i = 0; i < input.length; i++) {
			insertHeap(input[i]);
		}
	}
	
	//힙트리 채워가기
	private void insertHeap(char c){
		int k = heap.size();
		heap.add(k, c);//노드가 k-1개인 트리에서 k번째에 노드 자리 확장
		System.out.print(c + "  ");
		int parentIndex = k / 2;
		heapifyUpward(parentIndex);//heapify시작
		System.out.println(heap.toString());
	}
	
	private void heapifyUpward(int parentIndex){
		// left child ->  heap트리에서 왼쪽자식 먼저 들어오므로, 왼쪽자식부터.
		int k = 2 * parentIndex;
		//트리보다 범위를 벗어나거나, 부모가 null인 경우에는 return
		if (k >= heap.size() || parentIndex < 1)
			return;
		
		// right child -> right가 트리 범위에 있고,이미 정렬된 left보다 크다면 right로 heapify
		if (k + 1 < heap.size() && heap.get(k + 1) > heap.get(k))
			k++;
		//자식이 부모보다 크다면 위치 바꾸고, 부모의 부모와도 heapify진행
		if (heap.get(k) > heap.get(parentIndex)){
			swap(heap, k, parentIndex);
			heapifyUpward(parentIndex / 2);
		}
	}                                                                  
	
	//다른 insert메소드 이게 간결하고 좋은 것 같음
//	public void insert(char val){
//		heap.add(val);
//		int p = heap.size() - 1;
//		while (p > 1 && heap.get(p / 2) < heap.get(p)){
//			char temp = heap.get(p / 2);
//			heap.set(p / 2, heap.get(p));
//			heap.set(heap.get(p), temp);
//
//			p = p / 2;
//		}
//	}
	
	private void sortOut(){
		System.out.println("< Max Heap > ");
		while (heap.size() > 1){
			System.out.println(deleteHeap() + "    " + heap.toString());
		}
	}

	//제일 큰 노드. 즉 root노드를 반환한다.
	private Character deleteHeap(){
		//heap의 크기가 범위 이하면 끝냄
		if (heap.size() <= 1)
			return null;
		//1. 제일 큰 노드. root노드 삭제
		char retVal = heap.remove(1);
		//트리크기가 2이상. 즉 null제외하고 노드가 2개 이상 있다면
		if (heap.size() > 2) {
			//2. 제일 뒤에있는 노드를 root의 자리로 옮긴다.
			heap.add(1, heap.remove(heap.size() - 1));
			heapifyDownward(1);
		}
		return retVal;
	}
	
	//노드의 자리를 찾아기는 과정의 메소드
	private void heapifyDownward(int i){
		// left child
		int k = 2 * i;
		//k가 범위 밖이거나, root노드 인덱스가 null이면 끝내기
		if (k >= heap.size() || i < 1){
			return;
		}
		// right child -> k+1이 범위에 있고, k+1(right)가 left보다 크다면 right와 비교해주는 것이 맞다.
		if (k + 1 < heap.size() && heap.get(k + 1) > heap.get(k))
			k++;
		//자식과 부모 크기비교해서 자식이 더 크다면 바꿔준다.
		if (heap.get(k) > heap.get(i)){
			swap(heap, k, i);
			heapifyDownward(k);//Upwnward라면 부모를 부르지만 Downward에선 자식을 부른다.
		}
	}


	private void swap(ArrayList<Character> heap, int k, int i){
		char temp = heap.get(k);
		heap.set(k, heap.get(i));
		heap.set(i, temp);
	}

	public static void main(String[] args) {
		char[] data = { 'M', 'Y', 'U', 't','N', 'G','a' ,'I', 'S', 'W','P', 'e','5'};
		HeapInArrayList h = new HeapInArrayList();
		h.heapSort(data);
		
	}
}
