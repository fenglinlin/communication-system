package com.im.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.im.client.model.ClientConnToServerThread;
import com.im.client.model.IMClientServer;
import com.im.client.model.SendFileThread;
import com.im.client.util.Manager;
import com.im.client.view.ui.ScrollBarUI;
import com.im.common.Message;
import com.im.common.MessageType;
import com.im.common.User;

import com.sun.awt.AWTUtilities;

/**
 * 聊天的弹出框
 * 在这里面就有
 * 发送消息
 * 发送图片
 * 发送文件可视化操作功能
 * @author FengLin
 *
 */
public class IMChat extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1941512160861055290L;

	private JPanel panel;
	private Integer ownerId;
	private GraphicsConfiguration translucencyCapableGC;// 图形环境
	private Point location;
	private Dimension size = new Dimension(466, 510);;// 界面大小
	private boolean isMax;

	public boolean isMax() {
		return isMax;
	}

	public void setMax(boolean isMax) {
		this.isMax = isMax;
	}

	IMChat frame = this;

	private final JLabel headimg = new JLabel("");
	private JPanel headPanel = new JPanel();
	private JPanel head_rightPanel = new JPanel();
	private GridLayout gl_head_leftPanel = new GridLayout(3, 1);
	private JPanel head_leftPanel = new JPanel(gl_head_leftPanel);
	private final JLabel signatureLabel = new JLabel();
	private final JLabel nameLabel = new JLabel();

	private final JScrollPane output_scrollPane;
	/**
	 * @wbp.nonvisual location=98,482
	 */
	private JSplitPane splitPane = new JSplitPane();
	// private final JTextField outputField = new JTextField();
	private final JTextPane output_pane = new JTextPane();
	private final JPanel inputPanel = new JPanel();
	private final JPanel input_functionpanel = new JPanel();
	private final JScrollPane input_scrollPane;
	/**
	 * @wbp.nonvisual location=61,562
	 */
	private final JTextArea input_textArea = new JTextArea();
	private final JLabel label = new JLabel("");
	/**
	 * @wbp.nonvisual location=140,522
	 */
	private final JPanel tailPanel = new JPanel();
	private final JPanel conButtPanel = new JPanel();
	private final JButton confirmButton = new JButton("\u53D1\u9001");
//	private final JButton hideButton = new JButton();
	private final JPanel closeButtPanel = new JPanel();
	private final JButton closeButton = new JButton("\u5173\u95ED");
	private final JPanel panel_1 = new JPanel();
	private final JPanel picturePanel = new JPanel();
	private final JLabel northPicLabel = new JLabel("");
	private final JLabel southPicLabel = new JLabel("");
    private User user;
    private final JLabel jietu_Label = new JLabel("");
    private final JLabel browse_Label = new JLabel("");
    private  final JProgressBar progressBar=new JProgressBar();
    
    private final JFileChooser fileDialog=new JFileChooser("e:");
    private final JButton hideButton = new JButton("New button");
    private final JPanel progress_Panel = new JPanel();
    private  File f;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					// IMChat frame = new IMChat("");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public IMChat(final User user, final Integer ownerId) {
		this.ownerId = ownerId;
        this.user=user;
        //设置信息
        nameLabel.setText(user.getName() + "(" + user.getAccount() + ")");
        signatureLabel.setText(user.getSignature());
        headimg.setIcon(new ImageIcon(IMChat.class
				.getResource("/images/\u5934\u50CF_" +user.getProfileID()+".png")));
        
        
		setTitle(user.getName());
		output_pane.setForeground(Color.BLACK);
		output_pane.setFont(new Font("宋体", Font.PLAIN, 12));
		output_pane.setBackground(Color.WHITE);
		output_pane.setEditable(false);
