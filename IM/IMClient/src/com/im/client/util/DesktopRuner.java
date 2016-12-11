package com.im.client.util;

import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;

/**
 * 
 * @author FengLin ���ฺ����ϵͳ��Ĭ��������ȳ��򣬲�������������
 * @netSite ָ��Ҫ��ʾ����ַ
 */
public class DesktopRuner {
	private Desktop desktop;
	private URI uri;
	private String netSite;
	private Cursor hander;
	private String path="http://localhost:8080/IMServer/userAction!register";

	/** Creates a new instance of DesktopRuner */
	public DesktopRuner() {
		this.desktop = Desktop.getDesktop();
	}

	/*
	 * ���ϵͳ�Ƿ�֧�������
	 */
	public boolean checkBroswer() {
		if (desktop.isDesktopSupported()
				&& desktop.isSupported(Desktop.Action.BROWSE)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * ����Ĭ�������������������ʾָ����ַ
	 */
	public void runBroswer() {
		netSite =path;
		try {
			uri = new URI(netSite);
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
		try {
			desktop.browse(uri);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * �ı������״
	 */
	public void changeMouse(JLabel label) {
		hander = new Cursor(Cursor.HAND_CURSOR);
		label.setCursor(hander);
	}
}