package com.ndt.repositories;

import com.ndt.pojo.ShippingProcess;
import java.util.List;
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

	public void addCartItem(ShippingProcess shippingProcess) {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
		s.save(shippingProcess);
	}

}



