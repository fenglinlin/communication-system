package com.im.server.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import com.im.server.thread.ClientThread;

/**
 * �����̳߳أ���������ͻ��������û��Ĳ���
 * @author FengLin
 *
 */
public class ClientThreadManager {
	private static HashMap<Integer, ClientThread> threadPool = new HashMap<Integer, ClientThread>();

	// ��threadPool�����һ���ͻ���ͨѶ�߳�
	public static void addClientThread(Integer uid, ClientThread ct) {
		threadPool.put(uid, ct);
	}

	//ͨ��uid��ȡһ���ͻ���ͨ���߳�
	public static ClientThread getClientThread(Integer uid) {
		return (ClientThread) threadPool.get(uid);
	}

	//��ȡ���ߵ��û��˻�
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

	//ɾ���̳߳��е�uid��Ӧ�Ŀͻ���ͨ���߳�
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
