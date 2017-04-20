package com.logistics.model.lg.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 司机会员驾驶信息表
 * @author mt
 *
 */
@Entity
@Table(name = "user_drive",catalog="lg")
public class UserDrive extends EntityStrategyAuto<UserDrive> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8693835021333241059L;

}
