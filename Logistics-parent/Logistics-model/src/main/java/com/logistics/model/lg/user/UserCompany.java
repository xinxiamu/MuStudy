package com.logistics.model.lg.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 注册公司信息。
 * @author mt
 *
 */
@Entity
@Table(name = "user_company",catalog="lg")
public class UserCompany extends EntityStrategyAuto<UserCompany> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2352854779237329867L;


}
