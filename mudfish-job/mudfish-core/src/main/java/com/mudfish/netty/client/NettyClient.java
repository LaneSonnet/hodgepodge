package com.mudfish.netty.client;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.common.constants.RpcServerConstant;
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
	private volatile Channel channel;
	private NioEventLoopGroup group;
	private int connectTimes = 0;
	private volatile Thread thread;
	private volatile String host;
	private volatile int port;
	private String id;

	public NettyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void connect() {
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline()
									.addLast(new NettyEncoder())
									.addLast(new NettyDecoder(MudfishRpcResponse.class))
									.addLast(new HeartBeatHandler())
									.addLast(new NettyClientHandler());
						}
					})
					.option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.SO_REUSEADDR, true)
					.option(ChannelOption.SO_KEEPALIVE, true);
			this.channel = bootstrap.connect(host, port).sync().channel();
			logger.info("***********MUDFISH CLIENT CONNECT SUCCESS, HOST【{}】PORT【{}】**********", host, port);
			connectTimes = 0;
			//TODO 更新数据库状态
			channel.closeFuture().sync();
		} catch (Exception e) {
			logger.debug("mudfish client connect exception：", e);
		} finally {
			//TODO 更新数据库状态
			if (connectTimes++ > 3) {
				if (channel != null) {
					channel.close();
				}
				group.shutdownGracefully();
			} else {
				try {
					TimeUnit.SECONDS.sleep(RpcServerConstant.CLIENT_RECONNECT_INTERVAL_TIME);
					logger.info("mudfish client reconnect...");
					connect();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void start() {
		thread = new Thread(new Runnable() {
			public void run() {
				group = new NioEventLoopGroup();
				connect();
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	public void close() {
		if (this.channel!=null && this.channel.isActive()) {
			this.channel.close();		// if this.channel.isOpen()
		}
		if (this.group!=null && !this.group.isShutdown()) {
			this.group.shutdownGracefully();
		}
	}

	public void send(MudfishMessage message) throws InterruptedException {
		this.channel.writeAndFlush(message);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Thread getThread() {
		return thread;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
}
