package co.jufeng.jdbctemplate;

import java.beans.Transient;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import co.jufeng.accessor.IAccessor;
import co.jufeng.accessor.IEntityFactory;
import co.jufeng.accessor.criterion.ICriterion;
import co.jufeng.logger.LoggerUtil;


public class JdbcTemplateAccessor implements IAccessor{

	private static final long serialVersionUID = -2838974184653903777L;
	
	private JdbcTemplate jdbcTemplateRead;
	
	private JdbcTemplate jdbcTemplateWrite;
	

	public JdbcTemplate getJdbcTemplateRead() {
		return jdbcTemplateRead;
	}

	public void setJdbcTemplateRead(JdbcTemplate jdbcTemplateRead) {
		this.jdbcTemplateRead = jdbcTemplateRead;
	}

	public JdbcTemplate getJdbcTemplateWrite() {
		return jdbcTemplateWrite;
	}

	public void setJdbcTemplateWrite(JdbcTemplate jdbcTemplateWrite) {
		this.jdbcTemplateWrite = jdbcTemplateWrite;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable save(Object... entity) throws Exception {
		if(entity[0] instanceof Collection || entity[0] instanceof List || entity[0] instanceof Set){
			return save((Collection<Serializable>) entity[0]);
		}else if(entity.getClass().isArray()){
			return save(Arrays.asList(entity));
		}
		return null;
	}

	protected String getInsertSql(Class<?> entity) {
		Field[] classFields = getEntityFields(entity);
		List<String> fields = new ArrayList<String>();
		List<String> properties = new ArrayList<String>();
		for (int i = 0; i < classFields.length; i++) {
			String column = TableEntityMapperUtil.mapperToDB(classFields[i]
					.getName());
			if (column.equals(getIdColumn())) {
				continue;
			}
			fields.add(column);
			properties.add(":" + classFields[i].getName());
		}

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append(getTabeName(entity));
		sql.append("(");
		sql.append(StringUtils.join(fields, ","));
		sql.append(")");
		sql.append(" VALUES(");
		sql.append(StringUtils.join(properties, ","));
		sql.append(")");

		return sql.toString();
	}

	private Field[] getEntityFields(Class<?> clz) {
		List<Field> fields = new ArrayList<Field>();
		for (Field field : clz.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			if (field.getAnnotation(Transient.class) != null) {
				continue;
			}
			fields.add(field);
		}

		Field[] fieldArray = new Field[fields.size()];
		return fields.toArray(fieldArray);
	}

	private void setId(Object entity, Object id) throws Exception {
		String propertyName = StringUtils.capitalize(TableEntityMapperUtil.mapperToProperty(getIdColumn()));
		BeanUtils.getPropertyDescriptor(entity.getClass(), propertyName).getWriteMethod().invoke(entity, id);
	}

	private String getIdColumn() {
		return "id";
	}

	private NamedParameterJdbcTemplate getNamedParameterJdbcTemplateWriteOnly() {
		return new NamedParameterJdbcTemplate(this.getJdbcTemplateRead());
	}

	private Object getTabeName(Class<?> entity) {
		Entity e = entity.getAnnotation(Entity.class);
		String tableName = e.name();
		return tableName;
//		return TableEntityMapperUtil.mapperToDB(entity.getSimpleName());
	}

	@Override
	public Serializable save(Collection<Serializable> entities) throws Exception {
		Collection<Serializable> primaryKey = null;
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			primaryKey = new ArrayList<Serializable>();
			Iterator<?> it = entities.iterator();
			while (it.hasNext()) {
				Object entity = it.next();
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity);
				Class<?> entityClass = Class.forName(entity.getClass().getName());
				String insertSql = getInsertSql(entityClass);
				LoggerUtil.info(JdbcTemplateAccessor.class, insertSql);
				getNamedParameterJdbcTemplateWriteOnly().update(insertSql, paramSource, keyHolder);
				if (keyHolder.getKey() != null) {
					setId(entity, keyHolder.getKey().longValue());
				}
				if(entities.size() == 1){
					return keyHolder.getKey().longValue();
				}else{
					primaryKey.add(keyHolder.getKey().longValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Serializable) primaryKey;
	}

	@Override
	public boolean delete(Object... entity) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Class<?> entity, Serializable... id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Class<?> entity, Collection<Serializable> ids)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Collection<Serializable> entities) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll(IEntityFactory entityFactory) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object... entity) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Collection<Object> entities) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T getById(Serializable id, Class<T> t) throws Exception {
		return jdbcTemplateRead.queryForObject("select * from " + getTabeName(t) + " where id = 1", new BeanPropertyRowMapper<T>(t));
	}

	@Override
	public Serializable get(ICriterion... criterions) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getFunction(ICriterion... criterion) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty(Class<?> clazz, String criteriaKey,
			String criteriaValue) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object beginTransaction() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
