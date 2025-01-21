public class Queens {

	private static int m = 8;
	private static int n = 8;

	public static boolean isValid(boolean[][] q, int i, int j) {
		if (j == 0) {
			return true;
		}

		// check if same row
		for (int k = 0; k < j; k++) {
			if (q[i][k]) {
				return false;
			}
		}
		// check if upper diagonal
		int k = i - 1;
		int l = j - 1;
		while (k >= 0 && l >= 0) {
			if (q[k][l]) {
				return false;
			}
			k--;
			l--;
		}

		// check if lower diagoal
		k = i + 1;
		l = j - 1;
		while (k < q.length && l >= 0) {
			if (q[k][l]) {
				return false;
			}
			k++;
			l--;
		}
		return true;
	}

	private static void print(boolean q[][]) {
		for (int i = 0; i < q.length; i++) {
			for (int j = 0; j < q[0].length; j++) {
				int k = q[i][j] == true ? 1 : 0;
				System.out.print(k + " ");
			}
			System.out.println();
		}
	}

	public static boolean queenBack(boolean q[][], int j) {
		// if we reach next to last column then we are done
		if (j >= n) {
			print(q);
			return true;
		}
		// check for all rows if we can place the queen in any row
		for (int k = 0; k < m; k++) {
			if (isValid(q, k, j)) {
				// place the queen
				q[k][j] = true;
				// call for next column
				boolean ans = queenBack(q, j + 1);
				// if we get solution then return true;
				if (ans) {
					return true;
				}
				// else backtrack try for next solution
				q[k][j] = false;
			}
		}
		return false;
	}

	public static void main(String... args) {
		boolean q[][] = new boolean[m][n];
		queenBack(q, 0);
	}
}
