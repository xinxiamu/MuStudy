package com.logistics.model.lg.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 用户身份证信息
 */
@Entity
@Table(name = "user_idcard",catalog="lg")
public class UserIdcard extends EntityStrategyAuto<UserIdcard> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -431332460986874625L;

	/**
	 * 身份证姓名
	 */
	@Column(nullable = false, length = 100)
	private String idCardName;

	/**
	 * 身份证号码
	 */
	@Column(nullable = false, length = 30, unique = true)
	private String idCardNo;
	
	/**
	 * 身份证地址全称
	 */
	@Column(nullable = false)
	private String idCardAddr;
	
	/**
	 * 身份证图片url
	 */
	@Column
	private String idCardPicPath;
	
	/**
	 * 性别
	 */
	@Column(length=5)
	private String sex;
	
	/**
	 * 民族
	 */
	@Column(length=15)
	private String nation;
	
	/**
	 * 出生年月
	 */
	@Temporal(TemporalType.DATE)
	@Column
	private Date bornDate;
	
	/**
	 * 签证机关
	 */
	@Column(length=100)
	private String visaOffice;
	
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
	 * 会员id
	 */
	@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(nullable = false, updatable = true)
	private User user;

	public String getIdCardName() {
		return idCardName;
	}

	public void setIdCardName(String idCardName) {
		this.idCardName = idCardName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getIdCardAddr() {
		return idCardAddr;
	}

	public void setIdCardAddr(String idCardAddr) {
		this.idCardAddr = idCardAddr;
	}

	public String getIdCardPicPath() {
		return idCardPicPath;
	}

	public void setIdCardPicPath(String idCardPicPath) {
		this.idCardPicPath = idCardPicPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getVisaOffice() {
		return visaOffice;
	}

	public void setVisaOffice(String visaOffice) {
		this.visaOffice = visaOffice;
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
}
