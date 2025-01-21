package r_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

interface Iterator_ {
	public boolean hashNext();

	public Trie next();
}

class Trie {
	Map<Character, Trie> node;
	boolean isEnd;

	public Trie() {
		node = new HashMap<>();
	}

	public Map<Character, Trie> newNode() {
		return new HashMap<Character, Trie>();
	}

	public boolean containsKey(char s) {
		if (node.containsKey(s))
			return true;
		return false;
	}

	public void add(char s) {
		node.put(s, new Trie());
	}

	public Trie get(char s) {
		return node.get(s);
	}

	public TrieIterator getIterator() {
		return new TrieIterator();
	}

	private class TrieIterator implements Iterator_ {
		Set<Character> set;
		Iterator<Character> itr;

		TrieIterator() {
			set = node.keySet();
			itr = set.iterator();
		}

		public boolean hashNext() {
			if (itr.hasNext())
				return true;
			return false;
		}

		public Trie next() {
			if (itr.hasNext())
				return node.get(itr.next());
			return null;
		}
	}
}

public class TrieDemo {

	public static void add(Trie trie, String s, int i) {
		if (i >= s.length()) {
			trie.isEnd = true;
			return;
		}

		if (!trie.containsKey(s.charAt(i))) {
			trie.add(s.charAt(i));
		}
		Trie t = trie.get(s.charAt(i));
		add(t, s, i + 1);
	}

	public static boolean findWord(Trie root, String s) {
		Trie t = root;
		for (int i = 0; i < s.length(); i++) {
			if (!t.containsKey(s.charAt(i))) {
				return false;
			}
			t = t.get(s.charAt(i));
		}
		return t.isEnd;
	}

	public static void createPrefixList(Trie t, String s, List<String> ans) {
		Map<Character, Trie> map = t.node;
		for (Map.Entry<Character, Trie> entry : map.entrySet()) {
			Character c = entry.getKey();
			Trie tt = t.get(c);
			if (tt.isEnd)
				ans.add(s + c);
			createPrefixList(tt, s + c, ans);
		}
	}

	public static List<String> getPrefixList(Trie root, String prefix) {
		Trie t = root;
		for (int i = 0; i < prefix.length(); i++) {
			if (!t.containsKey(prefix.charAt(i))) {
				return null;
			}
			t = t.get(prefix.charAt(i));
		}
		List<String> ans = new ArrayList<String>();
		if (t.isEnd)
			ans.add(prefix);
		createPrefixList(t, prefix, ans);
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub\\

		String wordList = "agon ahahaha haha Hellow Ganesh here new thing world has seen again hah nothing worry is about";
		Trie root = new Trie();
		
		String[] words = wordList.split(" ");
		for (String word : words) {
			word = word.toLowerCase();
			add(root, word, 0);
		}

		// System.out.println(findWord(root,"agon"));
		System.out.println(getPrefixList(root, "a"));
	}

}
