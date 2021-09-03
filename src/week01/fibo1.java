package week01;

public class fibo1 {
	long[] arr = new long[100];
	static int cnt=0; 
//	
//	public long fiboIter(int n) {
//		if(n<0) return -1;
//		if(n<=1) return 1;
//		long f1 = 0, f2 = 1, fn=0;
//		for(int i=1; i<n; i++) {
//			fn = f1+f2;
//			f1 = f2;
//			f2 = fn;
//			System.out.println(fn+", ");
//		}
//		return fn;
//	}

	public long fiboRecur(int n) {
		arr[n] = arr[n-1] + arr[n-2];
		arr[n] = fiboRecur(n-1)+fiboRecur(n-2);
		cnt ++;
		if(n<=0) return 0;
		if(n==1) return 1;
		return fiboRecur(n-1) + fiboRecur(n-2);
	}
	
	
	public static void main(String[] args) {	
		fibo1 f = new fibo1();
		//System.out.println(f.fiboIter(10));
		System.out.println(f.fiboRecur(40));
		System.out.println("호출횟수:"+cnt);
	}
}
