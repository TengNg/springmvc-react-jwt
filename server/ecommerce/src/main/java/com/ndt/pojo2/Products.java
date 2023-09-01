/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
	@NamedQuery(name = "Products.findByProductId", query = "SELECT p FROM Products p WHERE p.productId = :productId"),
	@NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name"),
	@NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price"),
	@NamedQuery(name = "Products.findByStockQuantity", query = "SELECT p FROM Products p WHERE p.stockQuantity = :stockQuantity"),
	@NamedQuery(name = "Products.findByCreatedAt", query = "SELECT p FROM Products p WHERE p.createdAt = :createdAt")})
public class Products implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "product_id")
	private String productId;
	@Size(max = 255)
    @Column(name = "name")
	private String name;
	@Lob
    @Size(max = 65535)
    @Column(name = "description")
	private String description;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "stock_quantity")
	private Integer stockQuantity;
	@Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToMany(mappedBy = "productId")
	private Set<CartItems> cartItemsSet;
	@OneToMany(mappedBy = "productId")
	private Set<ProductReviews> productReviewsSet;
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
	private Categories categoryId;
	@JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    @ManyToOne
	private Sellers sellerId;

	public Products() {
	}

	public Products(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@XmlTransient
	public Set<CartItems> getCartItemsSet() {
		return cartItemsSet;
	}

	public void setCartItemsSet(Set<CartItems> cartItemsSet) {
		this.cartItemsSet = cartItemsSet;
	}

	@XmlTransient
	public Set<ProductReviews> getProductReviewsSet() {
		return productReviewsSet;
	}

	public void setProductReviewsSet(Set<ProductReviews> productReviewsSet) {
		this.productReviewsSet = productReviewsSet;
	}

	public Categories getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Categories categoryId) {
		this.categoryId = categoryId;
	}

	public Sellers getSellerId() {
		return sellerId;
	}

	public void setSellerId(Sellers sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (productId != null ? productId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Products)) {
			return false;
		}
		Products other = (Products) object;
		if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.Products[ productId=" + productId + " ]";
	}
	
}
