package com.im.common;

/**
 * **
 * �����������
 * @author FengLin
 *
 */
public interface MessageType {

	Integer message_succeed=1;//�����ǵ�½�ɹ�
	Integer message_login_fail=2;//������¼ʧ��
	Integer message_comm_mes=3;//��ͨ��Ϣ��
	Integer message_get_onLineFriend=4;//Ҫ�����ߺ��ѵİ�
	Integer message_ret_onLineFriend=5;//�������ߺ��ѵİ�
	Integer message_offline=6;//����֪ͨ��
	Integer message_img=7;//ͼƬ��
	Integer message_file=8;//�ļ���
}
