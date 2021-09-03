package q2;

public class pathfind02_1 {
	static int nOfCalls = 0;
	private static int[][] matrix= {
			{6,-7,12,-5,2,8},
			{0,-3,11,18,-1,6},
			{0,0,17,-3,5,-11},
			{0,0,0,-2,13,-4},
			{0,0,0,0,-9,10},
			{0,0,0,0,0,5},
	};
	
	public static int maxPathWeight(int row, int col){
		nOfCalls++;
		// row가 col보다 작은부분은 제외하여 문제의 그래프를 만족시킨다.
		if(row > col)
			return 0;
		//도착지점에 도달.
		if(row==0&&col==0)
			return matrix[row][col];
		//재귀호출을 지속
		else if(row==0)
			return matrix[row][col]+maxPathWeight(row, col-1);
		//재귀호출을 지속

		else if(col==0)
			return matrix[row][row]+maxPathWeight(row-1,col);
		//재귀호출을 지속 또한 위로 가는 경우와 옆으로 가는 경우 중 더 큰 것을 선택하도록 한다.
		else
			return matrix[row][col]
					+Math.max(maxPathWeight(row,col-1), maxPathWeight(row-1,col)); 
	}
	

	public static void main(String[] args) {
		System.out.println("최대값:"+maxPathWeight(5, 5));
		System.out.println("호출:"+nOfCalls);
	}
}
