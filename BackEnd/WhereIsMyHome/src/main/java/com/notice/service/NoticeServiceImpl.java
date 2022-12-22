package com.notice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notice.dto.Notice;
import com.notice.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper mapper;
	
	@Override
	public ArrayList<Notice> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int writeNotice(Notice notice) throws Exception {
		return mapper.writeNotice(notice);
	}

	@Override
	public int deleteNotice(int num) throws Exception {
		return mapper.deleteNotice(num);
	}

	@Override
	public int modifyNotice(Notice notice) throws Exception {
		return mapper.modifyNotice(notice);
	}

	@Override
	public Notice selectOne(int num) {
		return mapper.selectOne(num);
	}

	@Override
	public ArrayList<Notice> searchByTitle(String title) {
		return mapper.searchByTitle(title);
	}

}
