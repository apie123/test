package be.kdg.reference.backingBeans;

import be.kdg.reference.model.SessionId;
import be.kdg.reference.services.UserController;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginBean {
    private String username;
    private String password;
    private SessionId sessionId;

    private UserController userController;

    public LoginBean() {
        this.username = "";
        this.password = "";
        this.sessionId = SessionId.INVALID_SESSION_ID;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public String login() {
        FacesContext facesContext = FacesContextHelper.getCurrentFacesContext();
        this.sessionId = userController.login(username, password);
        if (!sessionId.isValid()) {
            facesContext.addMessage("form", new FacesMessage("wrong username or password, please try again"));
            return "failure";
        }
        return "success";
    }
}
