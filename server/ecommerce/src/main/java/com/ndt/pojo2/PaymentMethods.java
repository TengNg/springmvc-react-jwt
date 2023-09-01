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
@Table(name = "payment_methods")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "PaymentMethods.findAll", query = "SELECT p FROM PaymentMethods p"),
	@NamedQuery(name = "PaymentMethods.findByMethodId", query = "SELECT p FROM PaymentMethods p WHERE p.methodId = :methodId"),
	@NamedQuery(name = "PaymentMethods.findByMethodName", query = "SELECT p FROM PaymentMethods p WHERE p.methodName = :methodName")})
public class PaymentMethods implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "method_id")
	private Integer methodId;
	@Size(max = 255)
    @Column(name = "method_name")
	private String methodName;
	@OneToMany(mappedBy = "paymentMethodId")
	private Set<Carts> cartsSet;
	@OneToMany(mappedBy = "methodId")
	private Set<PaymentTransactions> paymentTransactionsSet;

	public PaymentMethods() {
	}

	public PaymentMethods(Integer methodId) {
		this.methodId = methodId;
	}

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@XmlTransient
	public Set<Carts> getCartsSet() {
		return cartsSet;
	}

	public void setCartsSet(Set<Carts> cartsSet) {
		this.cartsSet = cartsSet;
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
		hash += (methodId != null ? methodId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof PaymentMethods)) {
			return false;
		}
		PaymentMethods other = (PaymentMethods) object;
		if ((this.methodId == null && other.methodId != null) || (this.methodId != null && !this.methodId.equals(other.methodId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.PaymentMethods[ methodId=" + methodId + " ]";
	}
	
}
