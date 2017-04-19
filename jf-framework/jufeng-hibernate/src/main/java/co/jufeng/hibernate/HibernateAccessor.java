package co.jufeng.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.Transformers;

import co.jufeng.accessor.AbstractAccessor;
import co.jufeng.accessor.IEntityFactory;
import co.jufeng.accessor.PropertyEnum;
import co.jufeng.accessor.criterion.ICriterion;
import co.jufeng.accessor.criterion.IProjection;
import co.jufeng.accessor.criterion.IQuery;
import co.jufeng.accessor.criterion.IRestrictions;
import co.jufeng.hibernate.criterion.impl.Avg;
import co.jufeng.hibernate.criterion.impl.Between;
import co.jufeng.hibernate.criterion.impl.Count;
import co.jufeng.hibernate.criterion.impl.Eq;
import co.jufeng.hibernate.criterion.impl.Ge;
import co.jufeng.hibernate.criterion.impl.Group;
import co.jufeng.hibernate.criterion.impl.Gt;
import co.jufeng.hibernate.criterion.impl.Hql;
import co.jufeng.hibernate.criterion.impl.Ilike;
import co.jufeng.hibernate.criterion.impl.In;
import co.jufeng.hibernate.criterion.impl.IsNotNull;
import co.jufeng.hibernate.criterion.impl.IsNull;
import co.jufeng.hibernate.criterion.impl.Le;
import co.jufeng.hibernate.criterion.impl.Lt;
import co.jufeng.hibernate.criterion.impl.Max;
import co.jufeng.hibernate.criterion.impl.Min;
import co.jufeng.hibernate.criterion.impl.Ne;
import co.jufeng.hibernate.criterion.impl.Or;
import co.jufeng.hibernate.criterion.impl.Paging;
import co.jufeng.hibernate.criterion.impl.Property;
import co.jufeng.hibernate.criterion.impl.Sql;
import co.jufeng.hibernate.criterion.impl.Sum;
import co.jufeng.hibernate.criterion.impl.UniqueResult;
import co.jufeng.logger.LoggerUtil;

@SuppressWarnings("unchecked")
public class HibernateAccessor extends AbstractAccessor implements IHibernateAccessor {
	
	private static final long serialVersionUID = 4376162086967771456L;
	
	private org.hibernate.SessionFactory sessionFactory;
	
	private org.hibernate.Session session;
	
	@Override
	public Serializable save(Object... entity) throws Exception {
		if(entity[0] instanceof Collection || entity[0] instanceof List || entity[0] instanceof Set){
			return save((Collection<Serializable>) entity[0]);
		}else if(entity.getClass().isArray()){
			return save(Arrays.asList(entity));
		}
		return null;
	}
	
