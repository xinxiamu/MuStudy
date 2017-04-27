package com.ymu.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_details", catalog = "ymu_master")
public class UserDetails implements java.io.Serializable {

	// Fields

	private Long id;
	private String mobile;
	private String realName;
	private Long userId;

	// Constructors

	/** default constructor */
	public UserDetails() {
	}

	/** full constructor */
	public UserDetails(String mobile, String realName, Long userId) {
		this.mobile = mobile;
		this.realName = realName;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "mobile", nullable = false)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "real_name", nullable = false)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}