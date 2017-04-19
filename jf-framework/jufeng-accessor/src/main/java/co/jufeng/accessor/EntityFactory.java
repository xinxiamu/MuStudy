package co.jufeng.accessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.Reference;


public class EntityFactory extends AbstractEntityFactory {
	
	private static final long serialVersionUID = 2204184890085453177L;
	private String name;
	private Object value;
	private boolean cacheable;
	private boolean empty;
	private IEntityFactory entityFactory;
	public static Map<String, Object> ENTITY_FACTORYS = new HashMap<String, Object>();
	public EntityFactory() {
	}
	
	public EntityFactory(IEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public String getName() {
		return name;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	public static IEntityFactory add(Class<?>... entityClass){
		return initEntityClass(null, false, entityClass);
	}
	
	public static IEntityFactory add(PropertyEnum ALIAS, Class<?>... entityClass){
		return initEntityClass(ALIAS, entityClass);
	}
	
	public static IEntityFactory add(boolean cacheable, Class<?>... entityClass){
		return initEntityClass(cacheable, entityClass);
	}
	
	public static IEntityFactory add(PropertyEnum ALIAS, boolean cacheable, Class<?>... entityClass){
		return initEntityClass(ALIAS, cacheable, entityClass);
	}
	
	public static IEntityFactory createEntity(Class<?>... entityClass){
		return initEntityClass(null, false, entityClass);
	}
	
	public static IEntityFactory createEntity(PropertyEnum ALIAS, Class<?>... entityClass){
		return initEntityClass(ALIAS, entityClass);
	}
	
	public static IEntityFactory createEntity(boolean cacheable, Class<?>... entityClass){
		return initEntityClass(cacheable, entityClass);
	}
	
	public static IEntityFactory createEntity(PropertyEnum ALIAS, boolean cacheable, Class<?>... entityClass){
		return initEntityClass(ALIAS, cacheable, entityClass);
	}
	
	public static IEntityFactory newInstance(Class<?>... entityClass){
		return initEntityClass(null, false, entityClass);
	}
	
	public static IEntityFactory newInstance(PropertyEnum ALIAS, Class<?>... entityClass){
		return initEntityClass(ALIAS, entityClass);
	}
	
	public static IEntityFactory newInstance(boolean cacheable, Class<?>... entityClass){
		return initEntityClass(cacheable, entityClass);
	}
	
	public static IEntityFactory newInstance(PropertyEnum ALIAS, boolean cacheable, Class<?>... entityClass){
		return initEntityClass(ALIAS, cacheable, entityClass);
	}
	
	private static IEntityFactory initEntityClass(PropertyEnum ALIAS, Class<?>... entityClass) {
		return initEntityClass(ALIAS, false, entityClass);
	}
	
	private static IEntityFactory initEntityClass(boolean cacheable, Class<?>... entityClass) {
		return initEntityClass(null, cacheable, entityClass);
	}
	
	private static IEntityFactory initEntityClass(PropertyEnum ALIAS, boolean cacheable, Class<?>... entityClass) {
		IEntityFactory entityFactory = null;
		Object obj = null;
		List<String> list = new ArrayList<String>();
		try {
			for (int i = 0; i < entityClass.length; i++) {
				obj = entityClass[i].newInstance();
				if(obj instanceof EntityFactory){
					entityFactory = new EntityFactory((IEntityFactory) obj);
					ENTITY_FACTORYS.put(entityClass[i].getSimpleName(), entityClass[i].newInstance());
					list.add(entityClass[i].getName());
					entityFactory.setCacheable(cacheable);
				}
			}
			if(!list.isEmpty()){
				entityFactory.setValue(list.toArray());
				if(null != ALIAS){
					entityFactory.setName(ALIAS.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityFactory;
	}

	@Override
	public boolean isEmpty() {
		return ENTITY_FACTORYS.size() == 0;
	}

	@Override
	public int size() {
		return ENTITY_FACTORYS.size();
	}

	@Override
	public IEntityFactory getEntityFactory() {
		return entityFactory;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setValue(Object... value) {
		this.value = value;
	}
	
	@Override
	public boolean getCacheable() {
		return cacheable;
	}
	
	@Override
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	@Override
	public Reference getReference() throws NamingException {
		return null;
	}
	
	public boolean getEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
}
