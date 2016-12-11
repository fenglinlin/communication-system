package com.im.server.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import com.im.server.thread.ClientThread;

/**
 * 创建线程池，用来管理客户端在线用户的并发
 * @author FengLin
 *
 */
public class ClientThreadManager {
	private static HashMap<Integer, ClientThread> threadPool = new HashMap<Integer, ClientThread>();

	// 向threadPool中添加一个客户端通讯线程
	public static void addClientThread(Integer uid, ClientThread ct) {
		threadPool.put(uid, ct);
	}

	//通过uid获取一个客户端通信线程
	public static ClientThread getClientThread(Integer uid) {
		return (ClientThread) threadPool.get(uid);
	}

	//获取在线的用户账户
	public static Integer[] getOnlinessUserId() {
		Integer account[] = new Integer[threadPool.keySet().size()];
		Iterator i = threadPool.keySet().iterator();
		int j = 0;
		while (i.hasNext()) {
			account[j] = (Integer) i.next();
			j++;
		}
		return account;
	}

	//删掉线程池中的uid对应的客户端通信线程
	public static void removeClientThread(Integer uid) {
		ClientThread ct = threadPool.remove(uid);
	}

	public static void removeAll() {
		Iterator i = threadPool.keySet().iterator();
		while (i.hasNext()) {
			ClientThread ct = threadPool.get(i.next());
			ct.interrupt();
			i.remove();
		}

	}

	public static int size() {
		return threadPool.size();
	}

	public static Collection<ClientThread> values() {
		return threadPool.values();
	}

}
