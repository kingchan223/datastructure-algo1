package week11;
	
public class Sort{
	int nOfCompare , nOfSwap, nOfMove;
	
	//Math.random을 사용하여 길이35의 난수 배열을 만드는 메소드
	public int[] createData(int size){
		int[] data = new int[size];
		for(int i=0; i<size; i++){
			data[i] = (int)(Math.random()*100 + 1);
		}
		return data;
	}
	
	public int[] swap(int[] data, int i, int j){
		nOfSwap++;
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		return data;
	}

	public void resetCounter(){
		nOfCompare = 0;
		nOfSwap = 0;
		nOfMove =0;
	}
	
	//현재 배열의 상태 확인 메소드
	public void showStat(String algoName){
		System.out.println("\n  <  "+algoName+"  >");                            
		System.out.println(" nOfCompare  : "+nOfCompare);
		if(nOfSwap!=0) System.out.println(" nOfSwap     : "+nOfSwap);
		if(nOfMove!=0) System.out.println(" nOfMove     : "+nOfMove);
		System.out.println();
	}
	
	private static void makeCopy(int[] dataSorted, int[] data){
		int n = data.length;
		for(int i=0; i<n; i++) {
			dataSorted[i] = data[i];
		}
	}
	
	private static void showData(int[] data){
		int len = data.length;
		System.out.println("---Data Status---");
		int nRow = 1+(int)len/10;
		for(int i=0; i<nRow; i++) {
			for(int j=i*10; j < Math.min(len,(i+1)*10); j++){
				System.out.print(" "+data[j]);
			}
			System.out.println();
		}
	}

	/*
	 * 선택정렬
	 * unstable
	 * */
	public int[] selectionSort(int[] data){
		int len = data.length;
		for(int i=0;i<len-1;i++){//하나를 선택해서
			for(int j=i+1; j<len;j++){//계속 뒤의 인덱스를 늘리며 비교한다.
				nOfCompare++;
				if(data[i] > data[j]){ 
					data = swap(data, i, j);
				}
			}
		}
		return data;
	}

	/*
	 * 버블정렬
	 * stable
	 * */
	public int[] bubbleSort(int[] data){
		int len = data.length;
		for(int i=len-1; i>=0; i--){
			for(int j=0; j<i ; j++){
				nOfCompare++;
				if(data[j] > data[j+1]){
					data = swap(data, j, j+1);
				}
			}
		}
		return data;
	}
	
//	//버블정렬2
//	public int[] bubbleSort2(int[] data){
//		int len = data.length;
//		for(int i=0; i<len; i++){
//			for(int j=0; j < len-(i+1); j++){
//				nOfCompare++;
//				if(data[j] > data[j+1]){
//					data = swap(data, j, j+1);
//				}
//			}
//		}
//		return data;
//	}
	/*----------------------    QuickSort   --------------------------------*/
	
	public int[] quickSort(int[] data){
		return quickSort(data, 0, data.length-1);
	}
	
	private int[] quickSort(int[] data, int start, int end){
		if( start < end ){ // start==end 라면 데이터가 한개 남은 경우이므로 진행할 필요가 없음.
			int p = partition(data, start, end);
			quickSort(data, start, p-1);
			quickSort(data, p+1, end);
		}
		return data;
	}
	
	private int partition(int[] data, int start, int end){
		int pivot = end;//제일 마지막 데이터를 피벗으로 지정
		int left = start;
		int right = end;
		
		while(left<right){//이 while문을 빠져나왔다는 것은 left==right라는 것이다.
			while(data[left]<data[pivot] && left<right) left++; nOfCompare++;
			while(data[right]>=data[pivot] && left<right) right--; nOfCompare++;
			//while안의 left<right요 조건 때문에 left>right가 되기전에는 무조건 빠져나온다.
			//즉 위 2개의 while문을 빠져나간다는 것은 
			//1. left==right
			//2. left<right
			//->left>right일 수는 없다.
			if(left<right) swap(data, left, right);
		}
		swap(data, pivot, right);//right가 left여도 상관없음(제일 큰 while문을 빠져나왔다는 것은 어차피 left==right이므로)
		return right;//left여도 상관업음
	}
	
