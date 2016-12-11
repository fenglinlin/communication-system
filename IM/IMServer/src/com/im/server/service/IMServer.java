package com.im.server.service;

import com.im.server.thread.ServerThread;

/**
 * �ṩ����Ŀ����͹رշ���
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

	//�������񣨿���Socket�ļ����˿ڣ�
	public void startServer() {
		st.start();
	}

	/**
	 * ǿ���������������û���Ȼ��رշ��񣨹ر�Socket�ļ����˿ڣ�
	 */
	public void close() {
		
		if (ClientThreadManager.size() > 0) {
			ClientThreadManager.removeAll();
		}		
		st.interrupt();
		
	}

}
