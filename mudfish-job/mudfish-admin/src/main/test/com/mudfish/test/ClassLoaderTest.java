package com.mudfish.test;

import java.io.File;
import java.net.URL;

/**
 * Created by Mudfish on 2019/2/23 0023.
 */
public class ClassLoaderTest {

	public static void main(String[] args) {
		URL resource = ClassLoaderTest.class.getClassLoader().getResource("com/mudfish");
		String path = resource.getPath();
		System.out.println(path);
		File file = new File(path);
		File[] files = file.listFiles();
		for (File file1 : files) {
			System.out.println(file1.getName());
		}


	}


}
