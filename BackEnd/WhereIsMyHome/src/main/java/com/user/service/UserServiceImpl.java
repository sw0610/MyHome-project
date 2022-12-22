package com.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.User;
import com.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public int join(User user) {
		return mapper.join(user);
	}

	@Override
	public String findPwd(String id) {
		return mapper.findPwd(id);
	}

	@Override
	public User login(User user) {
		return mapper.login(user);
	}

	@Override
	public User checkInfo(String id) {
		return mapper.checkInfo(id);
	}


	@Override
	public int modify(User user) {
		return mapper.modify(user);
	}

	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}

	@Override
	public List<User> search(HashMap<String, String> map) {
		return mapper.search(map);
	}

	@Override
	public List<User> getAll() {
		return mapper.getAll();
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		mapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return mapper.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		mapper.deleteRefreshToken(map);
	}

	@Override
	public User confirmPwd(User user) throws SQLException {
		return mapper.confirmPwd(user);
		
	}

	@Override
	public void updatePwd(User user) {
		mapper.updatePwd(user);
	}

	public User kakaoLogin(User user) {
		// TODO Auto-generated method stub
		return null;
	}


}
