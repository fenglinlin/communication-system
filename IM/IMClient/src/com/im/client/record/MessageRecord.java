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
				bw.write(msg.getTime()+"    ������Ϣ��       "+msg.getReceiverAccount()+":"+"\n");
				bw.write(msg.getContent()+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_img){
				bw.write(msg.getTime()+"    ����ͼƬ��       "+msg.getReceiverAccount()+":"+"\n");
				bw.write("ͼƬ��С  ��"+msg.getImageByte().length+"byte"+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_file){
				if(msg.getState()==2){
					bw.write(msg.getTime()+"    �������ļ���       "+msg.getReceiverAccount()+":"+"\n");
					bw.write("�ļ��� ��"+msg.getFileName()+"  �ļ���С: "+msg.getLength()+"KB"+"\n");
				}
				if(msg.getState()==0){
					bw.write(msg.getTime()+"    "+msg.getReceiverAccount()+"�ܾ������ļ�  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==1){
					bw.write(msg.getTime()+"    "+msg.getReceiverAccount()+"ͬ������ļ�  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==4){
					bw.write(msg.getTime()+" ���͸�   "+msg.getReceiverAccount()+"���ļ�  :"+msg.getFileName()+"�������"+"\n");
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
				bw.write(msg.getTime()+"    �յ���Ϣ����       "+msg.getSenderAccount()+":"+"\n");
				bw.write(msg.getContent()+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_img){
				bw.write(msg.getTime()+"    �յ�ͼƬ����      "+msg.getSenderAccount()+":"+"\n");
				bw.write("ͼƬ��С  ��"+msg.getImageByte().length+"byte"+"\n");
			}
			else if(msg.getMsgType().intValue()==MessageType.message_file){
				if(msg.getState()==2){
					bw.write(msg.getTime()+"    �յ������ļ��������      "+msg.getSenderAccount()+":"+"\n");
					bw.write("�ļ��� ��"+msg.getFileName()+"  �ļ���С: "+msg.getLength()+"KB"+"\n");
				}
				if(msg.getState()==0){
					bw.write(msg.getTime()+"    ��ܾ������ļ�  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==1){
					bw.write(msg.getTime()+"    ��ͬ������ļ�  :"+msg.getFileName()+"\n");
				}
				if(msg.getState()==4){
					bw.write(msg.getTime()+" ����   "+msg.getSenderAccount()+"���ļ�  :"+msg.getFileName()+"�������"+"\n");
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
