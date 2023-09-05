package com.ndt.repositories;

import com.ndt.pojo.Reply;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReplyRepository {
	@Autowired
	LocalSessionFactoryBean localSessionFactoryBean;

	public List<Reply> getReplies(String reviewId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM Reply WHERE review_id = :reviewId ORDER BY created_at ASC");
		q.setParameter("reviewId", reviewId);
		return q.getResultList();
	}

	public Reply postReply(Reply reply) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(reply);
		return reply;
	}
}


