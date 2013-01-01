package be.kdg.reference.services;

import be.kdg.reference.model.SessionId;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestSessionManager extends AbstractDependencyInjectionSpringContextTests {
    private SessionManager sessionManager;

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:userControllerContext.xml"};
    }

    public void testCreateSessionId() {
        SessionId sessionId = sessionManager.createNewSessionId("bla");
        assertNotNull(sessionId);
        assertTrue(sessionId.isValid());
    }

    public void testMultipleSessionIds() {
        SessionId sessionId1 = sessionManager.createNewSessionId("bla");
        SessionId sessionId2 = sessionManager.createNewSessionId("bla");
        assertFalse(sessionId1.equals(sessionId2));
        assertFalse(sessionId1.getValue().equals(sessionId2.getValue()));
    }

    public void testFindUsername() {
        SessionId sessionId = SessionId.INVALID_SESSION_ID;
        String username = sessionManager.findUsername(sessionId);
        assertNull(username);
        sessionId = sessionManager.createNewSessionId("bla");
        sessionManager.createNewSessionId("boe");
        username = sessionManager.findUsername(sessionId);
        assertNotNull(username);
        assertEquals("bla", username);
    }
}
