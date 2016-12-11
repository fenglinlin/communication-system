package com.im.server.service;

import java.util.HashMap;
import java.util.LinkedList;

import com.im.common.Message;

/**
 * �ü���HashMap��LinkedList���洢������Ϣ
 * HashMap    ��ֵ��   key��Ӧ�����û���account
 * LinkedList  ˫������  ���ڴ洢�������Ϣ���У������ȳ�ȡ��Ϣ
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
