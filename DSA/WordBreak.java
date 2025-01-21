package r_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a string s and a dictionary of strings wordDict, 
//add spaces in s to construct a sentence where each word is a valid dictionary word. 
//Return all such possible sentences in any order.
//Note that the same word in the dictionary may be reused multiple times in the segmentation.
//

class TrieNode {
	Map<Character, TrieNode> map;
	boolean isEnd;

	TrieNode() {
		map = new HashMap<>();
		isEnd = false;
	}

	boolean containsKey(char c) {
		if (map.containsKey(c)) {
			return true;
		}
		return false;
	}

	TrieNode getNode(char c) {
		return map.get(c);
	}

	void insert(char c) {
		map.put(c, new TrieNode());
	}
}

class TrieT {
	TrieNode head;

	TrieT() {
		head = new TrieNode();
	}

	void _insert(TrieNode h, String s, int i) {
		if (i >= s.length()) {
			h.isEnd = true;
			return;
		}
		if (!h.containsKey(s.charAt(i))) {
			h.insert(s.charAt(i));
		}
		_insert(h.getNode(s.charAt(i)), s, i + 1);
	}

	void insert(String s) {
		TrieNode h = head;
		_insert(h, s, 0);
	}

	boolean _present(TrieNode h, String s, int i) {
		if (i >= s.length()) {
			return h.isEnd;
		}
		if (h.containsKey(s.charAt(i))) {
			return _present(h.getNode(s.charAt(i)), s, i + 1);
		} else {
			return false;
		}
	}

	boolean isPresent(String s) {
		TrieNode h = head;
		return _present(h, s, 0);
	}

	TrieNode getHead() {
		return this.head;
	}

	void print(TrieNode n, String s) {
		if (n == null) {
			return;
		}
		if (n.isEnd) {
			System.out.println(s);
		}
		Map<Character, TrieNode> map = n.map;
		for (Map.Entry<Character, TrieNode> entry : map.entrySet()) {
			print(entry.getValue(), s + entry.getKey());
		}
	}

	void printDict() {
		TrieNode node = head;
		print(node, "");
	}
}

public class WordBreak {
	static void ans(TrieT t, TrieNode head, String s, int i, String ans, List<String> list) {
		// System.out.println("String: " + s + " i:" + i);
		if (i >= s.length()) {
			if (head != null && head.isEnd) {
				ans = ans + " " + s;
				ans = ans.trim();
				// System.out.println("String ans:" + ans);
				list.add(ans);
			}
			return;
		}
		if (head == null) {
			return;
		}
		if (head.isEnd) {
			String anss = ans + " " + s.substring(0, i);
			;
			// System.out.println("ans: " +ans);
			ans(t, t.getHead(), s.substring(i), 0, anss, list);
		}
		ans(t, head.getNode(s.charAt(i)), s, i + 1, ans, list);
	}

	public static List<String> wordBreak(String s, List<String> wordDict) {
		TrieT t = new TrieT();
		for (String ss : wordDict) {
			t.insert(ss);
		}
		t.printDict();
		TrieNode h = t.getHead();
		List<String> list = new ArrayList<>();
		ans(t, h, s, 0, "", list);
		// System.out.println(list);
		return list;
	}

	public static void main(String... args) {
		TrieT t = new TrieT();
		List<String> ans = wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
		System.out.println(ans);
	}
}