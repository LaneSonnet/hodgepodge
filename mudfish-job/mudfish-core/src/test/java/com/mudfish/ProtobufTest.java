package com.mudfish;

import com.mudfish.struct.Header;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.util.ProtostuffSerializer;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public class ProtobufTest {

	public static void main(String[] args) {
		MudfishMessage message = new MudfishMessage();
		Header header = new Header();
		header.setLength(22);
		header.setType(2);
		message.setHeader(header);
		message.setBody("I am body");
		System.out.println(message);
		byte[] bytes = ProtostuffSerializer.serialize(message);
		System.out.println(bytes.length);
		MudfishMessage message1 = (MudfishMessage) ProtostuffSerializer.deserialize(bytes, MudfishMessage.class);
		System.out.println(message1);
	}

}
