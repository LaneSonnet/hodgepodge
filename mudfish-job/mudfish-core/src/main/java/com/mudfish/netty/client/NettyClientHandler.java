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

public class NettyClientHandler extends SimpleChannelInboundHandler<MudfishMessage> {

	private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

	private volatile ScheduledFuture<?> heartBeat;

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("mudfish client caught exception", cause);
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MudfishMessage request) throws Exception {
		logger.debug("mudfish client accept params【{}】", request);
	}
}


