package com.im.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;


import com.im.client.util.DesktopRuner;
import com.im.client.view.friendsCard.MainListPanel;
import com.im.client.view.friendsCard.MyFriendPanel;
import com.im.client.view.friendsCard.StrangerPanel;
import com.im.common.User;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.util.List;


import javax.swing.SwingConstants;


public class IMFriendList extends JFrame {

	/**
	 * 
	 */
	private Integer myselfAccount;
	private static final long serialVersionUID = 7058774328447433004L;
	private JPanel contentPane;
	private JPanel myselfPanel = new JPanel();
	private final JPanel tail = new JPanel();

	private JPanel friendsContainer;
	CardLayout cl=new CardLayout();
	
	MouseAdapter cca=new ChangeCardAction();
	private JPanel myFriendPanel;
	



	private JPanel strangerPanel=new StrangerPanel(cca);
	private JPanel mainlistpPanel=new MainListPanel(cca);
	
	
	private final JPanel searchPanel = new JPanel();
	private JTextField searchField;
	private JLabel searchIcoLabel;
	private final JLabel nameLabel = new JLabel("");
	private final JLabel stateLabel = new JLabel("\u6211\u5728\u7EBF\u4E0A");
	private final JLabel signatureLabel = new JLabel("");
	private final JLabel lblNewLabel = new JLabel("");
	private final DesktopRuner dr=new DesktopRuner();
	private final JComboBox stateBox = new JComboBox();
	private final JLabel myImgLabel = new JLabel("");
 
	private List<User> friendList;

	/**
	 * Create the frame.
	 */
	public IMFriendList(User myself,List<User> friendList) {
		this.myselfAccount=myself.getAccount();
		this.friendList=friendList;
		
		setTitle(myselfAccount+"");
		myFriendPanel= new MyFriendPanel(cca,myselfAccount,friendList);
       //设置好友列表的头信息
		myImgLabel.setIcon(new ImageIcon(IMFriendList.class.getResource("/images/\u5934\u50CF_" +
				myself.getProfileID()+".png")));
		nameLabel.setText(myself.getName());
		signatureLabel.setText(myself.getSignature());
		stateBox.setModel(new DefaultComboBoxModel(new String[] {"在线", "隐身"}));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		myselfPanel.setBounds(0, 0, 264, 74);
		myselfPanel.setBackground(new Color(43,182,239));
		contentPane.add(myselfPanel);
		myselfPanel.setLayout(null);
		
		
		myImgLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				dr.changeMouse(myImgLabel);
			}
		});
	
		myImgLabel.setBounds(10, 10, 46, 46);
		myselfPanel.add(myImgLabel);
		
		
		stateBox.setForeground(Color.BLACK);
		stateBox.setFont(new Font("宋体", Font.PLAIN, 12));
		
//		stateBox.setBorder(BorderFactory.createLineBorder(new Color(72,192,242), 1));
		
		
		stateBox.setBackground(new Color(43,182,239));
		stateBox.setBounds(66, 10, 32, 21);
		myselfPanel.add(stateBox);
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setFont(new Font("宋体", Font.BOLD, 13));
		nameLabel.setBounds(97, 13, 103, 15);
		
		myselfPanel.add(nameLabel);
		stateLabel.setForeground(Color.BLACK);
		stateLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		stateLabel.setBounds(200, 13, 54, 15);
		
		myselfPanel.add(stateLabel);
		signatureLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		signatureLabel.setForeground(Color.BLACK);
		signatureLabel.setBounds(66, 41, 188, 15);
		
		myselfPanel.add(signatureLabel);
		lblNewLabel.setIcon(new ImageIcon(IMFriendList.class.getResource("/images/\u5929\u6C14.jpg")));
		lblNewLabel.setBounds(196, 14, 68, 38);
		
	//	myselfPanel.add(lblNewLabel);
		myselfPanel.add(lblNewLabel, new   Integer(Integer.MIN_VALUE));
		tail.setBounds(0, 512, 264, 52);
		tail.setBackground(new Color(45,183,240));
		contentPane.add(tail);
		
		friendsContainer = new JPanel();
		friendsContainer.setBackground(Color.WHITE);
		friendsContainer.setBounds(0, 100, 264, 414);
		friendsContainer.setLayout(cl);
		
		friendsContainer.add(mainlistpPanel,"0");
		friendsContainer.add(myFriendPanel,"1");
