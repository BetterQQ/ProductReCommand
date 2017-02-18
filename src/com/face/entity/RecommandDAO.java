package com.face.entity;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Recommand entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.face.entity.Recommand
 * @author MyEclipse Persistence Tools
 */
public class RecommandDAO implements IRecommandDAO {
	// property constants
	public static final String W1 = "w1";
	public static final String W2 = "w2";
	public static final String W3 = "w3";
	public static final String W4 = "w4";
	public static final String W5 = "w5";
	public static final String W6 = "w6";
	public static final String W7 = "w7";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Recommand entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RecommandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Recommand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Recommand entity) {
		EntityManagerHelper.log("saving Recommand instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Recommand entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RecommandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Recommand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Recommand entity) {
		EntityManagerHelper
				.log("deleting Recommand instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Recommand.class,
					entity.getProductid());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Recommand entity and return it or a copy of it
	 * to the sender. A copy of the Recommand entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RecommandDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Recommand entity to update
	 * @return Recommand the persisted Recommand entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Recommand update(Recommand entity) {
		EntityManagerHelper
				.log("updating Recommand instance", Level.INFO, null);
		try {
			Recommand result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Recommand findById(Integer id) {
		EntityManagerHelper.log("finding Recommand instance with id: " + id,
				Level.INFO, null);
		try {
			Recommand instance = getEntityManager().find(Recommand.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Recommand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Recommand property to query
	 * @param value
	 *            the property value to match
	 * @return List<Recommand> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Recommand> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Recommand instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Recommand model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Recommand> findByW1(Object w1) {
		return findByProperty(W1, w1);
	}

	public List<Recommand> findByW2(Object w2) {
		return findByProperty(W2, w2);
	}

	public List<Recommand> findByW3(Object w3) {
		return findByProperty(W3, w3);
	}

	public List<Recommand> findByW4(Object w4) {
		return findByProperty(W4, w4);
	}

	public List<Recommand> findByW5(Object w5) {
		return findByProperty(W5, w5);
	}

	public List<Recommand> findByW6(Object w6) {
		return findByProperty(W6, w6);
	}

	public List<Recommand> findByW7(Object w7) {
		return findByProperty(W7, w7);
	}

	/**
	 * Find all Recommand entities.
	 * 
	 * @return List<Recommand> all Recommand entities
	 */
	@SuppressWarnings("unchecked")
	public List<Recommand> findAll() {
		EntityManagerHelper.log("finding all Recommand instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Recommand model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}