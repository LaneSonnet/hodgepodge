package com.mudfish.netty.server;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;

import com.mudfish.constants.MessageType;
import com.mudfish.factory.RpcInvokeFactory;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcRequest;
import com.mudfish.struct.MudfishRpcResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<MudfishMessage> {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(NettyServerHandler.class);

	private ThreadPoolExecutor executor;
	private RpcInvokeFactory invokeFactory;

	public NettyServerHandler(ThreadPoolExecutor executor, RpcInvokeFactory invokeFactory) {
		this.executor = executor;
		this.invokeFactory = invokeFactory;
	}

	@Override
	public void channelRead0(final ChannelHandlerContext ctx, final MudfishMessage request) throws Exception {
		logger.debug("mudfish server accept params【{}】", request);
		try {
			executor.execute(new Runnable() {
				public void run() {
					MudfishMessage responseMessage = invokeFactory.invokeService(request);
					ctx.writeAndFlush(responseMessage);
				}
			});
		} catch (Exception e) {
			MudfishRpcResponse response = new MudfishRpcResponse();
			response.setRequestId(((MudfishRpcRequest) request.getBody()).getRequestId());
			response.setErrorMsg(e.getMessage());
			MudfishMessage responseMessage = new MudfishMessage(MessageType.NORMAL, response);
			ctx.writeAndFlush(responseMessage);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.error("mudfish server catch exception【{}】", cause);
		ctx.close();
	}
}
