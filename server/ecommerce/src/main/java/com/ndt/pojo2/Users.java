/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo2;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
	@NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
	@NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
	@NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
	@NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
	@NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
	@NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
	@NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
	@NamedQuery(name = "Users.findByRefreshToken", query = "SELECT u FROM Users u WHERE u.refreshToken = :refreshToken"),
	@NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt")})
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "user_id")
	private String userId;
	@Size(max = 50)
    @Column(name = "first_name")
	private String firstName;
	@Size(max = 50)
    @Column(name = "last_name")
	private String lastName;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
	private String email;
	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
	@Size(max = 20)
    @Column(name = "phone")
	private String phone;
	@Lob
    @Size(max = 65535)
    @Column(name = "address")
	private String address;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
	private String username;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
	private String password;
	@Size(max = 255)
    @Column(name = "refresh_token")
	private String refreshToken;
	@Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToMany(mappedBy = "userId")
	private Set<Carts> cartsSet;
	@OneToMany(mappedBy = "userId")
	private Set<PaymentTransactions> paymentTransactionsSet;
	@OneToMany(mappedBy = "userId")
	private Set<ProductReviews> productReviewsSet;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
	private Sellers sellers;
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne
	private UserRoles roleId;

	public Users() {
	}

	public Users(String userId) {
		this.userId = userId;
	}

	public Users(String userId, String email, String username, String password) {
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	@XmlTransient
	public Set<ProductReviews> getProductReviewsSet() {
		return productReviewsSet;
	}

	public void setProductReviewsSet(Set<ProductReviews> productReviewsSet) {
		this.productReviewsSet = productReviewsSet;
	}

	public Sellers getSellers() {
		return sellers;
	}

	public void setSellers(Sellers sellers) {
		this.sellers = sellers;
	}

	public UserRoles getRoleId() {
		return roleId;
	}

	public void setRoleId(UserRoles roleId) {
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.Users[ userId=" + userId + " ]";
	}
	
}
