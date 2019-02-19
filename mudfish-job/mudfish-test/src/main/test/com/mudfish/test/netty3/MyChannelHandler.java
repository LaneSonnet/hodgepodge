//package com.mudfish.test;
//
//import org.jboss.netty.buffer.ChannelBuffer;
//import org.jboss.netty.buffer.ChannelBuffers;
//import org.jboss.netty.channel.Channel;
//import org.jboss.netty.channel.ChannelFuture;
//import org.jboss.netty.channel.ChannelFutureListener;
//import org.jboss.netty.channel.ChannelHandlerContext;
//import org.jboss.netty.channel.ChannelStateEvent;
//import org.jboss.netty.channel.ExceptionEvent;
//import org.jboss.netty.channel.MessageEvent;
//import org.jboss.netty.channel.SimpleChannelHandler;
//
//public class MyChannelHandler extends SimpleChannelHandler {
//
//	@Override
//	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//		System.out.println("channelConnected running-------" + e.getState());
//		Channel channel = e.getChannel();
//		ChannelBuffer time = ChannelBuffers.buffer(4);
//		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//		ChannelFuture future = channel.write(time);
//		future.addListener(new ChannelFutureListener() {
//			public void operationComplete(ChannelFuture future) throws Exception {
//				future.getChannel().close();
//			}
//		});
//		super.channelConnected(ctx, e);
//	}
//
//	@Override
//	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//		System.out.println("messageReceived running-------" + e.getMessage());
//		Channel channel = e.getChannel();
//		channel.write("server: " + e.getMessage());
//		super.messageReceived(ctx, e);
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
//		System.out.println("exceptionCaught running-------" + e.getCause());
//		e.getChannel().close();
//		super.exceptionCaught(ctx, e);
//	}
//
//	@Override
//	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//		System.out.println("channelDisconnected running-------" + e.getState());
//		super.channelDisconnected(ctx, e);
//	}
//
//	@Override
//	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//		System.out.println("channelClosed running-------" + e.getState());
//		super.channelClosed(ctx, e);
//	}
//}