//		outputField.setColumns(10);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 505);

		// 定义颜色
		final int R = 72;
		final int G = 192;
		final int B = 242;
		// 定义窗体颜色渐变
		final Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 100),
				getWidth(), getHeight(), new Color(R, G, B, 200), true);
		// 初始化窗体面板
		panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(p);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.setColor(new Color(72, 192, 242));
				Shape shape = null;
				shape = new RoundRectangle2D.Double(0, 0, frame.getWidth() - 1,
						frame.getHeight() - 1, 15.0D, 15.0D);// 绘制窗体边框
				g2d.draw(shape);
				// g2d.drawImage(image, 8, 35, 40, 40, null);
				g2d.drawString("2010", 5, 15);
			}
		};
		panel.setForeground(new Color(72, 192, 242));

		setUndecorated(true);// 去掉所有装饰：标题栏、大小、关闭

		panel.setBackground(new Color(72, 192, 242));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		setContentPane(panel);

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBorder(null);
		// splitPane.setOpaque(false);
		splitPane.setResizeWeight(0.75D);
		splitPane.setDividerSize(1);// 设置分隔条大小
		panel.add(splitPane, BorderLayout.CENTER);

		output_scrollPane = new JScrollPane(output_pane);
		output_scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		splitPane.setLeftComponent(output_scrollPane);
		inputPanel.setBackground(Color.WHITE);

		splitPane.setRightComponent(inputPanel);
		inputPanel.setLayout(new BorderLayout(0, 0));
		input_functionpanel.setBackground(new Color(199, 232, 246));

		inputPanel.add(input_functionpanel, BorderLayout.NORTH);
		input_functionpanel.setLayout(new GridLayout(0, 3, 1, 0));
		label.setIcon(new ImageIcon(IMChat.class.getResource("/images/A.jpg")));

		input_functionpanel.add(label);
		jietu_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/\u56FE\u72471.png")));
		
		input_functionpanel.add(jietu_Label);
		browse_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/\u622A\u56FE2.png")));
		
		input_functionpanel.add(browse_Label);
		input_textArea.setForeground(Color.BLACK);
		input_textArea.setFont(new Font("宋体", Font.PLAIN, 13));

		input_scrollPane = new JScrollPane(input_textArea);
		input_scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		inputPanel.add(input_scrollPane, BorderLayout.CENTER);
		// splitPane.setLeftComponent(label);

		headPanel.setLayout(new BorderLayout());
		headPanel.add(head_rightPanel, BorderLayout.WEST);

		head_rightPanel.add(headimg);
		head_rightPanel.setBackground(new Color(72, 192, 242));

		headPanel.add(head_leftPanel, BorderLayout.CENTER);
		head_leftPanel.setBackground(new Color(72, 192, 242));
		nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setFont(new Font("宋体", Font.BOLD, 12));
		

		head_leftPanel.add(nameLabel);
		signatureLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		signatureLabel.setForeground(Color.BLACK);
		signatureLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		head_leftPanel.add(signatureLabel);
		head_leftPanel.add(hideButton);
		
		hideButton.setText(user.getAccount() + "");
		hideButton.setVisible(false);
		
