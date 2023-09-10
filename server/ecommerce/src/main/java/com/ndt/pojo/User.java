/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
	@NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
	@NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
	@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
	@NamedQuery(name = "User.findByImageUrl", query = "SELECT u FROM User u WHERE u.imageUrl = :imageUrl"),
	@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
	@NamedQuery(name = "User.findByRefreshToken", query = "SELECT u FROM User u WHERE u.refreshToken = :refreshToken"),
	@NamedQuery(name = "User.findByUserRole", query = "SELECT u FROM User u WHERE u.userRole = :userRole"),
	@NamedQuery(name = "User.findByCreatedAt", query = "SELECT u FROM User u WHERE u.createdAt = :createdAt")})
public class User implements Serializable {
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
	@Size(max = 255)
    @Column(name = "image_url")
	private String imageUrl;
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
	@Size(max = 20)
    @Column(name = "user_role")
	private String userRole;
	@Column(name = "is_confirmed")
	private Boolean isConfirmed;
	@Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToMany(mappedBy = "userId")
	@JsonIgnore
	private Set<ProductReview> productReviewSet;
	@OneToMany(mappedBy = "userId")
	@JsonIgnore
	private Set<PaymentTransaction> paymentTransactionSet;
	@OneToMany(mappedBy = "userId")
	@JsonIgnore
	private Set<Cart> cartSet;

	@Transient
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Boolean getIsConfirmed() {
		return this.isConfirmed;
	}

	public User() {
	}

	public User(String userId) {
		this.userId = userId;
	}

	public User(String userId, String email, String username, String password) {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@XmlTransient
	public Set<ProductReview> getProductReviewSet() {
		return productReviewSet;
	}

	public void setProductReviewSet(Set<ProductReview> productReviewSet) {
		this.productReviewSet = productReviewSet;
	}

	@XmlTransient
	public Set<PaymentTransaction> getPaymentTransactionSet() {
		return paymentTransactionSet;
	}

	public void setPaymentTransactionSet(Set<PaymentTransaction> paymentTransactionSet) {
		this.paymentTransactionSet = paymentTransactionSet;
	}

	@XmlTransient
	public Set<Cart> getCartSet() {
		return cartSet;
	}

	public void setCartSet(Set<Cart> cartSet) {
		this.cartSet = cartSet;
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
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo.User[ userId=" + userId + " ]";
	}
	
}
