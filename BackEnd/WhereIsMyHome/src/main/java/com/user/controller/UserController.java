package com.user.controller;

import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jwt.JwtServiceImpl;
import com.user.dto.User;
import com.user.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/*
	 * int checkId(String id); int addInterest(String id, String regCode);
	 */

	@Autowired
	UserService service;

	@Autowired
	private JwtServiceImpl jwtService;

//	private final JavaMailSender javaMailSender;

	// 회원 가입
	@PostMapping("/join")
	public ResponseEntity<?> userJoin(@RequestBody User user) {
		service.join(user);
		return new ResponseEntity(HttpStatus.OK);
	}

	// 회원 정보 수정
	@PutMapping("/modify")
	public ResponseEntity<?> userModify(@RequestBody User user) {
		service.modify(user);
		System.out.println(user);
		return new ResponseEntity(HttpStatus.OK);
	}

	// 회원 정보
	@GetMapping("/info/{memberid}")
	public ResponseEntity<?> getUserInfo(@PathVariable String memberid, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		System.out.println("info 호출");

		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				User user = service.checkInfo(memberid);
				resultMap.put("userInfo", user);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	// 회원 삭제(탈퇴)
	@DeleteMapping("/delete/{memberid}")
	public ResponseEntity<?> userDelete(@PathVariable String memberid) {
		int del = service.delete(memberid);
		if (del == 1) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println("login 호출");
		try {
			User userLogin = service.login(user);
			if (userLogin != null) {
				System.out.println(user);
				String accessToken = jwtService.createAccessToken("memberid", userLogin.getMemberid());// key, data
				String refreshToken = jwtService.createRefreshToken("memberid", userLogin.getMemberid());// key, data
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 로그아웃
	@GetMapping("/logout/{memberid}")
	public ResponseEntity<?> userLogout(@PathVariable String memberid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.deleRefreshToken(memberid);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		if (jwtService.checkToken(token)) {
			if (token.equals(service.getRefreshToken(user.getMemberid()))) {
				String accessToken = jwtService.createAccessToken("memberid", user.getMemberid());

				resultMap.put("access-token", accessToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/findpwd")
	public ResponseEntity<?> findPwd(@RequestBody User user){
		try {
			User res = service.confirmPwd(user);
			System.out.println(user);
			if(res==null) {
				return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else {
				return new ResponseEntity<User>(res, HttpStatus.OK);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	}
	
	//비밀번호 수정
	@PutMapping("/updatepwd")
	public ResponseEntity<?> updatePwd(@RequestBody User User){
		service.updatePwd(User);
		return  new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/kakao")
	public ResponseEntity<?> kakaoLogin(@RequestBody HashMap<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		String reqURL = "https://kapi.kakao.com/v2/user/me";
		String result = "";
		JsonElement element = null;
		// access_token을 이용하여 사용자 정보 조회
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + map.get("access_code")); // 전송할 header 작성,
																						  // access_token전송

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리로 JSON파싱
			JsonParser parser = new JsonParser();
			element = parser.parse(result);
			String id = "@" + element.getAsJsonObject().get("id").getAsString();
			String name = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
			boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email")
					.getAsBoolean();
			String email = "";
			if (hasEmail) {
				JsonElement email_element = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email");
				System.out.println(email_element);
				if(email_element!=null) {
					email = email_element.getAsString();
				}
				else {
					email = "NULL";
				}
				
			}else {
				email = "NULL";
			}
			User user = new User();
			user.setMemberid(id);
			user.setPwd(map.get("access_code").substring(0, 29));
			user.setName(name);
			user.setEmail(email);
			User try_login = service.checkInfo(id);
			if (try_login == null) {
				int try_join = service.join(user);
				if(try_join!=1) {
					resultMap.put("message", "fail");
					status = HttpStatus.ACCEPTED;
				}else {
					try_login=service.checkInfo(id);
				}
			}
			
			User userLogin = service.login(try_login);
			if (userLogin != null) {
				String accessToken = jwtService.createAccessToken("memberid", userLogin.getMemberid());// key, data
				String refreshToken = jwtService.createRefreshToken("memberid", userLogin.getMemberid());// key, data
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}

			br.close();

		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
