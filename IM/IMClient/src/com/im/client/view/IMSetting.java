package com.im.client.view;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

import com.im.client.model.IMClientServer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IMSetting extends JFrame {

	private JPanel contentPane;
	private JTextField serverField;
	private JTextField portField;


	/**
	 * Create the frame.
	 */
	public IMSetting() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(470, 510, 340, 105);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228,244,255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel serverLabel = new JLabel("\u670D\u52A1\u5668\uFF1A");
		serverLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		serverLabel.setForeground(Color.BLACK);
		serverLabel.setBounds(7, 13, 54, 15);
		contentPane.add(serverLabel);
		
		serverField = new JTextField();
		serverField.setFont(new Font("宋体", Font.PLAIN, 12));
		serverField.setForeground(Color.BLACK);
		serverField.setBounds(71, 10, 111, 21);
		contentPane.add(serverField);
		serverField.setColumns(10);
		
		JLabel protLabel = new JLabel("\u7AEF\u53E3\u53F7\uFF1A");
		protLabel.setForeground(Color.BLACK);
		protLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		protLabel.setBounds(192, 13, 54, 15);
		contentPane.add(protLabel);
		
		portField = new JTextField();
		portField.setText("9999");
		portField.setFont(new Font("宋体", Font.PLAIN, 12));
		portField.setForeground(Color.BLACK);
		portField.setBounds(256, 10, 66, 21);
		contentPane.add(portField);
		portField.setColumns(10);
		
		JButton yes = new JButton("\u786E\u5B9A");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IMClientServer.ADDRESS=serverField.getText();
				IMClientServer.POST=Integer.parseInt(portField.getText());
				dispose();
			}
		});
		yes.setFont(new Font("宋体", Font.PLAIN, 12));
		yes.setForeground(Color.BLACK);
		yes.setBounds(229, 38, 93, 23);
		setResizable(false);
		contentPane.add(yes);
		setVisible(true);
	}
}
