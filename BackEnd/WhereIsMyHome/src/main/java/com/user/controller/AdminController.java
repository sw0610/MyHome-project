package com.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.User;
import com.user.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService service;
	
	// 모든 회원 정보 목록
	@GetMapping("/userlist")
	public ResponseEntity<?> userAllList(HttpSession session){
		List<User> list = service.getAll();
		if(list!=null && !list.isEmpty()) {
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	// 회원 정보 검색
	@GetMapping("/search/{option}/{word}")
	public ResponseEntity<?> userSearch(@PathVariable String option, @PathVariable String word, HttpSession session){


		if(!word.equals("")) {
			HashMap<String, String> map = new HashMap<>();
			map.put("option", option);
			map.put("word",word);

			List<User> list = service.search(map);
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("검색어를 입력하세요", HttpStatus.NO_CONTENT);
		}
	}
	
}
