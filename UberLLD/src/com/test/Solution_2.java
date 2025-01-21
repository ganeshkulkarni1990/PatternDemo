package com.test;

class Node
{
	int data;
	Node next;
	Node(int data)
	{
		this.data = data;
		next = null;
	}
}

// 1 8 7
// 5 9
// 2 8 6
public class Solution_2 {

	static Node head3;
	static int carry = 0;
	static void getSumList(Node head1, Node head2)
	{
		
		if(head1.next!=null && head2.next!=null)
			getSumList(head1.next,head2.next);
		if(head1.next!=null)
			getSumList(head1.next,head2);
		if(head2.next!=null)
			getSumList(head1,head2.next);
		
		System.out.println("head1:"+head1.data+"head2:"+head2.data);
		
		int ans = head1.data + head2.data + carry;
		if(ans > 10){
			carry = ans /10;
			ans = ans % 10;
		}
		else
			carry = 0;
		
		if(head3==null)
		{
			head3 = new Node(ans);
		}
		else
		{
			Node tmp = new Node(ans);
			tmp.next = head3;
			head3 = tmp;
		}
		
	}
	static void addNode(Node head,int data)
	{
		while(head.next!=null)
		{
			head = head.next;
		}
		head.next = new Node(data);
	}
	
	static void printList(Node head)
	{
		while(head!=null)
		{
			System.out.print(head.data+" ");
			head = head.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node head1 = new Node(1);
		addNode(head1,8);
		addNode(head1,7);
		Node head2 = new Node(5);
		addNode(head2,9);
		getSumList(head1,head2);
		printList(head3);
		
	}

}
