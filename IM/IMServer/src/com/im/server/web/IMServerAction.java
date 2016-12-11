package com.im.server.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.im.server.service.IMServer;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ͨ������IMServer�������͹رշ���(Socket)
 * ��Ӧ��Ӧҳ��Ŀ������񡢹رշ���İ�ť
 * @author FengLin
 *
 */
public class IMServerAction extends ActionSupport {

	private IMServer server;

	public void startServer() {
		
		server = IMServer.getIMServer();
		server.startServer();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain");// �������Ϊ������
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			// ֱ�������Ӧ������
			out.write("�����ѿ���");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeServer() {
		
		server = IMServer.getIMServer();
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain");// �������Ϊ������
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		String info="�����ѹر�";
		try {
		   out= response.getWriter();		
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			server.close();
		} catch (Exception e) {
			info="�����ѹر��ǳ����쳣��Ϊ�����ر�";
		}
		
		// ֱ�������Ӧ������
		out.write(info);
		out.flush();
		out.close();

	}
}
