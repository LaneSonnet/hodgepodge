package com.mudfish.common.classLoad;

import java.util.Arrays;

/**
 * Created by Mudfish on 2019/4/1 0001.
 */
public class Father {

	static {
		System.out.println("father static block");
	}


	{
		System.out.println("father code block");
	}

	public Father() {
		System.out.println("father constructor");
	}

	public static void main(String[] args) {
		int[] arr = {2,3,1,9,11,1,5,4,22};
		System.out.println(Arrays.toString(sort(arr)));
		System.out.println(Arrays.toString(arr));
	}

	public static int[] sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length -  i -1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
		return arr;
	}

}
