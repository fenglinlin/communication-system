package com.im.client.view.friendsCard;

import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.im.client.view.IMFriendList;
import com.im.client.view.ui.ScrollBarUI;
import com.im.common.User;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MyFriendPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7366552456007872033L;
	
	
	private MouseAdapter ma;
	private JPanel headPanel = new JPanel();
	private JLabel myFriendsLabel = new JLabel("我的好友");
	
	private JPanel myFriendsPanel =new JPanel(new GridLayout(50,1,4,4));
	private final JScrollPane scrollPane;
	private FriendHeadPanel fp[];
	
	private List<User> friendList;


	public MyFriendPanel( MouseAdapter ma,Integer ownerAccount,List<User> friendList) {
		this.ma=ma;
		this.friendList=friendList;
		
		
		setName(MyFriendPanel.class.getSimpleName());
		setBackground(Color.WHITE);
		setLayout(null);
		myFriendsLabel.setForeground(Color.BLACK);
		
		myFriendsLabel.setName("myFriendsLabel");
		
		
		//设置鼠标事件，事件做为内部类被定义在IMFriendList之中
		myFriendsLabel.addMouseListener(ma);
		
		myFriendsLabel.setName("myFriendsLabel");
		headPanel.setBackground(Color.WHITE);
		headPanel.setBounds(0, 0, 264, 23);
		add(headPanel);
		headPanel.setLayout(new GridLayout(1, 1, 0, 0));
		myFriendsLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		myFriendsLabel.setIcon(new ImageIcon(MyFriendPanel.class.getResource("/images/open.jpg")));
		headPanel.add(myFriendsLabel);
		myFriendsPanel.setBackground(Color.WHITE);
		
		
		fp=new FriendHeadPanel[friendList.size()];	
		for(int i = 0; i < friendList.size(); i++){
			FriendHeadPanel fp1=new FriendHeadPanel(friendList.get(i),ownerAccount);
			if(friendList.get(i).getAccount()==ownerAccount)
				fp1.onLine();
			fp[i]=fp1;
			myFriendsPanel.add(fp1);
		}
		
		scrollPane = new JScrollPane(myFriendsPanel);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarUI());
		scrollPane.setBounds(0, 22, 264, 414);
		
		add(scrollPane);
		
	}
	



	//更新在线人数
	//list里面存放的是在线用户
	public void updateOnline(List list){
		for(FriendHeadPanel fp1 : fp){
			if(list.contains(fp1.getUser())){
				fp1.onLine();
			}
		}
	}
	
	//更新下在线人数
	//list里面存放的是下线用户ID
	public void updateOffline(Integer uid){
		for(FriendHeadPanel fp1 : fp){
			if(fp1.getUser().getAccount().intValue()==uid){
				fp1.offLine();
			}
		}
	}
	
	
	//所有人全部下线
	public void Offline(){
		for(FriendHeadPanel fp1 : fp){
			fp1.offLine();
		}
	}

}
