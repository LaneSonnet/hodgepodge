package com.mudfish.common.constants;

/**
 * Created by Mudfish on 2019/3/2 0002.
 */
public class RpcServerConstant {

	public static final int STATUS_START = 0;
	public static final int STATUS_STOP = 1;

	//端口连通性测试超时时间
	public static final int PORT_REACHABLE_TIME_OUT = 5;
	//客户端连接监视线程循环间隔时间
	public static final int CLIENT_MONITER_INTERVAL_TIME = 60;
	//客户端三次自动重连间隔时间
	public static final int CLIENT_RECONNECT_INTERVAL_TIME = 30;
	//客户端保持连接心跳间隔时间
	public static final int CLIENT_HEARTBEAT_INTERVAL_TIME = 30;
	//服务端阻塞超时时间
	public static final long SERVER_BLOCK_TIME_OUT = 30 * 1000;
	//客户端响应超时时间
	public static final long CLIENT_RESPONSE_TIME_OUT = 30 * 1000;
}
