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
@Table(name = "user_roles")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u"),
	@NamedQuery(name = "UserRoles.findByRoleId", query = "SELECT u FROM UserRoles u WHERE u.roleId = :roleId"),
	@NamedQuery(name = "UserRoles.findByRoleName", query = "SELECT u FROM UserRoles u WHERE u.roleName = :roleName")})
public class UserRoles implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id")
	private Integer roleId;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "role_name")
	private String roleName;
	@OneToMany(mappedBy = "roleId")
	private Set<Users> usersSet;

	public UserRoles() {
	}

	public UserRoles(Integer roleId) {
		this.roleId = roleId;
	}

	public UserRoles(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@XmlTransient
	public Set<Users> getUsersSet() {
		return usersSet;
	}

	public void setUsersSet(Set<Users> usersSet) {
		this.usersSet = usersSet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (roleId != null ? roleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserRoles)) {
			return false;
		}
		UserRoles other = (UserRoles) object;
		if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo2.UserRoles[ roleId=" + roleId + " ]";
	}
	
}
