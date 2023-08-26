package com.ndt.repositories;

import com.ndt.pojo.Category;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepository {
    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

    public List<Category> getCategories() {
        Session s = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Category");
        return q.getResultList();
    }
}
