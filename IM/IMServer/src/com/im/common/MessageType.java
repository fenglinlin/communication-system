package com.im.common;

/**
 * **
 * 定义包的种类
 * @author FengLin
 *
 */
public interface MessageType {

	Integer message_succeed=1;//表明是登陆成功
	Integer message_login_fail=2;//表明登录失败
	Integer message_comm_mes=3;//普通信息包
	Integer message_get_onLineFriend=4;//要求在线好友的包
	Integer message_ret_onLineFriend=5;//返回在线好友的包
	Integer message_offline=6;//下线通知包
	Integer message_img=7;//图片包
	Integer message_file=8;//文件包
}
