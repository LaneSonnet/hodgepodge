package com.mudfish.netty.server;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.factory.RpcInvokeFactory;
import com.mudfish.netty.codec.NettyDecoder;
import com.mudfish.netty.codec.NettyEncoder;
import com.mudfish.struct.MudfishRpcRequest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
	private Thread thread;
	private RpcInvokeFactory invokeFactory;

	public NettyServer(RpcInvokeFactory invokeFactory) {
		this.invokeFactory = invokeFactory;
	}

	public void start(final int port) {

		thread = new Thread(new Runnable() {
			public void run() {
				EventLoopGroup bossGroup = new NioEventLoopGroup();
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				final ThreadPoolExecutor serverHandlerPool = new ThreadPoolExecutor(
						60,
						300,
						60L,
						TimeUnit.SECONDS,
						new LinkedBlockingQueue<Runnable>()
				);
				try {
					// start server
					ServerBootstrap bootstrap = new ServerBootstrap();
					bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
							.childHandler(new ChannelInitializer<SocketChannel>() {
								@Override
								public void initChannel(SocketChannel channel) throws Exception {
									channel.pipeline()
											.addLast(new NettyDecoder(MudfishRpcRequest.class))
											.addLast(new NettyEncoder())
											.addLast(new HeartBeatHandler())
											.addLast(new NettyServerHandler(serverHandlerPool, invokeFactory));
								}
							})
							.option(ChannelOption.SO_TIMEOUT, 100)
							.option(ChannelOption.SO_BACKLOG, 128)
							.option(ChannelOption.TCP_NODELAY, true)
							.option(ChannelOption.SO_REUSEADDR, true)
							.childOption(ChannelOption.SO_KEEPALIVE, true);
					ChannelFuture future = bootstrap.bind(port).sync();
					printStartFlag(port);
					future.channel().closeFuture().sync();
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						logger.info("mudfish server stop by interrupted");
					} else {
						logger.error("mudfish server occur error：", e);
					}
				} finally {
					try {
						serverHandlerPool.shutdown();
						workerGroup.shutdownGracefully();
						bossGroup.shutdownGracefully();
					} catch (Exception e) {
						logger.error("mudfish server close threadpool occur exception：", e);
					}
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	public void stop() {
		if (thread != null && thread.isAlive()) {
			thread.interrupt();
		}
		logger.info("mudfish server destroy success.");
	}

	public void printStartFlag(int port) {
		logger.info(System.lineSeparator() + "************************************************************************"
						+ System.lineSeparator() + "************MUDFISH SERVER START SUCCESS, PORT = 【{}】**************"
						+ System.lineSeparator() + "************************************************************************",
				port);
	}
}
