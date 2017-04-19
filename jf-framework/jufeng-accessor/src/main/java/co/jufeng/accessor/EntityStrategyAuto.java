package co.jufeng.accessor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import co.jufeng.string.ToStringBuilder;
import co.jufeng.accessor.EntityFactory;
import co.jufeng.accessor.IEntityFactory;

@MappedSuperclass
public abstract class EntityStrategyAuto extends EntityFactory implements IEntityFactory, Cloneable, Serializable {
	
	private static final long serialVersionUID = 7110625171763157114L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ADD_DATE", unique = true, nullable = false)
	private Date addDate = new Date();

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
