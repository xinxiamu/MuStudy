package co.jufeng.json;

import java.sql.Timestamp;
import java.util.Date;

public class UserInfo {
	
	private Long id = 12L;
	private String name = "wergf";
	private Integer age = 85;
	private Double meony = 5.2d;
	private Date date = new Date();  
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public UserInfo() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getMeony() {
		return meony;
	}
	public void setMeony(Double meony) {
		this.meony = meony;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", age=" + age
				+ ", meony=" + meony + ", date=" + date + ", timestamp="
				+ timestamp + "]";
	}

	
}
