package com.face.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", catalog = "face")
public class Product implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String type;
	private Integer salesForMoon;
	private Integer commentCount;
	private Float disNegativeCommentRate;
	private Integer collection;
	private Float pride;
	private String tburl;
	private String jdurl;
	private String imageUrl;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(String name, String type, Integer salesForMoon,
			Integer commentCount, Float disNegativeCommentRate,
			Integer collection, Float pride, String tburl, String jdurl,
			String imageUrl) {
		this.name = name;
		this.type = type;
		this.salesForMoon = salesForMoon;
		this.commentCount = commentCount;
		this.disNegativeCommentRate = disNegativeCommentRate;
		this.collection = collection;
		this.pride = pride;
		this.tburl = tburl;
		this.jdurl = jdurl;
		this.imageUrl = imageUrl;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Type", length = 100)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "SalesForMoon")
	public Integer getSalesForMoon() {
		return this.salesForMoon;
	}

	public void setSalesForMoon(Integer salesForMoon) {
		this.salesForMoon = salesForMoon;
	}

	@Column(name = "CommentCount")
	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name = "DisNegativeCommentRate", precision = 12, scale = 0)
	public Float getDisNegativeCommentRate() {
		return this.disNegativeCommentRate;
	}

	public void setDisNegativeCommentRate(Float disNegativeCommentRate) {
		this.disNegativeCommentRate = disNegativeCommentRate;
	}

	@Column(name = "Collection")
	public Integer getCollection() {
		return this.collection;
	}

	public void setCollection(Integer collection) {
		this.collection = collection;
	}

	@Column(name = "Pride", precision = 12, scale = 0)
	public Float getPride() {
		return this.pride;
	}

	public void setPride(Float pride) {
		this.pride = pride;
	}

	@Column(name = "TBURL", length = 65535)
	public String getTburl() {
		return this.tburl;
	}

	public void setTburl(String tburl) {
		this.tburl = tburl;
	}

	@Column(name = "JDURL", length = 65535)
	public String getJdurl() {
		return this.jdurl;
	}

	public void setJdurl(String jdurl) {
		this.jdurl = jdurl;
	}

	@Column(name = "ImageURL", length = 65535)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}