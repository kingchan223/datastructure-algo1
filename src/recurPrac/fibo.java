package recurPrac;

public class fibo {
	static long[] memo = new long[100];
	static long cnt = 0;
	
	//recur를 사용한 피보나치
	public long fibo1(int n) {
		if(n<=0) return 0;
		if(n==1) return 1;
		return fibo1(n-1) + fibo1(n-2);
	}
	
	//recur에 메모제이션을 사용한 피보나치
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

}
