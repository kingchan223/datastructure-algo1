package week09;

public class PathTriangle{
	static int call = 0;
	int [][] memo;
	int[][] matrix;
	
	public PathTriangle(int[][] matrix){
		this.matrix = matrix;
		this.memo = new int[matrix.length][matrix.length];
	}
	
	public int maxPathReCur(int x, int y){
		if(x==0 && y==0){
			return matrix[x][y];
		}
		else if(x==0){
			return matrix[x][y] +maxPathReCur(x,y-1);
		}
		else if(x==y){
			return matrix[x][y] 
					+ Math.max(maxPathReCur(x-1,y-1), maxPathReCur(x-1,y));
		}
		else{
			return matrix[x][y] 
					+ Math.max( Math.max(maxPathReCur(x-1,y-1), maxPathReCur(x,y-1)),
							             maxPathReCur(x-1,y));
		}                                                                       
	}																			
																			  
	public int maxPathReCur2(int x, int y){                                     									     
		call++;																 	 	
		if(x==0 && y==0){																
			if(memo[x][y]==0){														
				memo[x][y] = matrix[x][y]; 											
			}
			return memo[x][y];
		}
		else if(x==0){
			if(memo[x][y]==0)
				memo[x][y] = matrix[x][y] 
						+maxPathReCur2(x,y-1);
			return memo[x][y];
		}
		else if(x==y){
			if(memo[x][y]==0)
				memo[x][y] = matrix[x][y] 
						+ Math.max(maxPathReCur2(x-1,y-1),
								   maxPathReCur2(x-1,y));                             
			return memo[x][y];
		}
		else{
			if(memo[x][y]==0)
				memo[x][y] = matrix[x][y] 
						+ Math.max( Math.max(maxPathReCur2(x-1,y-1),
								maxPathReCur2(x,y-1)), maxPathReCur2(x-1,y));
			return memo[x][y];                                                  
		}
	}
	
	public int maxPathReCur(){
		return maxPathReCur(matrix.length-1, matrix.length-1);
	}
	
	public int maxPathReCur2(){
		return maxPathReCur2(matrix.length-1, matrix.length-1);
	}
	
	public static void main(String[] args){
		int[][] matrix = {{6,-7,12,-5,2,8},
						  {0,-3,11,18,-1,6},
						  {0,0,17,-3,5,-11},
						  {0,0,0,-2,13,-4},
						  {0,0,0,0,-9,10},
						  {0,0,0,0,0,5}};
		
		PathTriangle pt = new PathTriangle(matrix);
		System.out.println("maxPathReCur RESULT:"+pt.maxPathReCur());
		System.out.println("maxPathReCur: "+call);
		call = 0;
		System.out.println("maxPathReCur2 RESULT:"+pt.maxPathReCur2());
		System.out.println("maxPathReCur2: "+call);
	}
}
