package be.kdg.reference.userstories;

import be.kdg.reference.backingBeans.FacesContextHelper;
import be.kdg.reference.backingBeans.LoginBean;
import be.kdg.reference.backingBeans.UserBean;
import be.kdg.reference.dao.UserDao;
import be.kdg.reference.model.Favorite;
import be.kdg.reference.model.User;
import be.kdg.reference.util.FacesContextMock;
import be.kdg.reference.util.OpenSessionInTestBase;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestUserStory2 extends OpenSessionInTestBase {
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

        loginBean.setUsername("validUsername");
        loginBean.setPassword("validPassword");
        loginBean.login();
    }

    @Override
    protected void doTearDown() {
        FacesContextHelper.reset();
    }

    public void testReturnValue() {
        userBean.setNewFavorite("fav4");
        String result = userBean.addFavorite();
        assertEquals("success", result);
    }

    public void testFavoriteAdded() {
        userBean.setNewFavorite("fav4");
        userBean.addFavorite();
        User user = userDao.retrieve("validUsername");
        Set<Favorite> favorites = user.getFavorites();
        assertEquals(4, favorites.size());
        assertTrue(favorites.contains(new Favorite("fav4")));
    }
}
