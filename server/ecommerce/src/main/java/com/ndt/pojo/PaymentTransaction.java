/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "payment_transaction")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "PaymentTransaction.findAll", query = "SELECT p FROM PaymentTransaction p"),
	@NamedQuery(name = "PaymentTransaction.findByTransactionId", query = "SELECT p FROM PaymentTransaction p WHERE p.transactionId = :transactionId"),
	@NamedQuery(name = "PaymentTransaction.findByMethodId", query = "SELECT p FROM PaymentTransaction p WHERE p.methodId = :methodId"),
	@NamedQuery(name = "PaymentTransaction.findByAmount", query = "SELECT p FROM PaymentTransaction p WHERE p.amount = :amount"),
	@NamedQuery(name = "PaymentTransaction.findByTransactionDate", query = "SELECT p FROM PaymentTransaction p WHERE p.transactionDate = :transactionDate")})
public class PaymentTransaction implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
	private Integer transactionId;
	@Column(name = "method_id")
	private Integer methodId;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @ManyToOne
	private Cart cartId;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
	private User userId;

	public PaymentTransaction() {
	}

	public PaymentTransaction(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Cart getCartId() {
		return cartId;
	}

	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (transactionId != null ? transactionId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof PaymentTransaction)) {
			return false;
		}
		PaymentTransaction other = (PaymentTransaction) object;
		if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo.PaymentTransaction[ transactionId=" + transactionId + " ]";
	}
	
}
