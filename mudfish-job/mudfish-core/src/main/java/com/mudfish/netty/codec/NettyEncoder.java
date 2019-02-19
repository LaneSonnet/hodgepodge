package com.mudfish.netty.codec;

import com.mudfish.struct.MudfishMessage;
import com.mudfish.util.ProtostuffSerializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder<MudfishMessage> {

	@Override
	public void encode(ChannelHandlerContext ctx, MudfishMessage message, ByteBuf out) throws Exception {
		out.writeInt(message.getHeader().getType());
		byte[] data = ProtostuffSerializer.serialize(message.getBody());
		out.writeInt(data.length);
		out.writeBytes(data);
	}
}