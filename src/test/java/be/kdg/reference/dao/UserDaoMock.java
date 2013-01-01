package be.kdg.reference.dao;

import be.kdg.reference.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoMock implements UserDao {
    private Map<String, User> users;

    public UserDaoMock() {
        this.users = new HashMap<String, User>();
    }

    public User retrieve(String username) {
        User user = users.get(username);
        if (user == null) return User.INVALID_USER;
        return new User(user);
    }

    public void create(User user) {
        users.put(user.getUsername(), new User(user));
    }

    public void update(User user) {
        users.put(user.getUsername(), new User(user));
    }
}
