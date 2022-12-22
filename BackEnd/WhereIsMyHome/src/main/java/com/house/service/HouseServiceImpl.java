package com.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.dto.DongCode;
import com.house.dto.HouseDeal;
import com.house.dto.HouseInfo;
import com.house.dto.HouseInfoRespDto;
import com.house.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private HouseMapper mapper;
	
	@Override
	public List<HouseInfo> selectAllByDongCode(String[] dongCode){
		return mapper.selectAllByDongCode(dongCode);
	}

	@Override
	public List<HouseDeal> searchHouseDeal(Long aptCode) {
		return mapper.searchHouseDeal(aptCode);
	}

	@Override
	public List<HouseInfoRespDto> searchTerm(String term) {
		return mapper.searchTerm(term);
	}

	@Override
	public List<HouseInfo> getUserInterestArea(String memberid) {
		return mapper.getUserInterestArea(memberid);
	}

	@Override
	public int addUserInterestArea(String memberid, long aptCode) {
		// TODO Auto-generated method stub
		return mapper.addUserInterestArea(memberid,aptCode);
	}

	@Override
	public List<String> searchSido() {
		// TODO Auto-generated method stub
		return mapper.searchSido();
	}

	@Override
	public List<String> searchGugun(String sidoName) {
		// TODO Auto-generated method stub
		return mapper.searchGugun(sidoName);
	}

	@Override
	public List<DongCode> searchDong(String sidoName, String gugunName) {
		// TODO Auto-generated method stub
		return mapper.searchDong(sidoName,gugunName);
	}

	@Override
	public int deleteUserInterestArea(String memberid, long aptCode) {
		// TODO Auto-generated method stub
		return mapper.deleteUserInterestArea(memberid,aptCode);
	}



	
}
