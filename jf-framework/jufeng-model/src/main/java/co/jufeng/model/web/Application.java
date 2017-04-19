package co.jufeng.model.web;

import javax.persistence.Column;
import javax.persistence.Entity;

import co.jufeng.accessor.EntityStrategyIdentity;

@Entity(name = "JF_APPLICATION")
public class Application extends EntityStrategyIdentity {

	private static final long serialVersionUID = -7941818368109614615L;
	
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "APP_ID", nullable = false, unique = true, length = 13)
	private Long appId;
	
	@Column(name = "APP_NAME", nullable = false, unique = true, length = 32)
	private String appName;
	
	@Column(name = "APP_SECRET", nullable = false, unique = true, length = 32)
	private String appSecret;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}


	
}