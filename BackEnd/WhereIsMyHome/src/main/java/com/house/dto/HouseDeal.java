package com.house.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseDeal {
	long no;
	long aptCode;
	String dealAmount;
	int dealYear;
	int dealMonth;
	int dealDay;
	String area;
	String floor;
	String cancelDealType;
	
	public HouseDeal() {}
	public HouseDeal(ResultSet rs) throws SQLException {
		// TODO Auto-generated constructor stub
		this.no = rs.getLong("no");
		this.dealAmount = rs.getString("dealAmount");
		this.dealYear = rs.getInt("dealYear");
		this.dealMonth = rs.getInt("dealMonth");
		this.dealDay = rs.getInt("dealDay");
		this.area = rs.getString("area");
		this.floor = rs.getString("floor");
		this.cancelDealType = rs.getString("cancelDealType");
		this.aptCode = rs.getLong("aptCode");
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getCancelDealType() {
		return cancelDealType;
	}
	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType;
	}
}
