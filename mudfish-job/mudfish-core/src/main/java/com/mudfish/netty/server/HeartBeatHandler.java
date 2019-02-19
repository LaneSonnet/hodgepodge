package com.mudfish.netty.server;

import org.slf4j.Logger;

import com.mudfish.constants.MessageType;
import com.mudfish.factory.RpcReflectInvokeFactory;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcRequest;
import com.mudfish.struct.MudfishRpcResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatHandler extends SimpleChannelInboundHandler<MudfishMessage> {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(HeartBeatHandler.class);

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, final MudfishMessage request) throws Exception {
        if (MessageType.HEART_BEAT == request.getHeader().getType()) {
            logger.debug("Mudfish server accept heart beat --> 【{}】", request);
        } else {
            ctx.fireChannelRead(request);
        }
    }


}
