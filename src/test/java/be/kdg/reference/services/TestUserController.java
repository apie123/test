package be.kdg.reference.services;

import be.kdg.reference.dao.UserDao;
import be.kdg.reference.model.Favorite;
import be.kdg.reference.model.SessionId;
import be.kdg.reference.model.User;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.util.Set;

public class TestUserController extends AbstractDependencyInjectionSpringContextTests {
    private UserController userController;
    private UserDao userDao;

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:userControllerContext.xml"};
    }

    @Override
    public void onSetUp() {
        User user = new User("validUsername", "validPassword");
        user.addFavorite("fav1");
        user.addFavorite("fav2");
        user.addFavorite("fav3");
        userDao.create(user);
    }

    @Override
    protected void onTearDown() throws Exception {
    }

    public void testLoginSuccess() {
        SessionId sessionId = userController.login("validUsername", "validPassword");
        assertNotNull("a sessionId may not be null", sessionId);
        assertTrue("succesful login should return a valid session", sessionId.isValid());
    }

    public void testLoginWrongPassword() {
        SessionId sessionId = userController.login("validUsername", "wrongpassword");
        assertNotNull("a sessionId may not be null", sessionId);
        assertFalse("login with wrong password should return invalid sessionId", sessionId.isValid());
    }

    public void testLoginWrongUsername() {
        SessionId sessionId = userController.login("wrongUsername", "validPassword");
        assertNotNull("a sessionId may not be null", sessionId);
        assertFalse("login with wrong username should return invalid sessionId", sessionId.isValid());
    }

    public void testSessionId() {
        SessionId sessionId1 = userController.login("validUsername", "validPassword");
        SessionId sessionId2 = userController.login("validUsername", "validPassword");
        assertNotSame("sessionIds must be different with each login", sessionId1, sessionId2);
        assertFalse("sessionIds must be different with each login", sessionId1.equals(sessionId2));
        String value1 = sessionId1.getValue();
        String value2 = sessionId2.getValue();
        assertFalse("values of different sessionIds must be different", value1.equals(value2));
    }

    public void testGetFavorites() {
        try {
            SessionId sessionId = userController.login("validUsername", "validPassword");
            Set<Favorite> favorites = userController.getFavorites(sessionId);
            assertEquals(3, favorites.size());
            assertTrue(favorites.contains(new Favorite("fav1")));
            assertTrue(favorites.contains(new Favorite("fav2")));
            assertTrue(favorites.contains(new Favorite("fav3")));
        } catch (IllegalAccessException e) {
            fail();
        }
    }

    public void testGetFavoritesSecurity() {
        try {
            SessionId sessionId = SessionId.INVALID_SESSION_ID;
            userController.getFavorites(sessionId);
            fail();
        } catch (IllegalAccessException e) {
            // test ok, should throw an exception
        }
        try {
            SessionId sessionId = new SessionId("42");
            userController.getFavorites(sessionId);
            fail();
        } catch (IllegalAccessException e) {
            // test ok, should throw an exception
        }
    }

    public void testAddFavorite() {
        try {
            SessionId sessionId = userController.login("validUsername", "validPassword");
            userController.addFavorite(sessionId, "fav4");
            Set<Favorite> favorites = userController.getFavorites(sessionId);
            assertEquals(4, favorites.size());
            assertTrue(favorites.contains(new Favorite("fav1")));
            assertTrue(favorites.contains(new Favorite("fav2")));
            assertTrue(favorites.contains(new Favorite("fav3")));
            assertTrue(favorites.contains(new Favorite("fav4")));
        } catch (IllegalAccessException e) {
            fail();
        }
    }

    public void testAddFavoriteSecurity() {
        try {
            SessionId sessionId = SessionId.INVALID_SESSION_ID;
            userController.addFavorite(sessionId, "fav4");
            fail();
        } catch (IllegalAccessException e) {
            // test ok, should throw an exception
        }
        try {
            SessionId sessionId = new SessionId("42");
            userController.addFavorite(sessionId, "fav4");
            fail();
        } catch (IllegalAccessException e) {
            // test ok, should throw an exception
        }
    }
}