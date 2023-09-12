package com.ndt.repositories;

import com.ndt.pojo.Product;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

    public List<Product> getProducts() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Product");
        return q.getResultList();
    }

    public List<Product> getProducts(int page, int size) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		int offset = page * size;
        Query q = s.createQuery("FROM Product ORDER BY created_at DESC")
				.setFirstResult(offset)
				.setMaxResults(size);
        return q.getResultList();
    }

	public long getTotalPages(int size) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        String countHql = "SELECT COUNT(*) FROM Product";
        Query countQuery = s.createQuery(countHql, Long.class);
        long totalCount = (long) countQuery.getSingleResult();
        long totalPages = (totalCount + size - 1) / size; 
        return totalPages;
    }

    public Product getProductById(String id) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM Product WHERE product_id=:id");
		q.setParameter("id", id);
		List results = q.getResultList();
		if (results.isEmpty()){
			return null;
		}	
		return (Product) results.get(0);
    }

	public List<Product> getProductsBySellerId(String sellerId) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Product WHERE user_id = :sellerId");
		q.setParameter("sellerId", sellerId);
        return q.getResultList();
	}	

    public void update(Product p) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.update(p);
    }

	public void deleteProductById(String id) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("DELETE FROM Product WHERE product_id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public Product addProduct(Product product) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        s.save(product);
        return product;
	}
}
