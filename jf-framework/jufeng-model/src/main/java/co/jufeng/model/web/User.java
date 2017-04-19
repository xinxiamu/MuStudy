package co.jufeng.model.web;

import javax.persistence.Column;
import javax.persistence.Entity;

import co.jufeng.accessor.EntityStrategyAuto;

@Entity(name = "JF_USER")
public class User extends EntityStrategyAuto{
	
	private static final long serialVersionUID = 5809341356510582185L;

	
	@Column(name = "USER_NAME", nullable = false, unique = true, length = 32)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false, length = 32)
	private String password;
	
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

}
