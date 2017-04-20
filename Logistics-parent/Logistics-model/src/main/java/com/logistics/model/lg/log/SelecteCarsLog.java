package com.logistics.model.lg.log;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 用户查询发布车源日志表。字段一般对应查询表单
 * @author mt
 */
@Entity
@Table(name = "log_selecte_cars",catalog="lg_log")
public class SelecteCarsLog extends EntityStrategyAuto<SelecteCarsLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6174601460818643747L;


}
