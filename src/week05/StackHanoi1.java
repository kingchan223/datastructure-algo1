package week05;
	
public class StackHanoi1 {
	int maximum;
	//3개의 스택을 가지는 poles
	MyStack[] poles;
		
	//생성시 3개의 스택을 바로 만들고 poles가 참조하게 한다.
	public StackHanoi1(String[] plates) {
		maximum = plates.length;
		poles = new MyStack[3];
		
		//각각 항목에 대한 stack, heap만들기
		for(int i=0; i<3; i++) {
			//하노이 탑의 기둥이 3개 이므로 3개의 pole을 만들어야 한다.
			poles[i] = new MyStack();
		}
		//첫 번째 스택인 pole[0]에 우선 모든 원반을 넣는다.
		for(int i=0; i<maximum; i++) {
			poles[0].push(plates[i]);
		}
	}	
	//stack클래스를 직접 정의
	private class MyStack{
		//스택의 최대길이는 원반의 최대 개수로 한다.
		String[] stack = new String[maximum];
		//현재 스택의사이즈
		int stackNow=0;
		//스택에 원반을 맨 위에 추가해주는 메소드
		private void push(String s) {
			if(stackNow==maximum) {
				System.out.println("저장공간이 더 이상 없습니다.");
				return;
			}
			stack[stackNow] = s;
			stackNow++;
		}
		//스택 제일 위에 있는 원반을 없애주는 메소드
		private String pop() {
			if(stackNow==0) {
				System.out.println("꺼낼 데이터가 없습니다.");
				return null;
			}
			stackNow--;
			return stack[stackNow];
		}
		//현재 스택의 사이즈 반환
		private int sizeOf() {
			return stackNow;
		}
		//현재 pole. 즉 스택에 들어있는 원반을 보여준다.
		public String toString() {
			String ret="|";
			for(int i=0; i<stackNow; i++)
				ret += stack[i]+"|";
			return ret;
		}
	}
	//hanoi오버로드 함수
	private void hanoi(int i, int j) {
		hanoi(maximum, i-1, j-1);
	}	
	//각 단계의 원반을 from에서 to로 옮긴다.
	private void hanoi(int stage, int from, int to) {
		//기저조건: stage 1단계 원반을 옮긴다. 
		if(stage == 1) {
			move(stage, from, to);
		}
		else {
			//rest는 항상  0, 1, 2 중 from이 1, to가 2라면 0이 나온다.from,to이외의 값이 나온디. 
			int rest = (3-from-to)%3;
			//더 큰 단계를 옮기기 위해서는 전 단계의 원반들을 rest로 옮겨야 한다.
			hanoi(stage-1, from, rest);
			//현재 poles들의 상태 출력
			showPoles();
			//전 단계 원반들이 모두 rest로 옮겨졌으니 현재 단계의 원반을 to로 이동시킬수 있음
			move(stage, from, to);
			showPoles();
			//이제 다시 rest로 옮겨진 현재 단계보다 작은 원반들을 현재 단계에 있는 위치로 옮겨야한다.
			hanoi(stage-1, rest, to);
		}
	}
	//poles에 담긴 스택들을 모두 출력한다.
	public void showPoles() {
		System.out.println();
		for(int i=0; i<3; i++)
			System.out.println("Pole "+(i+1)+": "+poles[i].toString());
	}
	//from의 pole 맨 위에 있는 원반을 삭제하고, to의 pole로 옮긴다.
	public void move(int level, int from, int to) {
		poles[to].push(poles[from].pop());
	}
	
	public static void main(String[] args) {
		String [] plates = {"군청", "하늘","연두","보라"};
		StackHanoi1 me = new StackHanoi1(plates);
		me.showPoles();
		me.hanoi(1, 2);
		me.showPoles();
	}
}
