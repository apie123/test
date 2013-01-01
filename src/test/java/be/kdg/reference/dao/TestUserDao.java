package be.kdg.reference.dao;

import be.kdg.reference.model.Favorite;
import be.kdg.reference.model.User;
import be.kdg.reference.util.OpenSessionInTestBase;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public class TestUserDao extends OpenSessionInTestBase {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:userDaoContext.xml"};
    }

    @Override
    public void doSetUp() {
        User user = new User("bla", "pass");
        user.addFavorite("test1");
        user.addFavorite("test2");
        userDao.create(user);
    }

    @Override
    protected void doTearDown() {
    }

    public void testCreateAndRetrieveUser() {
        User user = new User("boe", "boepass");
        userDao.create(user);
        User retrievedUser = userDao.retrieve("boe");
        assertEquals(user, retrievedUser);
        retrievedUser = userDao.retrieve("bla");
        Set<Favorite> favorites = retrievedUser.getFavorites();
        assertEquals(2, favorites.size());
        assertEquals("bla", retrievedUser.getUsername());
        assertTrue(retrievedUser.isPasswordEqualTo("pass"));
    }
}
