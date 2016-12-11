package com.im.client.view.friendsCard;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import com.im.client.util.Manager;
import com.im.client.view.IMChat;
import com.im.common.User;

public class FriendHeadPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6108977722907715079L;
	private User user;
	private final JPanel rightPanel = new JPanel(new GridLayout(3,1));
	private JLabel nameLabel = new JLabel();
	private JLabel signature = new JLabel();
	private final JPanel leftPanel = new JPanel();
	private final JLabel imgLabel = new JLabel("");
	private Integer ownerAccount;
	private MouseAdapter ma=new HighLight();
	/**
	 * Create the panel.
	 */
	public FriendHeadPanel(User user,Integer ownerAccount) {
		this.ownerAccount=ownerAccount;
		this.user=user;
		
		//设置信息
		imgLabel.setIcon(new ImageIcon(FriendHeadPanel.class.getResource("/images/offline.png")));
		nameLabel.setText(user.getName()+"(" +user.getAccount()+")");
		signature.setText(user.getSignature());
		
		setName(user.getAccount().toString());
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		rightPanel.setBackground(Color.WHITE);
		
		
		nameLabel.setForeground(new Color( 128,128,128));
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		rightPanel.add(nameLabel);
		
		signature.setForeground(new Color( 128,128,128));
		signature.setFont(new Font("宋体", Font.PLAIN, 12));
		rightPanel.add(signature);
		add(rightPanel,BorderLayout.CENTER);
		leftPanel.setBackground(Color.WHITE);
		
		add(leftPanel,BorderLayout.WEST);
		imgLabel.setBackground(Color.WHITE);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		leftPanel.add(imgLabel);
		addMouseListener(ma);
	}
	

	
	
	class HighLight extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			leftPanel.setBackground(new Color(194, 226, 248));
			rightPanel.setBackground(new Color(194, 226, 248));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			leftPanel.setBackground(Color.WHITE);
			rightPanel.setBackground(Color.WHITE);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
//			JPanel j=(JPanel) e.getSource();
			if(e.getClickCount()==2){
				System.out.println("和"+user.getAccount()+"聊天");
				IMChat chat=Manager.getIMChat(ownerAccount+""+user.getAccount());
				if(chat==null){
					chat=new IMChat(user,ownerAccount);
					Manager.addIMChat(ownerAccount+""+user.getAccount(), chat);
				}else
					chat.toFront();
				
			}
		}
		
		
	}
		
	
	public void onLine(){
		imgLabel.setIcon(new ImageIcon(FriendHeadPanel.class.getResource("/images/头像_" +user.getProfileID()+
				".png")));
	}

	public void offLine(){
		imgLabel.setIcon(new ImageIcon(FriendHeadPanel.class.getResource("/images/offline.png")));
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
}
