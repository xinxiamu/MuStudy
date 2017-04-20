package com.mu.logistics.model.iwl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mu.logistics.model.base.EntityStrategyAuto;

@Entity(name = "user_type")
public class UserType extends EntityStrategyAuto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7375870563833114377L;
	
	@Column(nullable = false,length=50)
	private String userTypeName;
	
	@Column(nullable = false)
	private int userTypeCode;

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public int getUserTypeCode() {
		return userTypeCode;
	}

	public void setUserTypeCode(int userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
}