//		head_leftPanel.add(btnNewButton);
//		btnNewButton.setVisible(false);
		progressBar.setVisible(false);
		
		panel.add(headPanel, BorderLayout.NORTH);
		panel_1.setBackground(new Color(72, 192, 242));

		headPanel.add(panel_1, BorderLayout.EAST);
        
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 38, 38, 38, 0 };
		gbl_panel_1.rowHeights = new int[] { 45, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		final JLabel min = new JLabel(new ImageIcon(IMChat.class
				.getResource("/images/min_1.jpg")));

		min.setIcon(new ImageIcon(IMChat.class
						.getResource("/images/min_1.jpg")));

		GridBagConstraints gbc_min = new GridBagConstraints();
		gbc_min.anchor = GridBagConstraints.NORTHEAST;
		gbc_min.insets = new Insets(0, 0, 0, 5);
		gbc_min.gridx = 0;
		gbc_min.gridy = 0;
		panel_1.add(min, gbc_min);
		
		final JLabel max = new JLabel(new ImageIcon(IMChat.class
				.getResource("/images/max_1.jpg")));

		max
				.setIcon(new ImageIcon(IMChat.class
						.getResource("/images/max_1.jpg")));
		// 最小化按钮
		GridBagConstraints gbc_max = new GridBagConstraints();
		gbc_max.anchor = GridBagConstraints.NORTHEAST;
		gbc_max.insets = new Insets(0, 0, 0, 5);
		gbc_max.gridx = 1;
		gbc_max.gridy = 0;
		panel_1.add(max, gbc_max);
		
		final JLabel closeButton1 = new JLabel();

		closeButton1.setIcon(new ImageIcon(IMChat.class
				.getResource("/images/closed.jpg")));

		
		// 最大化按钮
		GridBagConstraints gbc_closeButton1 = new GridBagConstraints();
		gbc_closeButton1.anchor = GridBagConstraints.NORTHEAST;
		gbc_closeButton1.gridx = 2;
		gbc_closeButton1.gridy = 0;
		panel_1.add(closeButton1, gbc_closeButton1);
		// 获取系统图形环境
		translucencyCapableGC = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		tailPanel.setBackground(new Color(179, 225, 246));
		panel.add(tailPanel, BorderLayout.SOUTH);
		tailPanel.setLayout(new GridLayout(1, 4, 0, 0));
		
		progress_Panel.setBackground(new Color(179, 225, 246));
		tailPanel.add(progress_Panel);
		progress_Panel.add(progressBar);
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(new Color(53,213,56));
//		progressBar.setBackground(new Color(179, 225, 246));
		conButtPanel.setBackground(new Color(179, 225, 246));

		confirmButton.addActionListener(this);
		conButtPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		confirmButton.setFont(new Font("宋体", Font.PLAIN, 12));
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		

		conButtPanel.add(confirmButton);
		closeButtPanel.setBackground(new Color(179, 225, 246));
		
		closeButton.setFont(new Font("宋体", Font.PLAIN, 12));
		closeButton.addActionListener(this);
		closeButtPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		closeButtPanel.add(closeButton);
		tailPanel.add(closeButtPanel);

		tailPanel.add(conButtPanel);
		
		picturePanel.setBackground(new Color(232, 242, 249));

		panel.add(picturePanel, BorderLayout.EAST);
		picturePanel.setLayout(new BorderLayout(0, 0));
		northPicLabel.setIcon(new ImageIcon(IMChat.class
				.getResource("/images/t1.jpg")));

		picturePanel.add(northPicLabel, BorderLayout.NORTH);
		southPicLabel.setIcon(new ImageIcon(IMChat.class
				.getResource("/images/t2.jpg")));

		picturePanel.add(southPicLabel, BorderLayout.SOUTH);

		setVisible(true);
        //设置输入框为自动换行
		input_textArea.setLineWrap(true);
		
		// 添加最小化事件
		min.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isMax()) {// 判断是否是最大化状态
					// 如果是最大化状态则还原最小化时的坐标和值
					frame.setSize(size);
					// 重新设定可见区域
					setVisibleRegion(frame.getWidth(), frame.getHeight());
					frame.repaint();
					if (location != null) {
						frame.setLocation(location);
					}
					setMax(false);
				} else {
					// 正常状态最小化
					setExtendedState(JFrame.ICONIFIED);
					// frame.hide();// 隐藏任务栏
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class
						.getResource("/images/min_2.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class
						.getResource("/images/min_1.jpg")));
			}
		});
		// 关闭事件
		closeButton1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class.getResource("/images/closed.jpg")));
				dispose();
				//从IMChatManager中移除当前的对象
				Manager.delete(ownerId+""+user.getAccount());
				// setExtendedState(JFrame.EXIT_ON_CLOSE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class.getResource("/images/close_1.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class.getResource("/images/closed.jpg")));
			}

		});
		// 最大化事件
		max.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				final Rectangle rec = translucencyCapableGC.getBounds();// 获取图形环境桌面大小
				Insets d = Toolkit.getDefaultToolkit().getScreenInsets(
						translucencyCapableGC);// 获取桌面工作区大小
				frame.setSize(rec.width, rec.height - d.bottom);// 设置窗体大小
				// 重新设定可见区域
				setVisibleRegion(frame.getWidth(), frame.getHeight());
				frame.repaint();
				location = frame.getLocation();
				frame.setLocation(0, 0);
				setMax(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class
						.getResource("/images/max_1.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class
						.getResource("/images/max_2.jpg")));
			}

		});
		//拖动事件
		headPanel.addMouseMotionListener(new MouseMotionListener() {
			int old_x;
			int old_y;

			public void mouseDragged(MouseEvent e) {
				IMChat.this.setLocation(IMChat.this.getLocation().x + e.getX()
								- old_x, IMChat.this.getLocation().y + e.getY()
								- old_y);
			}

			public void mouseMoved(MouseEvent e) {
				old_x = e.getX();
				old_y = e.getY();
			}

		});
		//截图事件
		jietu_Label.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				Screenshot s=new Screenshot(IMChat.this);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				jietu_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/图片2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				jietu_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/图片1.png")));
			}
			
		});
		
		//浏览按钮事件
		browse_Label.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				browse_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/截图1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				browse_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/截图2.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				fileDialog.setDialogTitle("选择文件");
				fileDialog.showOpenDialog(IMChat.this);	
				//只能选中文件
				fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				f=fileDialog.getSelectedFile();
				
				//发送数据包，请求发送文件
				Message m = new Message();
				m.setMsgType(MessageType.message_file);
				m.setSenderAccount(ownerId);	
				m.setReceiverAccount(Integer.parseInt(hideButton.getText()));
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//发送时间
				m.setTime(s.format(new java.util.Date()));
				m.setFileName(f.getName());//文件名
				m.setState(2);//请求发送文件
				int length=(int) (f.length()/1000);
				m.setLength(length);//文件大小
				sendMessage(m);
			}	
		});
	}
	
	//开启发送文件线程
	public void SendFile(){
		//准备数据包
		Message m = new Message();
		m.setSenderAccount(ownerId);
		m.setReceiverAccount(Integer.parseInt(hideButton.getText()));
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		m.setTime(s.format(new java.util.Date()));
		m.setState(3); //正在传输
		int length=(int) (f.length()/1000);
		m.setLength(length);
		//开启线程
		int max =(int) (f.length()/10000);
		progressBar.setMaximum(max);
		progressBar.setMinimum(0);
		progressBar.setVisible(true);
		SendFileThread thread=new SendFileThread(f,IMChat.this,m);
		thread.start();
	}
	

	// 设定可见区域
	public void setVisibleRegion(int width, int height) {
		Shape shape = null;
		shape = new RoundRectangle2D.Double(0, 0, width, height, 5.5D, 5.5D);
		AWTUtilities.setWindowShape(frame, shape);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//点击的如果是发送button
		if (e.getSource() == confirmButton) {
			Message m=(Message) makePackage(MessageType.message_comm_mes,null);
			//发送
			sendMessage(m);
			//在自己的Ouput上显示
		    showMessage(m,Color.BLACK);
		  //清空输入
			input_textArea.setText("");
		
		}if(e.getSource()==closeButton){
			//点击的是关闭
			dispose();
			//从IMChatManager中移除当前的对象
			Manager.delete(ownerId+""+user.getAccount());
		}

	}
	
	//制作数据包
	public Object makePackage(int megType,Image img){
		Message m = new Message();
		m.setSenderAccount(ownerId);
		m.setReceiverAccount(Integer.parseInt(hideButton.getText()));
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		m.setTime(s.format(new java.util.Date()));
		//普通信息包
		if (megType == MessageType.message_comm_mes) {
			m.setMsgType(MessageType.message_comm_mes);
			m.setContent(input_textArea.getText());
			return m;
		//图片信息包
		}else if(megType==MessageType.message_img){
			m.setMsgType(MessageType.message_img);		
//			m.setContent(input_textArea.getText());
			m.setImg(img);
			return m;
		}

		return m;
	}
	
	
	//发送数据包
	public void sendMessage(Object o){
		try {
			Message m=(Message) o;
		    ClientConnToServerThread t = Manager.THREAD;
		    t.send(m);		
			
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
	}
	//将image转换成 byte[]
	public byte[] imageToBytes(Image img){
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		try {
			ImageIO.write((RenderedImage) img, "jpg", bos);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	
	
	//将 byte[]转换成image
	public Image  bytesToImage(byte[] b){
		try {
			return ImageIO.read(new ByteArrayInputStream(b));
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		return null;
	}
	
	
	//设置光标到末尾 setCaretPosition(docs.getLength())可以让滚动条自动向下滚动
	//在自己的窗口显示
	public void showMessage(Message m,Color c) {		
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset,c);
		Document docs = output_pane.getDocument();// 利用getDocument()方法取得JTextPane的Document
		
		//一般信息
		if(m.getMsgType().intValue()==MessageType.message_comm_mes){		
			String str=m.getSenderAccount() + " " +m.getTime()+"\n"+"     "+ m.getContent()+ "\n";
			try {
				docs.insertString(docs.getLength(), str, attrset);
				//设置光标到末尾
				output_pane.setCaretPosition(docs.getLength());
			} catch (BadLocationException e) {
				
				e.printStackTrace();
			}
			
		}else if(m.getMsgType().intValue()==MessageType.message_img){
			//图片信息
			String str=m.getSenderAccount() + " " +m.getTime()+ "\n";
			try {
				//显示发送人 和时间
				docs.insertString(docs.getLength(), str, attrset);
				//设置光标到末尾
				output_pane.setCaretPosition(docs.getLength());
				output_pane.insertIcon(new ImageIcon(m.getImg()));
				docs.insertString(docs.getLength(), "\n", attrset);
				//设置光标到末尾
				output_pane.setCaretPosition(docs.getLength());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void append(Color c,String s){
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset,c);
		StyleConstants.setFontSize(attrset,17);
		Document docs = output_pane.getDocument();
		try {
			docs.insertString(docs.getLength(), s, attrset);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//设置光标到末尾
		output_pane.setCaretPosition(docs.getLength());
	}

	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	              
	
}
