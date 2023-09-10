package com.ndt.repositories;

import com.ndt.pojo.User;
import java.util.Date;
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
public class UserRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    public User getUserByUsername(String username) {
		Session s = this.factory.getObject().getCurrentSession();
		Query q = s.createQuery("FROM User WHERE username=:username");
		q.setParameter("username", username);
		List results = q.getResultList();
		if (results.isEmpty()){
			return null;
		}	
		return (User) results.get(0);
    }

    public User getUserByRefreshToken(String refreshToken) {
		Session s = this.factory.getObject().getCurrentSession();
		Query q = s.createQuery("FROM User WHERE refresh_token=:refreshToken");
		q.setParameter("refreshToken", refreshToken);
		List results = q.getResultList();
		if (results.isEmpty()){
			return null;
		}	
		return (User) results.get(0);
    }

    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
		if (u == null) return false;
        return password.equals(u.getPassword());
    }

	public void updateRefreshToken(String username, String refreshToken) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("UPDATE User u SET u.refresh_token = :refreshToken WHERE u.username = :username");
        q.setParameter("username", username);
        q.setParameter("refreshToken", refreshToken);
		q.executeUpdate();
	}

	public List<User> getAllUsers() {
		Session s = this.factory.getObject().getCurrentSession();
		Query q = s.createQuery("FROM User");
		return q.getResultList();
	}

	public List<User> getAllSellerAccounts() {
		Session s = this.factory.getObject().getCurrentSession();
		Query q = s.createQuery("FROM User where user_role='ROLE_SELLER'");
		return q.getResultList();
	}

    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
		u.setUserId(UUID.randomUUID().toString());
		u.setCreatedAt(new Date());
        s.save(u);
        return u;
    }

    public void update(User u) {
        Session s = this.factory.getObject().getCurrentSession();
		s.update(u);
    }

    public void delete(int id) {
        Session s = this.factory.getObject().getCurrentSession();
		Query q = s.createQuery("DELETE FROM User WHERE id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
    }
}
