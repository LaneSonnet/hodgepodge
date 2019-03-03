package com.mudfish.netty.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.common.constants.RpcServerConstant;
import com.mudfish.dao.rpc.RpcServerDao;
import com.mudfish.exception.MudfishRpcException;
import com.mudfish.factory.RpcInvokeFactory;
import com.mudfish.netty.codec.NettyDecoder;
import com.mudfish.netty.codec.NettyEncoder;
import com.mudfish.po.rpc.RpcServer;
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
	private RpcServerDao rpcServerDao;
	private Integer id;

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
					initStatus(port, this.hashCode() + "");
					printStartFlag(port);
					future.channel().closeFuture().sync();
				} catch (Exception e) {
					throw new MudfishRpcException("mudfish server occur excption：" + e.getMessage());
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
		if (id != null) {
			rpcServerDao.deleteById(id);
		}
		logger.info("mudfish server destroy success.");
	}

	public void setRpcServerDao(RpcServerDao rpcServerDao) {
		this.rpcServerDao = rpcServerDao;
	}

	private void initStatus(int rpcPort, String instantId) throws UnknownHostException {
		RpcServer rpcServer = new RpcServer();
		rpcServer.setIp(InetAddress.getLocalHost().getHostAddress());
		rpcServer.setRpcPort(rpcPort);
		Date date = new Date();
		rpcServer.setStartTime(date);
		rpcServer.setLastUpdateTime(date);
		rpcServer.setStatus(RpcServerConstant.STATUS_START);
		rpcServer.setInstanceId(instantId);
		rpcServerDao.create(rpcServer);
		this.id = rpcServer.getId();
	}

	public void printStartFlag(int port) {
		logger.info(System.lineSeparator() + "************************************************************************"
						+ System.lineSeparator() + "************MUDFISH SERVER START SUCCESS, PORT = 【{}】**************"
						+ System.lineSeparator() + "************************************************************************",
				port);
	}
}
