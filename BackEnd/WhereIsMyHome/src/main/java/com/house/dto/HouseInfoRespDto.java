package com.house.dto;

public class HouseInfoRespDto {
	DongCode dongCode;
	HouseInfo houseInfo;
	HouseDeal houseDeal;
	
	public DongCode getDongCode() {
		return dongCode;
	}
	public void setDongCode(DongCode dongCode) {
		this.dongCode = dongCode;
	}
	public HouseInfo getHouseInfo() {
		return houseInfo;
	}
	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}
	public HouseDeal getHouseDeal() {
		return houseDeal;
	}
	public void setHouseDeal(HouseDeal houseDeal) {
		this.houseDeal = houseDeal;
	}
}
