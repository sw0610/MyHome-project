package com.notice.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notice.dto.Notice;
import com.notice.service.NoticeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	NoticeService service;
	/*
	 * 	<!-- 
	ArrayList<Notice> selectAll();
	int writeNotice(Notice notice);
	int deleteNotice(int num);
	int modifyNotice(Notice notice);
	Notice selectOne(int num);
	 -->
	 */
	
	@GetMapping("/list")
	public ResponseEntity<?> selectAll(){
		ArrayList<Notice> list=null;
//		if(title.length()>0) {
//			 service.searchByTitle(title);
//		}
//		else {
			list = service.selectAll();
//		}
		if(list!=null && !list.isEmpty()) {
			return new ResponseEntity<ArrayList<Notice>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/article/{no}")
	public Notice selectOne(@PathVariable int no){
		Notice notice = service.selectOne(no);
		return notice;
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> writeNotice(@RequestBody Notice notice) {
		try {
			service.writeNotice(notice);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modifyNotice(@RequestBody Notice notice){
		try {
			System.out.println(notice);
			service.modifyNotice(notice);
			return new ResponseEntity(HttpStatus.OK);
 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<?> deleteNotice(@PathVariable int no){
		try {
			service.deleteNotice(no);
			return new ResponseEntity(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/search/{title}")
	public ResponseEntity<?> searchByTitle(@PathVariable String title){
		ArrayList<Notice> list = service.searchByTitle(title);
		if(list!=null && !list.isEmpty()) {
			return new ResponseEntity<ArrayList<Notice>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
