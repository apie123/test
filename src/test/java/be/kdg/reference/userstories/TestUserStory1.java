package be.kdg.reference.userstories;

import be.kdg.reference.backingBeans.FacesContextHelper;
import be.kdg.reference.backingBeans.LoginBean;
import be.kdg.reference.backingBeans.UserBean;
import be.kdg.reference.dao.UserDao;
import be.kdg.reference.model.SessionId;
import be.kdg.reference.model.User;
import be.kdg.reference.model.Favorite;
import be.kdg.reference.util.FacesContextMock;
import be.kdg.reference.util.OpenSessionInTestBase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TestUserStory1 extends OpenSessionInTestBase {
    private LoginBean loginBean;
    private UserBean userBean;
    private UserDao userDao;

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:integrationTestContext.xml"};
    }

    @Override
    public void doSetUp() {
        FacesContextHelper.setFacesContext(new FacesContextMock());
        User user = new User("validUsername", "validPassword");
        user.addFavorite("fav1");
        user.addFavorite("fav2");
        user.addFavorite("fav3");
        userDao.create(user);
    }

    @Override
    protected void doTearDown() {
        FacesContextHelper.reset();
    }

    public void testLoginSuccess() throws Exception {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("validPassword");
        String result = loginBean.login();
        assertEquals("valid login should return 'success'", "success", result);
        SessionId sessionId = loginBean.getSessionId();
        assertTrue("session id should be valid", sessionId.isValid());
    }

    public void testLoginWrongPassword() throws Exception {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("wrongPassword");
        String result = loginBean.login();
        assertEquals("login with wrong password should return 'failure'", "failure", result);
        SessionId sessionId = loginBean.getSessionId();
        assertFalse("session id for wrong login must be invalid", sessionId.isValid());
    }

    public void testLoginWrongUsername() throws Exception {
        loginBean.setUsername("wrongUsername");
        loginBean.setPassword("validPassword");
        String result = loginBean.login();
        assertEquals("login with wrong username should return 'failure'", "failure", result);
        SessionId sessionId = loginBean.getSessionId();
        assertFalse("session id for wrong login must be invalid", sessionId.isValid());
    }

    public void testDifferentSessionIdOnSession() throws Exception {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("validPassword");
        loginBean.login();
        SessionId sessionId1 = loginBean.getSessionId();
        loginBean.login();
        SessionId sessionId2 = loginBean.getSessionId();
        assertFalse("session id's must be different", sessionId1.equals(sessionId2));
    }

    public void testGetFavorites() {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("validPassword");
        loginBean.login();
        List<Favorite> favorites = userBean.getFavorites();
        assertTrue(favorites.contains(new Favorite("fav1")));
        assertTrue(favorites.contains(new Favorite("fav2")));
        assertTrue(favorites.contains(new Favorite("fav3")));
    }
}
