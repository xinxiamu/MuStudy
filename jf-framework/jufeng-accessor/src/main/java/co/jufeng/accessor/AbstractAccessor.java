package co.jufeng.accessor;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Table;

import co.jufeng.accessor.criterion.ICriterion;

public abstract class AbstractAccessor implements IAccessor {
	
	private static final long serialVersionUID = -6262800394663390052L;

	protected int entityFactorySize;
	
	protected int criterionSize;
	
	protected Object[] arrayEntity;
	
	IAccessor accessor;
	
	boolean isEmptyEntityFactory(){
		return entityFactorySize == 0;
	}
	
	public int getCriterionSize() {
		return criterionSize;
	}
	
	public Object[] getArrayEntity() {
		return arrayEntity;
	}
	
	protected String getClassName(Object object){
		return object.toString().split("class")[1].trim();
	}
	
	String getTableName(Class<?> clazz) {
        Table annotation = (Table)clazz.getAnnotation(Table.class);
        if(annotation != null){
            return annotation.name();
        }
        return null;
    }
	
	public IAccessor getAccessor() {
		return accessor;
	}

	public void setAccessor(IAccessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public Serializable save(Object... entity) throws Exception {
		return accessor.save(entity);
	}

	@Override
	public Serializable save(Collection<Serializable> entities)	throws Exception {
		return accessor.save(entities);
	}
	
	@Override
	public boolean delete(Object... entity) throws Exception {
		return accessor.delete(entity);
	}

	@Override
	public boolean delete(Class<?> entity, Serializable... id) throws Exception {
		return accessor.delete(entity, id);
	}

	@Override
	public boolean delete(Class<?> entity, Collection<Serializable> ids) throws Exception {
		return accessor.delete(entity, ids);
	}

	@Override
	public boolean delete(Collection<Serializable> entities) throws Exception {
		return accessor.delete(entities);
	}

	@Override
	public boolean deleteAll(IEntityFactory entityFactory) throws Exception {
		return accessor.deleteAll(entityFactory);
	}

	@Override
	public boolean update(Object... entity) throws Exception {
		return accessor.update(entity);
	}

	@Override
	public boolean update(Collection<Object> entities) throws Exception {
		return accessor.update(entities);
	}

	@Override
	public Serializable get(ICriterion... criterions) throws Exception {
		return accessor.get(criterions);
	}

	@Override
	public Serializable getFunction(ICriterion... criterion) throws Exception {
		return accessor.getFunction(criterion);
	}

	@Override
	public <T> T getById(Serializable id, Class<T> T) throws Exception {
		return accessor.getById(id, T);
	}
	@Override
	public boolean isEmpty(Class<?> clazz, String criteriaKey, String criteriaValue) throws Exception {
		return accessor.isEmpty(clazz, criteriaKey, criteriaValue);
	}

	@Override
	public Object beginTransaction() throws Exception {
		return accessor.beginTransaction();
	}

	@Override
	public void commit() throws Exception {
		accessor.commit();
	}

	@Override
	public void rollback() throws Exception {
		accessor.rollback();
	}

	@Override
	public void close() throws Exception {
		accessor.close();
	}

	
	
}
