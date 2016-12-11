package com.im.server.service;

import java.util.HashMap;
import java.util.LinkedList;

import com.im.common.Message;

/**
 * 用集合HashMap、LinkedList来存储离线消息
 * HashMap    键值对   key对应的是用户的account
 * LinkedList  双向链表  用于存储有序的消息队列，先入先出取消息
 * @author FengLin
 *
 */
public class OffLineMsgManager {
	private static HashMap<Integer, LinkedList<Message>> hm = new HashMap<Integer, LinkedList<Message>>();

	public static LinkedList<Message> get(Object key) {
		return hm.get(key);
	}

	public static LinkedList<Message> put(Integer key, LinkedList<Message> value) {
		return hm.put(key, value);
	}
	
	public static LinkedList<Message> putSingle(Integer key, Message msg) {
		LinkedList<Message> list=hm.get(key);
		if(list==null){
			list=new LinkedList<Message>();
			list.add(msg);
		}else{
			list.add(msg);
		}		
		return hm.put(key, list);
	}
}
