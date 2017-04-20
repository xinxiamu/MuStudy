package com.logistics.model.lg.log;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 删除记录表
 * @author mt
 *
 */
@Entity
@Table(name = "log_del_res",catalog="lg_log")
public class DeleteResLog extends EntityStrategyAuto<DeleteResLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3456591055521069661L;
}
