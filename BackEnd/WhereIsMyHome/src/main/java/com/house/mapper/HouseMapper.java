package com.house.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.house.dto.DongCode;
import com.house.dto.HouseDeal;
import com.house.dto.HouseInfo;
import com.house.dto.HouseInfoRespDto;
import com.user.dto.User;

@Mapper
public interface HouseMapper {
	
	List<HouseInfo> selectAllByDongCode(String[] dongList);

	List<HouseDeal> searchHouseDeal(Long aptCode);

	List<HouseInfoRespDto> searchTerm(String term);

	List<HouseInfo> getUserInterestArea(String memberid);

	int addUserInterestArea(String memberid, long aptCode);

	List<String> searchSido();

	List<String> searchGugun(String sidoName);

	List<DongCode> searchDong(String sidoName, String gugunName);

	int deleteUserInterestArea(String memberid, long aptCode);
}
