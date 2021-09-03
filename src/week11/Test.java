package week11;


public class Test {
	
	public int[][] swap(int[][] data,int i,int j) {
		int[] temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		return data;
	}
	
	public void bubbleSort1(int[][] data){
		int len = data.length;
		for(int i=len-1; i>=0; i--){
			for(int j=0; j<i ; j++){
				if(data[j][0] > data[j+1][0]) {
					data = swap(data, j, j+1);
				}
			}
		}
	}
	
	public void bubbleSort2(int[][] data){
		int len = data.length;
		for(int i=0; i<len; i++){
			for(int j=0; j < len-(i+1); j++){
				if(data[j][0] > data[j+1][0]){
					data = swap(data, j, j+1);
				}
			}
		}
	}
	
	public int[] mergeSort2(int[] data) {
		return mergeSort2(data, 0, data.length-1);
	}
	
	public int[] mergeSort2(int[] data, int start, int end){
		if(start<end){
			int mid = (start+end)/2;
			mergeSort2(data, start, mid);
			mergeSort2(data, mid+1, end);
			merge2(data, start, mid, end);
		}
		return data;
	}
	
	public int[] merge2(int[] data, int start, int middle, int end){
		int prevP = start;
		int postP = middle +1;
		int arrayP = start;
		int[] sorted = new int[data.length];
		
		while((prevP<=middle) && (postP<=end)){
			
			if(data[prevP]< data[postP]) {
				sorted[arrayP] = data[prevP];
				arrayP++;
				prevP++;
			}else{
				sorted[arrayP] = data[postP];
				arrayP++;
				postP++;
			}
		}
		
		while( prevP<= middle){
			
			sorted[arrayP] = data[prevP];
			arrayP++;
			prevP++;
		}
		while(postP <= end){
			
			sorted[arrayP] = data[postP];
			arrayP++;
			postP++;
		}
		for(int l=start; l<=end; l++) {
			data[l] = sorted[l];
		}
		return data;
	}
	

	public static void main(String[] args) {
		int[][] data1 = {{3,1},{5,2},{1,3},{2,4},{4,5},{1,6},{1,7},{2,8},{5,9},{3,10},{4,11}};	
		Test s = new Test();
		s.bubbleSort1(data1);
		for(int i=0; i<data1.length; i++) {
			System.out.println("key:"+data1[i][0]+" ,val:"+data1[i][1]);
		}
		
		
		System.out.println();
		
		
		int[][] data2 = {{3,1},{5,2},{1,3},{2,4},{4,5},{1,6},{1,7},{2,8},{5,9},{3,10},{4,11}};	
		s.bubbleSort2(data2);
		for(int i=0; i<data2.length; i++) {
			System.out.println("key:"+data2[i][0]+" ,val:"+data2[i][1]);
		}

	}

}