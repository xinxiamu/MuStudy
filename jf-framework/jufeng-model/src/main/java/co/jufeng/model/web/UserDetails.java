package co.jufeng.model.web;

import javax.persistence.Column;
import javax.persistence.Entity;

import co.jufeng.accessor.EntityStrategyIdentity;

@Entity(name = "JF_USER_DETAILS")
public class UserDetails extends EntityStrategyIdentity {

	private static final long serialVersionUID = 2946328738157840076L;

	@Column(name = "USER_ID", nullable = false, unique = true)
	private Long userId;
	
	@Column(name = "MOBILE", nullable = false, unique = true, length = 11)
	private String mobile;
	
	@Column(name = "EMAIL", length = 20)
	private String email;
	
	@Column(name = "QQ", length = 20)
	private String qq;
	
	@Column(name = "TELEPHONE", length = 13)
	private String telephone;
	
	@Column(name = "REAL_NAME", length = 10)
	private String realName;
	
	@Column(name = "SEX", length = 1)
	private Integer sex;
	
	@Column(name = "STATUS", length = 1, columnDefinition = "int default 1")
	private Integer status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}