package com.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.user.dto.User;

@Service
public interface UserService {
	int join(User user);
	String findPwd(String id);
	User login(User user);
	User checkInfo(String id);
	int modify(User user);
	int delete(String id);
	List<User> search(HashMap<String, String> map);
	List<User> getAll();
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	User confirmPwd(User user) throws SQLException;
	void updatePwd(User user);
	User kakaoLogin(User user);
}
