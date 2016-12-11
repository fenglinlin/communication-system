package com.im.client.model;

import com.im.client.util.Manager;
import com.im.client.view.IMFriendList;
import com.im.common.Message;
import com.im.common.MessageType;
import com.im.common.User;


public class IMClientUserServer {
	private IMClientServer server;
	
	private ClientConnToServerThread connThread;

	public IMClientUserServer() {
//		server= IMClientServer.getInstance();

		server=new IMClientServer();
	}

	public boolean checkLogin(Object o) {
		boolean success=false;
		Message msg=(Message) server.sendLoginInfo(o);
//		System.out.println(msg.getMsgType());
//		System.out.println(MessageType.message_succeed);
		if(msg.getMsgType().intValue()==MessageType.message_succeed.intValue()){
			success=true;
			User u=(User) o;
			//�������б�ŵ��������У����������߲���	
			IMFriendList fl=new IMFriendList(msg.getMyself(),msg.getFriends());
			Manager.FRIEND_LIST=fl;
			//�����ͻ��߳� �����շ������˵���Ϣ
			connThread =new ClientConnToServerThread(server.getSocket());
			connThread.setSocket(server.getSocket());
			connThread.start();
			connThread.setName(u.getAccount()+"");
			Manager.THREAD=connThread;
		
		}
			
		return success;
		
	}
	
	
	
	public IMClientServer getServer() {
		return server;
	}

	public void setServer(IMClientServer server) {
		this.server = server;
	}

	public ClientConnToServerThread getConnThread() {
		return connThread;
	}

	public void setConnThread(ClientConnToServerThread connThread) {
		this.connThread = connThread;
	}
	
	
	
}
