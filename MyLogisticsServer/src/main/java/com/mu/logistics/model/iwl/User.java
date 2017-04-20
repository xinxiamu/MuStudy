package com.mu.logistics.model.iwl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.mu.logistics.model.base.EntityStrategyAuto;

/**
 * 
 * @类描述：会员信息基础表
 * 
 * @创建人：mt
 * @创建时间：2014年7月11日下午3:09:19
 * @修改人：Administrator
 * @修改时间：2014年7月11日下午3:09:19
 * @修改备注：
 * @version v1.0
 * @Copyright
 * @mail 932852117@qq.com
 */
@Entity(name = "user")
public class User extends EntityStrategyAuto<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3584445608643927919L;

	/**
	 * 用户名
	 */
	@Column(nullable = false, unique = true)
	private String userName;

	/**
	 * 密码
	 */
	@Column(nullable = false)
	private String password;

	/**
	 * 手机账号
	 */
	@Column(nullable = false, unique = true)
	private String moblieAccount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, updatable = true)
	private UserType userType;

	/**
	 * 头像
	 */
	@Column(nullable = true)
	private String headPhoto;

	/**
	 * 用户真实姓名
	 */
	@Column
	private String realName;

	/**
	 * 用户常驻地址
	 */
	@Column(nullable = true)
	private String userAddress;
	
	/**
	 * 会员性别
	 */
	@Column(nullable = true, length = 5)
	private String userSex;

	/**
	 * 数据终端来源。 类型：app_android、app_ios、app_pc,web_android、web_ios、web_pc
	 */
	@Column(nullable = false, length = 50)
	private String fromSystem;

	/**
	 * 删除标志。1删除，默认0。
	 */
	@Column(length = 1)
	private int del = 0;

	/**
	 * 删除时间
	 */
	@Column
	private Date delDate;

	@Transient
	private int userType_id;

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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	public int getUserType_id() {
		return userType_id;
	}

	public void setUserType_id(int userType_id) {
		this.userType_id = userType_id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
