
/*
 * 
 *   5(11) left side (sum - root.data)
   /   \
  2(6)     8(7)
 / \   / \
1(5)4(4) 6   9

Output:
     5
   /   \
  8     2
 / \   / \
9   6 4   1
     
number 8;
sum = 9

    9 8 6 5 4 2 1 
    leftsbubTree
    reighsubTree
    root.left = right;
    root.right = left;
    return root;
 *
 */

class Node1
{
	int data;
	Node1 left,right;
	Node1(int data)
	{
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

class Tree
{
	Node1 root;
	Tree(Node1 root)
	{
		this.root = root;
	}
	void printTree(Node1 root)
	{
		if(root==null)
			return;
		
		//VLR preorder
		printTree(root.left);
		System.out.print(root.data+" ");
		printTree(root.right);
	}
	
	Node1 mirrorTree(Node1 root)
	{
		if(root==null)
			return null;
		
		Node1 left = mirrorTree(root.left);
		Node1 right  = mirrorTree(root.right);
		root.left = right;
		root.right = left;
		
		return root;
	}
	
	
}
public class MirrorTree {

	public static void main(String[] args) {
		Node1 root = new Node1(5);
		Tree t = new Tree(root);
		root.left = new Node1(2);
		root.right  = new Node1(8);
		
		root.left.left = new Node1(1);
		root.left.right = new Node1(4);
		
		root.right.left = new Node1(6);
		root.right.right = new Node1(9);
		
		t.printTree(root);
		
		System.out.println();
		
		Node1 root1 = t.mirrorTree(root);
		
		t.printTree(root1);
		
		System.out.println();
	}
	

}
