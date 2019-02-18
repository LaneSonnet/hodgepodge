package com.mudfish.netty.client;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.constants.MessageType;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcRequest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatHandler extends SimpleChannelInboundHandler<MudfishMessage> {

	private static final Logger logger = LoggerFactory.getLogger(HeartBeatHandler.class);

	private volatile ScheduledFuture<?> heartBeat;

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (heartBeat != null) {
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MudfishMessage request) throws Exception {
		ctx.fireChannelRead(request);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		heartBeat = ctx.executor().scheduleAtFixedRate(
				new HeartBeatTask(ctx), 0, 30000,
				TimeUnit.MILLISECONDS);
	}

	private class HeartBeatTask implements Runnable {

		private final ChannelHandlerContext ctx;

		public HeartBeatTask(final ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		public void run() {
			MudfishRpcRequest request = new MudfishRpcRequest();
			MudfishMessage message = new MudfishMessage(MessageType.HEART_BEAT, request);
			logger.info("Client send heart beat messsage to server : ---> 【{}】", message);
			ctx.writeAndFlush(message);
		}
	}
}


