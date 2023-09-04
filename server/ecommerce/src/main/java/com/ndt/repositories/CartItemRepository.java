package com.ndt.repositories;

import com.ndt.pojo.CartItem;
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
public class CartItemRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

	public void addCartItem(CartItem cartItem) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(cartItem);
	}

}


