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
 * ���ڽ��շ���˷���������Ϣ���ļ���ͼƬ
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
					System.out.println(m.getSenderAccount()+"  ���͸�"+m.getReceiverAccount()+"  ���ݣ�"+m.getContent());
					//����Ự�����Ѿ���
					//����Ự����δ�򿪣����Զ��򿪴���
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
					//����Ự�����Ѿ���
					//����Ự����δ�򿪣����Զ��򿪴���
					checkChatWindow(m);
					m.setImg(chat.bytesToImage(m.getImageByte()));
					chat.showMessage(m,new Color(0,0,255));
				}else if(m.getMsgType().intValue()==MessageType.message_file){
					//���յ������ļ���
					checkChatWindow(m);
//					String s="c:/";
					//�����Ͱ�
					if(m.getState()==2){	   
					    //�Ի��� �Ƿ����
					    int i=JOptionPane.showConfirmDialog(chat, "ȷ������" +
					    		m.getFileName()+"(" +m.getLength()+"KB)", "�����ļ�",JOptionPane.YES_NO_OPTION);
					    Integer sender=m.getReceiverAccount();
				    	Integer getter=m.getSenderAccount();
				    	m.setSenderAccount(sender);
				    	m.setReceiverAccount(getter);
					    //ͬ��ͷ���ͬ��������ݰ�
					    if(i==JOptionPane.YES_OPTION){
					    	
					    	//�򿪱����ļ��Ի���
					    	JFileChooser jfc=new JFileChooser("c:/");
					    	jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					    	int mod=jfc.showSaveDialog(chat);
					    	
					    	if(mod==JFileChooser.APPROVE_OPTION){
					    		//���ѡ����·��
					    		File f=jfc.getSelectedFile();
						    	f=new File(f.getAbsolutePath()+"/"+m.getFileName());
						    	bos=new BufferedOutputStream(new FileOutputStream(f));
						    	m.setState(1);
						    	send(m);
					    	}else{
					    		//ѡ����ȡ����رնԻ���
					    		m.setState(0);
						    	send(m);
					    	}
					    }else{
					    	m.setState(0);
					    	send(m);
					    }
					}else if(m.getState()==3){
						//���ڴ����
						byte b[]=m.getFileByte();
						//���ý�����
						chat.getProgressBar().setVisible(true);
						chat.getProgressBar().setMaximum(m.getLength()/10);
						chat.getProgressBar().setMinimum(0);
						chat.getProgressBar().setValue(chat.getProgressBar().getValue()+b.length/10000);
						bos.write(b);
					}else if (m.getState()==0){
						//��ͬ�ⷢ��
						JOptionPane.showMessageDialog(chat, "�Է��ܾ������ļ�", "֪ͨ", JOptionPane.WARNING_MESSAGE);
					}else if (m.getState()==1){
						//ͬ�ⷢ��
						System.out.println("ͬ�ⷢ��");
						chat.SendFile();
					}else if(m.getState()==4){
						System.out.println(m.getFileName()+"�������");
						chat.getProgressBar().setVisible(false);
						chat.append(new Color(0,0,255), "�ļ�"+m.getFileName()+"(" +m.getLength()/1000+"M)"+"�������"+"\n");
					}	
					
				}
				
			} catch (IOException e) {
				//ʹ����ͷ����
				MyFriendPanel mfp=(MyFriendPanel) fl.getMyFriendPanel();
				mfp.Offline();
				JOptionPane.showMessageDialog(fl, "����������ӶϿ�", "����", JOptionPane.ERROR_MESSAGE);
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
	
	
	//����Ự�����Ѿ���������ʾ��Ϣ
	//����Ự����û�򿪣������Զ��򿪴���
	public void checkChatWindow(Message m){
		 if(Manager.getIMChat(m.getReceiverAccount()+""+m.getSenderAccount())!=null){
//		    	System.out.println("�Ի������Ѿ�����");
		    	chat=Manager.getIMChat(m.getReceiverAccount()+""+m.getSenderAccount());
		    }
		    else {
		    	//����Ự����δ�򿪣����Զ��򿪴���
				User u = new User();
				//�õ����͵��û�
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
