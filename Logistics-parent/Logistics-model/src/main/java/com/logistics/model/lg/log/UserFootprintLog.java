package com.logistics.model.lg.log;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * app客户端用户浏览足迹。记录客户端访问页面足迹
 * @author mt
 *
 */
@Entity
@Table(name = "log_user_footprint",catalog="lg_log")
public class UserFootprintLog extends EntityStrategyAuto<UserFootprintLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2785058354201469483L;


}
