# communication-system
基于Java和JP的企业内部通信系统

配置:
tomcat
jdk 本地环境
eclipse 开发环境
mysql 数据库

框架：C/S架构 （C：Client客户端，S：Server服务端）


IMServer服务端：
	ServerThread.java   	启动
	UserDao.java	配置数据库连接（没有做配置项）
  后台管理模块：用户管理、在线用用户管理
	

IMClient客户端：
	IMClientLogin.java	启动
  多客户端启动，用于通信，支持传输图片、文字
