package com.face.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private Set<Recommand> recommands = new HashSet<Recommand>(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(String name, Set<Recommand> recommands) {
		this.name = name;
		this.recommands = recommands;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Recommand> getRecommands() {
		return this.recommands;
	}

	public void setRecommands(Set<Recommand> recommands) {
		this.recommands = recommands;
	}

}