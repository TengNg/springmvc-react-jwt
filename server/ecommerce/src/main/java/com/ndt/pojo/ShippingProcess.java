/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "shipping_process")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ShippingProcess.findAll", query = "SELECT s FROM ShippingProcess s"),
	@NamedQuery(name = "ShippingProcess.findByProcessId", query = "SELECT s FROM ShippingProcess s WHERE s.processId = :processId"),
	@NamedQuery(name = "ShippingProcess.findByShippingStatus", query = "SELECT s FROM ShippingProcess s WHERE s.shippingStatus = :shippingStatus"),
	@NamedQuery(name = "ShippingProcess.findByShippingDate", query = "SELECT s FROM ShippingProcess s WHERE s.shippingDate = :shippingDate")})
public class ShippingProcess implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "process_id")
	private Integer processId;
	@Size(max = 255)
    @Column(name = "shipping_status")
	private String shippingStatus;
	@Column(name = "shipping_date")
    @Temporal(TemporalType.DATE)
	private Date shippingDate;
	@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @ManyToOne
	private Cart cartId;
	@JoinColumn(name = "shipper_id", referencedColumnName = "shipper_id")
    @ManyToOne
	private Shipper shipperId;

	public ShippingProcess() {
	}

	public ShippingProcess(Integer processId) {
		this.processId = processId;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Cart getCartId() {
		return cartId;
	}

	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}

	public Shipper getShipperId() {
		return shipperId;
	}

	public void setShipperId(Shipper shipperId) {
		this.shipperId = shipperId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (processId != null ? processId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ShippingProcess)) {
			return false;
		}
		ShippingProcess other = (ShippingProcess) object;
		if ((this.processId == null && other.processId != null) || (this.processId != null && !this.processId.equals(other.processId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo.ShippingProcess[ processId=" + processId + " ]";
	}
	
}
