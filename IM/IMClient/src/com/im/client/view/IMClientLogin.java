package com.im.client.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import com.im.client.model.ClientConnToServerThread;
import com.im.client.model.IMClientUserServer;
import com.im.client.util.DesktopRuner;
import com.im.client.util.Manager;
import com.im.common.Message;
import com.im.common.MessageType;
import com.im.common.User;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IMClientLogin extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7532928147550053239L;
	private IMClientUserServer cus;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("New label");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JButton setup = new JButton("设置");
	private final JButton login = new JButton("登录");
	private final JLabel label = new JLabel("账号:");//\u5E10\u53F7
	private final JTextField account;
	private final JLabel lblNewLabel_1 = new JLabel("密码:");//\u5BC6\u7801
	@SuppressWarnings("unchecked")
	private final JComboBox state = new JComboBox();
	private final JCheckBox rememberPasswd = new JCheckBox("记住密码");//\u8BB0\u4F4F\u5BC6\u7801
	private final JCheckBox autoLogin = new JCheckBox("自动登陆");//\u81EA\u52A8\u767B\u5F55
	private JPasswordField passwordField;
	private final JLabel register = new JLabel("注册账号");//\u6CE8\u518C\u5E10\u53F7
	private final JLabel getBackPasswd = new JLabel("找回密码");//\u627E\u56DE\u5BC6\u7801

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMClientLogin frame = new IMClientLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public IMClientLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(340, 253);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		
		lblNewLabel.setIcon(new ImageIcon(IMClientLogin.class.getResource("/images/ClientHead.jpg")));
		lblNewLabel.setBounds(0, 0, 332, 63);
		contentPane.add(lblNewLabel);	
		
		
		panel.setBackground(new Color(228,244,255));
		panel.setBounds(0, 62, 332, 128);
		panel.setLayout(null);
		contentPane.add(panel);
		//账号：
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 21, 54, 15);
		panel.add(label);
		
		//输入的登陆名
		account = new JTextField();
		account.setForeground(Color.BLACK);
		account.setFont(new Font("宋体", Font.PLAIN, 13));
		account.setBounds(70, 18, 182, 23);
		account.setColumns(10);
		account.addMouseListener(new MouseAction());
		panel.add(account);
		
		//密码
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 61, 54, 15);
		panel.add(lblNewLabel_1);
		
		//输入登陆密码
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordField.setEchoChar('*');
		passwordField.setBounds(70, 58, 182, 23);
		passwordField.addMouseListener(new MouseAction());
		panel.add(passwordField);
		
		
//		JLabel lblNewLabel_2 = new JLabel("\u72B6\u6001:");
//		lblNewLabel_2.setForeground(Color.BLACK);
//		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 12));
//		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_2.setBounds(10, 103, 54, 15);
//		panel.add(lblNewLabel_2);
		
		//选择登陆状态
		state.setModel(new DefaultComboBoxModel(new String[] {"在线", "隐身"}));//\u5728\u7EBF  \u9690\u8EAB
		state.setFont(new Font("宋体", Font.PLAIN, 12));
		state.setBounds(70, 100, 50, 21);
		state.setBackground(new Color(228,244,255));
		panel.add(state);
		
		//是否记住密码
		rememberPasswd.setForeground(Color.BLACK);
		rememberPasswd.setFont(new Font("宋体", Font.PLAIN, 12));
		rememberPasswd.setBounds(126, 99, 73, 23);
		rememberPasswd.setBackground(new Color(228,244,255));
		panel.add(rememberPasswd);
		
		//是否自动登陆
		autoLogin.setForeground(Color.BLACK);
		autoLogin.setFont(new Font("宋体", Font.PLAIN, 12));
		autoLogin.setBounds(201, 99, 73, 23);
		autoLogin.setBackground(new Color(228,244,255));
		panel.add(autoLogin);
		
		//注册账号
		register.setForeground(new Color(22,112,235));
		register.setFont(new Font("宋体", Font.PLAIN, 12));
		register.setBounds(256, 21, 54, 15);
        register.addMouseListener(new LinkMouseAction());
		panel.add(register);
		
		//找回密码
		getBackPasswd.setFont(new Font("宋体", Font.PLAIN, 12));
		getBackPasswd.setBounds(256, 61, 54, 15);
		getBackPasswd.setForeground(new Color(22,112,235));
		getBackPasswd.addMouseListener(new LinkMouseAction());
		panel.add(getBackPasswd);
		
		
		panel_1.setBounds(0, 188, 332, 31);
		panel_1.setBackground(new Color(191,225,250));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//设置按钮事件
		setup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IMSetting();
			}
		});
		setup.setForeground(Color.BLACK);
		setup.setFont(new Font("宋体", Font.PLAIN, 12));
		setup.setBounds(10, 6, 67, 20);
		panel_1.add(setup);
		
		//登录事件
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cus=new IMClientUserServer();
//				System.out.println(cus.getServer());
				User u=new User();
				u.setAccount(Integer.parseInt(account.getText().trim()));
				u.setPassword(new String(passwordField.getPassword()));