	/*-----------------------------------------------------------------------*/
	
	
	/*-----------------------  Merge Sort -----------------------------*/
	
	public int[] mergeSort(int[] data) {
		return mergeSort(data, 0, data.length-1);
	}
	
	private int[] mergeSort(int[] data, int start, int end) { // {5, 3, 1, 4, 6, 2}
		if(start<end) {
			//배열의 중간지점을 찾고
			int mid = (start+end)/2; //mid = 2
			//맨처음~중간이전까지 정렬을 한다.
			//(맨처음에 실행했을 시에)재귀적으로 호출하기에 데이터가 하나가 될 때까지 호출될 것이다.
			mergeSort(data, start, mid);     //{5,3,1} - > {5,3} -> {5}
			//중간~마지막까지 정렬한다.
			mergeSort(data, mid+1, end);     
			//정렬된 앞데이터와 뒤의 데이터를 합친다.
			merge(data, start, mid, end);
		}
		return data;
	}
	//두개의 배열을 합치고 크기 순으로 정렬하여 반환히는 메소드
	private int[] merge(int[] data, int start, int mid, int end){
		
		//새로운 배열에 정렬된 값을 넣어 반환한다.
		int[] sorted = new int[data.length];
		
		//앞부분 데이터 하나하나를 가리킬 포인터이다.
		int prevP=start;
		//뒷부분 데이터를 가리킬 포인터
		int postP=mid+1;
		//정렬될 데이터를 위한 포인터다.
		int sortedP=start;
		
		//앞부분, 뒷부분의 배열의 포인터가 그 크기를 넘어가기 전까지 반복한다.
		while(prevP<=mid && postP<=end){
			nOfCompare++;
			nOfMove++;
			//뒷부분의 데이터가 더 크다면
			if(data[prevP]<data[postP]){
				//정렬된 데이터에 앞부분의 더 작은 데이터를 넣고, 
				//앞부분 데이터는 하나를 집어넣었으므로 포인터를 증가시킨다.
				//sorted의 데이터 포인터 역시 증가시켜야 한다.
				sorted[sortedP++] = data[prevP++];
			}else{
				//반대의 경우는
				//뒷부분의 데이터를 집어넣고 뒷부분 배열 포인터를 증가 시킨다.
				sorted[sortedP++]=data[postP++];
			}
		}
		// (아래 두개의 while 중 하나만 실행됨)
		// 아래 두 while문은 앞부분 배열과 뒷부분 배열 중 어느 한부분이
		// 데이터들을 이미 다 sorted에 삽입하여 더 이상 비교할 것이 없을 때 수행된다.
		
		//앞부분 배열의 데이터들이 아직 남아있는 경우
		while(prevP<=mid){
			nOfMove++;
			sorted[sortedP++] = data[prevP++];
		}
		//뒷 부분의 데이터들이 아직 남아있는 경우
		while(postP<=end){
			nOfMove++;
			sorted[sortedP++] = data[postP++];
		}
		//초기 배열인 data에 정렬된 sorted배열의 데이터들을 복사해준다.
		for(int l=start;  l<=end; l++){
			nOfMove++;
			data[l] = sorted[l];
		}
		//정렬된 data를 반환
		return data;
	}
	/*-----------------------------------------------------------------------*/
	
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
			nOfCompare++;
			nOfMove++;
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
			nOfMove++;
			sorted[arrayP] = data[prevP];
			arrayP++;
			prevP++;
		}
		while(postP <= end){
			nOfMove++;
			sorted[arrayP] = data[postP];
			arrayP++;
			postP++;
		}
		for(int l=start; l<=end; l++) {
			data[l] = sorted[l];
		}
		return data;
	}
	/*
	 * 삽입정렬
	 * stable
	 * */
	public int[] insertionSort(int[] data){
		int dataSize = data.length;
		if(dataSize>1){
			for(int target=1; target<dataSize; target++){
				int j = 0; 
				while( (data[j] < data[target]) && (j < target) ){
					nOfCompare++;
					j++;
				}
				if(j < target) {
					int temp = data[target];
					for(int k=target-1; k>=j;k--){
						nOfMove++;
						data[k+1] = data[k];
					}
					data[j] = temp;
				}
			}
		}
		return data;
	}
	
	public int[] insertionSort2(int[] data){
		int dataSize = data.length;
		if(dataSize > 1) {
			for(int target=1; target<dataSize; target++){
				int j=0; 
				while(data[target] > data[j] && (j<target)) {
					j++;
				}
				if(j < target) {
					int temp = data[target];
					for(int k = target-1;k >= j; k--) {
						data[k+1] = data[k];
					}
					data[j] = temp;
				}
			}
		}
		return data;
	}
	

	public void insertion_sort(int[] a) {
		insertion_sort(a, a.length);
	}
	
	private void insertion_sort(int[] a, int size) {
		for(int i = 1; i < size; i++) {
			// 타겟 넘버
			int target = a[i];
			
			int j = i - 1;
			
			// 타겟이 이전 원소보다 크기 전 까지 반복
			while(j >= 0 && target < a[j]) {
				nOfCompare++;
				a[j + 1] = a[j];	// 이전 원소를 한 칸씩 뒤로 미룬다.
				j--;
			}
			
			/*
			 * 위 반복문에서 탈출 하는 경우 앞의 원소가 타겟보다 작다는 의미이므로
			 * 타겟 원소는 j번째 원소 뒤에 와야한다.
			 * 그러므로 타겟은 j + 1 에 위치하게 된다.
			 */
			a[j + 1] = target;	
		}
	}

	
	//링크드 리스트를 활용한 insertion sort
	public int[] insertionSortLinkedList(int[] data){
		int dataSize = data.length;
		MySortLinkedList sorted = new MySortLinkedList();
		for(int i=0; i<dataSize; i++ ){
			sorted.insertAscendingOrder(data[i]);
			nOfCompare += sorted.getNofCompare();
			nOfMove++;
		}
		
		for(int i=0; i<dataSize; i++){
			data[i] = sorted.removeFirst();
		}
		return data;
	}
	 
	public static void main(String[] args) {
		int dataSize = 35;
		int[] initData = new int[dataSize];
		int[] dataSorted = new int[dataSize];
		
		Sort exSort = new Sort();
		initData = exSort.createData(dataSize);
		System.out.println("\n < Initial data >");
		showData(initData);
		
		exSort.resetCounter();
		makeCopy(dataSorted, initData);
		exSort.selectionSort(dataSorted);
		exSort.showStat("SelectionSort");
		showData(dataSorted);
		
		exSort.resetCounter();
		makeCopy(dataSorted, initData);
		exSort.bubbleSort(dataSorted);
		exSort.showStat("BubbleSort");
		showData(dataSorted);

		exSort.resetCounter();
		makeCopy(dataSorted, initData);
		exSort.quickSort(dataSorted);
		exSort.showStat("QuickSort");
		showData(dataSorted);
		
		exSort.resetCounter();
		makeCopy(dataSorted, initData);
		exSort.mergeSort(dataSorted);
		exSort.showStat("MergeSort");
		showData(dataSorted);
		
		exSort.resetCounter();
		makeCopy(dataSorted, initData);
		exSort.insertionSort(dataSorted);
		exSort.showStat("InsertionSort");
		showData(dataSorted);
		
		exSort.resetCounter();
		makeCopy(dataSorted, initData);
		exSort.insertionSortLinkedList(dataSorted);
		exSort.showStat("insertionSort with LinkedList");
		showData(dataSorted); 
		
	}




}
