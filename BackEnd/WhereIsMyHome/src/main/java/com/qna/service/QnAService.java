package com.qna.service;

import java.util.List;

import com.qna.dto.Answer;
import com.qna.dto.Question;

public interface QnAService {
	List<Question> getAllQuestionList(); // 모든 질문 목록
	Question getQuestionDetail(int qustionNo); // 질문 상세정보
	List<Answer> getAnswerList(int questionNo); // 답변 가져오기

	int isComplete(int questionNo) throws Exception;
	
	int insertQuestion(Question question) throws Exception; // 질문 등록
	int deleteQuestion(int questionNo) throws Exception; // 질문 삭제
	int modifyQuestion(Question question) throws Exception; // 질문 수정
	
	int insertAnswer(Answer answer) throws Exception; //질문 등록
	int deleteAnswer(int answerNo) throws Exception; // 질문 삭제
	int modifyAnswer(Answer answer) throws Exception; // 질문 수정
	
}
