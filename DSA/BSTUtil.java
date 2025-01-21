package r_3;

import java.util.Arrays;

class Node {
	int data;
	Node left, right;

	Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

class Tree {
	Node root;

	Node getRoot() {
		return this.root;
	}

	Tree(Node root) {
		this.root = root;
	}

	Node insert(Node root, int data) {
		if (root == null) {
			root = new Node(data);
			return root;
		}
		if (root.data > data)
			root.left = insert(root.left, data);
		else
			root.right = insert(root.right, data);
		return root;

	}

	void printInOrder(Node root) {
		if (root == null)
			return;
		printInOrder(root.left);
		System.out.print(root.data + " ");
		printInOrder(root.right);
	}

	void printPreOrder(Node root) {
		if (root == null)
			return;
		System.out.print(root.data + " ");
		printInOrder(root.left);
		printInOrder(root.right);
	}

}

public class BSTUtil {

	static int index = 0;

	public static int find(int data, int a[], int start, int end) {
		// System.out.println(data+" "+start+" "+end);
		while (start <= end) {
			int mid = (start + end) / 2;
			// System.out.println(data+" "+start+" "+mid+" "+end);
			if (a[mid] == data) {
				// System.out.println(data+" "+start+" "+mid+" "+end);
				return mid;
			}
			if (a[mid] > data)
				end = mid - 1;
			else
				start = mid + 1;
		}
		return -1;

	}

	public static Node creatTree(int[] pre, int[] in, int start, int end, Node r) {
		if (index == pre.length || start > end) {
			return null;
		}

		int i = find(pre[index], in, start, end);
		// System.out.println(pre[index]+" "+start+" "+end+" "+i);
		r = new Node(in[i]);
		index++;
		r.left = creatTree(pre, in, start, i - 1, r.left);
		// System.out.println("after:"+index);
		r.right = creatTree(pre, in, i + 1, end, r.right);
		return r;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Tree t = new Tree(new Node(10)); Node root = t.getRoot();
		 * t.insert(root,5); t.insert(root,1); t.insert(root,7);
		 * t.insert(root,40); t.insert(root,50);
		 * 
		 * //t.printInOrder(root); System.out.println();
		 */

		int pre[] = { 10, 5, 1, 7, 40, 50 }; // 1 5 7 10 40 50
		int in[] = new int[pre.length];
		for (int i = 0; i < pre.length; i++) {
			in[i] = pre[i];
		}
		Arrays.sort(in);
		Node r = null;
		// System.out.println(in[0]);
		index = 0;
		Node rr = creatTree(pre, in, 0, pre.length, r);
		Tree tt = new Tree(rr);
		tt.printPreOrder(tt.getRoot());

	}

}
