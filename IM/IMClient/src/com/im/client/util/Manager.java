package com.im.client.util;

import java.util.HashMap;

import com.im.client.model.ClientConnToServerThread;
import com.im.client.view.IMChat;
import com.im.client.view.IMFriendList;

/**
 * 这个就是把消息的接收ClientConnToServerThread、
 * IMFriendList（估计是展示好友列表页）统一放在Manager里面，去给其他的class调用
 * 为啥这样写，还没搞明白这个意图
 * @author FengLin
 *
 */
public class Manager {
    public static ClientConnToServerThread THREAD;
    public static IMFriendList FRIEND_LIST;
    
    private static HashMap hm = new HashMap<String, IMChat>();

	// 加入
	public static void addIMChat(String loginIdAnFriendId, IMChat chat) {
		hm.put(loginIdAnFriendId, chat);
	}

	// 取出
	public static IMChat getIMChat(String loginIdAnFriendId) {
		return (IMChat) hm.get(loginIdAnFriendId);
	}
	
	
	public static void delete(String loginIdAnFriendId){
		hm.remove(loginIdAnFriendId) ;
	}
}
