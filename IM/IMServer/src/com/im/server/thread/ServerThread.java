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
 * （Socket）服务监听启动
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
		try {// 在9999监听
			System.out.println("我是服务器，在9999监听");

			 ss = new ServerSocket(9999);
			while (!isInterrupted()) {
				 s = ss.accept();

				// 接收客户端发来的信息.
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();
				System.out.print("服务器接收到用户id:" + u.getAccount() + "  密码:"
						+ u.getPassword());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				
				if (checkLogin(u)) {
					// 返回一个成功登陆的信息报
                    User myself=ud.findById(u.getAccount());
					m.setMsgType(MessageType.message_succeed);
					m.setMyself(myself);
					m.setFriends(ud.findAll());//好友列表
					oos.writeObject(m);
					//为用户单独创建线程
					ClientThread t=new ClientThread(s);
					//查看是否有离线消息
					LinkedList<Message> list=OffLineMsgManager.get(u.getAccount());
					if(list!=null){
						for(Message msg:list){
							t.send(msg);
						}
						list.clear();
					}
					//开启线程
					t.start();
					t.setName(u.getAccount()+"");
					//把用户线程放入管理类中
					ClientThreadManager.addClientThread(u.getAccount(), t);
					//通知其他的用户
					t.notifyOnline();
					System.out.println("  成功");
				} else {
					m.setMsgType(3);
					oos.writeObject(m);
					System.out.println("  失败");
					// 关闭Socket
					s.close();
				}
				
			}
		} catch (IOException e) {
			System.out.println("IO错误");
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

    //关闭服务
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
