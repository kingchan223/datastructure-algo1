package week01;

public class Prac {
	static long[] memo = new long[100];
	
	public long fibo(int n) {
		if(n<=0) return 0;
		if(n==1) return 1;
		return fibo(n-1) + fibo(n-2);
	}
	
	public long fibo2(int n) {
		if(n<=0) return memo[0];
		if(n==1){
			memo[1] = 1;
			return memo[1];
		}
		if(memo[n]==0){
			memo[n] = fibo2(n-1) + fibo2(n-2); 
			return memo[n];
		}
		return memo[n];
	}
	
	public static void main(String[] args) {
		
		Prac p = new Prac();
		System.out.println(p.fibo(20));
		System.out.println(p.fibo2(90));
	}
}
