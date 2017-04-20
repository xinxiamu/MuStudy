package com.logistics.model.lg.res;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 车辆信息
 * @author mt
 *
 */
@Entity()
@Table(name = "car",catalog="lg")
public class Car extends EntityStrategyAuto<Car> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 999553206009544276L;

	/**
	 * 车牌号
	 */
	@Column(nullable = false, unique = true, length = 20)
	private String licensePlateNumber;
	
	/**
	 * 车辆类型。关联表
	 */
	private Long carType_id;
	
	/**
	 * 车长
	 */
	private Double carLength;
	
	/**
	 * 车宽
	 */
	private Double carWidth;
	
	/**
	 * 车高
	 */
	private Double carHeight;
	
	/**
	 * 车辆载重
	 */
	private Double carLoad;
	
	/**
	 * 购车时间
	 */
	private Date carBuyTime;
	
	/**
	 * 车辆照片。多个照片用英文逗号隔开。
	 */
	private String carPic;
	
	/**
	 * 车辆跟随人姓名。
	 */
	private String followPerson;
	
	/**
	 * 车辆跟随手机号码。
	 */
	private String followMobile;
	
	/**
	 * 车辆信息审核状态。true审核，否则未审核。
	 */
	private Boolean carAuditStatus = false;
	
	/**
	 * 车辆所属会员。关联{@link User.id}
	 */
	private Long user_id;

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public Long getCarType_id() {
		return carType_id;
	}

	public void setCarType_id(Long carType_id) {
		this.carType_id = carType_id;
	}

	public Double getCarLength() {
		return carLength;
	}

	public void setCarLength(Double carLength) {
		this.carLength = carLength;
	}

	public Double getCarWidth() {
		return carWidth;
	}

	public void setCarWidth(Double carWidth) {
		this.carWidth = carWidth;
	}

	public Double getCarHeight() {
		return carHeight;
	}

	public void setCarHeight(Double carHeight) {
		this.carHeight = carHeight;
	}

	public Double getCarLoad() {
		return carLoad;
	}

	public void setCarLoad(Double carLoad) {
		this.carLoad = carLoad;
	}

	public Date getCarBuyTime() {
		return carBuyTime;
	}

	public void setCarBuyTime(Date carBuyTime) {
		this.carBuyTime = carBuyTime;
	}

	public String getCarPic() {
		return carPic;
	}

	public void setCarPic(String carPic) {
		this.carPic = carPic;
	}

	public String getFollowPerson() {
		return followPerson;
	}

	public void setFollowPerson(String followPerson) {
		this.followPerson = followPerson;
	}

	public String getFollowMobile() {
		return followMobile;
	}

	public void setFollowMobile(String followMobile) {
		this.followMobile = followMobile;
	}

	public Boolean getCarAuditStatus() {
		return carAuditStatus;
	}

	public void setCarAuditStatus(Boolean carAuditStatus) {
		this.carAuditStatus = carAuditStatus;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
