package com.logistics.model.lg.log;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * app客户端异常崩溃日志表。用于改善客户端体验
 * @author mt
 *
 */
@Entity
@Table(name = "log_app_crash",catalog="lg_log")
public class AppCrashLog extends EntityStrategyAuto<AppCrashLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5516690295479305218L;


}
