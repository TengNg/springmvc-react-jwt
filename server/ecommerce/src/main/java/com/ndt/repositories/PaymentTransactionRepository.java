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

}



