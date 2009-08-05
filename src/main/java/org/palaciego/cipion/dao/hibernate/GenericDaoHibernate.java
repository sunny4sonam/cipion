package org.palaciego.cipion.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.palaciego.cipion.dao.GenericDao;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="org.palaciego.cipion.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="org.palaciego.cipion.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;

    /**
     * Constructor that takes in a class to see which type of entity to persist
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
    	return getAll(null);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll(String fieldOrder) {
    	if(fieldOrder==null)
    	{
            return super.getHibernateTemplate().loadAll(this.persistentClass);
    	}
    	else
    	{
    		Criteria a=super.getSession().createCriteria(this.persistentClass);
    		a.addOrder(Order.asc(fieldOrder));
    		return a.list();
    	}
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection result = new LinkedHashSet(getAll(null));
        return new ArrayList(result);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        T entity = (T) super.getHibernateTemplate().get(this.persistentClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        T entity = (T) super.getHibernateTemplate().get(this.persistentClass, id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        return (T) super.getHibernateTemplate().merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        super.getHibernateTemplate().delete(this.get(id));
    }
    
   /** 
    * {@inheritDoc}
    */
   @SuppressWarnings("unchecked")
   public List<T> findByNamedQuery(
       String queryName, 
       Map<String, Object> queryParams) {
       String []params = new String[queryParams.size()];
       Object []values = new Object[queryParams.size()];
       int index = 0;
       Iterator<String> i = queryParams.keySet().iterator();
       while (i.hasNext()) {
           String key = i.next();
           params[index] = key;
           values[index++] = queryParams.get(key);
       }
       return getHibernateTemplate().findByNamedQueryAndNamedParam(
           queryName, 
           params, 
           values);
   }
   
   /** 
    * {@inheritDoc}
    */
   @SuppressWarnings("unchecked")
   public List<T> findHQL(String hql)
   {
	   return getHibernateTemplate().find(hql);
   }
}
