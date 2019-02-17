package com.mudfish.netty.codec;

import java.util.List;

import com.mudfish.struct.Header;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.util.ProtostuffSerializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class NettyDecoder extends ByteToMessageDecoder {

	private Class<?> genericClass;

	public NettyDecoder(Class<?> genericClass) {
		this.genericClass = genericClass;
	}

	@Override
	public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 8) {
			return;
		}
		in.markReaderIndex();
		int messageType = in.readInt();
		int dataLength = in.readInt();
		if (messageType < 0 || dataLength < 0) {
			ctx.close();
		}
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
			return;    // fix 1024k buffer splice limix
		}
		Header header = new Header();
		header.setType(messageType);
		header.setLength(dataLength);
		MudfishMessage message = new MudfishMessage();
		message.setHeader(header);

		byte[] data = new byte[dataLength];
		in.readBytes(data);
		Object obj = ProtostuffSerializer.deserialize(data, genericClass);
		message.setBody(obj);
		out.add(message);
	}
}
