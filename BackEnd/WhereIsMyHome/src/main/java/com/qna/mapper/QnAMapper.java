package com.qna.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qna.dto.Answer;
import com.qna.dto.Question;

@Mapper
public interface QnAMapper {
	List<Question> getAllQuestionList(); // 모든 질문 목록
	Question getQuestionDetail(int questionNo); // 질문 상세정보
	List<Answer> getAnswerList(int questionNo); // 답변 가져오기

	int getAnswerCount(int questionNo); // 답변개수
	int isComplete(int questionNo) throws Exception;// 조회수
	
	int insertQuestion(Question question) throws Exception; // 질문 등록
	int deleteQuestion(int questionNo) throws Exception; // 질문 삭제
	int modifyQuestion(Question question) throws Exception; // 질문 수정
	
	int insertAnswer(Answer answer) throws Exception; //질문 등록
	int deleteAnswer(int answerNo) throws Exception; // 질문 삭제
	int modifyAnswer(Answer answer) throws Exception; // 질문 수정
	
	
}
