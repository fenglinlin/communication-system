package com.im.client.view.friendsCard;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import com.im.client.view.ui.ScrollBarUI;

public class StrangerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4616095776290924520L;
	/**
	 * Create the panel.
	 */
	private MouseAdapter ma;
	private JPanel headPanel = new JPanel();
	private JPanel tailPanel = new JPanel();
	
	
	private JLabel myFriendsLabel = new JLabel("我的好友");
	private JLabel strangersLabel = new JLabel("陌生人");
	private JLabel blacklistLabel = new JLabel("黑名单");
	private final JScrollPane scrollPane;
	JPanel strangersPanel = new JPanel(new GridLayout(50,1,4,4));
	
	public StrangerPanel(MouseAdapter ma) {
		setName(StrangerPanel.class.getSimpleName());
		this.ma=ma;
		setBackground(Color.WHITE);
		setLayout(null);
		myFriendsLabel.setForeground(Color.BLACK);
		
		myFriendsLabel.setName("myFriendsLabel");
		strangersLabel.setForeground(Color.BLACK);
		strangersLabel.setName("strangersLabel");
		blacklistLabel.setForeground(Color.BLACK);
		blacklistLabel.setName("backlistLabel");
		//设置鼠标事件，事件做为内部类被定义在IMFriendList之中
		myFriendsLabel.addMouseListener(ma);
		strangersLabel.addMouseListener(ma);
		blacklistLabel.addMouseListener(ma);
		
		
		headPanel.setLayout(new GridLayout(2,1));
		headPanel.setBackground(Color.WHITE);
		headPanel.setBounds(0, 0, 264, 46);
		myFriendsLabel.setIcon(new ImageIcon(StrangerPanel.class.getResource("/images/close.jpg")));
		myFriendsLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		headPanel.add(myFriendsLabel);
		strangersLabel.setIcon(new ImageIcon(StrangerPanel.class.getResource("/images/open.jpg")));
		strangersLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		headPanel.add(strangersLabel);
		add(headPanel);
		
		tailPanel.setLayout(new GridLayout(1,1));
		tailPanel.setBackground(Color.WHITE);
		tailPanel.setBounds(0, 392, 264, 23);	
		blacklistLabel.setIcon(new ImageIcon(StrangerPanel.class.getResource("/images/close.jpg")));
		blacklistLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		tailPanel.add(blacklistLabel);
		add(tailPanel);
		
 
		for (int i = 0; i < 30; i++) {
			strangersPanel.add(new JButton(i+""));
		}
		strangersPanel.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane(strangersPanel);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		scrollPane.setBounds(0, 45, 264, 349);
		
		add(scrollPane);

	}

}
