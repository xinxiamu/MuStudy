package com.logistics.model.lg.log;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 会员登录记录表
 * @author mt
 *
 */
@Entity
@Table(name = "log_user_login",catalog="lg_log")
public class UserLoginLog extends EntityStrategyAuto<UserLoginLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5161062471530599592L;


}
