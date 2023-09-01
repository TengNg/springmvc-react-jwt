/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "product_reviews")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ProductReviews.findAll", query = "SELECT p FROM ProductReviews p"),
	@NamedQuery(name = "ProductReviews.findByReviewId", query = "SELECT p FROM ProductReviews p WHERE p.reviewId = :reviewId"),
	@NamedQuery(name = "ProductReviews.findByRating", query = "SELECT p FROM ProductReviews p WHERE p.rating = :rating"),
	@NamedQuery(name = "ProductReviews.findByReviewDate", query = "SELECT p FROM ProductReviews p WHERE p.reviewDate = :reviewDate")})
public class ProductReviews implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "review_id")
	private String reviewId;
	@Lob
    @Size(max = 65535)
    @Column(name = "review_text")
	private String reviewText;
	@Column(name = "rating")
	private Integer rating;
	@Column(name = "review_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date reviewDate;
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
	private Products productId;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
	private Users userId;

	public ProductReviews() {
	}

	public ProductReviews(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (reviewId != null ? reviewId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProductReviews)) {
			return false;
		}
		ProductReviews other = (ProductReviews) object;
		if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.ProductReviews[ reviewId=" + reviewId + " ]";
	}
	
}
