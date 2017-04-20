package com.mu.logistics.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhengJianYan
 * @update mutian
 * @since 30.06.2014
 * @see Abstract entity class that can be used as the base class for all domain
 *      driven entity
 * @see Copyright:JuFeng
 * @see QQ:375273058
 * @see <a href="mailto:admin@jufeng.co">Email</a>
 * @since 1.3
 */
@MappedSuperclass
public abstract class EntityStrategyAuto<T> implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -207519689013657658L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 时间戳。数据唯一性和录入时间顺序
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime = new Date();

	/**
	 * 乐观锁。数据版本控制。默认1
	 */
	@Version
	@Column(nullable = true)
	private Integer version = 1;

	public EntityStrategyAuto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
