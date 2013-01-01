package be.kdg.reference.services;

import be.kdg.reference.dao.UserDao;
import be.kdg.reference.model.Favorite;
import be.kdg.reference.model.SessionId;
import be.kdg.reference.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public class UserControllerImpl implements UserController {
    private UserDao userDao;
    private SessionManager sessionManager;

    public UserControllerImpl(UserDao userDao, SessionManager sessionManager) {
        this.userDao = userDao;
        this.sessionManager = sessionManager;
    }

    public SessionId login(String username, String password) {
        User user = userDao.retrieve(username);
        if (user.isPasswordEqualTo(password)) {
            return sessionManager.createNewSessionId(username);
        } else {
            return SessionId.INVALID_SESSION_ID;
        }
    }

    public Set<Favorite> getFavorites(SessionId sessionId) throws IllegalAccessException {
        if (!sessionId.isValid()) {
            throw new IllegalAccessException("Access denied");
        }
        String username = sessionManager.findUsername(sessionId);
        if (username == null) {
            throw new IllegalAccessException("Access denied");
        }
        User user = userDao.retrieve(username);
        return user.getFavorites();
    }

    public void addFavorite(SessionId sessionId, String favorite) throws IllegalAccessException {
        if (!sessionId.isValid()) {
            throw new IllegalAccessException("Access denied");
        }
        String username = sessionManager.findUsername(sessionId);
        if (username == null) {
            throw new IllegalAccessException("Access denied");
        }
        User user = userDao.retrieve(username);
        user.addFavorite(favorite);
        userDao.update(user);
    }
}