//				登陆成功
				if(cus.checkLogin(u)){
					
			        //向服务器发送数据包，请求在线人得信息	
					ClientConnToServerThread t=Manager.THREAD;
					Message m=new Message();
					m.setMsgType(MessageType.message_get_onLineFriend);
					m.setSenderAccount(u.getAccount());
					try {
						t.send(m);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					dispose();
				}				
				else
					JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
			}
		});
		login.setForeground(Color.BLACK);
		login.setFont(new Font("宋体", Font.PLAIN, 12));
		login.setBounds(255, 6, 67, 20);
		panel_1.add(login);
		//显示在屏幕中间
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);

//		System.out.println((int) (width - this.getWidth()) / 2);
//		System.out.println((int) (height - this.getHeight()) / 2);
//		System.out.println(getWidth());
//		System.out.println(getHeight());
		//enter键
		getRootPane().setDefaultButton(login);
        setVisible(true);
	}
	
	
	//实现文本框高亮效果
	class MouseAction extends MouseAdapter {

		@Override
		public void mouseExited(MouseEvent e) {
			JTextField jtf=(JTextField) e.getSource();
			jtf.setBorder(BorderFactory.createLineBorder(new Color(78,160,209),1));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JTextField jtf=(JTextField) e.getSource();
	        jtf.setBorder(BorderFactory.createLineBorder(new Color(78,160,209),3));
		}
		
	}
	
	//实现超链接效果
	class LinkMouseAction extends MouseAdapter {
        DesktopRuner dr=new DesktopRuner();

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel jl=(JLabel) e.getSource();
			jl.setForeground(new Color(22,112,235));
			jl.setFont(new Font("宋体", Font.PLAIN, 12));
			
		}

		@Override
		@SuppressWarnings("unchecked")
		public void mouseEntered(MouseEvent e) {
			HashMap hm = new HashMap();
			hm.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); // 定义是否有下划线
			hm.put(TextAttribute.SIZE, 12); // 定义字号
			hm.put(TextAttribute.FAMILY, "宋体"); // 定义字体名
			Font font = new Font(hm); // 生成字号为12，字体为宋体，字形带有下划线的字体

			JLabel jl=(JLabel) e.getSource();
			jl.setForeground(new Color(22,112,235));
			jl.setFont(font);
			dr.changeMouse(jl);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!dr.checkBroswer())
				JOptionPane.showMessageDialog(null, "系统不支持浏览器", "错误", JOptionPane.ERROR_MESSAGE);	
			else
				dr.runBroswer();
		}
		
	}

	public IMClientUserServer getCus() {
		return cus;
	}

	public void setCus(IMClientUserServer cus) {
		this.cus = cus;
	}
	

}


