package com.face.entity;

import java.util.List;

/**
 * Interface for RecommandDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRecommandDAO {
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
	 * IRecommandDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Recommand entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Recommand entity);

	/**
	 * Delete a persistent Recommand entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRecommandDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Recommand entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Recommand entity);

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
	 * entity = IRecommandDAO.update(entity);
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
	public Recommand update(Recommand entity);

	public Recommand findById(Integer id);

	/**
	 * Find all Recommand entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Recommand property to query
	 * @param value
	 *            the property value to match
	 * @return List<Recommand> found by query
	 */
	public List<Recommand> findByProperty(String propertyName, Object value);

	public List<Recommand> findByW1(Object w1);

	public List<Recommand> findByW2(Object w2);

	public List<Recommand> findByW3(Object w3);

	public List<Recommand> findByW4(Object w4);

	public List<Recommand> findByW5(Object w5);

	public List<Recommand> findByW6(Object w6);

	public List<Recommand> findByW7(Object w7);

	/**
	 * Find all Recommand entities.
	 * 
	 * @return List<Recommand> all Recommand entities
	 */
	public List<Recommand> findAll();
}