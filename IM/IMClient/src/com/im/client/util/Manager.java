package com.im.client.util;

import java.util.HashMap;

import com.im.client.model.ClientConnToServerThread;
import com.im.client.view.IMChat;
import com.im.client.view.IMFriendList;

/**
 * ������ǰ���Ϣ�Ľ���ClientConnToServerThread��
 * IMFriendList��������չʾ�����б�ҳ��ͳһ����Manager���棬ȥ��������class����
 * Ϊɶ����д����û�����������ͼ
 * @author FengLin
 *
 */
public class Manager {
    public static ClientConnToServerThread THREAD;
    public static IMFriendList FRIEND_LIST;
    
    private static HashMap hm = new HashMap<String, IMChat>();

	// ����
	public static void addIMChat(String loginIdAnFriendId, IMChat chat) {
		hm.put(loginIdAnFriendId, chat);
	}

	// ȡ��
	public static IMChat getIMChat(String loginIdAnFriendId) {
		return (IMChat) hm.get(loginIdAnFriendId);
	}
	
	
	public static void delete(String loginIdAnFriendId){
		hm.remove(loginIdAnFriendId) ;
	}
}
