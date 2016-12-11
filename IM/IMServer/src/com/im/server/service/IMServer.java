package com.im.server.service;

import com.im.server.thread.ServerThread;

/**
 * 提供服务的开启和关闭服务
 * @author FengLin
 *
 */
public class IMServer {

	static ServerThread st = new ServerThread();
	static IMServer server = new IMServer();

	private IMServer() {

	}

	public static IMServer getIMServer() {
		return server;
	}

	//开启服务（开启Socket的监听端口）
	public void startServer() {
		st.start();
	}

	/**
	 * 强制下线所有在线用户、然后关闭服务（关闭Socket的监听端口）
	 */
	public void close() {
		
		if (ClientThreadManager.size() > 0) {
			ClientThreadManager.removeAll();
		}		
		st.interrupt();
		
	}

}
