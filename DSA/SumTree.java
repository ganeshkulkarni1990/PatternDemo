import java.util.LinkedList;
import java.util.Queue;

/*
 * 
 *   5 left side (sum - root.data)
   /   \
  2    8
 / \   / \
1  4 6   9


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


class Tree1
{
	Node1 root;
	Tree1(Node1 root)
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
	
	boolean sumPath(Node1 root,int sum)
	{
		if(root==null)
			return false;
		
		//check if(sum) matches root data
		if(sum == root.data)
			return true;
		
		boolean left = sumPath(root.left,sum-root.data);
		boolean right  = sumPath(root.right,sum-root.data);
		return left || right;
	}
	
	void bfs(Node1 root)
	{
		if(root==null)
			return;
		
		Queue<Node1> q = new LinkedList<Node1>();
		
		q.add(root);
		
		while(!q.isEmpty())
		{
			int size = q.size();
			//add all the childs of the level
			for(int i=0;i<size;i++)
			{
				Node1 tmp=q.poll();
				if(tmp.left!=null)
					q.add(tmp.left);
				if(tmp.right!=null)
					q.add(tmp.right);
		
				if(i==0 || i==size-1)
				{
					System.out.print(tmp.data+" ");
				}	
			}
		}
	}
}
public class SumTree {

	public static void main(String[] args) {
		Node1 root = new Node1(5);
		Tree1 t = new Tree1(root);
		root.left = new Node1(2);
		root.right  = new Node1(8);
		
		root.left.left = new Node1(1);
		root.left.right = new Node1(4);
		
		root.right.left = new Node1(6);
		root.right.right = new Node1(9);
		
		//t.printTree(root);
		
		System.out.println();
		
		t.bfs(root);
		
		//Node1 root1 = t.mirrorTree(root);
		
		//t.printTree(root1);
		
		//System.out.println();
		
		//System.out.println(t.sumPath(root,-1));
	}
	
}
