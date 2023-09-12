/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo;

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
@Table(name = "shipper")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Shipper.findAll", query = "SELECT s FROM Shipper s"),
	@NamedQuery(name = "Shipper.findByShipperId", query = "SELECT s FROM Shipper s WHERE s.shipperId = :shipperId"),
	@NamedQuery(name = "Shipper.findByShipperName", query = "SELECT s FROM Shipper s WHERE s.shipperName = :shipperName")})
public class Shipper implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipper_id")
	private Integer shipperId;
	@Size(max = 255)
    @Column(name = "shipper_name")
	private String shipperName;
	@OneToMany(mappedBy = "shipperId")
	private Set<ShippingProcess> shippingProcessSet;

	public Shipper() {
	}

	public Shipper(Integer shipperId) {
		this.shipperId = shipperId;
	}

	public Shipper(Integer shipperId, String shipperName) {
		this.shipperId = shipperId;
		this.shipperName = shipperName;
	}

	public Integer getShipperId() {
		return shipperId;
	}

	public void setShipperId(Integer shipperId) {
		this.shipperId = shipperId;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	@XmlTransient
	public Set<ShippingProcess> getShippingProcessSet() {
		return shippingProcessSet;
	}

	public void setShippingProcessSet(Set<ShippingProcess> shippingProcessSet) {
		this.shippingProcessSet = shippingProcessSet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (shipperId != null ? shipperId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Shipper)) {
			return false;
		}
		Shipper other = (Shipper) object;
		if ((this.shipperId == null && other.shipperId != null) || (this.shipperId != null && !this.shipperId.equals(other.shipperId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo.Shipper[ shipperId=" + shipperId + " ]";
	}
	
}
