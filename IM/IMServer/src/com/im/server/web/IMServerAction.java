package com.im.server.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.im.server.service.IMServer;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 通过调用IMServer来开启和关闭服务(Socket)
 * 响应对应页面的开启服务、关闭服务的按钮
 * @author FengLin
 *
 */
public class IMServerAction extends ActionSupport {

	private IMServer server;

	public void startServer() {
		
		server = IMServer.getIMServer();
		server.startServer();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain");// 设置输出为文字流
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			// 直接输出响应的内容
			out.write("服务已开启");
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
		response.setContentType("text/plain");// 设置输出为文字流
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		String info="服务已关闭";
		try {
		   out= response.getWriter();		
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			server.close();
		} catch (Exception e) {
			info="服务已关闭是出现异常，为正常关闭";
		}
		
		// 直接输出响应的内容
		out.write(info);
		out.flush();
		out.close();

	}
}
