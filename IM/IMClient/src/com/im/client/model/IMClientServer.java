package com.im.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.im.common.Message;

/**
 * 
 * 客户端请求连接服务端，进行Socket长连接通信
 * @author FengLin
 *
 */
public class IMClientServer {
	public static String ADDRESS = "localhost";
	public static int POST = 9999;
	public Socket socket=null;
//	private static IMClientServer ims=new IMClientServer();;

	public IMClientServer() {
		try {
			socket = new Socket(ADDRESS, POST);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	public Object sendLoginInfo(Object o) {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message ms = (Message) ois.readObject();
			return ms;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void sendMesgInfo(Object o) {

	}
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void close() {
            try {
            	socket.close();
			} catch (IOException e) {
				System.out.println("未正常关闭");
				e.printStackTrace();
			}
	}

}
