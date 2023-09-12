package com.ndt.repositories;

import com.ndt.pojo.Cart;
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
public class CartRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

	public void addCart(Cart cart) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(cart);
	}

	public Cart getCart(String id) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM Cart WHERE cart_id = :id");
		q.setParameter("id", id);
		List results = q.getResultList();
		if (results.isEmpty()){
			return null;
		}	
		return (Cart) results.get(0);
	}

	public List<Cart> getCartsBydUserId(String userId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM Cart WHERE user_id = :userId");
		q.setParameter("userId", userId);
		return q.getResultList();
	}

	public void delete(String cartId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("DELETE FROM Cart WHERE cart_id = :cartId");
		q.setParameter("cartId", cartId);
		q.executeUpdate();
	}
}

