

/*
 * 
 * Array [0,-1,2,-3,1] --> sum = -2
 * pair in given array which sum to given number
 * 2 loops pair --> that sum is SUM then return true;
 *end of the loop return false;
 *O(n2)
 *HashMap number when new elemet comes SUM-currNumbr is present in HashMap print the number
 *else -1 errorcode
 *
 */


public class SumHash {

	public static void printNumbers(int a[],int sum)
	{
		int l = a.length;
		for(int i=0;i<l;i++)
		{
			for(int j=i+1;j<l;j++)
			{
				if(a[i]+a[j]==sum)
				{
					System.out.println(a[i]+" "+a[j]);
					return;
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int []a = {0,-1,2,-3,1};
		int sum = -2;
		
		printNumbers(a,sum);
	}

}


/* hashMap 

<key,value> --> get map(key) --> value;

table fix size 

key --> value
Hash(key) --> number % size of table -->10

0
 1  --> (key,value) -> (key,value) -> (key,value) 
10

n key added to hashmap--> n+1 --> resize --> re hash n , n+1 
failFast and FailSafe

*/


