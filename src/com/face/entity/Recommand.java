package com.face.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Recommand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "recommand", catalog = "face")
public class Recommand implements java.io.Serializable {

	// Fields

	private Integer productid;
	private Double w1;
	private Double w2;
	private Double w3;
	private Double w4;
	private Double w5;
	private Double w6;
	private Double w7;

	// Constructors

	/** default constructor */
	public Recommand() {
	}

	/** full constructor */
	public Recommand(Integer productid, Double w1, Double w2, Double w3,
			Double w4, Double w5, Double w6, Double w7) {
		this.productid = productid;
		this.w1 = w1;
		this.w2 = w2;
		this.w3 = w3;
		this.w4 = w4;
		this.w5 = w5;
		this.w6 = w6;
		this.w7 = w7;
	}

	// Property accessors
	@Id
	@Column(name = "productid", unique = true, nullable = false)
	public Integer getProductid() {
		return this.productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	@Column(name = "w1", nullable = false, precision = 22, scale = 0)
	public Double getW1() {
		return this.w1;
	}

	public void setW1(Double w1) {
		this.w1 = w1;
	}

	@Column(name = "w2", nullable = false, precision = 22, scale = 0)
	public Double getW2() {
		return this.w2;
	}

	public void setW2(Double w2) {
		this.w2 = w2;
	}

	@Column(name = "w3", nullable = false, precision = 22, scale = 0)
	public Double getW3() {
		return this.w3;
	}

	public void setW3(Double w3) {
		this.w3 = w3;
	}

	@Column(name = "w4", nullable = false, precision = 22, scale = 0)
	public Double getW4() {
		return this.w4;
	}

	public void setW4(Double w4) {
		this.w4 = w4;
	}

	@Column(name = "w5", nullable = false, precision = 22, scale = 0)
	public Double getW5() {
		return this.w5;
	}

	public void setW5(Double w5) {
		this.w5 = w5;
	}

	@Column(name = "w6", nullable = false, precision = 22, scale = 0)
	public Double getW6() {
		return this.w6;
	}

	public void setW6(Double w6) {
		this.w6 = w6;
	}

	@Column(name = "w7", nullable = false, precision = 22, scale = 0)
	public Double getW7() {
		return this.w7;
	}

	public void setW7(Double w7) {
		this.w7 = w7;
	}

}