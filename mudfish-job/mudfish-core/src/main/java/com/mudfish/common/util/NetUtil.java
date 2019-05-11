package com.mudfish.common.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mudfish on 2019/3/2 0002.
 */
public class NetUtil {

	private static final Logger logger = LoggerFactory.getLogger(NetUtil.class);

	public static boolean isReachable(String remoteIp, int port, int timeout) {
		boolean isReachable = false;
		Socket socket = null;
		InetAddress localInetAddr = null;
		InetAddress remoteInetAddr = null;
		try {
			localInetAddr = InetAddress.getLocalHost();
			remoteInetAddr = InetAddress.getByName(remoteIp);
			socket = new Socket();
			// 端口号设置为 0 表示在本地挑选一个可用端口进行连接
			SocketAddress localSocketAddr = new InetSocketAddress(localInetAddr, 0);
			socket.bind(localSocketAddr);
			InetSocketAddress endpointSocketAddr = new InetSocketAddress(remoteInetAddr, port);
			socket.connect(endpointSocketAddr, timeout);
			isReachable = true;
		} catch (Exception e) {
			logger.info("Can not connect to the ip【{}】，port【{}】，exception【{}】", remoteIp, port, e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					logger.error("Error occurred while closing socket:{}", e.getMessage());
				}
			}
		}
		return isReachable;
	}
}
