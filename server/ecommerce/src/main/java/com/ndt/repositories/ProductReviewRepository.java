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
		Query q = s.createQuery("FROM ProductReview WHERE product_id = :productId ORDER BY review_date DESC");
		q.setParameter("productId", productId);
		return q.getResultList();
	}

	public ProductReview getProductReviewById(String id) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM ProductReview WHERE review_id = :id");
		q.setParameter("id", id);
		List results = q.getResultList();
		if (results.isEmpty()){
			return null;
		}	
		return (ProductReview) results.get(0);
	}

	public ProductReview postReview(ProductReview productReview) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(productReview);
		return productReview;
	}

    public void update(ProductReview productReview) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.update(productReview);
    }
}

