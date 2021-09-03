package week04;

public class Sort {

	
	public int[] quickSort(int[] data, int le, int ri) {//범위로 들어온 le~ri에 대해서 정렬하기.z
		if(le<ri) {
			//분할할 위치를 q에 저장한다.
			int q = partitionByPivot(data, le, ri);
			//분할된 인덱스를 기준으로 왼쪽에서 다시 분할 정복을 재귀적으로 수행한다.
			quickSort(data, le, q-1);
			// 분할된 인덱스를 기준으로 오른쪽에서 다시 
			quickSort(data, q+1, ri);
		}
		return data;
	}  
	
	//한번 고정된 부분을 건드리지 않기 때문에 data가 static이어도 상관없다.
	private int partitionByPivot(int[] data, int le, int ri) {
		int pivot = ri;
		int left = le;
		int right = ri;
		
		while(left<right) {
			
			while(data[left]<data[pivot]&&left<right) left++;//pivot보다 큰거 찾으면 멈추기. 단 left가 right보다 커져도 멈춰야한다.
			while(data[right]>data[pivot]&&left<right) right--;//pivot보다 작은거 찾으면 멈추기. 단, right가 left보다 커져도 멈춘다.
			if(left<right) swapData(data, left, right);// pivot보다 작은 애는 앞으로, 큰애는 뒤로 자리를 교환.
		}
		//right = left이라면 피벗 위치의 데이터와 data[right](=data(left)의 데이터를 맞바꿔준다.
		//이렇게 되면 피벗의 오른쪽은 무조건 다 피벗보다 크고, 왼쪽은 무조건 피벗보다 작다.
		swapData(data, pivot, right);
		//위에서 확정된 위치의 인덱스를 반환한다. (left를 반환해도 상관없다.)
		return right;
	}
	
	private void swapData(int[] data, int i, int j) {

		int temp = data[j];
		data[j] = data[i];
		data[i] = temp;
	}

	public static void main(String[] args) {
		int[] data = {12, 21, 15, 32, 22, 9, 11, 33, 41, 27};
		int[] sortedData = new int[data.length];
		
		Sort s = new Sort();
		sortedData = s.quickSort(data,  0, data.length-1);
		for(int i=0; i<sortedData.length; i++)
			System.out.print(sortedData[i]+" ");
		System.out.println();
	}
}
