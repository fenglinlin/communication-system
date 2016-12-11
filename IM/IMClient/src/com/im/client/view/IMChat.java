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
 * ����ĵ�����
 * �����������
 * ������Ϣ
 * ����ͼƬ
 * �����ļ����ӻ���������
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
	private GraphicsConfiguration translucencyCapableGC;// ͼ�λ���
	private Point location;
	private Dimension size = new Dimension(466, 510);;// �����С
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
        //������Ϣ
        nameLabel.setText(user.getName() + "(" + user.getAccount() + ")");
        signatureLabel.setText(user.getSignature());
        headimg.setIcon(new ImageIcon(IMChat.class
				.getResource("/images/\u5934\u50CF_" +user.getProfileID()+".png")));
        
        
		setTitle(user.getName());
		output_pane.setForeground(Color.BLACK);
		output_pane.setFont(new Font("����", Font.PLAIN, 12));
		output_pane.setBackground(Color.WHITE);
		output_pane.setEditable(false);
//		outputField.setColumns(10);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 505);

		// ������ɫ
		final int R = 72;
		final int G = 192;
		final int B = 242;
		// ���崰����ɫ����
		final Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 100),
				getWidth(), getHeight(), new Color(R, G, B, 200), true);
		// ��ʼ���������
		panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(p);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.setColor(new Color(72, 192, 242));
				Shape shape = null;
				shape = new RoundRectangle2D.Double(0, 0, frame.getWidth() - 1,
						frame.getHeight() - 1, 15.0D, 15.0D);// ���ƴ���߿�
				g2d.draw(shape);
				// g2d.drawImage(image, 8, 35, 40, 40, null);
				g2d.drawString("2010", 5, 15);
			}
		};
		panel.setForeground(new Color(72, 192, 242));

		setUndecorated(true);// ȥ������װ�Σ�����������С���ر�

		panel.setBackground(new Color(72, 192, 242));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		setContentPane(panel);

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBorder(null);
		// splitPane.setOpaque(false);
		splitPane.setResizeWeight(0.75D);
		splitPane.setDividerSize(1);// ���÷ָ�����С
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
		input_textArea.setFont(new Font("����", Font.PLAIN, 13));

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
		nameLabel.setFont(new Font("����", Font.BOLD, 12));
		

		head_leftPanel.add(nameLabel);
		signatureLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		signatureLabel.setForeground(Color.BLACK);
		signatureLabel.setFont(new Font("����", Font.PLAIN, 12));
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
		// ��С����ť
		GridBagConstraints gbc_max = new GridBagConstraints();
		gbc_max.anchor = GridBagConstraints.NORTHEAST;
		gbc_max.insets = new Insets(0, 0, 0, 5);
		gbc_max.gridx = 1;
		gbc_max.gridy = 0;
		panel_1.add(max, gbc_max);
		
		final JLabel closeButton1 = new JLabel();

		closeButton1.setIcon(new ImageIcon(IMChat.class
				.getResource("/images/closed.jpg")));

		
		// ��󻯰�ť
		GridBagConstraints gbc_closeButton1 = new GridBagConstraints();
		gbc_closeButton1.anchor = GridBagConstraints.NORTHEAST;
		gbc_closeButton1.gridx = 2;
		gbc_closeButton1.gridy = 0;
		panel_1.add(closeButton1, gbc_closeButton1);
		// ��ȡϵͳͼ�λ���
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
		confirmButton.setFont(new Font("����", Font.PLAIN, 12));
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		

		conButtPanel.add(confirmButton);
		closeButtPanel.setBackground(new Color(179, 225, 246));
		
		closeButton.setFont(new Font("����", Font.PLAIN, 12));
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
        //���������Ϊ�Զ�����
		input_textArea.setLineWrap(true);
		
		// �����С���¼�
		min.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isMax()) {// �ж��Ƿ������״̬
					// ��������״̬��ԭ��С��ʱ�������ֵ
					frame.setSize(size);
					// �����趨�ɼ�����
					setVisibleRegion(frame.getWidth(), frame.getHeight());
					frame.repaint();
					if (location != null) {
						frame.setLocation(location);
					}
					setMax(false);
				} else {
					// ����״̬��С��
					setExtendedState(JFrame.ICONIFIED);
					// frame.hide();// ����������
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
		// �ر��¼�
		closeButton1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				JLabel jl = (JLabel) e.getSource();
				jl.setIcon(new ImageIcon(IMChat.class.getResource("/images/closed.jpg")));
				dispose();
				//��IMChatManager���Ƴ���ǰ�Ķ���
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
		// ����¼�
		max.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				final Rectangle rec = translucencyCapableGC.getBounds();// ��ȡͼ�λ��������С
				Insets d = Toolkit.getDefaultToolkit().getScreenInsets(
						translucencyCapableGC);// ��ȡ���湤������С
				frame.setSize(rec.width, rec.height - d.bottom);// ���ô����С
				// �����趨�ɼ�����
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
		//�϶��¼�
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
		//��ͼ�¼�
		jietu_Label.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				Screenshot s=new Screenshot(IMChat.this);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				jietu_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/ͼƬ2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				jietu_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/ͼƬ1.png")));
			}
			
		});
		
		//�����ť�¼�
		browse_Label.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				browse_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/��ͼ1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				browse_Label.setIcon(new ImageIcon(IMChat.class.getResource("/images/��ͼ2.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				fileDialog.setDialogTitle("ѡ���ļ�");
				fileDialog.showOpenDialog(IMChat.this);	
				//ֻ��ѡ���ļ�
				fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				f=fileDialog.getSelectedFile();
				
				//�������ݰ����������ļ�
				Message m = new Message();
				m.setMsgType(MessageType.message_file);
				m.setSenderAccount(ownerId);	
				m.setReceiverAccount(Integer.parseInt(hideButton.getText()));
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ��
				m.setTime(s.format(new java.util.Date()));
				m.setFileName(f.getName());//�ļ���
				m.setState(2);//�������ļ�
				int length=(int) (f.length()/1000);
				m.setLength(length);//�ļ���С
				sendMessage(m);
			}	
		});
	}
	
	//���������ļ��߳�
	public void SendFile(){
		//׼�����ݰ�
		Message m = new Message();
		m.setSenderAccount(ownerId);
		m.setReceiverAccount(Integer.parseInt(hideButton.getText()));
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		m.setTime(s.format(new java.util.Date()));
		m.setState(3); //���ڴ���
		int length=(int) (f.length()/1000);
		m.setLength(length);
		//�����߳�
		int max =(int) (f.length()/10000);
		progressBar.setMaximum(max);
		progressBar.setMinimum(0);
		progressBar.setVisible(true);
		SendFileThread thread=new SendFileThread(f,IMChat.this,m);
		thread.start();
	}
	

	// �趨�ɼ�����
	public void setVisibleRegion(int width, int height) {
		Shape shape = null;
		shape = new RoundRectangle2D.Double(0, 0, width, height, 5.5D, 5.5D);
		AWTUtilities.setWindowShape(frame, shape);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//���������Ƿ���button
		if (e.getSource() == confirmButton) {
			Message m=(Message) makePackage(MessageType.message_comm_mes,null);
			//����
			sendMessage(m);
			//���Լ���Ouput����ʾ
		    showMessage(m,Color.BLACK);
		  //�������
			input_textArea.setText("");
		
		}if(e.getSource()==closeButton){
			//������ǹر�
			dispose();
			//��IMChatManager���Ƴ���ǰ�Ķ���
			Manager.delete(ownerId+""+user.getAccount());
		}

	}
	
	//�������ݰ�
	public Object makePackage(int megType,Image img){
		Message m = new Message();
		m.setSenderAccount(ownerId);
		m.setReceiverAccount(Integer.parseInt(hideButton.getText()));
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		m.setTime(s.format(new java.util.Date()));
		//��ͨ��Ϣ��
		if (megType == MessageType.message_comm_mes) {
			m.setMsgType(MessageType.message_comm_mes);
			m.setContent(input_textArea.getText());
			return m;
		//ͼƬ��Ϣ��
		}else if(megType==MessageType.message_img){
			m.setMsgType(MessageType.message_img);		
//			m.setContent(input_textArea.getText());
			m.setImg(img);
			return m;
		}

		return m;
	}
	
	
	//�������ݰ�
	public void sendMessage(Object o){
		try {
			Message m=(Message) o;
		    ClientConnToServerThread t = Manager.THREAD;
		    t.send(m);		
			
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
	}
	//��imageת���� byte[]
	public byte[] imageToBytes(Image img){
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		try {
			ImageIO.write((RenderedImage) img, "jpg", bos);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	
	
	//�� byte[]ת����image
	public Image  bytesToImage(byte[] b){
		try {
			return ImageIO.read(new ByteArrayInputStream(b));
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		return null;
	}
	
	
	//���ù�굽ĩβ setCaretPosition(docs.getLength())�����ù������Զ����¹���
	//���Լ��Ĵ�����ʾ
	public void showMessage(Message m,Color c) {		
		SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset,c);
		Document docs = output_pane.getDocument();// ����getDocument()����ȡ��JTextPane��Document
		
		//һ����Ϣ
		if(m.getMsgType().intValue()==MessageType.message_comm_mes){		
			String str=m.getSenderAccount() + " " +m.getTime()+"\n"+"     "+ m.getContent()+ "\n";
			try {
				docs.insertString(docs.getLength(), str, attrset);
				//���ù�굽ĩβ
				output_pane.setCaretPosition(docs.getLength());
			} catch (BadLocationException e) {
				
				e.printStackTrace();
			}
			
		}else if(m.getMsgType().intValue()==MessageType.message_img){
			//ͼƬ��Ϣ
			String str=m.getSenderAccount() + " " +m.getTime()+ "\n";
			try {
				//��ʾ������ ��ʱ��
				docs.insertString(docs.getLength(), str, attrset);
				//���ù�굽ĩβ
				output_pane.setCaretPosition(docs.getLength());
				output_pane.insertIcon(new ImageIcon(m.getImg()));
				docs.insertString(docs.getLength(), "\n", attrset);
				//���ù�굽ĩβ
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
		//���ù�굽ĩβ
		output_pane.setCaretPosition(docs.getLength());
	}

	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	              
	
}
