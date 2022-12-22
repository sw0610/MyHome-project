package com.user.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.user.dto.User;

@Mapper
public interface UserMapper {
	int join(User user);
	String findPwd(String id);
	User login(User user);
	User checkInfo(String id);
	int modify(User user);
	int delete(String id);
	List<User> search(HashMap<String, String> map);
	List<User> getAll();
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	User confirmPwd(User user) throws SQLException;
	void updatePwd(User user);
	
}
