package Practice;

public class Sort {
	
	public int[][] swap(int[][] data,int i,int j) {
		int[] temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		return data;
	}
	
	public void bubbleSort(int[][] data){
		int len = data.length;
		for(int i=len-1; i>=0; i--){
			for(int j=0; j<i ; j++){
				if(data[j][0] > data[i][0]) {
					data = swap(data, i, j);
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

	public static void main(String[] args) {
		int[][] data1 = {{3,1},{5,2},{1,3},{2,4},{4,5},{1,6},{1,7},{2,8},{5,9},{3,10}};	
		Sort s = new Sort();
		s.bubbleSort(data1);
		for(int i=0; i<data1.length; i++) {
			System.out.println("key:"+data1[i][0]+" ,val:"+data1[i][1]);
		}
		
		
		System.out.println();
		
		
		int[][] data2 = {{3,1},{5,2},{1,3},{2,4},{4,5},{1,6},{1,7},{2,8},{5,9},{3,10}};	
		s.bubbleSort2(data2);
		for(int i=0; i<data2.length; i++) {
			System.out.println("key:"+data2[i][0]+" ,val:"+data2[i][1]);
		}
	}

}
