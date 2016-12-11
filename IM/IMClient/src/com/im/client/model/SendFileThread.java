package com.im.client.model;


import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;


import javax.swing.JProgressBar;

import com.im.client.util.Manager;
import com.im.client.view.IMChat;
import com.im.common.Message;
import com.im.common.MessageType;

public class SendFileThread extends Thread{
	private File f;
	private IMChat chat;
	final int LENGTH=20480;
    private byte[] b=new byte[LENGTH];
    private Message m;
	public SendFileThread(File f, IMChat chat,Message m) {
		this.f = f;
		this.chat = chat;
		this.m=m;
	}



	@Override
	public void run() {
		try {
			int num ;
//			bar.setStringPainted(true);
			JProgressBar bar=chat.getProgressBar();
			m.setMsgType(MessageType.message_file);
			m.setFileName(f.getName());
			BufferedInputStream bos=new BufferedInputStream(new FileInputStream(f));
			while((num = bos.read(b)) != -1){
				m.setFileByte(b);
				Manager.THREAD.send(m);
				int value=LENGTH/10000;
				bar.setValue(bar.getValue()+value);
			}
			bar.setVisible(false);
//			bar.setValue(0);
			//发送完毕
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.setTime(s.format(new java.util.Date()));
			m.setState(4);
			Manager.THREAD.send(m);
			//在窗口上显示发送成功
			chat.append(new Color(0,0,255), "文件"+f.getName()+"(" +f.length()/1000000+"M)"+"发送成功"+"\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
