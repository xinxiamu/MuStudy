package co.jufeng.model.web;

import javax.persistence.Column;
import javax.persistence.Entity;

import co.jufeng.accessor.EntityStrategyAuto;

@Entity(name = "JF_API_AUTHORIZED")
public class ApiAuthorized extends EntityStrategyAuto{
	
	private static final long serialVersionUID = -6832766895251350891L;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "SERVICE_NAME", nullable = false, length = 32)
	private String serviceName;
	
	@Column(name = "SERVICE_METHOD", nullable = false, unique = true, length = 32)
	private String serviceMethod;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceMethod() {
		return serviceMethod;
	}

	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	
}
