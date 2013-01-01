package be.kdg.reference.dao;

import be.kdg.reference.model.User;

public interface UserDao {
    User retrieve(String username);

    void create(User user);

    void update(User user);
}
