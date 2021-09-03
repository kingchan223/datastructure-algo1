package week03;

public class RecursionSample {
	   int [] data;
	   public RecursionSample(int[] input) {
	      data = input;
	   }
	   
	   public String toString() {
	      String res = "";
	      for(int i=0; i<data.length; i++)
	         res += (data[i]+" ");
	      return res;
	   }
	   
	   // 얘는 데이터를 옮기거나 하지 않고 탐색만 한다. 
	   // 그래서 permutation에서 데이터 망가지는 거처럼 망가지지 않는다.
	   public int maxIndexIter() {
	      if(data.length<1) {
	         return -1;
	      }
	      int index = 0;
	      int max = data[0];
	      int i = 1;
	      while(i<data.length) {//처음부터 끝까지 탐색.
	         if(data[i]>max) {
	            max = data[i];
	            index=i;
	         }
	         i++;
	      }
	      return index;
	   }
	   
	   public int search1(int value) {
	      int i=0;
	      while(i<data.length) {
	         if(data[i]==value)
	            return i;
	         else
	            i++;
	      }
	      return -1;//없으면 -1반환
	   }
	   
	   //recur방식의 매개변수는 반복방식의 while,for의 i를 대신하기 위함이다.
	   //recur 자체가 반복문의 역할을 대신 해주기 때문
	   public int search2(int value, int i) {
	      if(i>=data.length)//마지막인덱스를 넘어섰다면 없는 것이다.
	         return -1;
	      if(data[i]==value)
	         return i;
	      else
	         return search2(value, i+1);
	   }
	   
	   public int search3(int value, int start, int end){
	      if(start> end)
	         return -1;
	      int mid = (start+end)/2;
	      if(data[mid]==value)
	         return mid;
	      else if(data[mid] < value)
		    return search3(value, mid+1, end);
	      else
	      	return search3(value, start, mid-1);
	   }
	   
	   //위로 커지는 것도 수렴할 수 있다.
	   //제일 큰 인덱스를 찾는 것은 배열을 바꾸거나 하지 않다. 그래서 인덱스를 매개변수로 전달
	   public int maxIndexRec(int startIndex) {// 배열의 startIndex부터 마지막까지 제일 큰애의 인덱스를 찾아서 반환.
	      if(startIndex==(data.length-1))//맨 마지막까지 가면 리턴
	         return startIndex;
	      int tempIndex = maxIndexRec(startIndex+1);//맨 마지막까지 보내기
	      if(data[startIndex]>data[tempIndex])//인덱스와 인덱스+1비교해서 더 큰 인덱스 반환.
	         return startIndex;
	      else
	         return tempIndex;
	   }
	   
	   //얘는 암시적데이터인데 안망가지는 이유: 제일 큰거 앞에 두고 그 뒤 부터만 처리한다.
	   //즉 이미 정렬한 데이터는 안 건드린다.
	   public int[] mySort() {
	      int startIndex = 0;
	      while(startIndex<data.length-1) {
	         int tempIndex = maxIndexRec(startIndex);
//	         if(data[startIndex]<data[tempIndex])
	         swapData(startIndex, tempIndex);
	         startIndex++;
	      }
	      return data;
	   }
	   
	   private void swapData(int level, int index) {
	      if(level != index) {
	         int temp = data[level];
	         data[level] = data[index];
	         data[index] = temp;
	      }
	   }
	   
	   public void changeAscDesc() {
		   int mid = data.length/2;
		   int start = 0;
		   int end = data.length-1;
		   for(int i=0; i<=mid; i++){
			   int temp = data[end];
			   data[end] = data[start];
			   data[start] = temp;
			   end--;
			   start++;
		   }
	   }
	   
	   public static void main(String[] args) {
	      int[] input = {101, 48, 91, 3, 16, 33, 67, 40, 87, 90};
	      RecursionSample me = new RecursionSample(input);
	      System.out.println(me.toString());
	      System.out.println(me.maxIndexIter());
	      System.out.println(me.maxIndexRec(0));
	      me.mySort();
	      System.out.println(me.toString());
	      
	      
	      int val = 33;
	      me.changeAscDesc();
	      System.out.println(me.toString());
	      System.out.println("Loc. of "+val+": "+me.search1(val));
	      System.out.println("Loc. of "+val+": "+me.search2(val, 0));
	      System.out.println("Loc. of "+val+": "+me.search3(val, 0, 8));
	   }
	}