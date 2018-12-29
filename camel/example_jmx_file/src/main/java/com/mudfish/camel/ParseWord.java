package com.mudfish.camel;

/**
 * Created by gyrx-dskf15 on 2018/11/6.
 */
public class ParseWord {

	public String prase(String src) {
		String[] strArr = src.split(" ");
		StringBuffer buf = new StringBuffer();
		for (String str :
				strArr) {
			buf.append(str);
			buf.append("::");
		}
		return buf.toString();
	}

	public String prase2(String src) {
		String[] strArr = src.split(" ");
		StringBuffer buf = new StringBuffer();
		for (String str :
				strArr) {
			buf.append(str);
			buf.append(":22:");
		}
		return buf.toString();
	}
}
