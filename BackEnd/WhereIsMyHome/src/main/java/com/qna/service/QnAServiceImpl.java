package com.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qna.dto.Answer;
import com.qna.dto.Question;
import com.qna.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	QnAMapper mapper;
	
	@Override
	public List<Question> getAllQuestionList() {
		return mapper.getAllQuestionList();
	}

	@Override
	public Question getQuestionDetail(int questionNo) {
		mapper.getAnswerCount(questionNo);
		return mapper.getQuestionDetail(questionNo);
	}

	@Override
	public List<Answer> getAnswerList(int questionNo) {
		return mapper.getAnswerList(questionNo);
	}

	@Override
	public int insertQuestion(Question question) throws Exception {
		return mapper.insertQuestion(question);
	}

	@Override
	public int deleteQuestion(int questionNo) throws Exception {
		return mapper.deleteQuestion(questionNo);
	}

	@Override
	public int modifyQuestion(Question question) throws Exception {
		return mapper.modifyQuestion(question);
	}

	@Override
	public int insertAnswer(Answer answer) throws Exception {
		return mapper.insertAnswer(answer);
	}

	@Override
	public int deleteAnswer(int answerNo) throws Exception {
		return mapper.deleteAnswer(answerNo);
	}

	@Override
	public int modifyAnswer(Answer answer) throws Exception {
		return mapper.modifyAnswer(answer);
	}

	@Override
	public int isComplete(int questionNo) throws Exception {
		return mapper.isComplete(questionNo);
	}

}
