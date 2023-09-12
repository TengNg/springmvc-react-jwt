package com.ndt.repositories;

import com.ndt.pojo.ShippingProcess;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ShippingProcessRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

	public void saveShippingProcess(ShippingProcess shippingProcess) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(shippingProcess);
	}

	public void deleteShippingProcessByCartId(String cartId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("DELETE FROM ShippingProcess WHERE cart_id = :cartId");
		q.setParameter("cartId", cartId);
		q.executeUpdate();
	}	

}


