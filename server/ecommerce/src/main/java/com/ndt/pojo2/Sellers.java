/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "sellers")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Sellers.findAll", query = "SELECT s FROM Sellers s"),
	@NamedQuery(name = "Sellers.findBySellerId", query = "SELECT s FROM Sellers s WHERE s.sellerId = :sellerId"),
	@NamedQuery(name = "Sellers.findByIsConfirmed", query = "SELECT s FROM Sellers s WHERE s.isConfirmed = :isConfirmed"),
	@NamedQuery(name = "Sellers.findByTotalRevenue", query = "SELECT s FROM Sellers s WHERE s.totalRevenue = :totalRevenue"),
	@NamedQuery(name = "Sellers.findByTotalProductsSold", query = "SELECT s FROM Sellers s WHERE s.totalProductsSold = :totalProductsSold")})
public class Sellers implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "seller_id")
	private String sellerId;
	@Column(name = "is_confirmed")
	private Boolean isConfirmed;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "total_revenue")
	private BigDecimal totalRevenue;
	@Column(name = "total_products_sold")
	private Integer totalProductsSold;
	@JoinColumn(name = "seller_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
	private Users users;
	@OneToMany(mappedBy = "sellerId")
	private Set<Products> productsSet;

	public Sellers() {
	}

	public Sellers(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Integer getTotalProductsSold() {
		return totalProductsSold;
	}

	public void setTotalProductsSold(Integer totalProductsSold) {
		this.totalProductsSold = totalProductsSold;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@XmlTransient
	public Set<Products> getProductsSet() {
		return productsSet;
	}

	public void setProductsSet(Set<Products> productsSet) {
		this.productsSet = productsSet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (sellerId != null ? sellerId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Sellers)) {
			return false;
		}
		Sellers other = (Sellers) object;
		if ((this.sellerId == null && other.sellerId != null) || (this.sellerId != null && !this.sellerId.equals(other.sellerId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.Sellers[ sellerId=" + sellerId + " ]";
	}
	
}
