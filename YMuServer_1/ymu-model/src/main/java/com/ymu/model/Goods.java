package com.ymu.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods", catalog = "ymu_master")
public class Goods implements java.io.Serializable {

	// Fields

	private Long id;
	private Double costPrice;
	private Double discounRate;
	private String goodsName;
	private Double marketPrice;
	private Double sellPrice;
	private Long userId;

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String goodsName, Double sellPrice, Long userId) {
		this.goodsName = goodsName;
		this.sellPrice = sellPrice;
		this.userId = userId;
	}

	/** full constructor */
	public Goods(Double costPrice, Double discounRate, String goodsName,
			Double marketPrice, Double sellPrice, Long userId) {
		this.costPrice = costPrice;
		this.discounRate = discounRate;
		this.goodsName = goodsName;
		this.marketPrice = marketPrice;
		this.sellPrice = sellPrice;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cost_price", precision = 22, scale = 0)
	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	@Column(name = "discoun_rate", precision = 22, scale = 0)
	public Double getDiscounRate() {
		return this.discounRate;
	}

	public void setDiscounRate(Double discounRate) {
		this.discounRate = discounRate;
	}

	@Column(name = "goods_name", nullable = false, length = 150)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "market_price", precision = 22, scale = 0)
	public Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	@Column(name = "sell_price", nullable = false, precision = 22, scale = 0)
	public Double getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}