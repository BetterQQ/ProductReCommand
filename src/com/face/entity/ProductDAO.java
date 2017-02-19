package com.face.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.face.dbconn.DBConnection;

/**
 * A data access object (DAO) providing persistence and search support for
 * Product entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.face.entity.Product
 * @author MyEclipse Persistence Tools
 */
public class ProductDAO implements IProductDAO {
	// property constants
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String SALES_FOR_MOON = "salesForMoon";
	public static final String COMMENT_COUNT = "commentCount";
	public static final String DIS_NEGATIVE_COMMENT_RATE = "disNegativeCommentRate";
	public static final String COLLECTION = "collection";
	public static final String PRIDE = "pride";
	public static final String TBURL = "tburl";
	public static final String JDURL = "jdurl";
	public static final String IMAGE_URL = "imageUrl";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * ProductDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Product entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Product entity) {
		EntityManagerHelper.log("saving Product instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Product entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProductDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Product entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Product entity) {
		EntityManagerHelper.log("deleting Product instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Product.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = ProductDAO.update(entity);
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
	public Product update(Product entity) {
		EntityManagerHelper.log("updating Product instance", Level.INFO, null);
		try {
			Product result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Product findById(Integer id) {
		EntityManagerHelper.log("finding Product instance with id: " + id,
				Level.INFO, null);
		try {
			Product instance = getEntityManager().find(Product.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	public ArrayList<Product> findByIds(Integer...id) {
		StringBuffer sb = new StringBuffer();
		sb.append(""+id);
		for(int i=0;i<id.length;i++){
			sb.append(","+id);
		}
		String Ids = sb.toString();
		StringBuffer sql = new StringBuffer(
				"select * from product where Id in ("
						+ Ids + ")");
		Connection con = DBConnection.getDBcon();
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				Product ab = new Product();
				ab.setId(rs.getInt("Id"));
				ab.setName(rs.getString("Name"));
				ab.setType(rs.getString("Type"));
				ab.setSalesForMoon(rs.getInt("SalesForMoon"));
				ab.setCommentCount(rs.getInt("CommentCount"));
				ab.setDisNegativeCommentRate(rs.getFloat("DisNegativeCommentRate"));
				ab.setCollection(rs.getInt("Collection"));
				ab.setPride(rs.getFloat("Pride"));
				ab.setTburl(rs.getString("TBURL"));
				ab.setJdurl(rs.getString("JDURL"));
				ab.setImageUrl(rs.getString("ImageURL"));
				list.add(ab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConn(con);
		}
		return list;
	}

	/**
	 * Find all Product entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Product property to query
	 * @param value
	 *            the property value to match
	 * @return List<Product> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Product instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Product model where model."
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

	public List<Product> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Product> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Product> findBySalesForMoon(Object salesForMoon) {
		return findByProperty(SALES_FOR_MOON, salesForMoon);
	}

	public List<Product> findByCommentCount(Object commentCount) {
		return findByProperty(COMMENT_COUNT, commentCount);
	}

	public List<Product> findByDisNegativeCommentRate(
			Object disNegativeCommentRate) {
		return findByProperty(DIS_NEGATIVE_COMMENT_RATE, disNegativeCommentRate);
	}

	public List<Product> findByCollection(Object collection) {
		return findByProperty(COLLECTION, collection);
	}

	public List<Product> findByPride(Object pride) {
		return findByProperty(PRIDE, pride);
	}

	public List<Product> findByTburl(Object tburl) {
		return findByProperty(TBURL, tburl);
	}

	public List<Product> findByJdurl(Object jdurl) {
		return findByProperty(JDURL, jdurl);
	}

	public List<Product> findByImageUrl(Object imageUrl) {
		return findByProperty(IMAGE_URL, imageUrl);
	}

	/**
	 * Find all Product entities.
	 * 
	 * @return List<Product> all Product entities
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		EntityManagerHelper.log("finding all Product instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Product model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}