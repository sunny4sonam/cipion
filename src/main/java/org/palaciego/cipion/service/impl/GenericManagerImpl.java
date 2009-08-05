package org.palaciego.cipion.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.palaciego.cipion.dao.GenericDao;
import org.palaciego.cipion.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="org.palaciego.cipion.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="org.palaciego.cipion.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="org.palaciego.cipion.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="org.palaciego.cipion.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="org.palaciego.cipion.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="org.palaciego.cipion.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDao instance, set by constructor of this class
     */
    protected GenericDao<T, PK> genericDao;

    /**
     * Public constructor for creating a new GenericManagerImpl.
     * @param genericDao the GenericDao to use for persistence
     */
    public GenericManagerImpl(final GenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll(String fieldOrder) {
        return genericDao.getAll(fieldOrder);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return genericDao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return genericDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return genericDao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return genericDao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        genericDao.remove(id);
    }
    
    /**
     * Find a list of records by using a named query
     * @param queryName query name of the named query
     * @param queryParams a map of the query names and the values
     * @return a list of the records found
     */
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams)
    {
    	return genericDao.findByNamedQuery(queryName, queryParams);
    }
    
    /**
     * Ejecuta la HQL que se le pase.
     * @param hql
     * @return
     */
    public List<T> findHQL(String hql)
    {
    	return genericDao.findHQL(hql);
    }

}
