package be.kdg.reference.backingBeans;

import be.kdg.reference.model.Favorite;
import be.kdg.reference.model.SessionId;
import be.kdg.reference.services.UserController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserBean {
    private String newFavorite;

    private LoginBean loginBean;
    private UserController userController;

    public UserBean() {
        this.newFavorite = "";
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getUsername() {
        return loginBean.getUsername();
    }

    public String getPassword() {
        return loginBean.getPassword();
    }

    public String getNewFavorite() {
        return newFavorite;
    }

    public void setNewFavorite(String newFavorite) {
        this.newFavorite = newFavorite;
    }

    public List<Favorite> getFavorites() {
        try {
            SessionId sessionId = loginBean.getSessionId();
            return new ArrayList<Favorite>(userController.getFavorites(sessionId));
        } catch (IllegalAccessException e) {
            return Collections.emptyList();
        }
    }

    public String addFavorite() {
        try {
            SessionId sessionId = loginBean.getSessionId();
            userController.addFavorite(sessionId, newFavorite);
            return "success";
        } catch (IllegalAccessException e) {
            return "accessDenied";
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContextHelper.getCurrentFacesContext().getExternalContext().getSession(true);
        session.invalidate();
        return "logout";
    }
}
