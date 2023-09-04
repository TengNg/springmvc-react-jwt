/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
	@NamedQuery(name = "Cart.findByCartId", query = "SELECT c FROM Cart c WHERE c.cartId = :cartId"),
	@NamedQuery(name = "Cart.findByPaymentMethod", query = "SELECT c FROM Cart c WHERE c.paymentMethod = :paymentMethod")})
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "cart_id")
	private String cartId;
	@Column(name = "payment_method")
	private String paymentMethod;
	@OneToMany(mappedBy = "cartId")
	@JsonIgnore
	private Set<ShippingProcess> shippingProcessSet;
	@OneToMany(mappedBy = "cartId")
	private Set<PaymentTransaction> paymentTransactionSet;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
	private User userId;
	@OneToMany(mappedBy = "cartId")
	private Set<CartItem> cartItemSet;

	public Cart() {
	}

	public Cart(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethodId) {
		this.paymentMethod = paymentMethodId;
	}

	@XmlTransient
	public Set<ShippingProcess> getShippingProcessSet() {
		return shippingProcessSet;
	}

	public void setShippingProcessSet(Set<ShippingProcess> shippingProcessSet) {
		this.shippingProcessSet = shippingProcessSet;
	}

	@XmlTransient
	public Set<PaymentTransaction> getPaymentTransactionSet() {
		return paymentTransactionSet;
	}

	public void setPaymentTransactionSet(Set<PaymentTransaction> paymentTransactionSet) {
		this.paymentTransactionSet = paymentTransactionSet;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@XmlTransient
	public Set<CartItem> getCartItemSet() {
		return cartItemSet;
	}

	public void setCartItemSet(Set<CartItem> cartItemSet) {
		this.cartItemSet = cartItemSet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cartId != null ? cartId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Cart)) {
			return false;
		}
		Cart other = (Cart) object;
		if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo.Cart[ cartId=" + cartId + " ]";
	}
	
}
