package be.kdg.reference.backingBeans;

import be.kdg.reference.model.Favorite;
import be.kdg.reference.util.FacesContextMock;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.util.List;

public class TestUserBean extends AbstractDependencyInjectionSpringContextTests {
    private LoginBean loginBean;
    private UserBean userBean;

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:backingBeanContext.xml"};
    }

    @Override
    public void onSetUp() {
        FacesContextHelper.setFacesContext(new FacesContextMock());
    }

    @Override
    protected void onTearDown() {
        FacesContextHelper.reset();
    }

    public void testGetFavorites() {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("validPassword");
        loginBean.login();
        List<Favorite> favorites = userBean.getFavorites();
        assertEquals(2, favorites.size());
        assertEquals("fav1", favorites.get(0).getName());
        assertEquals("fav2", favorites.get(1).getName());
    }

    public void testAddFavoriteSuccess() {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("validPassword");
        loginBean.login();
        userBean.setNewFavorite("favorite");
        String result = userBean.addFavorite();
        assertEquals("success", result);
    }

    public void testAddFavoriteAccessDenied() {
        loginBean.setUsername("bla");
        loginBean.setPassword("bla");
        loginBean.login();
        userBean.setNewFavorite("favorite");
        String result = userBean.addFavorite();
        assertEquals("accessDenied", result);
    }
}
