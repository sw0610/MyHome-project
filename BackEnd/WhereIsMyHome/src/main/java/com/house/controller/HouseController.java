package com.house.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.dto.HouseDeal;
import com.house.dto.HouseInfo;
import com.house.dto.HouseInfoRespDto;
import com.house.service.HouseService;
import com.jwt.JwtServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private HouseService service;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@GetMapping("/term/{term}")
	public ResponseEntity<?> searchTerm(@PathVariable String term){
		try {
			List<HouseInfoRespDto> list = service.searchTerm(term);
			System.out.println(list.size());
			System.out.println(list.get(0));
			return new ResponseEntity<List<HouseInfoRespDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	
	@PostMapping("/search/dongList")
	public ResponseEntity<?> searchDongCode(String[] dongList){
		try {
			System.out.println(Arrays.toString(dongList));
			List<HouseInfo> list = service.selectAllByDongCode(dongList);
			return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/search/housedeal/{aptCode}")
	public ResponseEntity<?> searchHouseDeal(@PathVariable Long aptCode){
		try {
			List<HouseDeal> list = service.searchHouseDeal(aptCode);
			
			return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/interest") //관심지역 검색
	public ResponseEntity<?> getUserInterestArea(HttpServletRequest request){
		try {
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				String memberid = jwtService.getUserId();
				List<HouseInfo> list = service.getUserInterestArea(memberid);
				System.out.println(list);
				return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<List<HouseInfo>>(new ArrayList<HouseInfo>(), HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/interest") //관심지역 추가
	public ResponseEntity<?> addUserInterestArea(@RequestBody HashMap<String,String> map,HttpServletRequest request){

		try {
			int result = 0;
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				String memberid = jwtService.getUserId();
				result = service.addUserInterestArea(memberid,Long.parseLong(map.get("aptCode")));
			}
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/interest/{aptCode}") //관심지역 추가
	public ResponseEntity<?> addUserInterestArea(@PathVariable long aptCode,HttpServletRequest request){
		try {
			int result = 0;
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				String memberid = jwtService.getUserId();
				result = service.deleteUserInterestArea(memberid,aptCode);
			}
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
