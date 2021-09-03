package q2;

public class pathfind02_2 {

	static int nOfCalls = 0;
	static int[][] memo = new int[6][6];
	private static int[][] matrix= {
			{6,-7,12,-5,2,8},
			{0,-3,11,18,-1,6},
			{0,0,17,-3,5,-11},
			{0,0,0,-2,13,-4},
			{0,0,0,0,-9,10},
			{0,0,0,0,0,5},
	};

	// 1번문제와 같지만 메모제이션을 활용해 호출을 줄인 방법
	public static int maxPathWeight2(int row, int col) {
		nOfCalls++;
		if(row > col) {
			return 0;
		}
		
		if(row==0&&col==0) {
			if(memo[row][col]==0)
				memo[row][col] = matrix[row][col];
			return memo[row][col];
		}
		else if(row==0) {
			if(memo[row][col]==0)
				memo[row][col] = matrix[row][col] + maxPathWeight2(row, col-1);
			return memo[row][col];
		}
		else if(col==0) {
			if(memo[row][col]==0)
				memo[row][col] = matrix[row][col] + maxPathWeight2(row-1, col);
			return memo[row][col];
		}
		else {
			if(memo[row][col]==0)
				memo[row][col] = matrix[row][col]
						+Math.max(maxPathWeight2(row, col-1), maxPathWeight2(row-1, col));
			return memo[row][col];
		}
	}

	public static void main(String[] args) {
		System.out.println("최대값:"+maxPathWeight2(5, 5));
		System.out.println("호출:"+nOfCalls);
	}
}