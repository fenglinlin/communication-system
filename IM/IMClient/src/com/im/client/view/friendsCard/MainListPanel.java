package com.im.client.view.friendsCard;

import javax.swing.*;

import com.im.client.view.IMFriendList;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private MouseAdapter ma;
	private JPanel headpanel = new JPanel();
	
	private final JLabel myFriendsLabel = new JLabel("�ҵĺ���");
//	private final JLabel strangersLabel= new JLabel("İ����");
//	private final JLabel blacklistLabel = new JLabel("������");
	
	public MainListPanel(MouseAdapter ma) {
		setName(MainListPanel.class.getSimpleName());
		this.ma=ma;
		setBackground(Color.WHITE);
		setLayout(null);
		myFriendsLabel.setForeground(Color.BLACK);
		myFriendsLabel.setFont(new Font("����", Font.PLAIN, 12));
		
		
		myFriendsLabel.setName("myFriendsLabel");
//		strangersLabel.setForeground(Color.BLACK);
//		strangersLabel.setFont(new Font("����", Font.PLAIN, 12));
//		strangersLabel.setName("strangersLabel");
//		blacklistLabel.setForeground(Color.BLACK);
//		blacklistLabel.setFont(new Font("����", Font.PLAIN, 12));
//		blacklistLabel.setName("backlistLabel");
		//��������¼����¼���Ϊ�ڲ��౻������IMFriendList֮��
		myFriendsLabel.addMouseListener(ma);
//		strangersLabel.addMouseListener(ma);
//		blacklistLabel.addMouseListener(ma);
		
		
		headpanel.setBackground(Color.WHITE);
		headpanel.setLayout(new GridLayout(3,1));
		myFriendsLabel.setIcon(new ImageIcon(MainListPanel.class.getResource("/images/close.jpg")));
		headpanel.add(myFriendsLabel);
		
//		strangersLabel.setIcon(new ImageIcon(BlacklistPanel.class.getResource("/images/close.jpg")));
//		headpanel.add(strangersLabel);
//		blacklistLabel.setIcon(new ImageIcon(MainListPanel.class.getResource("/images/close.jpg")));
//		headpanel.add(blacklistLabel);
		headpanel.setBounds(0, 0, 264, 69);
		add(headpanel);
		
		

	}


	
	

}
