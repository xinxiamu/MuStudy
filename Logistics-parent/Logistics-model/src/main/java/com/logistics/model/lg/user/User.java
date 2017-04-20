package com.logistics.model.lg.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 会员信息基础表
 * 
 * @author mt
 * 
 */
@Entity
@Table(name = "user",catalog="lg")
public class User extends EntityStrategyAuto<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3584445608643927919L;

	/**
	 * 用户名
	 */
	@Column(nullable = false, unique = true, length = 50)
	private String userName;

	/**
	 * 密码
	 */
	@Column(nullable = false)
	private String password;

	/**
	 * 手机账号
	 */
	@Column(nullable = false, unique = true, length = 13)
	private String moblieAccount;

	/**
	 * 用户类型。关联到 {@link UserTypeDic.id} 
	 */
	@Column(nullable = false, length = 2)
	private Long userType_id;

	/**
	 * 头像路径。
	 */
	@Column(nullable = true)
	private String headPhoto;

	/**
	 * 用户真实姓名
	 */
	@Column(length = 50, nullable = true)
	private String realName;
	
	/**
	 * 备用手机号码。可修改。
	 */
	@Column(nullable = true, unique = false, length = 15)
	private String  mobileReserve;

	/**
	 * 用户常住地址。在UserAddress端维护
	 */
	@OneToOne(mappedBy="user")
	private UserAddress userAddress;
	
	/**
	 * 一对一。在UserIdcard端维护
	 */
	@OneToOne(mappedBy="user")
	private UserIdcard userIdcard;

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

	public String getMoblieAccount() {
		return moblieAccount;
	}

	public void setMoblieAccount(String moblieAccount) {
		this.moblieAccount = moblieAccount;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	public Long getUserType_id() {
		return userType_id;
	}

	public void setUserType_id(Long userType_id) {
		this.userType_id = userType_id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public UserIdcard getUserIdcard() {
		return userIdcard;
	}

	public void setUserIdcard(UserIdcard userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getMobileReserve() {
		return mobileReserve;
	}

	public void setMobileReserve(String mobileReserve) {
		this.mobileReserve = mobileReserve;
	}
}