	@Override
	public Serializable save(Collection<Serializable> entities) throws Exception {
		Collection<Serializable> primaryKey = null;
		try {
			if(entities.size() == 1){
				return this.getSession().save(entities.iterator().next());
			}
			primaryKey = new ArrayList<Serializable>();
			Iterator<?> it = entities.iterator();
			int i = 0;
			while (it.hasNext()) {
				if (i % 1000 == 0) {
					this.getSession().flush();
					this.getSession().clear();
				}
				primaryKey.add(this.getSession().save(it.next()));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Serializable) primaryKey;
	}
	
	@Override
	public boolean delete(Object... entity) throws Exception {
		try {
			for (int i = 0; i < entity.length; i++) {
				this.getSession().delete(entity[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delete(Class<?> clazz, Serializable... id)  throws Exception {
		try {
			for (int i = 0; i < id.length; i++) {
				this.getSession().delete(getById(id[i], clazz));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean delete(Class<?> clazz, Collection<Serializable> ids) throws Exception {
		try {
			Iterator<Serializable> it = ids.iterator();
			while (it.hasNext()) {
				delete(clazz, it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delete(Collection<Serializable> entities) throws Exception {
		try {
			Iterator<?> it = entities.iterator();
			while (it.hasNext()) {
				delete(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean deleteAll(IEntityFactory entityFactory) throws Exception {
		try {
			Collection<Serializable> collection = (Collection<Serializable>) get(entityFactory);
			Iterator<?> it = collection.iterator();
			while (it.hasNext()) {
				delete(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean update(Object... entity) throws Exception {
		try {
//			List<Object> list = new ArrayList<>();
//			for(Object obj : entity ){
//				list.add(obj);
//			}
			return update(Arrays.asList(entity));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean update(Collection<Object> entities) throws Exception{
		Iterator<?> it = entities.iterator();
		int i = 0;
		while (it.hasNext()) {
			if (i % 30 == 0) {
				this.getSession().flush();
				this.getSession().clear();
			}
			this.getSession().update(it.next());
			i++;
		}
		return true;
	}
	
	
	@Override //TODO get
	public Serializable get(ICriterion... criterions) throws Exception {
		Criteria[] criteria = getCriteria(criterions);
		if(criteria.length <= 0){
			//return null;
		}
		checkCriterionNumber(criterions);
		Serializable result = null;
		
		if(criteria.length > 1){
			List<Serializable> list = new ArrayList<Serializable>();
			for (int i = 0; i < criteria.length; i++) {
				criteria[i] = createAGroupCriteria(criteria[i], criterions);
				boolean checkIsUniqueResult = checkIsUniqueResult(criterions);
				if(checkIsUniqueResult){
					 list.add((Serializable) criteria[i].uniqueResult());
				}else{
					list.add((Serializable) criteria[i].list());
				}
			}
			if(list.size() > 0){
				return (Serializable) list;
			}
		}
		
		if(criteria.length == 1){
			criteria[0] = createAGroupCriteria(criteria[0], criterions);
			boolean checkIsUniqueResult = checkIsUniqueResult(criterions);
			if(checkIsUniqueResult){
				return (Serializable) criteria[0].uniqueResult();
			}else{
				return (Serializable) criteria[0].list();
			}
		}
		
		IQuery iquery = createSQLQuery(criterions);
		if(null == iquery){
			return result;
		}
		if(iquery instanceof Sql){
			org.hibernate.Query query = getSQLQuery(iquery);
			query = createAGroupCriteria(query, criterions);
			boolean checkIsUniqueResult = checkIsUniqueResult(criterions);
			if(checkIsUniqueResult){
				return (Serializable) query.uniqueResult();
			}else{
				 return (Serializable) query.list();
			}
			
		}else if(iquery instanceof Hql){
//			org.hibernate.Query query = this.getSession().createQuery(iquery.getQuery());
			org.hibernate.Query query = getHQLQuery(iquery);
			query = createAGroupCriteria(query, criterions);
			boolean checkIsUniqueResult = checkIsUniqueResult(criterions);
			if(checkIsUniqueResult){
				return (Serializable) query.uniqueResult();
			}else{
				 return (Serializable) query.list();
			}
		}
		return null;
	 }
	
	private boolean checkIsUniqueResult(ICriterion[] criterions) {
		for (int i = 0; i < criterions.length; i++) {
			if(criterions[i] instanceof UniqueResult){
				return (Boolean) criterions[i].getValue();
			}
		}
		return false;
	}

	IQuery createSQLQuery(ICriterion... criterions){
		for (int i = 0; i < criterions.length; i++) {
			if(criterions[i] instanceof IQuery){
				return (IQuery) criterions[i];
			}
		}
		return null;
	}
	
	
	
	org.hibernate.Query getSQLQuery(IQuery query){
		SQLQuery sqlQuery = this.getSession().createSQLQuery(query.getQuery());
		Object[] arrayObject = (Object[]) query.getValue();
		for (int i = 0; i < arrayObject.length; i++) {
			try {
				String className = getClassName(arrayObject[i]);
				String simpleClassName = Class.forName(className).getSimpleName();
				sqlQuery.addEntity(simpleClassName, className);
				sqlQuery.setCacheable(query.getCacheable());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return sqlQuery;
	}
	
	org.hibernate.Query getHQLQuery(IQuery query){
		org.hibernate.Query hqlQuery = this.getSession().createQuery(query.getQuery());
		Object[] arrayObject = (Object[]) query.getValue();
		for (int i = 0; i < arrayObject.length; i++) {
			try {
				hqlQuery.setCacheable(query.getCacheable());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hqlQuery;
	}

	@Override //TODO getFunction
	public Serializable getFunction(ICriterion... criterions) throws Exception {
		Serializable serializable;
		List<Map<String, Map<ProjectionMode, Serializable>>> list = new ArrayList<Map<String, Map<ProjectionMode, Serializable>>>();
		try {
			CriteriaImpl[] criteriaArray = (CriteriaImpl[]) getCriteria(criterions);
			for (int j = 0; j < criteriaArray.length; j++) {
				criteriaArray[j] = (CriteriaImpl) createAGroupCriteria(criteriaArray[j], criterions);
				Map<ProjectionMode, Serializable> maps = new HashMap<ProjectionMode, Serializable>();
				Map<String, Map<ProjectionMode, Serializable>> mapFunction = new HashMap<String, Map<ProjectionMode, Serializable>>();
				for (int i = 0; i < criterions.length; i++) {
					if(criterions[i] instanceof Count){
						Count count = (Count) criterions[i];
						switch (count.getProjectionMode()) {
						case COUNT:
							serializable = (Serializable) criteriaArray[j].setProjection(Projections.rowCount()).uniqueResult();
							if(serializable != null && Integer.valueOf(serializable.toString()) > 0){
								maps.put(ProjectionMode.COUNT, serializable);
								mapFunction.put(criteriaArray[j].getEntityOrClassName(), maps);
							}
							break;
						case COUNT_DISTINCT:
							serializable = (Serializable) criteriaArray[j].setProjection(Projections.countDistinct(count.getName())).uniqueResult();
							if(serializable != null && Integer.valueOf(serializable.toString()) > 0){
								maps.put(ProjectionMode.COUNT_DISTINCT, serializable);
								mapFunction.put(criteriaArray[j].getClass().getName(), maps);
							}
							break;

						default:
							break;
						}
					}else if(criterions[i] instanceof Max){
						serializable = (Serializable) criteriaArray[j].setProjection(Projections.max(criterions[i].getName())).uniqueResult();
						if(serializable != null){
							maps.put(ProjectionMode.MAX, serializable);
							mapFunction.put(criteriaArray[j].getEntityOrClassName(), maps);
						}
					}else if(criterions[i] instanceof Min){
						serializable = (Serializable) criteriaArray[j].setProjection(Projections.projectionList().add(Projections.min(criterions[i].getName()))).uniqueResult();
						if(serializable != null){
							maps.put(ProjectionMode.MIN, serializable);
							mapFunction.put(criteriaArray[j].getEntityOrClassName(), maps);
						}
					}else if(criterions[i] instanceof Avg){
						serializable = (Serializable) criteriaArray[j].setProjection(Projections.avg(criterions[i].getName())).uniqueResult();
						if(serializable != null){
							maps.put(ProjectionMode.AVG, serializable);
							mapFunction.put(criteriaArray[j].getEntityOrClassName(), maps);
						}
					}else if(criterions[i] instanceof Sum){
						serializable = (Serializable) criteriaArray[j].setProjection(Projections.sum(criterions[i].getName())).uniqueResult();
						if(serializable != null){
							maps.put(ProjectionMode.SUM, serializable);
							mapFunction.put(criteriaArray[j].getEntityOrClassName(), maps);
						}
					}else if(criterions[i] instanceof Group){
						Group group = (Group) criterions[i];
						switch (group.getProjectionMode()) {
						case GROUP:
							serializable = (Serializable) criteriaArray[j].setProjection(Projections.groupProperty(criterions[i].getName())).list();
							if(serializable != null){
								maps.put(ProjectionMode.GROUP, serializable);
								mapFunction.put(criteriaArray[j].getEntityOrClassName(), maps);
							}
							break;

						case GROUP_COUNT:
							ProjectionList projectionList = Projections.projectionList();
							projectionList.add(Projections.groupProperty(criterions[i].getName()));
							projectionList.add(Projections.rowCount());
							criteriaArray[j].setProjection(projectionList);
							Map<String, Object> map = new HashMap<String, Object>();
							for (Iterator<?> ite = criteriaArray[j].list().iterator(); ite.hasNext();) {
								Object[] obj = (Object[]) ite.next();
								map.put(obj[0].toString(), obj[1]);
							}
							if(map.size() > 0){
								maps.put(ProjectionMode.GROUP_COUNT, (Serializable) map);
								mapFunction.put(criteriaArray[j].getEntityOrClassName(), maps);
							}
							break;
							
						default:
							break;
						}
					}
				}
				if(mapFunction.size() > 0){
					list.add(mapFunction);
				}
			}
			
			if(criteriaArray.length == 1){
				return (Serializable) list.get(0).get(criteriaArray[0].getEntityOrClassName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Serializable) list;
	}

	public static int getLineNumber(){  
        return new Throwable().getStackTrace()[1].getLineNumber();  
    }  
	
	@Override
	public Criteria[] getCriteria(ICriterion... criterions){
		Criteria[] criterias = new CriteriaImpl[checkEntityNumber(criterions)];
		int index = 0;
		for (int i = 0; i < criterions.length; i++) {
			if(criterions[i] instanceof IEntityFactory){
				arrayEntity = (Object[]) criterions[i].getValue();
				String alias = criterions[i].getName();
				
				if(arrayEntity.length > 1){
					for (int j = 0; j < arrayEntity.length; j++) {
						if(null != alias){
							criterias[j] = this.getSession().createCriteria(arrayEntity[j].toString(), alias);
							criterias[j].setCacheable(criterions[i].getCacheable());
						}else{
							criterias[j] = getCriteria(arrayEntity[j].toString());
							criterias[j].setCacheable(criterions[i].getCacheable());
						}
						index++;
					}
				}else{
					if(null != alias){
						criterias[index] = this.getSession().createCriteria(arrayEntity[0].toString(), alias);
						criterias[index].setCacheable(criterions[i].getCacheable());
					}else{
						criterias[index] = getCriteria(arrayEntity[0].toString());
						criterias[index].setCacheable(criterions[i].getCacheable());
						index++;
					}
					index++;
				}
			}
		}
		for (int i = 0; i < criterias.length; i++) {
			LoggerUtil.info(this.getClass(), criterias[i].toString());
		}
		return criterias;
	}
	
	private int checkEntityNumber(ICriterion... criterions){
		entityFactorySize = 0;
		for (int i = 0; i < criterions.length; i++) {
			if(criterions[i] instanceof IEntityFactory){
				Object[] arrayEntity = (Object[]) criterions[i].getValue();
				if(arrayEntity.length > 1){
					entityFactorySize += arrayEntity.length;
				}else{
					entityFactorySize++;
				}
			}
		}
		return entityFactorySize;
	}
	
	private int checkCriterionNumber(ICriterion... criterions){
		criterionSize = 0;
		for (int i = 0; i < criterions.length; i++) {
			if(criterions[i] instanceof IRestrictions || criterions[i] instanceof IProjection){
				criterionSize++;
			}
		}
		return criterionSize;
	}
	
	@Override
	public Criteria createAGroupCriteria(Criteria criteria, ICriterion... criterions) {
		if(criterionSize <= 0){
			return criteria;
		}
		Disjunction ors = Restrictions.disjunction();  
		try {
			//等于equal
			Conjunction createAGroupEqCriteria = Restrictions.conjunction();
			//不等于not equal
			Conjunction createAGroupNeCriteria = Restrictions.conjunction(); 
			//大于greater than
			Conjunction createAGroupGtCriteria = Restrictions.conjunction(); 
			//大于等于greater than or equal
			Conjunction createAGroupGeCriteria = Restrictions.conjunction();
			//小于less than
			Conjunction createAGroupLtCriteria = Restrictions.conjunction(); 
			//小于等于less than or equal
			Conjunction createAGroupLeCriteria = Restrictions.conjunction(); 
			//等于空值
			Conjunction createAGroupIsNullCriteria = Restrictions.conjunction(); 
			//非空值
			Disjunction createAGroupIsNotNullCriteria =  Restrictions.disjunction(); 
			//字符串模式匹配
			Disjunction createAGroupIlikeCriteria = Restrictions.disjunction();
			//逻辑或
			Disjunction createAGroupOrCriteria = Restrictions.disjunction(); 
			//等于列表中的某一个值
			Disjunction createAGroupInCriteria = Restrictions.disjunction(); 
			//闭区间xy中的任意值
			Disjunction createAGroupBetweenCriteria = Restrictions.disjunction(); 
			//过虑字段以key取值
			ProjectionList createAGroupPropertyCriteria = Projections.projectionList(); 
			//自增变量
			int ieq = 0, ine = 0, igt = 0, ige = 0, ilt = 0, ile = 0, iIsNull = 0, iIsNotNull = 0, ior = 0, iIn = 0, ilikei = 0, ibetween = 0;
			for (int i = 0; i < criterions.length; i++) {
				if(criterions[i] instanceof Eq){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupEqCriteria.add(Restrictions.eq(criterions[i].getName(), array[j]));
					}
					ieq++;
				}else if(criterions[i] instanceof Ne){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupNeCriteria.add(Restrictions.ne(criterions[i].getName(), array[j]));
					}
					ine++;
				}else if(criterions[i] instanceof Gt){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupGtCriteria.add(Restrictions.gt(criterions[i].getName(), array[j]));
					}
					igt++;
				}else if(criterions[i] instanceof Ge){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupGeCriteria.add(Restrictions.ge(criterions[i].getName(), array[j]));
					}
					ige++;
				}else if(criterions[i] instanceof Lt){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupLtCriteria.add(Restrictions.lt(criterions[i].getName(), array[j]));
					}
					ilt++;
				}else if(criterions[i] instanceof Le){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupLeCriteria.add(Restrictions.le(criterions[i].getName(), array[j]));
					}
					ile++;
				}else if(criterions[i] instanceof IsNull){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupIsNullCriteria.add(Restrictions.isNull(array[j].toString()));
					}
					iIsNull++;
				}else if(criterions[i] instanceof IsNotNull){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupIsNotNullCriteria.add(Restrictions.isNotNull(array[j].toString()));
					}
					iIsNotNull++;
				}else if(criterions[i] instanceof Or){
					Object[] array = (Object[]) criterions[i].getValue();
					for (int j = 0; j < array.length; j++) {
						createAGroupOrCriteria.add(Restrictions.eq(criterions[i].getName(), array[j]));
					}
					ior++;
				}else if(criterions[i] instanceof In){
					Object[] array = (Object[]) criterions[i].getValue();
					createAGroupInCriteria.add(Restrictions.in(criterions[i].getName(), array));
					iIn++;
				}else if(criterions[i] instanceof Between){
					Object[] array = (Object[]) criterions[i].getValue();
					createAGroupBetweenCriteria.add(Restrictions.between(criterions[i].getName(), array[0], array[1]));
					ibetween++;
				}else if(criterions[i] instanceof Property){
					Object[] array = (Object[]) criterions[i].getValue();
					List<Projection> projectionList = new ArrayList<Projection>();
					for (int j = 0; j < array.length; j++) {
						projectionList.add(Projections.property(PropertyEnum.ALIAS.getValue() + "." + array[j]).as(array[j].toString()));
					}
					
					for (int j = 0; j < projectionList.size(); j++) {
						createAGroupPropertyCriteria.add(projectionList.get(j));
					}
					String entityVO = criterions[i].getName();
					if(entityVO == null){
						entityVO = arrayEntity[0].toString();
					}
					Class<?> classNameVO = Class.forName(entityVO);
					criteria.setProjection(createAGroupPropertyCriteria);
					criteria.setResultTransformer(Transformers.aliasToBean(classNameVO));  
				}else if(criterions[i] instanceof Ilike){
					Ilike ilike = (Ilike) criterions[i];
					Object[] array = (Object[]) ilike.getValue();
					switch (ilike.getMatchMode()) {
					case EXACT:
						for (int j = 0; j < array.length; j++) {
							createAGroupIlikeCriteria.add(Restrictions.ilike(ilike.getName(), array[j].toString(), MatchMode.EXACT));
						}
						ilikei++;
						break;
						
					case START:
						for (int j = 0; j < array.length; j++) {
							createAGroupIlikeCriteria.add(Restrictions.ilike(ilike.getName(), array[j].toString(), MatchMode.START));
						}
						ilikei++;
						break;
						
					case END:
						for (int j = 0; j < array.length; j++) {
							createAGroupIlikeCriteria.add(Restrictions.ilike(ilike.getName(), array[j].toString(), MatchMode.END));
						}
						ilikei++;
						break;
						
					case ANYWHERE:
						for (int j = 0; j < array.length; j++) {
							createAGroupIlikeCriteria.add(Restrictions.ilike(ilike.getName(), array[j].toString(), MatchMode.ANYWHERE));
						}
						ilikei++;
						break;
					default:
						break;
					}
				}else if(criterions[i] instanceof co.jufeng.hibernate.criterion.impl.Paging){
					Paging paging = (Paging) criterions[i];
					int firstResult = paging.getFirstResult();
					int maxResults = paging.getMaxResults();
					criteria.setFirstResult((firstResult - 1) * maxResults);
					criteria.setMaxResults(maxResults);
				}else if(criterions[i] instanceof co.jufeng.hibernate.criterion.impl.Order){
					if(co.jufeng.hibernate.OrderEnum.DESC.getValue().equalsIgnoreCase(criterions[i].getName())){
						criteria.addOrder(Order.desc(criterions[i].getValue().toString()));
					}else if(co.jufeng.hibernate.OrderEnum.ASC.getValue().equalsIgnoreCase(criterions[i].getName())){
						criteria.addOrder(Order.asc(criterions[i].getValue().toString()));
					}
				}
			}
			if(ieq > 0){
				ors.add(createAGroupEqCriteria);
			}
			if(ine > 0){
				ors.add(createAGroupNeCriteria);
			}
			if(igt > 0){
				ors.add(createAGroupGtCriteria);
			}
			if(ige > 0){
				ors.add(createAGroupGeCriteria);
			}
			if(ilt > 0){
				ors.add(createAGroupLtCriteria);
			}
			if(ile > 0){
				ors.add(createAGroupLeCriteria);
			}
			if(iIsNull > 0){
				ors.add(createAGroupIsNullCriteria);
			}
			if(iIsNotNull > 0){
				ors.add(createAGroupIsNotNullCriteria);
			}
			if(ior > 0){
				ors.add(createAGroupOrCriteria);
			}
			if(iIn > 0){
				ors.add(createAGroupInCriteria);
			}
			if(ilikei > 0){
				ors.add(createAGroupIlikeCriteria);
			}
			if(ibetween > 0){
				ors.add(createAGroupBetweenCriteria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return criteria.add(ors);
	}

	@Override
	public org.hibernate.Query createAGroupCriteria(org.hibernate.Query query, ICriterion... criterions) {
		for (int i = 0; i < criterions.length; i++) {
			if(criterions[i] instanceof Paging){
				Paging paging = (Paging) criterions[i];
				int firstResult = paging.getFirstResult();
				int maxResults = paging.getMaxResults();
				query.setFirstResult((firstResult - 1) * maxResults);
				query.setMaxResults(maxResults);
			}
		}
		return query;
	}
	
	@Override
	public boolean isEmpty(Class<?> clazz, String criteriaKey, String criteriaValue) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from " + clazz.getName());
		hql.append(" as model where model." + criteriaKey + "=" + "'" + criteriaValue + "'");
		Object object = this.getSession().createQuery(hql.toString()).uniqueResult();
		return object != null ? false : true;
	}
	
	public <T> T getById(Serializable id, Class<T> T) throws Exception {
		return (T) this.getSession().get(T, id);
	}
	
	public org.hibernate.Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSession(org.hibernate.Session session) {
		this.session = session;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public org.hibernate.Session openSession() {
		return sessionFactory.openSession();
	}
	
	@Override
	public Criteria getCriteria(Class<?> entity){
		return this.getSession().createCriteria(entity);
	}
	
	@Override
	public Criteria getCriteria(String className){
		return this.getSession().createCriteria(className);
	}

	@Override
	public void closeSession() {
		System.out.println(this.getSession());
		if(null != this.getSession()){
			this.getSession().close();
		}
	}
	
	@Override
	public void close() {
		closeSession();
	}

	@Override
	public void evict(Object entity) {
		this.getSession().evict(entity);
	}

	@Override
	public void clear() {
		this.getSession().clear();
	}

	@Override
	public void contains(Object entity) {
		this.getSession().contains(entity);
	}

	@Override
	public void flush() {
		this.getSession().flush();
		
	}

	@Override
	public Object beginTransaction() {
		return this.getSession().beginTransaction();
	}
	
	@Override
	public void commit() {
		this.getSession().getTransaction().commit();
	}

}
