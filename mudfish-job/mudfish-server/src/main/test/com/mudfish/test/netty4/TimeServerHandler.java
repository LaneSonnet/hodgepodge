package com.mudfish.test.netty4;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] bytes = new byte[buf.readableBytes()];
//		buf.readBytes(bytes);
//		String body = new String(bytes, "UTF-8");
		String body = (String) msg;
		System.out.println("接收到字符串：" + body);

		String currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "BAD"
				+ " ORDER";
		ByteBuf resp = Unpooled.copiedBuffer((currentTime + System.getProperty("line.separator")).getBytes());
		ctx.write(resp);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause);
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("---读取完成---");
		ctx.flush();
	}
}
