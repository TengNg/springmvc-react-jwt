/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "cart_items")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "CartItems.findAll", query = "SELECT c FROM CartItems c"),
	@NamedQuery(name = "CartItems.findByCartItemId", query = "SELECT c FROM CartItems c WHERE c.cartItemId = :cartItemId"),
	@NamedQuery(name = "CartItems.findByQuantity", query = "SELECT c FROM CartItems c WHERE c.quantity = :quantity")})
public class CartItems implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cart_item_id")
	private Integer cartItemId;
	@Column(name = "quantity")
	private Integer quantity;
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @ManyToOne
	private Carts cartId;
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
	private Products productId;

	public CartItems() {
	}

	public CartItems(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Carts getCartId() {
		return cartId;
	}

	public void setCartId(Carts cartId) {
		this.cartId = cartId;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cartItemId != null ? cartItemId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CartItems)) {
			return false;
		}
		CartItems other = (CartItems) object;
		if ((this.cartItemId == null && other.cartItemId != null) || (this.cartItemId != null && !this.cartItemId.equals(other.cartItemId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.CartItems[ cartItemId=" + cartItemId + " ]";
	}
	
}
