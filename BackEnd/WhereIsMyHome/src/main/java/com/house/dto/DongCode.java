package com.house.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DongCode {
	String dongCode;
	String sidoName;
	String gugunName;
	String dongName;
	public DongCode() {}
	public DongCode(String dongCode, String sidoName, String gugunName, String dongName) {		
		// TODO Auto-generated constructor stub
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}
	public DongCode(String dongCode, ResultSet rs) throws SQLException {
		// TODO Auto-generated constructor stub
		super();
		this.dongCode = dongCode;
		this.sidoName = rs.getString("sidoName");
		this.gugunName = rs.getString("gugunName");
		this.dongName = rs.getString("dongName"); 
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	
	
}
