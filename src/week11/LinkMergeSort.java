package week11;

import java.util.LinkedList;

public class LinkMergeSort {
	int nOfCompare;
	
	public LinkedList<Integer> mergeSort(LinkedList<Integer> data){
		return mergeSort(data, 0, data.size()-1);
	}

	private LinkedList<Integer> mergeSort(LinkedList<Integer> data, int start, int end) {
		if(start < end) {
			int mid = (start + end)/2;
			mergeSort(data, start, mid);
			mergeSort(data, mid+1, end);
			merge(data, start, mid, end);
		}
		return data;
	}


	private LinkedList<Integer> merge(LinkedList<Integer> data, int start, int mid, int end) {
		LinkedList<Integer> sorted = new LinkedList<Integer>();
		for(int i=0; i<data.size(); i++) {
			sorted.add(0);
		}
		
		int prevP = start;
		int postP = mid+1;
		int sortedP = start;
		
		while(prevP<=mid && postP<=end){
			nOfCompare++;
			if(data.get(prevP)<data.get(postP)){
				sorted.set(sortedP++, data.get(prevP++));
			}
			else{
				sorted.set(sortedP++, data.get(postP++));
			}
		}
		while(prevP<=mid) {
			sorted.set(sortedP++,data.get(prevP++));
		}
		while(postP<=end) {
			sorted.set(sortedP++,data.get(postP++));
		}
		for(int l=start; l<=end; l++) {
			data.set(l, sorted.get(l));
		}
		return data;
	}


	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(4);
		list.add(1);
		list.add(6);
		list.add(9);
		list.add(10);
		list.add(2);
		
		System.out.println(list);
		
		LinkMergeSort lms = new LinkMergeSort();
		System.out.println(lms.mergeSort(list));
	}
}
