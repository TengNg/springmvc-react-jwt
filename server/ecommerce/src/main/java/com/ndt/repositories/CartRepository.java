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
		cart.setCartId(UUID.randomUUID().toString());
		s.save(cart);
	}

}

