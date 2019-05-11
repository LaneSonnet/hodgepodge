package com.mudfish.common.classLoad;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mudfish on 2019/4/1 0001.
 */
public class Child extends Father{

	static {
		System.out.println("child static block");
	}


	{
		System.out.println("child code block");
	}

	public Child() {
		System.out.println("child constructor");
	}

	public static void main(String[] args) {
		List<String> chars = Arrays.asList("a", "b", "c", "e", "z");
		List<String> charS = Arrays.asList("A", "B", "C", "D", "Z");
		List<String> nums = Arrays.asList("0", "1", "2", "3", "9");
		List<String> numS = Arrays.asList("1", "2", "3", "4", "0");
		String test = "";
		char[] chars1 = test.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars1.length; i++) {
			char c = chars1[i];
			if (chars.contains(c + "")) {
				int i1 = chars.indexOf(c + "") + 1;
				if (i1 == chars.size()) {
					i1 = 0;
				}
				sb.append(charS.get(i1));
			} else if (charS.contains(c + "")) {
				int i1 = charS.indexOf(c + "") + 1;
				if (i1 == charS.size()) {
					i1 = 0;
				}
				sb.append(chars.get(i1));
			}
		}
	}



}
