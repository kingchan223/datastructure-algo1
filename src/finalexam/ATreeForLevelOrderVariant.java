package finalexam;

import java.util.ArrayList;

public class ATreeForLevelOrderVariant {
	
	private ArrayList<Character> heap = new ArrayList<>();
	
	public ATreeForLevelOrderVariant() {
		heap.add(null);
	}
	
	public void heapSort(char [] input) {
		buildHeap(input);
		sortOut();
	}

	private void sortOut() {
		System.out.println("< Max.Heap >");

		while(heap.size()>1) {
			System.out.println(deleteHeap()+ "  "+ heap.toString());
		}
	}

	private Character deleteHeap() {
		if (heap.size()<=1)
			return null;
		char retVal = heap.remove(1);
		
		if (heap.size()>2) {
			heap.add(1, heap.remove(heap.size()-1));
			heapifyDownward(1);
		}
		
		return retVal;
	}

	private void heapifyDownward(int i) {
		int k = 2*i;
		if (k>=heap.size()||i<1)
			return;
		if (k+1<heap.size() && (heap.get(k+1)>heap.get(k)))
			k++;
		
		if (heap.get(k)>heap.get(i)) {
			swap(heap, k, i);
			heapifyDownward(k);
		}		
	}

	public void buildHeap(char[] input) {
		System.out.println("<< Heap implemented in ArrayList >>");

		for (int i = 0; i<input.length ; i++)
			insertHeap(input[i]);
		
	} 

	private void insertHeap(char c) {
		int k = heap.size();
		heap.add(k, c);
		
		System.out.print(c+"  ");
		int parentIndex = k/2;
		heapifyUpward(parentIndex);
		System.out.println(heap.toString());
	}

	private void heapifyUpward(int i) {
		int k = 2*i;
		if (k>=heap.size()||i<1)
			return;
		if (k+1<heap.size() && (heap.get(k+1)>heap.get(k)))
			k++;
		
		if (heap.get(k)>heap.get(i)) {
			swap(heap, k, i);
			heapifyUpward(i/2);
		}
	}

	private void swap(ArrayList<Character> heap, int k, int i) {
		char temp = heap.get(k);
		heap.set(k, heap.get(i));
		heap.set(i, temp);
	}
	
	public void updateNRepaireHeap(int k, char value) {
		
		heap.set(k, value);
		
		System.out.println("< Before Repair >");
		System.out.println(heap.toString());
		
		heapRepair(k);
		
		System.out.println("< After Repair >");
		System.out.println(heap.toString());
		
		
	}

	private void heapRepair(int k){
		int parentIndex = k/2;
		if(heap.get(parentIndex) < heap.get(k))
			heapifyUpward(parentIndex);
		else
			heapifyDownward(k);
	}

	public static void main(String[] args) {
		char [] data = {'M','Y','U','N','G','I','S','W'};
		
		ATreeForLevelOrderVariant h = new ATreeForLevelOrderVariant();
		
		h.buildHeap(data);
		
		h.updateNRepaireHeap(3,'A');
		h.updateNRepaireHeap(4,'B');
		
	}

}
