package com.work.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.work.dto.User;
import com.work.model.UserDao;
@Repository
public class UserServiceImpl implements UserService {

	private UserDao dao;
	@Autowired
	public void setUserDao(UserDao dao) {
		this.dao=dao;
	}
	
	public String login(String userId,String userPw) {
		return dao.login(userId,userPw);
	}
	
	/**
	 * ȸ�� ����
	 * 1. ���̵� �ߺ� üũ
	 * 2. ������� : ȸ�� ����
	 * 3. ����� : ���� 0 ��ȯ
	 */
	public int join(User user) {
		boolean isIdCheck = dao.isId(user.getUserId());
		if(!isIdCheck) {
			return dao.join(user);
		} 
		return 0;
	}
}
