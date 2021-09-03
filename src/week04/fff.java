package week04;

public class fff {
	static int cnt=0;
	long[] data = new long[100];
	
	public long fibo(int n) {
		cnt++;
		if(n<=0) return 0;
		if(n==1) data[1] = 1;
		if(data[n] != 0) return data[n];
		else {
			data[n] = fibo(n-2) + fibo(n-1);
			return data[n];
		}
	}

	public static void main(String[] args) {
		fff f = new fff();
		System.out.println(f.fibo(60));
		System.out.println("call:"+cnt);

	}
}
