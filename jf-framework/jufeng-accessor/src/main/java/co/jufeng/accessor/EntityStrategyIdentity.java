package co.jufeng.accessor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import co.jufeng.string.ToStringBuilder;
import co.jufeng.accessor.EntityFactory;
import co.jufeng.accessor.IEntityFactory;

@MappedSuperclass
public abstract class EntityStrategyIdentity extends EntityFactory implements IEntityFactory, Cloneable, Serializable {

	private static final long serialVersionUID = -5960844038946356794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "ADD_DATE", unique = true, nullable = false)
	private Date addDate = new Date();

//	@Version
//	@Column(name = "UPDATE_DATE", unique = true, nullable = false, updatable = true)
//	private  Timestamp updateDate = new Timestamp(System.currentTimeMillis());

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

//	public Timestamp getUpdateDate() {
//		return updateDate;
//	}
//
//	public void setUpdateDate(Timestamp updateDate) {
//		this.updateDate = updateDate;
//	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
