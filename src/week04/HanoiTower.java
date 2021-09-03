package week04;


import java.util.Stack;

public class HanoiTower {

	static Stack<Integer> left = new Stack<>();
	static Stack<Integer> center = new Stack<>();
	static Stack<Integer> right = new Stack<>();
	static int cnt=0;
	
//	public void print() {
//		System.out.print("left:");
//		String str1="";
//		for(Integer i: left) {
//			str1 += i+", ";
//		}
//		System.out.println(str1);
//		System.out.print("center:");
//		String str2="";
//		for(Integer i: center) {
//			str2 += i+", ";
//		}
//		System.out.println(str2);
//		String str3="";
//		System.out.print("right:");
//		for(Integer i: right) {
//			str3 += i+", ";
//		}
//		System.out.println(str3);
//		System.out.println("--------------------");
//	}
//	
//	public Stack isThere(int now) {
//		
//		if(-1!=left.search(now)) {
//			return left;
//		}
//		else if(-1!=center.search(now)) {
//			return center;
//		}
//		else{
//			return right;
//		}
//	}
//	public void Hanoi3(int now, String nowLoc, String goal) {
//		
//		if(goal.equals("center")){
//			Stack loc = isThere(now);
//			left.push((Integer) loc.pop());
//			Hanoi3(now-1, "left", "right");
//		}else {
//			
//		}
//	}
	

	public static int hanoi1(int n) {
		if(n==1)
			return 1;
		return (hanoi1(n-1)*2) + 1;
	}
	
	
	
	public static void hanoi2(int n, String from, String mid, String end) {
		cnt++;
		if(n==1)
			System.out.println("원반"+ n + "을" + from+ "에서" +end+"로 이동");
		else {
			hanoi2(n-1, from, end, mid);
			System.out.println("원반"+ n + "을" + from+ "에서" +end+"로 이동");
			hanoi2(n-1, mid, from, end);
		}
	}
	
	
	public static void main(String[] args) {
		HanoiTower ht = new HanoiTower();


		hanoi2(5, "left", "center", "right");
		System.out.println("호출횟수:"+cnt);
	}
}
