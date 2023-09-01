/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

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
@Table(name = "payment_transactions")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "PaymentTransactions.findAll", query = "SELECT p FROM PaymentTransactions p"),
	@NamedQuery(name = "PaymentTransactions.findByTransactionId", query = "SELECT p FROM PaymentTransactions p WHERE p.transactionId = :transactionId"),
	@NamedQuery(name = "PaymentTransactions.findByAmount", query = "SELECT p FROM PaymentTransactions p WHERE p.amount = :amount"),
	@NamedQuery(name = "PaymentTransactions.findByTransactionDate", query = "SELECT p FROM PaymentTransactions p WHERE p.transactionDate = :transactionDate")})
public class PaymentTransactions implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
	private Integer transactionId;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @ManyToOne
	private Carts cartId;
	@JoinColumn(name = "method_id", referencedColumnName = "method_id")
    @ManyToOne
	private PaymentMethods methodId;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
	private Users userId;

	public PaymentTransactions() {
	}

	public PaymentTransactions(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
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

	public Carts getCartId() {
		return cartId;
	}

	public void setCartId(Carts cartId) {
		this.cartId = cartId;
	}

	public PaymentMethods getMethodId() {
		return methodId;
	}

	public void setMethodId(PaymentMethods methodId) {
		this.methodId = methodId;
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
		hash += (transactionId != null ? transactionId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof PaymentTransactions)) {
			return false;
		}
		PaymentTransactions other = (PaymentTransactions) object;
		if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.PaymentTransactions[ transactionId=" + transactionId + " ]";
	}
	
}
