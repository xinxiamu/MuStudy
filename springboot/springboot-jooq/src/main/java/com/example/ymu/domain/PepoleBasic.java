package com.example.ymu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.ymu.domain.type.SexType;

/**
 * 个人基础信息。
 * 
 * @author Administrator
 *
 */
@Entity
public class PepoleBasic extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7154284115402423593L;

	/**
	 * 姓名。
	 */
	@Column(nullable = false, length = 80)
	private String name;

	/**
	 * 性别
	 */
	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private SexType sextType;

	/**
	 * 生日
	 */
	@Column(nullable = true, length = 4)
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdayTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SexType getSextType() {
		return sextType;
	}

	public void setSextType(SexType sextType) {
		this.sextType = sextType;
	}

	public Date getBirthdayTime() {
		return birthdayTime;
	}

	public void setBirthdayTime(Date birthdayTime) {
		this.birthdayTime = birthdayTime;
	}
}
