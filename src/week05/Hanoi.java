package week05;

public class Hanoi {
//	int moveCounter = 0;
	public void hanoi(int stage, char from, char to, char extra) {
		if(stage==1) {
			move(stage, from, to);
			return;			
		}
		else {
			hanoi(stage-1, from, extra, to);
			move(stage, from, to);
			hanoi(stage-1, extra, to, from);
		}
	}
	
//	public int howManyMove() {
//		return moveCounter;
//	}
//	public void resetCount() {
//		moveCounter = 0;
//	}
	private void move(int level, char from, char to) {
//		moveCounter++;
		System.out.println(level+" 을 "+from+" --> "+to+"로 이동");
	}

	public static void main(String[] args) {
		Hanoi hn = new Hanoi();
		int n = 4;
		hn.hanoi(n, 'A', 'B', 'C');
		
//		System.out.println(hn.howManyMove());
//		for(int i=3; i<10; i++) {
//			hn.resetCount();
//			hn.hanoi(i, 'A', 'B', 'C');
//			System.out.println(i+" : "+hn.howManyMove());
//		}
	}

}
