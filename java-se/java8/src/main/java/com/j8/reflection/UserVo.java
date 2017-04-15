package com.j8.reflection;

import com.j8.annotations.AdapterLayout;
import com.j8.annotations.web.Param;

@AdapterLayout(layoutValue=11)
public class UserVo {
	
	@Param(value = "userName",required = true,defaultValue = "")
	private String userName;
	
	@Param(value = "password",required = true,defaultValue = "")
	private String password;
	
	@Param(value = "year",required = true,defaultValue = "27")
	private int year;
	
	@Param(value = "isMutou",required = false,defaultValue = "false")
	private boolean isMutou;

	public boolean isMutou() {
		return isMutou;
	}

	public void setMutou(boolean isMutou) {
		this.isMutou = isMutou;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
