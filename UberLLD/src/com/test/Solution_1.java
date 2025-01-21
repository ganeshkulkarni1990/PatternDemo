package com.test;

import java.util.*;

class Node1
{
	int data;
	Node1 left;
	Node1 right;
	
	Node1(int data)
	{
		this.data = data;
		left = null;
		right = null;
	}
}

class Tree
{
	Node1 root;
	
	Tree(Node1 n)
	{
		this.root = n;
	}
}

public class Solution_1
{	
	public static long getMaxContiguousSum(List<Integer> list)
	{
		long sum = 0;
		long curr_sum = 0;
		
		//do the sum till number is ++v
		int i=0,length = list.size();
		while(i<length)
		{
			while(i<length && list.get(i)>0)
			{
				curr_sum += list.get(i);
				i++;
			}
			//update the sum
			sum = Math.max(sum,curr_sum);
			curr_sum = 0;
			i++;
		}
		return sum;
	}

	public static int leastValue = Integer.MAX_VALUE;
	public static void topView(Node1 root,int value, Map<Integer,Integer> map)
	{
		if(root==null)
			return;
		
		//add value to map if first seen
		if(!map.containsKey(value))
		{
			map.put(value, root.data);
			//update the least
			leastValue = Math.min(leastValue, value);
		}
		
		//visit left
		if(root.left!=null)
			topView(root.left, value-1,map);
		//visit right node
		if(root.right!=null)
			topView(root.left, value+1,map);
		
	}
	public static void printMap(Map<Integer, Integer> map)
	{
		//System.out.println(leastValue);
		
		Set<Integer> set = map.keySet();
		
		for(Integer i: set)
		{
			System.out.println(i+"   "+map.get(i));
		}
		System.out.println();
	}
	
	/*
	 *       10
	 *     9    8  
	     7  6     
	   */
	public static void main(String[] args) {
	{
		Node1 root = new Node1(10);
		//Tree t = new Tree(root);
		root.left = new Node1(9);
		root.right = new Node1(7);
		root.left.left = new Node1(6);
		
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		topView(root,0,map);
		
		printMap(map);
	}
}
//input ["root/f1/sf1/file1(abcd) file2(xyz) file3(lmnop)","root/f1/sf2/file4(trdf) file5(abcd)", "root/f2/sf1/file1(abcd) file21(qwert)", "root/file5(lmnop)"]

}