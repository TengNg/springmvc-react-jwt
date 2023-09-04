package com.ndt.repositories;

import com.ndt.pojo.Shipper;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ShipperRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

	public List<Shipper> getShippers() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Shipper");
        return q.getResultList();
	}

}




