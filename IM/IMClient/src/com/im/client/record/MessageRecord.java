package com.im.client.record;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.im.common.Message;
import com.im.common.MessageType;

public class MessageRecord {

	public void afterSend(Object o) {
		Message msg=(Message) o;
		try {
			File f = new File("c:/"+msg.getSenderAccount().toString()+".txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));
			if(msg.getMsgType().intValue()==MessageType.message_comm_mes){
				bw.write(msg.getTime()+"    发送消息给       "+msg.getReceiverAccount()+":"+"\n");
				bw.write(msg.getContent()+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_img){
				bw.write(msg.getTime()+"    发送图片给       "+msg.getReceiverAccount()+":"+"\n");
				bw.write("图片大小  ："+msg.getImageByte().length+"byte"+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_file){
				if(msg.getState()==2){
					bw.write(msg.getTime()+"    请求发送文件给       "+msg.getReceiverAccount()+":"+"\n");
					bw.write("文件名 ："+msg.getFileName()+"  文件大小: "+msg.getLength()+"KB"+"\n");
				}
				if(msg.getState()==0){
					bw.write(msg.getTime()+"    "+msg.getReceiverAccount()+"拒绝接受文件  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==1){
					bw.write(msg.getTime()+"    "+msg.getReceiverAccount()+"同意接受文件  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==4){
					bw.write(msg.getTime()+" 发送给   "+msg.getReceiverAccount()+"的文件  :"+msg.getFileName()+"发送完成"+"\n");
				}
				
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void afterReceive(Object o) {
		System.out.println("--------------------------------------------------");
		Message msg=(Message) o;
		try {
			File f = new File("C:/"+msg.getReceiverAccount().toString()+".txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));
			if(msg.getMsgType().intValue()==MessageType.message_comm_mes){
				bw.write(msg.getTime()+"    收到消息来自       "+msg.getSenderAccount()+":"+"\n");
				bw.write(msg.getContent()+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_img){
				bw.write(msg.getTime()+"    收到图片来自      "+msg.getSenderAccount()+":"+"\n");
				bw.write("图片大小  ："+msg.getImageByte().length+"byte"+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_file){
				if(msg.getState()==2){
					bw.write(msg.getTime()+"    收到发送文件清除来自      "+msg.getSenderAccount()+":"+"\n");
					bw.write("文件名 ："+msg.getFileName()+"  文件大小: "+msg.getLength()+"KB"+"\n");
				}
				if(msg.getState()==0){
					bw.write(msg.getTime()+"    你拒绝接受文件  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==1){
					bw.write(msg.getTime()+"    你同意接受文件  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==4){
					bw.write(msg.getTime()+" 来自   "+msg.getSenderAccount()+"的文件  :"+msg.getFileName()+"发送完成"+"\n");
				}	
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
