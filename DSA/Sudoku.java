
class Pair
{
	int i,j;
	Pair(int i,int j)
	{
		this.i = i;this.j = j;
	}
}
public class Sudoku {

	public static void printMatrix(int mat[][])
	{
		for(int i=0;i<mat.length;i++)
		{
			for(int j =0;j<mat[0].length;j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	public static Pair getFirstFillPosition(int [][]mat)
	{
		for(int i=0;i<mat.length;i++)
		{
			for(int j =0;j<mat[0].length;j++)
			{
				if(mat[i][j]==0)
					return new Pair(i,j);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*0 3 6
		5 2 1
		8 7 9
		*/
		
		int matrix [][] = {{4,3,6},{5,2,1},{0,7,9}};
		
		Pair p = getFirstFillPosition(matrix);
		
		boolean a[] = new boolean[10];
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				a[matrix[i][j]] = true;
			}
		}
	
		//get the missing number
		int ans = -1;
		for(int i=1;i<10;i++)
		{
			if(a[i] == false)
				ans = i;
		}
		//got the missing number;
		if(ans == -1){
			System.out.println("All are filed");
			return;
		}
		matrix[p.i][p.j] = ans;
		
		printMatrix(matrix);
	}

}
