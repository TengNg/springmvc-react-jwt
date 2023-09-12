package com.ndt.repositories;

import com.ndt.pojo.PaymentTransaction;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PaymentTransactionRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

	public void saveTransaction(PaymentTransaction transaction) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(transaction);
	}

	public List<PaymentTransaction> getTransactionsByUserId(String userId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM PaymentTransaction WHERE user_id=:userId");
		q.setParameter("userId", userId);
		return q.getResultList();
	}	

	public PaymentTransaction getTransactionByCartId(String cartId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("FROM PaymentTransaction WHERE cart_id=:cartId");
		q.setParameter("cartId", cartId);
		List results = q.getResultList();
		if (results.isEmpty()){
			return null;
		}	
		return (PaymentTransaction) results.get(0);
	}	

	public void removePaymentTransactionByCartId(String cartId) {
		Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		Query q = s.createQuery("DELETE FROM PaymentTransaction WHERE cart_id = :cartId");
		q.setParameter("cartId", cartId);
		q.executeUpdate();
	}
}
