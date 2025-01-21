package com.test;
import java.util.*;

public class TestSolution {

	static List<String> removeDuplicate(List<String> list)
	{
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		List<String> ans =  new ArrayList<String>();
		for(String s:list)
		{
			if(!map.containsKey(s))
			{
				map.put(s, true);
				ans.add(s);
			}
		}
		return ans;
		
	}
	static int findOfWords(String s)
	{
		return s.split(" ").length;
	}
	static boolean isOdd(int number)
	{
		if((number & 1) == 1)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "Test";
		String ss = null;
		System.out.println(ss.equals(s));
	}

}
