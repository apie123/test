package be.kdg.reference.services;

import be.kdg.reference.model.Favorite;
import be.kdg.reference.model.SessionId;

import java.util.Set;

public interface UserController {
    SessionId login(String username, String password);

    Set<Favorite> getFavorites(SessionId sessionId) throws IllegalAccessException;

    void addFavorite(SessionId sessionId, String favorite) throws IllegalAccessException;
}
