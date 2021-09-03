package week05;


public class WorkHanoi {
	//제일큰 원반 n을 from에서 to로 옮겨야한다.
	public void hanoi(int n, String from, String to, String rest) {
		//기저조건. 제일 위의 원반의 경우에는 바로 옮겨준다.
		if(n==1)
			move(n, from, to);
		else {
			//더큰 원반이 만약 A에 있는데, B로 움직이려면 그 위의 원반이 나머지 원반인 C로 움직여야한다. 
			hanoi(n-1, from, rest, to);
			//더 작은 원반들이 다른 곳으로 이동했다면, 해당원반을 원래 움직이려던 지점으로 이동시킨다.
			move(n, from, to);
			//최종 원반이 목표지점으로 움직였다면 나머지 원반들도 그곳으로 움직여줘야한다.
			hanoi(n-1, rest, to, from);
		}
	}
	
	public void move(int n, String from, String to){
		System.out.println(n+" 크기의 원반 moved  from: "+from+"   to: "+to);
	}
	
	public static void main(String[] args) {
		WorkHanoi wh = new WorkHanoi();
		wh.hanoi(4, "A", "B", "C");
	}

}