//		friendsContainer.add(strangerPanel,"2");
//		friendsContainer.add(blacklistPanel,"3");
		
		
		contentPane.add(friendsContainer);
		cl.show(friendsContainer, "0");
		searchPanel.setBounds(0, 73, 264, 27);
		
		contentPane.add(searchPanel);
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		searchField = new JTextField();
		searchField.setBackground(Color.WHITE);
		
//		searchField.setBorder(BorderFactory.createEmptyBorder());
		searchPanel.add(searchField, BorderLayout.CENTER);
		searchField.setColumns(10);
		
//		getGraphics().drawLine(0, 98, 263, 98);
		
		searchIcoLabel = new JLabel("");
		searchIcoLabel.setIcon(new ImageIcon(IMFriendList.class.getResource("/images/Search.jpg")));
		searchIcoLabel.setBackground(Color.WHITE);
		searchIcoLabel.addMouseListener(cca);
		searchPanel.add(searchIcoLabel, BorderLayout.EAST);
		
		
		setSize(272, 598);
		setVisible(true);
//		setResizable(false);
		
	}
	
	

    class ChangeCardAction extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			String currentPanel=null;
			if(e.getSource() instanceof JLabel){
				for (int i = 0; i < friendsContainer.getComponentCount(); i++) {
					Component c=friendsContainer.getComponent(i);
//					System.out.println(c.getName()+"  "+c.isVisible());
					if(c.isVisible())
						currentPanel=c.getName();
				}
				JLabel jl=(JLabel) e.getSource();
//				System.out.println(jl.getName());
				if(jl.getName().equals("myFriendsLabel")&&currentPanel.equals("MyFriendPanel"))
					cl.show(friendsContainer, "0");
//				else if(jl.getName().equals("strangersLabel")&&currentPanel.equals("StrangerPanel"))
//					cl.show(friendsContainer, "0");
//				else if(jl.getName().equals("backlistLabel")&&currentPanel.equals("BlacklistPanel"))
//					cl.show(friendsContainer, "0");
				else if(jl.getName().equals("myFriendsLabel"))
					cl.show(friendsContainer, "1");
//				else if(jl.getName().equals("strangersLabel"))
//					cl.show(friendsContainer, "2");
//				else if(jl.getName().equals("backlistLabel"))
//					cl.show(friendsContainer, "3");
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==searchIcoLabel)
				dr.changeMouse(searchIcoLabel);
				
		}
			
	}
    
    
//    //重写dispose方法，在dispose前停止线程，并在ConnThreadManager中删除它
//    @Override
//	public void dispose() {
//    	//删除自己的线程
//    	ClientConnToServerThread t=ConnThreadManager.removeThread(myselfAccount);
//    	try {
//			t.getSocket().close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	//删除自身
//    	FriendListManager.removeIMFriendList(myselfAccount);
//		super.dispose();
//	}

    
    
	public JPanel getMyFriendPanel() {
		return myFriendPanel;
	}

	public void setMyFriendPanel(JPanel myFriendPanel) {
		this.myFriendPanel = myFriendPanel;
	}

	public JPanel getMainlistpPanel() {
		return mainlistpPanel;
	}

	public void setMainlistpPanel(JPanel mainlistpPanel) {
		this.mainlistpPanel = mainlistpPanel;
	}

	public List<User> getFriendList() {
		return friendList;
	}

	
	
	
}
