package com.im.server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.im.common.Message;
import com.im.common.MessageType;
import com.im.common.User;
import com.im.server.dao.UserDao;
import com.im.server.service.ClientThreadManager;
import com.im.server.service.OffLineMsgManager;

/**
 * �ͻ����û�֮���ͨ�Ŵ���
 * ��Ϣ��ͼƬ���ļ��Ĵ���
 * @author FengLin
 *
 */
public class ClientThread extends Thread {
	private Socket socket;
    private UserDao ud=new UserDao();
    
	public ClientThread(Socket s) {
		this.socket = s;
	}
	
	
	//�ø��߳�ȥ֪ͨ�����û�
	public void notifyOnline()
	{
		//�õ��������ߵ��˵��߳�
		Collection<ClientThread> c=ClientThreadManager.values();
		
	    //�����������û������Լ����ߵ�֪ͨ�������Լ�
		for(ClientThread t:c){
			if(!t.getName().equals(getName())){
				Message m=new Message();
				m.setMsgType(MessageType.message_ret_onLineFriend);
				List<User> l=new ArrayList<User>();
				l.add(new User(Integer.parseInt(getName())));
				m.setFriends(l);
				m.setSenderAccount(Integer.parseInt(getName()));
				m.setReceiverAccount(Integer.parseInt(t.getName()));
				try {
					t.send(m);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				Message m = (Message) receive();
				// �������ͨ��Ϣ
				if (m.getMsgType() == MessageType.message_comm_mes.intValue()) {
					System.out.println(m.getSenderAccount() + "send to"+ m.getReceiverAccount() + "at" + m.getTime());

					Integer receiverAccount = m.getReceiverAccount();
					
					ClientThread t = ClientThreadManager.getClientThread(receiverAccount);
					if (t == null) {
						//�����߾���ʱ�洢�ڷ�������
						OffLineMsgManager.putSingle(receiverAccount, m);
					} else
						t.send(m);
				}
				//������������ߺ����б����Ϣ
				else if(m.getMsgType()==MessageType.message_get_onLineFriend.intValue()){
					System.out.println(m.getSenderAccount()+" Ҫ���ĺ���");
					Integer account[]=ClientThreadManager.getOnlinessUserId();
					System.out.println("���ߺ�������:  "+account.length);
					Message m1=new Message();
					m1.setMsgType(MessageType.message_ret_onLineFriend);
					m1.setReceiverAccount(m.getSenderAccount());
					List<User> list=new ArrayList<User>();
					for(Integer i:account){
						list.add(ud.findById(i));
					}
					m1.setFriends(list);
					send(m1);
				}
				//������ͼ����Ϣ
				else if(m.getMsgType()==MessageType.message_img.intValue()){
					System.out.println(m.getSenderAccount()+"send img to"+m.getReceiverAccount()+"at"+m.getTime());
					
					Integer receiverAccount=m.getReceiverAccount();
					ClientThread t=ClientThreadManager.getClientThread(receiverAccount);
					t.send(m);
				}else if(m.getMsgType()==MessageType.message_file.intValue()){
					
					Integer receiverAccount=m.getReceiverAccount();
					ClientThread t=ClientThreadManager.getClientThread(receiverAccount);
					t.send(m);
				}
				
			} catch (IOException e) {
				System.out.println("==========IOException==========");
				e.printStackTrace();
				//ֹͣ�߳�
				interrupt();
				System.out.println(getName()+"����");
				//���̼߳���ɾȥ���߳�
				ClientThreadManager.removeClientThread(Integer.parseInt(getName()));
				//��������
				System.out.println("���������� "+ClientThreadManager.size());
				//�������ݰ�֪ͨ����
				notifyOffline();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	//֪ͨ�����߳��Լ�������
	public void notifyOffline(){
		Collection<ClientThread> c=ClientThreadManager.values();
		Message m=new Message();
		m.setMsgType(MessageType.message_offline);
		m.setSenderAccount(Integer.parseInt(getName()));
		for(ClientThread t:c){
			try {
				t.send(m);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	

	@Override
	public void interrupt() {
		
		super.interrupt();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(socket!=null)
				try {
					socket.close();
				} catch (IOException e) {
					
				}
		}
	}

	
	
    public void send(Object o) throws IOException{
    	ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());	
		oos.writeObject(o);
    }
    
    public Object receive() throws IOException, ClassNotFoundException{
    	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		return ois.readObject();
    }

    public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
