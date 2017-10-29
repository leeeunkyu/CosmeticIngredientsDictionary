package com.work.service;

import java.util.Map;

import com.work.dto.User;
import com.work.model.UserDao;

public interface UserService  {
	public String login(String userId,String userPw);
	public int join(User user);
}
