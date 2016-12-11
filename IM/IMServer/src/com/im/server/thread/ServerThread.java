package com.im.server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import com.im.common.Message;
import com.im.common.MessageType;
import com.im.common.User;
import com.im.server.dao.UserDao;
import com.im.server.service.ClientThreadManager;
import com.im.server.service.OffLineMsgManager;

/**
 * ��Socket�������������
 * @author FengLin
 *
 */
public class ServerThread extends Thread{

	private ServerSocket ss ;
	private UserDao ud;

	public ServerThread() {
		ud=new UserDao();
	}

	@Override
	public void run() {
		Socket s ;
		try {// ��9999����
			System.out.println("���Ƿ���������9999����");

			 ss = new ServerSocket(9999);
			while (!isInterrupted()) {
				 s = ss.accept();

				// ���տͻ��˷�������Ϣ.
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();
				System.out.print("���������յ��û�id:" + u.getAccount() + "  ����:"
						+ u.getPassword());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				
				if (checkLogin(u)) {
					// ����һ���ɹ���½����Ϣ��
                    User myself=ud.findById(u.getAccount());
					m.setMsgType(MessageType.message_succeed);
					m.setMyself(myself);
					m.setFriends(ud.findAll());//�����б�
					oos.writeObject(m);
					//Ϊ�û����������߳�
					ClientThread t=new ClientThread(s);
					//�鿴�Ƿ���������Ϣ
					LinkedList<Message> list=OffLineMsgManager.get(u.getAccount());
					if(list!=null){
						for(Message msg:list){
							t.send(msg);
						}
						list.clear();
					}
					//�����߳�
					t.start();
					t.setName(u.getAccount()+"");
					//���û��̷߳����������
					ClientThreadManager.addClientThread(u.getAccount(), t);
					//֪ͨ�������û�
					t.notifyOnline();
					System.out.println("  �ɹ�");
				} else {
					m.setMsgType(3);
					oos.writeObject(m);
					System.out.println("  ʧ��");
					// �ر�Socket
					s.close();
				}
				
			}
		} catch (IOException e) {
			System.out.println("IO����");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println();
			e.printStackTrace();
		}
	}

    public boolean checkLogin(User u){
    	User user=ud.findById(u.getAccount());
    	if(user!=null){
    		return user.getPassword().equals(u.getPassword());
    	}
    	return false;
    }

    //�رշ���
	@Override
	public void interrupt() {
		super.interrupt();
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(ss!=null)
				try {
					ss.close();
				} catch (IOException e) {
				}
		}
	}
	
}
