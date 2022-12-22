package com.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
	private int question_no, hit;
	private String memberid, title, content, createdate, isprivate, iscomplete; 


}
