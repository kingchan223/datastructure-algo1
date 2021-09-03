package week01;
	
public class Factorial{
	
	public long factorialIter(int n){
		if(n<=0) return 0;
		long result = 1;
		for(long i=2; i<=n; i++) {
			result *= i;
		}
		return result;
	}
	
	public long factorialRecur(int n){
		if(n<=0) return 0;
		if(n==1) return 1; //base case
		return n*factorialRecur(n-1);
	}
	
	public static void main(String[] args){
		Factorial f = new Factorial();
		System.out.println(f.factorialRecur(5));
	
	}
}
