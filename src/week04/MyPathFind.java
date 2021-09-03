package week04;

public class MyPathFind {
	int[][] matrix;
	int cnt = 0;
	int zero[][];
	
	public MyPathFind(int[][] matrix) {
		this.matrix = matrix;
	}

	
	//수업의 내용과 반대로 구현해보았다. (0,0)의 좌표지점을 도착지점이라고 생각한 것과 같다.
	// 인자로는 x=0,y=0을 넣어준다. 여기가 최종 도착지점이라 생각하고 재귀호출을 해나간다.
	public int RecurPathFind(int x, int y) {
		//호출횟수
		cnt++;
		//만약 [4][4]인 배열에서 인덱스가 (3,3)이라면 제일 마지막이므로 그 곳의 값을 그대로 리턴해준다.
		if(x==3&&y==3)
			return matrix[x][y];
		//x좌표를 하나씩 늘려주다가 배열의 끝에 오면(x==3) 아래로 향하게 해줘야한다. 
		else if(x==3)
			return matrix[x][y] + RecurPathFind(x, y+1);
		//y좌표를 하나씩 늘려주다가 배열의 끝에 오면(y==3) 오른쪽으로 향하게 해줘야한다. 
		else if(y==3)
			return matrix[x][y] + RecurPathFind(x+1, y);
		//현재좌표가 배열의 끝부분에 있는 것이 아니라면 오른쪽의 값과 아래 방향의 값을 비교하여 더 큰 곳을 선택한다.
		else
			return matrix[x][y] + Math.max(RecurPathFind(x+1, y), RecurPathFind(x, y+1));
	}
	
	//반복적 방법. 매개변수 x,y는 배열의 마지막 인덱스까지 연산해야함을 알리기 위해 필요하다.
	public int IterPathFind1(int x, int y) {
		//단계마다 축적해여 더해진 값을 더하기 위한 배열 accData를 matrix와 같은 크기의 빈 배열로 초기화.
		int[][] accData= new int[x+1][y+1];
		//첫번째 값은어차피 시작점이므로 그래도 넣어준다.
		accData[0][0] = matrix[0][0];
		// (x,y)와 같이 나타낼 때. y=0인 부분. 즉 맨 위의 가로줄을 먼저 축적하여 더하고 accData에 넣어준다.
		for(int i=1; i<x+1; i++)
			accData[i][0] = matrix[i][0] + accData[i-1][0];
		// (x,y)와 같이 나타낼 때. x=0인 부분. 즉 맨 오른쪽 쪽의 세로줄을 먼저 축적하여 더하고 accData에 넣어준다.
		for(int i=1; i<y+1; i++)
			accData[0][i] = matrix[0][i] + accData[0][i-1];
		//여기서부터는 특정위치의 칸의 max값은 위에서 오는 경우와 왼족에서 오는 경우 중 더 큰 경우를 선택해야한다.
		//stage값을 고정적으로 주어서 안의 for문 2개가 각 행과 열에 대하여 더 큰 값을 선택하여 더하도록한다.
		//상위 for문 아래의 두개의 for문에서 stage의 값이 같이게 '고정적'이라는 표현을 썻다.
		for(int stage=1; stage<x+1; stage++) {
			for(int i=stage; i<x+1; i++)
				accData[i][stage] = matrix[i][stage] + Math.max(accData[i-1][stage], accData[i][stage-1]);
			for(int i=stage; i<y+1; i++)
				accData[stage][i] = matrix[stage][i] + Math.max(accData[stage][i-1], accData[stage-1][i]);
		}
		//최종값을 출력한다.
		return accData[3][3];
	}
	
	//위와 같은 내용인데 사실 인자를 아래와 같이 matrix의 길이로 넣어줘도 된다.
	public int IterPathFind2(int len) {
		int[][] accData= new int[len][len];
		accData[0][0] = matrix[0][0];
		for(int i=1; i<len; i++)
			accData[i][0] = matrix[i][0] + accData[i-1][0];
		for(int i=1; i<len; i++)
			accData[0][i] = matrix[0][i] + accData[0][i-1];
		for(int stage=1; stage<len; stage++) {
			for(int i=stage; i<len; i++)
				accData[i][stage] = matrix[i][stage] + Math.max(accData[i-1][stage], accData[i][stage-1]);
			for(int i=stage; i<len; i++)
				accData[stage][i] = matrix[stage][i] + Math.max(accData[stage][i-1], accData[stage-1][i]);
		}
		return accData[3][3];
	}
	
	public void makeZero() {
		int d = matrix.length;
		zero = new int[d][d];
	}
	
	
	//메모제이션을 활용하여 재귀호출의 호출을 줄인 방법이다.
	//거의 모든 코드가 RecurPathFind와 같은데 계산한 값은 zero라는 배열에 저장한다는 것만 다르다.
	public int SaveRecurPathFind(int x, int y) {
		//호출횟수
		cnt++;
		//만약 [4][4]인 배열에서 인덱스가 (3,3)이라면 제일 마지막이므로 그 곳의 값을 그대로 리턴해준다.
		if(x==3&&y==3){
			//zero배열에 저장된 값이 없다면 
			if(zero[x][y]==0)
				//matrix값을 zero에 넣어준다. 이는 아래도 항상 같으므로 주석 생략.
				zero[x][y] = matrix[x][y];
			return zero[x][y]; 
		}	
		//x좌표를 하나씩 늘려주다가 배열의 끝에 오면(x==3) 아래로 향하게 해줘야한다. 
		else if(x==3) {
			if(zero[x][y]==0)
				zero[x][y] = matrix[x][y] + SaveRecurPathFind(x, y+1);
			return zero[x][y];
		}
		//y좌표를 하나씩 늘려주다가 배열의 끝에 오면(y==3) 오른쪽으로 향하게 해줘야한다. 
		else if(y==3) {
			if(zero[x][y]==0)
				zero[x][y] = matrix[x][y] + SaveRecurPathFind(x+1, y);
			return zero[x][y];
		
		}
		//현재좌표가 배열의 끝부분에 있는 것이 아니라면 오른쪽의 값과 아래 방향의 값을 비교하여 더 큰 곳을 선택한다.
		else {
			if(zero[x][y]==0)
				zero[x][y] = matrix[x][y] + Math.max(SaveRecurPathFind(x+1, y), SaveRecurPathFind(x, y+1));
			return zero[x][y];
		}
	}
	

	public static void main(String[] args) {
		int[][]data = {{6, 7, 12, 5},
				       {5, 3, 11, 18},
				       {0, 17, 3, 3},
				       {8, 10, 14, 9}};

		MyPathFind mpf = new MyPathFind(data);
		System.out.println("RecurPathFind:"+mpf.RecurPathFind(0, 0));
		System.out.println("Call:"+mpf.cnt);
		System.out.println("IterPathFind1:"+mpf.IterPathFind1(3, 3));
		System.out.println("IterPathFind2:"+mpf.IterPathFind2(mpf.matrix.length));
		mpf.makeZero();
		System.out.println("SaveRecurPathFind:"+mpf.SaveRecurPathFind(0, 0));
	}
}
