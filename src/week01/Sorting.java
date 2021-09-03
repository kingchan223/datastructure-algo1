package week01;

import java.util.ArrayList;



public class Sorting {

	public static void selectionSort(int[] list) {
		int temp;
		int minIndex;
		for(int i=0; i<list.length; i++) {
			minIndex = i;
			for(int j= i+1; j<list.length; j++) {
				if(list[minIndex] > list[j])
					minIndex = j;
			}
			temp = list[minIndex];
			list[minIndex] = list[i];
			list[i] = temp;
		}
	}
	
	public static void insertionSort(int[] list) {
		int temp;
		for(int i=1; i<list.length; i++)
		{
			int nowIndex = i;
			for(int j=i-1 ; j>=0; j--)
			{
				if(list[nowIndex] < list[j]) 
				{
					temp = list[j];
					list[j] = list[nowIndex];
					list[nowIndex] = temp;
					nowIndex--;
				}
				else 
					break;
			}
		}
	}
	// {6,4,2,1,5,9,7}
	public static void quickSort(int[] list, int start, int end) {
		if(start >= end) return; // base case
		int pivot = start;// 첫번째 원소를 pivot으로
		int left = start+1;
		int right = end;
		int temp;
		while(left <= right) // 엇갈릴 때까지.
		{
			while(left<=end && list[left] <=list[pivot]) left++;// pivot보다 작은 수를 찾을 때 까지 left(인덱스)를 1씩 증가
			while(right>start && list[pivot] <= list[right]) right--;//pivot보다 큰 수를 찾을 때까지 right인덱스를 줄이기
			if(left > right)// 엇갈렸다면 pivot과 현재 피벗보다 작은 list[right]를 교체
			{
				temp = list[right];
				list[right] = list[pivot];
				list[pivot] = temp;
			}
			else {
				temp = list[right];
				list[right] = list[pivot];
				list[pivot] = temp;
			}
		}
		quickSort(list, start, right-1);
		quickSort(list, right+1, end);

	}

	public static void main(String[] args) {
		int[] list = {1,8,4,9,6,5,3,2,7};
//		selectionSort(list);
		
		quickSort(list, 0, list.length-1);
		for(int i=0; i<list.length; i++) {
			System.out.print(list[i]+", ");
		}
	}
}
