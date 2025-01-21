package r_3;

import java.util.Stack;

/*
 * 
 *    [1, 2, 3, 4, 1, 2] 
		   
	4,3,2,1
	
	 2 -->1
	 1-->2  
	 4-->-1
	 3-->4
	 2-->3
	 1-->2
 */
public class NextMaxElement {

	public static int getNextmax(int s, int len, int aa[]) {
		// System.out.println(s+" "+aa[s]+" "+len);

		Stack<Integer> st = new Stack<Integer>();

		int ans = -1;
		int length = len;
		int i = 0;

		while (i <= length) {
			int j = s + len;

			if (st.isEmpty()) {
				st.push(aa[j]);
			} else if (aa[j] < st.peek()) {
				ans = st.peek();
				st.push(aa[j]);
			} else if (aa[j] > st.peek()) {
				while (!st.isEmpty() && aa[j] >= st.peek()) {
					st.pop();
				}
				if (st.isEmpty()) {
					ans = -1;
				} else {
					ans = st.peek();
				}
				st.push(aa[j]);
			}
			i++;
			len--;
		}

		return ans;
	}

	public static void printArray(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = { 6, 7, 1, 2, 3, 4 };
		// {1, 2, 4,1,2,1,2,4,1,2};
		int length = a.length;
		// [1,2,3,1,2,3]
		int aa[] = new int[2 * length];
		int j = 0;
		// copy first set
		for (int i = 0; i < a.length; i++) {
			aa[i] = a[i];
		}
		/// replicate 2nd
		for (int i = length; i < aa.length; i++) {
			aa[i] = a[i - length];
		}

		printArray(a);

		int ans[] = new int[length];

		for (int i = 0; i < length; i++) {
			j = getNextmax(i, length - 1, aa);
			ans[i] = j;
		}

		printArray(ans);
		// System.out.println(ans);
	}
}
