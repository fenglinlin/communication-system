package com.im.client.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.im.common.Message;
import com.im.common.MessageType;

public class Screenshot extends JFrame  implements MouseListener,MouseMotionListener{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	private JPanel contentPane;
	BufferedImage bufImg;
	BufferedImage targetImg;
    private int x=0;
    private int y=0;
    private int width=0;
    private int height=0;
    private boolean over=false; //��ͼ״̬�Ƿ����
    private IMChat chat;

	/**
	 * Create the frame.
	 */
	public Screenshot(IMChat chat) {
		this.chat=chat;
		getToolkit();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setUndecorated(true);
		try {
			bufImg=new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(bufImg, 0, 0,this);
		//���ƽ�ͼ�ı߽�
		g2d.drawRect(x, y, width, height);
	}
	
	
	public void success(){
		try {
			targetImg=(new Robot()).createScreenCapture(new Rectangle(x,y,width+1,height+1));
			
			Message m=(Message) chat.makePackage(MessageType.message_img, targetImg);
			m.setImageByte(chat.imageToBytes(targetImg));
//			//��ͼƬ�ȱ��浽����
//			File f=new File("images/ok.jpg");
//			try {
//				ImageIO.write(targetImg,"jpg",f);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//����
			chat.sendMessage(m);
			//��ʾ���Լ����������
			chat.showMessage(m, Color.BLACK);
			
//			System.out.println(x+"  "+y+"  "+width+"  "+height+"  ");

		} catch (AWTException e) {
			e.printStackTrace();
		} 
	}

	
	

	public void mouseClicked(MouseEvent e) {
		//���������Ҽ��Ļ�
		if(e.getButton()==MouseEvent.BUTTON3){
			dispose();
		}	
		if(e.getClickCount()==2){
			success();
			dispose();
		}
		
	}


	public void mousePressed(MouseEvent e) {
		if(!over){
			x=e.getX();
			y=e.getY();
		}
		
	}


	public void mouseReleased(MouseEvent e) {
		//��ͼ�Ѿ�����
		over=true;
		
	}

	
	public void mouseDragged(MouseEvent e) {
		int w=width;
		int h=height;
		
//		System.out.println(x+"  "+y+"  "+width+"  "+height+"  ");
		
		width=e.getX()-x;
		height=e.getY()-y;
		
		w=(w>width?w:width);
		h=(h>height?h:height);
		//�ػ�ı߽�Ҫ����ʵ����΢���
		//ֻrepaintһ�����򣬷�ֹ��Ļ��˸
		repaint(x,y,w+2,h+2);
		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}

