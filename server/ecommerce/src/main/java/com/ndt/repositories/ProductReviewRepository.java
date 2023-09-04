package com.ndt.repositories;

import com.ndt.pojo.ProductReview;
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
public class ProductReviewRepository {

	@Autowired
	LocalSessionFactoryBean localSessionFactoryBean;

	public List<ProductReview> getReviews(String productId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM ProductReview ORDER BY review_date DESC");
		return q.getResultList();
	}

	public ProductReview postReview(ProductReview productReview) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(productReview);
		return productReview;
	}
}

