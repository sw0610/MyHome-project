package com.notice.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.notice.dto.Notice;

@Service
public interface NoticeService {
	ArrayList<Notice> selectAll();
	int writeNotice(Notice notice) throws Exception;
	int deleteNotice(int num) throws Exception;
	int modifyNotice(Notice notice) throws Exception;
	Notice selectOne(int num);
	ArrayList<Notice> searchByTitle(String title);
}
