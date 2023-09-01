/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "carts")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Carts.findAll", query = "SELECT c FROM Carts c"),
	@NamedQuery(name = "Carts.findByCartId", query = "SELECT c FROM Carts c WHERE c.cartId = :cartId")})
public class Carts implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "cart_id")
	private String cartId;
	@OneToMany(mappedBy = "cartId")
	private Set<ShippingProcesses> shippingProcessesSet;
	@JoinColumn(name = "payment_method_id", referencedColumnName = "method_id")
    @ManyToOne
	private PaymentMethods paymentMethodId;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
	private Users userId;
	@OneToMany(mappedBy = "cartId")
	private Set<CartItems> cartItemsSet;
	@OneToMany(mappedBy = "cartId")
	private Set<PaymentTransactions> paymentTransactionsSet;

	public Carts() {
	}

	public Carts(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	@XmlTransient
	public Set<ShippingProcesses> getShippingProcessesSet() {
		return shippingProcessesSet;
	}

	public void setShippingProcessesSet(Set<ShippingProcesses> shippingProcessesSet) {
		this.shippingProcessesSet = shippingProcessesSet;
	}

	public PaymentMethods getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(PaymentMethods paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	@XmlTransient
	public Set<CartItems> getCartItemsSet() {
		return cartItemsSet;
	}

	public void setCartItemsSet(Set<CartItems> cartItemsSet) {
		this.cartItemsSet = cartItemsSet;
	}

	@XmlTransient
	public Set<PaymentTransactions> getPaymentTransactionsSet() {
		return paymentTransactionsSet;
	}

	public void setPaymentTransactionsSet(Set<PaymentTransactions> paymentTransactionsSet) {
		this.paymentTransactionsSet = paymentTransactionsSet;
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
		if (!(object instanceof Carts)) {
			return false;
		}
		Carts other = (Carts) object;
		if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.Carts[ cartId=" + cartId + " ]";
	}
	
}
