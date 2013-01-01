package be.kdg.reference.services;

import be.kdg.reference.model.Favorite;
import be.kdg.reference.model.SessionId;

import java.util.HashSet;
import java.util.Set;

public class UserControllerMock implements UserController {
    private int nextSessionId = 0;

    public SessionId login(String username, String password) {
        if (username.equals("validUsername") && password.equals("validPassword")) {
            return new SessionId("" + nextSessionId++);
        } else {
            return SessionId.INVALID_SESSION_ID;
        }
    }

    public Set<Favorite> getFavorites(SessionId sessionId) {
        Set<Favorite> result = new HashSet<Favorite>();
        result.add(new Favorite("fav1"));
        result.add(new Favorite("fav2"));
        return result;
    }

    public void addFavorite(SessionId sessionId, String favorite) throws IllegalAccessException {
        if (!sessionId.isValid()) {
            throw new IllegalAccessException();
        }
    }
}
