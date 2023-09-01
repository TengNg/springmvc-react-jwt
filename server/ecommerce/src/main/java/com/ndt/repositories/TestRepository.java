package com.ndt.repositories;

import com.ndt.pojo.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TestRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

    public List<SaleOrder> getUserOrders(int id) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM SaleOrder WHERE user_id=:id");
		q.setParameter("id", id);
		return q.getResultList();
    }

	public List<OrderDetail> getOrderDetails(int orderId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM OrderDetail WHERE order_id=:id");
		q.setParameter("id", orderId);
		return q.getResultList();
	}
}

