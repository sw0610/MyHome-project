package com.notice.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.notice.dto.Notice;

@Mapper
public interface NoticeMapper {
	ArrayList<Notice> selectAll();
	int writeNotice(Notice notice) throws Exception;
	int deleteNotice(int num) throws Exception;
	int modifyNotice(Notice notice) throws Exception;
	Notice selectOne(int num);
	ArrayList<Notice> searchByTitle(String title);
}
