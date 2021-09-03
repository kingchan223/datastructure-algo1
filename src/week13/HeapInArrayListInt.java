package week13;

import java.util.ArrayList;

public class HeapInArrayListInt {

	private ArrayList<Integer> heap = new ArrayList<>();

	public HeapInArrayListInt() {
		heap.add(null);
	}

	public void heapSort2(int[] input){
		buildHeap2(input);
		//sortOut();
	}
	
	private void buildHeap2(int[] input) {
		System.out.println("<< Heap implemented in ArrayList >>");
		for (int i = 0; i < input.length; i++) {
			insertHeap2(input[i]);
		}
	}

	private void insertHeap2(int c){
		int k = heap.size();
		heap.add(k, c);//노드가 k-1개인 트리에서 k번째에 노드 자리 확장
		System.out.print(c + "  ");
		int parentIndex = k / 2;
		heapifyUpward2(parentIndex);//heapify시작
		System.out.println(heap.toString());
	}
	
	private void heapifyUpward2(int parentIndex){
		// left child ->  heap트리에서 왼쪽자식 먼저 들어오므로, 왼쪽자식부터.
		int k = 2 * parentIndex;
		//트리보다 범위를 벗어나거나, 부모가 null인 경우에는 return
		if (k >= heap.size() || parentIndex < 1){
			return;
		}
		// right child -> right가 트리 범위에 있고,이미 정렬된 left보다 크다면 right로 heapify
		if (k + 1 < heap.size() && heap.get(k + 1) > heap.get(k))
			k++;
		//자식이 부모보다 크다면 위치 바꾸고, 부모의 부모와도 heapify진행
		if (heap.get(k) > heap.get(parentIndex)){
			swap(heap, k, parentIndex);
			heapifyUpward2(parentIndex / 2);
		}
	}                                                                  
	
	public void insert2(int val) {
		heap.add(val);
		int p = heap.size() - 1;
		while (p > 1 && heap.get(p / 2) < heap.get(p)) {
			Integer temp = heap.get(p / 2);
			heap.set(p / 2, heap.get(p));
			heap.set(heap.get(p), temp);

			p = p / 2;
		}
	}
	
	private void swap(ArrayList<Integer> heap, int k, int i) {
		Integer temp = heap.get(k);
		heap.set(k, heap.get(i));
		heap.set(i, temp);
	}
	public static void main(String[] args) {
		int[] data = { 5, 7, 1, 2, 10, 8, 4, 3 };
		HeapInArrayListInt h = new HeapInArrayListInt();
		h.heapSort2(data);
	}
}
