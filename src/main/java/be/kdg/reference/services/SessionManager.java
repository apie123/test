package be.kdg.reference.services;

import be.kdg.reference.model.SessionId;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private Map<SessionId, String> sessions;
    private int nextSessionId;

    public SessionManager() {
        this.sessions = new HashMap<SessionId, String>();
        this.nextSessionId = 0;
    }

    public String findUsername(SessionId sessionId) {
        return sessions.get(sessionId);
    }

    public SessionId createNewSessionId(String username) {
        SessionId sessionId = new SessionId("" + nextSessionId);
        nextSessionId++;
        sessions.put(sessionId, username);
        return sessionId;
    }
}
