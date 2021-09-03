package week01;

public class Fibonacci {
	static long countIter=0, countRecur=0;
	public long fiboIteration(int n) {
		if(n<0) return  -1;
		if(n<=1) return n;
		long f1=0, f2=1, fn=0;
		for(int i=2; i<=n; i++) {
			countIter++;
			fn = f1+f2;
			f1 = f2;
			f2 = fn;			
		}
		return fn;
	}
	
	public long fiboRecursion(int n) {
		countRecur++;
		if(n<0) return 0;
		if(n==1) return 1;
		return fiboRecursion(n-1)+fiboRecursion(n-2);
	}

	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		long val;
		for(int n=5; n<45; n++) {
			val = f.fiboIteration(n);
			System.out.println("n = "+n+", value = "+val+", count : (iteration)"+countIter);
			countIter=0;
			val = f.fiboRecursion(n);
			System.out.println("(recursion )"+countRecur);
		}
	}
}
