package com.im.server.service;

import java.util.ArrayList;
import java.util.List;

import com.im.common.User;
import com.im.server.dao.UserDao;
import com.im.server.thread.ClientThread;

/**
 * 数据逻辑处理层
 * @author FengLin
 *
 */
public class UserService {
	private UserDao ud = new UserDao();
	public final static int pageSize = 9;
 

	public User getLastUser() {
		return ud.getLastUser();
	}

	public void delete(Integer id) {
		ud.delete(id);
	}

	public List<User> findAll() {
		return ud.findAll();
	}

	public User findById(Integer id) {
		return ud.findById(id);
	}

	public List<User> findByPage(int pageNow) {
		List<User> list = new ArrayList<User>();
		int begin = pageSize * (pageNow - 1);
		int num = pageSize;
//		System.out.println(begin+"------"+num);
		list=ud.findByLimit(begin, num);
		return list;
	}
	public List<User> searchById(int id) {
		List<User> list = new ArrayList<User>();
		
		list.add(ud.findById(id));
		return list;
	}
	
	
	public void saveOrUpdate(User u){
		System.out.println("u.getAccount()="+u.getAccount());
		if(u.getAccount()==null)
			ud.save(u);
		else ud.update(u);
	}
	
	public List<User> findByNamePage(int pageNow,String name) {
		List<User> list = new ArrayList<User>();
		int begin = pageSize * (pageNow - 1);
		int num = pageSize;
		System.out.println(begin+"------"+num);
		list=ud.findByName(begin, num,"%"+name+"%");
		return list;
	}
	
	public Integer getPageCount() {
		Integer pageCount = null;
		int rowCount=ud.getRowCount();
		if (rowCount % pageSize == 0)
			pageCount = rowCount / pageSize;
		else
			pageCount = rowCount / pageSize + 1;
		System.out.println("pageCount="+pageCount);
		return pageCount;
	}	

	public  List<User> getOnlineUsers(){
		List<User> list = new ArrayList<User>();
		Integer id[]=ClientThreadManager.getOnlinessUserId();
		for(Integer i:id){
			list.add(ud.findById(i));
		}
		return list;
	}
	
	public  void offLine(Integer account){
		ClientThread ct=ClientThreadManager.getClientThread(account);
		ct.interrupt();
	}
	
	
	public static void main(String[] args) {
		List l=new UserService().findByPage(1);
	    System.out.println(l.size());
	}
}
