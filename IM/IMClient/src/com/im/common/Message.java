package com.im.common;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
	private Integer msgType;
	private String content;
	private Integer senderAccount;
	private Integer receiverAccount;
	private String time;
	private User myself;
	private List<User> friends;
	private transient Image img;
	private byte[] imageByte;
	
	//文件相关
	private byte[] fileByte;
	private String fileName;
	private int length;//文件长度，以KB为单位
	private int state; //0是不接受文件，1是接受文件，2请求发送文件，3文件传输中，4文件传送完成
	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public User getMyself() {
		return myself;
	}

	public void setMyself(User myself) {
		this.myself = myself;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Integer senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Integer getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Integer receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public List getFriends() {
		return friends;
	}

	public void setFriends(List friends) {
		this.friends = friends;
	}

}
