package be.kdg.reference.dao;

import be.kdg.reference.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDaoWithHibernate extends HibernateDaoSupport implements UserDao {
    public void setInitialUser(String username) {
        User user = new User(username, username);
        create(user);
    }

    public User retrieve(String username) {
        User user = (User) getHibernateTemplate().get(User.class, username);
        if (user == null) {
            return User.INVALID_USER;
        }
        return user;
    }

    public void create(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    public void update(User user) {
        getHibernateTemplate().update(user);
    }
}
