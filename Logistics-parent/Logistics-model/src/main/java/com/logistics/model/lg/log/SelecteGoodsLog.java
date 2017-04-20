package com.logistics.model.lg.log;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 查询货源日志表。一般对应查询表单。
 * @author mt
 *
 */
@Entity
@Table(name = "log_selecte_goods",catalog="lg_log")
public class SelecteGoodsLog extends EntityStrategyAuto<SelecteGoodsLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7461805481454645654L;


}
