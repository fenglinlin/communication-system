package com.im.server.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.im.common.User;
import com.im.server.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 对应于struts.xml中的userAction的一些操作
 * list 显示用户列表
 * refresh  刷新页面
 * show   编辑用户
 * showInfo   添加或修改用户
 * @author FengLin
 *
 */
public class UserAction extends ActionSupport{
    private UserService us=new UserService();
    
    private List<User> users;
    private Integer pageNow=1;
    private int pageCount;
    private User user;
    private Integer account;
    private boolean online;
    
    private static String flag="2";
    private static String condition;
    private String register;
    
    //分页查询用户
	public String list() {
		
		if(flag.equals("2"))
		    users=us.findByPage(pageNow);
		else if (flag.equals("0")) {
			int i=0;
			try {
				i = Integer.parseInt(condition);
			} catch (NumberFormatException e) {
				i=0;
			}
			users=us.searchById(i);
		}
        else if (flag.equals("1")) {
			users=us.findByNamePage(pageNow,condition);
		}
		online=false;
		return "list";
	}
	
	//列出在线用户
	public String listOnline() {
		users=us.getOnlineUsers();
		online=true;
		return "list";
	}
	
	//踢掉用户
	public String offLine(){
		us.offLine(account);
		users=us.getOnlineUsers();
		online=true;
		return "list";
	}
	
	//编辑用户并展示
	public String edit(){
		user=us.findById(account);
		return "show";
	}
	
	//删除用户、并刷新页面
	public String delete(){
		us.delete(account);
		return "refresh";
	}
	
	//添加或修改用户
    public String saveOrUpdate(){
		us.saveOrUpdate(user);
		user=us.getLastUser();
		if("client".equals(register)){
			return "showInfo";
		}
		return "refresh";
	}
    
    public String register(){
    	register="client";
    	return "show";
    }
    
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Integer getPageNow() {
		return pageNow;
	}
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	
	public int getPageCount() {		
		return us.getPageCount();
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}


	public boolean isOnline() {
		return online;
	}


	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}
	

}
