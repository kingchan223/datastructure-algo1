package week04;
public class Fibo {
	long[]memo = new long[100];
	static int cnt = 0;//memozation기법을 사용했을 때 호출 횟수가 얼마나 크게 줄어드는지 알기 위한 호출횟수.
	
	public long fiboRecur(int n) {
		cnt++;
		if(n<=0) return 0;
		// n==1일때 memo의 인덱스=1에 1을 저장한다.
		if(n==1) {
			memo[1] = 1;
			return 1;
		}
		//만약 memo에 값이 없다면 memo에 값을 저장한다.
		if(memo[n] == 0) {
			memo[n] = (fiboRecur(n-1)+fiboRecur(n-2));
			return memo[n];
		}
		//memo에 이미 값이 있다면 계산하지 않고 저장된 값을 출력한다.
		else
			return memo[n];
	}
	public static void main(String[] args) {
		Fibo f = new Fibo();
		System.out.println(f.fiboRecur(10));
		System.out.println("호출 횟수:"+cnt);
		//memo에 값을 저장하므로 아래와 같이 확인할 수도 있다.
		System.out.println(f.memo[40]); 
	}
}
