//package com.mudfish.test;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
//import org.jboss.netty.bootstrap.ServerBootstrap;
//import org.jboss.netty.channel.ChannelPipeline;
//import org.jboss.netty.channel.ChannelPipelineFactory;
//import org.jboss.netty.channel.Channels;
//import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
//import org.jboss.netty.handler.codec.string.StringDecoder;
//import org.jboss.netty.handler.codec.string.StringEncoder;
//
///**
//<dependency>
// <groupId>io.netty</groupId>
// <artifactId>netty</artifactId>
// <version>3.10.5.Final</version>
// </dependency>
// */
//public class MudfishServer {
//
//	public static void main(String[] args) {
//		NioServerSocketChannelFactory factory = new NioServerSocketChannelFactory(
//				Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
//		ServerBootstrap serverBootstrap = new ServerBootstrap(factory);
//		serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//			public ChannelPipeline getPipeline() throws Exception {
////				return Channels.pipeline(new MyChannelHandler());
//				ChannelPipeline pipeline = Channels.pipeline();
//				pipeline.addLast("decoder", new StringDecoder());
//				pipeline.addLast("encoder", new StringEncoder());
//				pipeline.addLast("MyChannelHandler", new MyChannelHandler());
//				return pipeline;
//			}
//		});
//		serverBootstrap.setOption("child.tcpNoDelay", true);
//		serverBootstrap.setOption("child.keepAlive", true);
//		serverBootstrap.bind(new InetSocketAddress(10101));
//	}
//}
