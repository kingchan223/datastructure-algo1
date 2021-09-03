package week03;

import java.util.Arrays;

public class Permutation {
   char[] data;
   int numberOfPatterns = 0;
   public Permutation(char[] data) {
      this.data = data;
   }
   //이전 상태가 유지가 되어야한다. 암시적 데이터를 주면 이랗게 힘들어진다.
   //암시적 데이터: 어디서든 접근해서 바꿀 수 있는 데이터
   
   //  data = {'a','b','c','d','e'}
   public void IterPermutation() {
      int size = data.length;
      numberOfPatterns = 0;
      for(int i0=0; i0<size; i0++) {
         swapData(0,i0);
//         System.out.print("After swap i0..........:");
//         System.out.println(data);
         for(int i1=1; i1<size; i1++) {
            swapData(1,i1);
//            System.out.print("After swap i1.....:");
//            System.out.println(data);
            for(int i2=2; i2<size; i2++) {
               swapData(2,i2);
//              System.out.print("After swap i2..:");
//              System.out.println(data);
               numberOfPatterns++;
               System.out.println(numberOfPatterns+" : "+toString());
               swapData(3,4);
               numberOfPatterns++;
               System.out.println(numberOfPatterns+" : "+toString());
               swapData(3,4);//원위치
               
//               System.out.print("Brfore swap i2:");
//               System.out.println(data);
               swapData(2,i2);//원위치
//               System.out.print("Re swap i2..:");
//               System.out.println(data);
            }
//            System.out.print("Brfore swap i1:");
//            System.out.println(data);
            swapData(1,i1);//원위치
//            System.out.print("Re swap i1.....:");
//            System.out.println(data);
         }
//         System.out.print("Brfore swap i0.....:");
//         System.out.println(data);
         swapData(0,i0);//원위치
//         System.out.print("Re swap i0..........:");
//         System.out.println(data);
      }
   }
   
   //더 일관성 있게 사용할 수 있도록 해준다.
   //오버로딩: 같은 기능에 매개변수만 다르게 한것.
   public void RecurPermutation(){
      RecurPermutation(this.data, "");
   }
   
   public void RecurPermutation(char[] input, String res){
      if(input.length == 1) {
         numberOfPatterns++;
         System.out.println(numberOfPatterns+" : "+res+input[0]);//abcd/
      } 
      for(int i=0; i<input.length; i++){//a,b,c,d   / b,c,d  /c,d 
         swapData(input, 0, i);         //a,b,c,d   / b,c,d  /cd  
         res += input[0];               //res = a   / ab     /abc
         //Arrays.copyOfRange(복사할 배열, index1, index2) => 배열[index1]부터 베열[index2-1]까지 복사
         RecurPermutation(Arrays.copyOfRange(input, 1, input.length), res);//b,c,d  a  /c,d  ab / d  abc / 
         //substring(index1, index2) => 문자열 index1부터 index2-1까지 자르기. 
         res = res.substring(0, res.length()-1);
      }
   }
   
   public void Recurperm(char[] data, String res){
	   if(data.length==1) {
		   numberOfPatterns++;
		   System.out.println(numberOfPatterns + "");
	   }
	   for(int i=0; i<data.length; i++) {
		   swapData(data, 0, i);
		   res += data[0];
		   Recurperm(Arrays.copyOfRange(data, 1, data.length), res);
		   
		   
	   }
   }
   
   public static void main(String[] args){
	   char[] data = {'a','b','c','d'};
	   Permutation p = new Permutation(data);
	   p.RecurPermutation();
	   //p.RecurPermutation(data,"");
	   //p.RecurPermutation();//더 일관성 있게 사용할 수 있도록 해준다.
   }
   
   
   
   public void IterPermutation2() {
	      int size = data.length;
	      numberOfPatterns = 0;
	      for(int i0=0; i0<size; i0++) {
	         swapData(0,i0);
	         for(int i1=1; i1<size; i1++) {
	            swapData(1,i1);
	            for(int i2=2; i2<size; i2++) {
	               swapData(2,i2);
	               for(int i3=3; i3<size; i3++) {
	            	   swapData(3,i3);
		               numberOfPatterns++;
		               System.out.println(numberOfPatterns+" : "+toString());
		               swapData(3,i3);//원위치	            
	               }
	               swapData(2,i2);//원위치
	            }
	            swapData(1,i1);//원위치
	         }
	         swapData(0,i0);//원위치
	      }
	   }
   
   private void swapData(int level, int index) {
      if(level!=index) {
         char temp = data[level];
         data[level] = data[index];
         data[index] = temp;
      }
   }
   
   private void swapData(char[] data, int level, int index){
      if(level!=index){
         char temp = data[level];
         data[level] = data[index];
         data[index] = temp;
      }
   }
   //오버라이딩
   public String toString() {
      String res = "";
      for(int i=0; i<data.length; i++)
         res+=data[i];
      return res;
   }
   


}
