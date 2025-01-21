
class StackEmptyException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	StackEmptyException()
	{
		System.out.println("stack is empty");
	}
}

class Node
{
	int data;
	Node next;
	Node(int data)
	{
		this.data = data;
		this.next = null;
	}
	
	static Node newNode(int data)
	{
		return new Node(data);
	}
}

class st
{
	Node top;
	
	st()
	{
		top = null;
	}
	
	Node getTop()
	{
		return top;
	}
	
	void push(int data)
	{
		if(top==null)
			top = new Node(data);
		else
		{
			Node tmp =  new Node(data);
			tmp.next = top;
			top = tmp;
		}
	}
	
	int pop()throws Exception
	{
		
		if(top==null)
			throw new StackEmptyException();
		
		Node tmp = top;
		top = top.next;
		int data = tmp.data;
		//tmp = null;
		return data;
	}
			
}
public class Stack {

	public static void main(String[] args) {
		
		try{
			st s = new st();
			s.push(10);
			s.push(20);
			s.push(30);
			
			System.out.println(s.pop());
			System.out.println(s.pop());
			System.out.println(s.pop());
			System.out.println(s.pop());
			System.out.println(s.pop());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}


