package com.im.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import sun.security.krb5.internal.UDPClient;

import com.im.common.User;

/**
 * 连接数据库
 * 对数据库的增删改查操作
 * 仅与数据库交互
 * @author FengLin
 *
 */
public class UserDao {

	public static final String URL="jdbc:mysql://localhost:3306/dh";//数据库 连接
	public static final String USERNAME="root";//用户名
	public static final String PASSWORD="123456";//密码
	
	public static final String FIND_ALL="select * from user";
	public static final String FIND_BY_ID="select * from user where account =?";
	public static final String FIND_BY_Limit="select * from user limit ?,?";
	public static final String FIND_BY_NAME="select * from user where name like ? ";
	public static final String SAVE="insert into user values (null,?,?,?,?,?,?)";
	public static final String UPDATE="update user set name=?,password=?,signature=?,age=?,sex=? where account=?";
	public static final String DELETE="delete from user where account=?";
	
	public Connection conn;
	public PreparedStatement ps;
	public ResultSet rs;
	private int rowCount; 
	
	//删除用户
	public void delete(Integer account){
		conn=getConnection();
    	try {
			ps=conn.prepareStatement(DELETE);
			ps.setInt(1, account);
			ps.execute();
			System.out.println("delete over");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}    
	}
	
	//更新用户
	public void update(User u){
		conn=getConnection();
    	try {
			ps=conn.prepareStatement(UPDATE);
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getSignature());
			ps.setInt(4, u.getAge());
			ps.setString(5, u.getSex());
			ps.setInt(6, u.getAccount());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}    
	}
	
	//保存用户
    public void save(User u){
    	conn=getConnection();
    	try {
			ps=conn.prepareStatement(SAVE);
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getSignature());
			ps.setInt(4, 1);
			ps.setInt(5, u.getAge());
			ps.setString(6, u.getSex());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}    	
	}
    
    //根据ID查询用户
	public User findById(Integer id){
		User u=null;
		conn=getConnection();
        try {
			ps=conn.prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				u=rowMapper(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return u;
	}
	
	//获取数据库中最后一个用户
	public User getLastUser(){
		User u=null;
		conn=getConnection();
		try {
			ps=conn.prepareStatement("select * from user order by account desc limit 0,1");
			rs=ps.executeQuery();
			while(rs.next()){
				u=rowMapper(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return u;
	}
	
	//获取所有用户
	public List<User> findAll(){
		List<User> list=new ArrayList<User>();
		conn=getConnection();
        try {
			ps=conn.prepareStatement(FIND_ALL);
			rs=ps.executeQuery();
			while(rs.next()){
				User u=rowMapper(rs);
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	
	
	public  List<User> findByLimit(int begin,int num){
		List<User> list=new ArrayList<User>();
		conn=getConnection();
        try {
			ps=conn.prepareStatement(FIND_BY_Limit);
			ps.setInt(1, begin);
			ps.setInt(2, num);
			rs=ps.executeQuery();
			while(rs.next()){
				User u=rowMapper(rs);
				list.add(u);
			}
			PreparedStatement p=conn.prepareStatement("select count(*) from user");
			rs=p.executeQuery();
			while(rs.next()){
				
				rowCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	
	//通过用户名查询用户
	public  List<User> findByName(int begin,int num,String name){
		List<User> list=new ArrayList<User>();
		conn=getConnection();
        try {
			ps=conn.prepareStatement(FIND_BY_NAME);
			ps.setString(1, name);
			rs=ps.executeQuery();
			while(rs.next()){
				User u=rowMapper(rs);
				list.add(u);
			}
			PreparedStatement p=conn.prepareStatement("select count(*) from user where name like ?");
			p.setString(1, name);
			rs=p.executeQuery();
			while(rs.next()){		
				rowCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	
	//去除数据库查出的数据，存放到User对象u中
	public User rowMapper(ResultSet rs) throws SQLException{
		User u = new User();
		u.setAccount(rs.getInt("account"));
        u.setName(rs.getString("name"));
        u.setPassword(rs.getString("password"));
        u.setSignature(rs.getString("signature"));
        u.setProfileID(rs.getInt("profileID"));
        u.setAge(rs.getInt("age"));
        u.setSex(rs.getString("sex"));
		return u;
	}
	
	
	/**
	 * 通过jdbc驱动进行数据库的连接
	 * @return
	 */
	public Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	//关闭数据库连接
	public void close() {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int getRowCount() {
		return rowCount;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public static void main(String[] args) {
		UserDao us= new UserDao();
		List l=us.findByLimit(0, 15);
		System.out.println(l.size());
	}
}
