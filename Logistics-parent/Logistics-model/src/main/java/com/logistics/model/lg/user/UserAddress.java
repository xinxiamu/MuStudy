package com.logistics.model.lg.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 会员常住地址表
 * 
 * @author mt
 * 
 */
@Entity
@Table(name = "user_address",catalog="lg")
public class UserAddress extends EntityStrategyAuto<UserAddress> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5959445534994920209L;

	/**
	 * 常住地址全称
	 */
	@Column(nullable = false)
	private String permanentAdr;

	/**
	 * 省
	 */
	@Column(nullable = true, length = 50)
	private String province;

	/**
	 * 市
	 */
	@Column(nullable = true, length = 50)
	private String city;

	/**
	 * 县/区
	 */
	@Column(nullable = true, length = 50)
	private String counties;
	
	/**
	 * 城镇
	 */
	@Column(nullable = true, length = 50)
	private String town;
	
	/**
	 * 街道/村 + 门牌号
	 */
	@Column(nullable = true, length = 100)
	private String street;
	
	/**
	 * 邮编
	 */
	@Column(nullable = true, length = 11)
	private String postCode;
	
	/**
	 * 关联用户
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, unique = true)
	private User user;

	public String getPermanentAdr() {
		return permanentAdr;
	}

	public void setPermanentAdr(String permanentAdr) {
		this.permanentAdr = permanentAdr;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounties() {
		return counties;
	}

	public void setCounties(String counties) {
		this.counties = counties;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
