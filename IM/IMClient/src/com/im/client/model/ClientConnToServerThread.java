package com.im.client.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.im.client.record.MessageRecord;
import com.im.client.util.Manager;
import com.im.client.view.IMChat;
import com.im.client.view.IMFriendList;
import com.im.client.view.friendsCard.MyFriendPanel;
import com.im.common.Message;
import com.im.common.MessageType;
import com.im.common.User;

/**
 * 
 * 用于接收服务端发过来的消息、文件、图片
 * @author FengLin
 *
 */
public class ClientConnToServerThread extends Thread {

	private Socket socket;
	private IMFriendList fl;
	private IMChat chat=null;
	private BufferedOutputStream bos;
	
	public ClientConnToServerThread() {
		
	}
	
	public ClientConnToServerThread(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		while(!isInterrupted()){
			try {
				Message m=(Message)receive();
				new MessageRecord().afterReceive(m);
				if(m.getMsgType()==MessageType.message_comm_mes.intValue()){				
					System.out.println(m.getSenderAccount()+"  发送给"+m.getReceiverAccount()+"  内容："+m.getContent());
					//如果会话窗口已经打开
					//如果会话窗口未打开，则自动打开窗口
					checkChatWindow(m);			
					chat.showMessage(m,new Color(0,0,255));
				}else if(m.getMsgType().intValue()==MessageType.message_ret_onLineFriend.intValue()){
					List list=m.getFriends();
					fl=Manager.FRIEND_LIST;
					MyFriendPanel mfp=(MyFriendPanel) fl.getMyFriendPanel();
					mfp.updateOnline(list);
				}else if(m.getMsgType().intValue()==MessageType.message_offline){
					fl=Manager.FRIEND_LIST;
					MyFriendPanel mfp=(MyFriendPanel) fl.getMyFriendPanel();
					mfp.updateOffline(m.getSenderAccount());	
				}else if(m.getMsgType().intValue()==MessageType.message_img){
					//如果会话窗口已经打开
					//如果会话窗口未打开，则自动打开窗口
					checkChatWindow(m);
					m.setImg(chat.bytesToImage(m.getImageByte()));
					chat.showMessage(m,new Color(0,0,255));
				}else if(m.getMsgType().intValue()==MessageType.message_file){
					//接收到的是文件包
					checkChatWindow(m);
//					String s="c:/";
					//请求发送包
					if(m.getState()==2){	   
					    //对话框 是否接收
					    int i=JOptionPane.showConfirmDialog(chat, "确定接收" +
					    		m.getFileName()+"(" +m.getLength()+"KB)", "接收文件",JOptionPane.YES_NO_OPTION);
					    Integer sender=m.getReceiverAccount();
				    	Integer getter=m.getSenderAccount();
				    	m.setSenderAccount(sender);
				    	m.setReceiverAccount(getter);
					    //同意就发送同意接收数据包
					    if(i==JOptionPane.YES_OPTION){
					    	
					    	//打开保存文件对话框
					    	JFileChooser jfc=new JFileChooser("c:/");
					    	jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					    	int mod=jfc.showSaveDialog(chat);
					    	
					    	if(mod==JFileChooser.APPROVE_OPTION){
					    		//如果选择了路径
					    		File f=jfc.getSelectedFile();
						    	f=new File(f.getAbsolutePath()+"/"+m.getFileName());
						    	bos=new BufferedOutputStream(new FileOutputStream(f));
						    	m.setState(1);
						    	send(m);
					    	}else{
					    		//选择了取消或关闭对话框
					    		m.setState(0);
						    	send(m);
					    	}
					    }else{
					    	m.setState(0);
					    	send(m);
					    }
					}else if(m.getState()==3){
						//正在传输包
						byte b[]=m.getFileByte();
						//设置进度条
						chat.getProgressBar().setVisible(true);
						chat.getProgressBar().setMaximum(m.getLength()/10);
						chat.getProgressBar().setMinimum(0);
						chat.getProgressBar().setValue(chat.getProgressBar().getValue()+b.length/10000);
						bos.write(b);
					}else if (m.getState()==0){
						//不同意发送
						JOptionPane.showMessageDialog(chat, "对方拒绝接收文件", "通知", JOptionPane.WARNING_MESSAGE);
					}else if (m.getState()==1){
						//同意发送
						System.out.println("同意发送");
						chat.SendFile();
					}else if(m.getState()==4){
						System.out.println(m.getFileName()+"传输完成");
						chat.getProgressBar().setVisible(false);
						chat.append(new Color(0,0,255), "文件"+m.getFileName()+"(" +m.getLength()/1000+"M)"+"接收完成"+"\n");
					}	
					
				}
				
			} catch (IOException e) {
				//使所有头像变黑
				MyFriendPanel mfp=(MyFriendPanel) fl.getMyFriendPanel();
				mfp.Offline();
				JOptionPane.showMessageDialog(fl, "与服务器连接断开", "错误", JOptionPane.ERROR_MESSAGE);
				interrupt();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void send(Object o) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(o);
	}

	public Object receive() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		return ois.readObject();
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket s) {
		this.socket = s;
	}
	
	
	//如果会话窗口已经存在则显示信息
	//如果会话窗口没打开，则制自动打开窗口
	public void checkChatWindow(Message m){
		 if(Manager.getIMChat(m.getReceiverAccount()+""+m.getSenderAccount())!=null){
//		    	System.out.println("对话窗口已经存在");
		    	chat=Manager.getIMChat(m.getReceiverAccount()+""+m.getSenderAccount());
		    }
		    else {
		    	//如果会话窗口未打开，则自动打开窗口
				User u = new User();
				//得到发送的用户
				List<User> list=Manager.FRIEND_LIST.getFriendList();
				for(User user:list){
					if(user.getAccount()==m.getSenderAccount().intValue())
						u=user;
				}
				chat = new IMChat(u,m.getReceiverAccount());
				Manager.addIMChat(m.getReceiverAccount()+""+m.getSenderAccount(), chat);
		}
	}
	
}
