package com.qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

import com.qna.dto.Answer;
import com.qna.dto.Question;
import com.qna.service.QnAService;
import com.user.dto.User;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/qna")
@Api("QnA Controller")
@CrossOrigin("*")
public class QnAController {
	
	@Autowired
	QnAService service;
	
	// 모든 질문 목록
	@GetMapping("/question")
	public ResponseEntity<?> getAllQuestionList(){
		List<Question> list = service.getAllQuestionList();
		if(list!=null && !list.isEmpty()) {
			return new ResponseEntity<List<Question>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//질문 상세정보
	@GetMapping("/question/{no}")
	public ResponseEntity<?> getQuestion(@PathVariable int no,HttpServletRequest request) {
		System.out.println(request);
		Question question = service.getQuestionDetail(no);
		if(question!=null ) {
			return new ResponseEntity<Question>(question, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	//답변 가져오기
	@GetMapping("/answer/{questionno}")
	public ResponseEntity<?> getAnswerList(@PathVariable int questionno){
		List<Answer> list = service.getAnswerList(questionno);
		if(list!=null && !list.isEmpty()) {
			return new ResponseEntity<List<Answer>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/question/write")
	public ResponseEntity<?> insertQuestion(@RequestBody Question question, HttpSession session){
		try {

			service.insertQuestion(question);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/question/delete/{questionno}")
	public ResponseEntity<?> deleteQuestion(@PathVariable int questionno){
		try {
			service.deleteQuestion(questionno);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/question/modify")
	public ResponseEntity<?> modifyQuestion(@RequestBody Question question){
		try {
			System.out.println(question);
			service.modifyQuestion(question);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/answer/write")
	public ResponseEntity<?> insertAnswer(@RequestBody Answer answer){
		try {
			service.insertAnswer(answer);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/answer/delete/{answerno}")
	public ResponseEntity<?> deleteAnswer(@PathVariable int answerno){
		try {
			service.deleteAnswer(answerno);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/answer/modify")
	public ResponseEntity<?> modifyAnswer(Answer answer){
		try {
			service.modifyAnswer(answer);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//답변 완료
	@PutMapping("question/complete/{questionno}")
	public ResponseEntity<?> isComplete(@PathVariable int questionno){
		System.out.println("sdfasdfa");
		try {
			service.isComplete(questionno);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}


}
