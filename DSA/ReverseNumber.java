import java.util.Scanner;

/*Integer is given 1234 --> 4321
//first approach 2 pointer again integer
//1234
j = 1
sum = 4*10 + 3 = 4320+1 4321
n = 0
*/
public class ReverseNumber {

	public static int getReverseNumber(int num)
	{
		int i = 10, sum=0;
		boolean isNegavtive = false;
		if(num<0)
		{
			isNegavtive = true;
			num *= -1;
		}
		//mod of the number 
		
		while(num>0)
		{
			int j = num%10;
			sum = (sum*i) + j;
			num = num/10;
		}
		
		if(isNegavtive)
			return sum * -1;
		
		return sum;
	}
	
	public static void main(String...args)
	{
		Scanner s = new Scanner(System.in);
		for(int i=0;i<10;i++)
		{
			int num = s.nextInt();
			System.out.println(getReverseNumber(num));
		}
		s.close();
	}
}
