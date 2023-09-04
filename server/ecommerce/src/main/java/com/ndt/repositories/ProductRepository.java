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

	public void deleteProductById(String id) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("DELETE FROM Product WHERE product_id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public Product addProduct(Product product) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		product.setProductId(UUID.randomUUID().toString());
		product.setCreatedAt(new Date());
        s.save(product);
        return product;
	}
}
