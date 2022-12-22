package com.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.dto.DongCode;
import com.house.service.HouseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/area")
public class AreaDataController {
	@Autowired
	private HouseService service;
	
	@GetMapping("")
	public ResponseEntity<?> searchSido(){
		try {
			List<String> list = service.searchSido();
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{sidoName}")
	public ResponseEntity<?> searchGugun(@PathVariable String sidoName){
		try {
			List<String> list = service.searchGugun(sidoName);
			System.out.println(list.size());
			System.out.println(list.get(0));
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{sidoName}/{gugunName}")
	public ResponseEntity<?> searchDong(@PathVariable String sidoName,@PathVariable String gugunName){
		try {
			List<DongCode> list = service.searchDong(sidoName,gugunName);
			return new ResponseEntity<List<DongCode>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
