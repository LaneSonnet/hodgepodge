package com.mudfish.netty.client;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.netty.codec.NettyDecoder;
import com.mudfish.netty.codec.NettyEncoder;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcResponse;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public class NettyClient {

	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
	private Channel channel;
	private NioEventLoopGroup group;
	private int connectTimes = 0;
	private Executor executor;
	private String host;
	private int port;

	public NettyClient(Executor executor, String host, int port) {
		this.executor = executor;
		this.host = host;
		this.port = port;
	}

	public void connect() {
		try {
			group = new NioEventLoopGroup();
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline()
									.addLast(new NettyEncoder())
									.addLast(new NettyDecoder(MudfishRpcResponse.class))
									.addLast(new NettyClientHandler());
						}
					})
					.option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.SO_REUSEADDR, true)
					.option(ChannelOption.SO_KEEPALIVE, true);
			this.channel = bootstrap.connect(host, port).sync().channel();
			logger.info("***********MUDFISH CLIENT CONNECT SUCCESS, HOST【{}】PORT【{}】**********", host, port);
			//TODO 更新数据库状态
			channel.closeFuture().sync();
		} catch (Exception e) {
			logger.debug("mudfish client connect exception：", e);
		} finally {
			//TODO 更新数据库状态
			if (connectTimes++ > 3) {
				channel.close();
				group.shutdownGracefully();
				return;
			}
			try {
				TimeUnit.SECONDS.sleep(3);
				logger.info("mudfish client reconnect...");
				connect();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				connect();
			}
		});
	}

	public void send(MudfishMessage message) throws InterruptedException {
		this.channel.writeAndFlush(message);
	}
}
