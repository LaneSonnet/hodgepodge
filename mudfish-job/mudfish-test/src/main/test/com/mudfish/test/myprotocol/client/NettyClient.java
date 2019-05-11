/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mudfish.test.myprotocol.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mudfish.test.myprotocol.NettyConstant;
import com.mudfish.test.myprotocol.codec.NettyMessageDecoder;
import com.mudfish.test.myprotocol.codec.NettyMessageEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author Lilinfeng
 * @version 1.0
 * @date 2014年3月15日
 */
public class NettyClient {

	private static final Log LOG = LogFactory.getLog(NettyClient.class);

		private ScheduledExecutorService executor = Executors
				.newScheduledThreadPool(1);

	EventLoopGroup group = new NioEventLoopGroup();

	public void connect(int port, String host) throws Exception {

		// 配置客户端NIO线程组

		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(
									new NettyMessageDecoder(1024 * 1024, 4, 4));
							ch.pipeline().addLast("MessageEncoder",
									new NettyMessageEncoder());
							ch.pipeline().addLast("readTimeoutHandler",
									new ReadTimeoutHandler(50));
							ch.pipeline().addLast("LoginAuthHandler",
									new LoginAuthReqHandler());
							ch.pipeline().addLast("HeartBeatHandler",
									new HeartBeatReqHandler());
						}
					});
			// 发起异步连接操作
			ChannelFuture future = b.connect(
					new InetSocketAddress(host, port),
					new InetSocketAddress(NettyConstant.LOCALIP,
							NettyConstant.LOCAL_PORT)).sync();
			System.out.println("=======================================================fdsafasf");
			// 当对应的channel关闭的时候，就会返回对应的channel。
			// Returns the ChannelFuture which will be notified when this channel is closed. This method always returns the same future instance.
			Channel channel = future.channel().closeFuture().sync().channel();
		} finally {
			// 所有资源释放完成之后，清空资源，再次发起重连操作
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
						try {
							connect(NettyConstant.PORT, NettyConstant.REMOTEIP);// 发起重连操作
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new NettyClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
		System.out.println("dasfsafsa-----------------------------------------------------------");
	}

}
