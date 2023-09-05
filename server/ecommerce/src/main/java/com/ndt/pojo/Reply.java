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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "reply")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Reply.findAll", query = "SELECT r FROM Reply r"),
	@NamedQuery(name = "Reply.findByReplyId", query = "SELECT r FROM Reply r WHERE r.replyId = :replyId"),
	@NamedQuery(name = "Reply.findByCreatedAt", query = "SELECT r FROM Reply r WHERE r.createdAt = :createdAt")})
public class Reply implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "reply_id")
	private String replyId;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "response_text")
	private String responseText;
	@Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@JoinColumn(name = "review_id", referencedColumnName = "review_id")
    @ManyToOne(optional = false)
	private ProductReview reviewId;

	public Reply() {
	}

	public Reply(String replyId) {
		this.replyId = replyId;
	}

	public Reply(String replyId, String responseText) {
		this.replyId = replyId;
		this.responseText = responseText;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ProductReview getReviewId() {
		return reviewId;
	}

	public void setReviewId(ProductReview reviewId) {
		this.reviewId = reviewId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (replyId != null ? replyId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Reply)) {
			return false;
		}
		Reply other = (Reply) object;
		if ((this.replyId == null && other.replyId != null) || (this.replyId != null && !this.replyId.equals(other.replyId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ndt.pojo.Reply[ replyId=" + replyId + " ]";
	}
	
}
