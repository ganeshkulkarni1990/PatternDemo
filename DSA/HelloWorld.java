package r_3;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class HelloWorld {
	public static String decodedString(String s) {
		String output = "";
		Stack<String> st = new Stack<>();
		int i = 0;
		while (i < s.length()) {
			//System.out.println(st);
			System.out.println("Output:" + output);
			// get the number and push to stack
			String number = "";
			while (i < s.length() && (int) s.charAt(i) >= 48 && (int) s.charAt(i) <= 57) {
				number += s.charAt(i);
				i++;
			}
			// push the number to stack
			if (!number.equals("")) {
				System.out.println("Number:" + number);
				st.push(number);
			}

			// System.out.println(st);
			// check for openning
			if (s.charAt(i) == '[') {
				st.push(s.charAt(i) + "");
				//System.out.println("index:" + i);
			}
			// check for closing
			else if (s.charAt(i) == ']') {
				String ss = "";
				if (!st.isEmpty()) {
					ss = st.pop();
					System.out.println("ss:" + ss);
				} else {
					System.out.println("Wrong input");
					return "";
				}
				// pop the opening brakcet
				if (!st.isEmpty()) {
					st.pop();
				}

				String numberS = "";
				// get the number
				if (!st.isEmpty()) {
					numberS = st.pop();
				} else {
					return "";
				}
				int num = Integer.parseInt(numberS);
				String r = "";
				for (int j = 0; j < num; j++) {
					r += ss;
				}
				output += r;
				if(!st.isEmpty()){
					String newString = st.pop();
					if(!newString.equals("[")){
						newString += r;
						st.push(newString);
					}
				}
			} else {
				// for normal String append to output
				// if st is not empty appned to the stack top
				if (st.isEmpty()) {
					//System.out.println("char:" + i + " " + s.charAt(i));
					output += s.charAt(i);
				} else {
					if (st.peek().equals("[")) {
						st.push("" + s.charAt(i));
					} else {
						String top = st.pop();
						top += s.charAt(i);
						st.push(top);
					}
				}
			}
			i++;
		}
		return new String(output);
	}

	public static void main(String[] args) {
		String s = "ab2[cd]2[de3[f]g]";
		// System.out.println((int)'9');
		String output = decodedString(s);
		System.out.println(output);
	}
}