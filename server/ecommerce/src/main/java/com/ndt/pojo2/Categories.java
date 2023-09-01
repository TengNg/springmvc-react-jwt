/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "categories")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c"),
	@NamedQuery(name = "Categories.findByCategoryId", query = "SELECT c FROM Categories c WHERE c.categoryId = :categoryId"),
	@NamedQuery(name = "Categories.findByCategoryName", query = "SELECT c FROM Categories c WHERE c.categoryName = :categoryName")})
public class Categories implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_id")
	private Integer categoryId;
	@Size(max = 255)
    @Column(name = "category_name")
	private String categoryName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
	private Set<Products> productsSet;

	public Categories() {
	}

	public Categories(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		hash += (categoryId != null ? categoryId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Categories)) {
			return false;
		}
		Categories other = (Categories) object;
		if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.Categories[ categoryId=" + categoryId + " ]";
	}
	
}
