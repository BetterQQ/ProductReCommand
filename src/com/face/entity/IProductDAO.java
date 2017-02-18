package com.face.entity;

import java.util.List;

/**
 * Interface for ProductDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IProductDAO {
	/**
	 * Perform an initial save of a previously unsaved Product entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IProductDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Product entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Product entity);

	/**
	 * Delete a persistent Product entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IProductDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Product entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Product entity);

	/**
	 * Persist a previously saved Product entity and return it or a copy of it
	 * to the sender. A copy of the Product entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IProductDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Product entity to update
	 * @return Product the persisted Product entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Product update(Product entity);

	public Product findById(Integer id);

	/**
	 * Find all Product entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Product property to query
	 * @param value
	 *            the property value to match
	 * @return List<Product> found by query
	 */
	public List<Product> findByProperty(String propertyName, Object value);

	public List<Product> findByName(Object name);

	public List<Product> findByType(Object type);

	public List<Product> findBySalesForMoon(Object salesForMoon);

	public List<Product> findByCommentCount(Object commentCount);

	public List<Product> findByDisNegativeCommentRate(
			Object disNegativeCommentRate);

	public List<Product> findByCollection(Object collection);

	public List<Product> findByPride(Object pride);

	public List<Product> findByTburl(Object tburl);

	public List<Product> findByJdurl(Object jdurl);

	public List<Product> findByImageUrl(Object imageUrl);

	/**
	 * Find all Product entities.
	 * 
	 * @return List<Product> all Product entities
	 */
	public List<Product> findAll();
}