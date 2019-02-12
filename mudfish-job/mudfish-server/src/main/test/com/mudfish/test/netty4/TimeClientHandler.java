package com.mudfish.test.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		byte[] bytes = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
		for (int i = 0; i < 100 ;  i++) {
			ByteBuf buffer = Unpooled.buffer(bytes.length);
			buffer.writeBytes(bytes);
			ctx.writeAndFlush(buffer);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] bytes = new byte[buf.readableBytes()];
//		buf.readBytes(bytes);
//		String body = new String(bytes, "UTF-8");
		String body = (String) msg;
		System.out.println("客户端接收字符串：" + body);

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause);
		ctx.close();
	}
}
