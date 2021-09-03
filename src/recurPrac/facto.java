package recurPrac;
	
public class facto {
	static long cnt = 0;
	
	//recur를 사용한 퍼뮤테이션
	public long factorial(int n) {
		cnt++;
		if(n==1) return 1;
		return n*factorial(n-1);
	}
	
	public static void main(String[] args) {
		facto p = new facto();
		
		System.out.println(p.factorial(10));
		System.out.println("호출횟수:"+cnt);
	}
}
