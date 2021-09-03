package week01;

public class Prac2 {
	
	public static void selectSort(int arr[]) {
		for(int i=0; i<arr.length; i++) {
			int minVal = arr[i];
			int smallIndex = i;
			for(int j=i+1; j<arr.length; j++) {
				if(minVal > arr[j]) {
					minVal = arr[j];
					smallIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = minVal;
			arr[smallIndex] = temp; 
		}
	}
	
	public static void insertSort(int arr[]) {// {2, 7, 10, 5, 20, 3, 18, 13}
		for(int i=1; i<arr.length; i++) {
			int minIndex = i;
			for(int j=i-1; j>=0; j--) {
				if(arr[minIndex]<arr[j]) {
					int temp = arr[minIndex];
					arr[minIndex] = arr[j];
					arr[j] = temp;
					minIndex--;
				}
				else break;
			}
		}
	}
	public static void quickSort2(int arr[], int start, int end) {
        // {20, 6, 10, 7, 4, 12, 8, 5}
        if (end <= start) return;

        int pivot = start;
        int left = start + 1;
        int right = end;
        int temp;

        while(left<right)//엇갈릴 때까지의 조건
        {
            while((left<=end) && arr[left] <= arr[pivot]) left++;//left=end이면 무조건 엇갈린거임 그래서 인덱스가 오버되도 그 인덱스 안쓰고 나간다.
            while((right>start) && arr[right] >= arr[pivot]) right--;//근데 얘는 갈리면 아래 if문에서 right을 인덱스로 써야한다.


            if (left > right) {
                temp = arr[right];
                arr[right] = arr[pivot];
                arr[pivot] = temp;
            } else {
                temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }
	
	public static void quickSort(int arr[], int start, int end) {
		// {20, 6, 10, 7, 4, 12, 8, 5}
		if(end<=start) return;
		
		int pivot = start;
		int left = start+1;
		int right = end;
		int temp;
		
		while(left<right)//엇갈릴 때까지의 조건
		{
			while((left<=end) && arr[left] <= arr[pivot]) left++;//left=end이면 무조건 엇갈린거임 그래서 인덱스가 오버되도 그 인덱스 안쓰고 나간다.
			while((right>start) && arr[right] >= arr[pivot]) right--;//근데 얘는 갈리면 아래 if문에서 right을 인덱스로 써야한다.
			
			if(left > right) {
				temp = arr[right];
				arr[right] = arr[pivot];
				arr[pivot] = temp;
			}else {
				temp = arr[right];
				arr[right] = arr[left];
				arr[left] = temp;
			}
		}
		quickSort(arr, start, right-1);
		quickSort(arr, right+1, end);
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		
	}
	public static void main(String[] args) {
		int [] data = {3, 6, 5, 4, 7, 12, 8, 10};
		//selectSort(data);
//		insertSort(data);
		quickSort2(data, 0, data.length-1);
		for(int i=0; i<data.length; i++)
			System.out.print(data[i]+" ");
	}
}
