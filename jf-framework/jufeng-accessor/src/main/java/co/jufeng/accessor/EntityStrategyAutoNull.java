package co.jufeng.accessor;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import co.jufeng.string.ToStringBuilder;

@MappedSuperclass
public abstract class EntityStrategyAutoNull extends EntityFactory implements IEntityFactory, Cloneable, Serializable {

	private static final long serialVersionUID = -5551088268840123101L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
}
