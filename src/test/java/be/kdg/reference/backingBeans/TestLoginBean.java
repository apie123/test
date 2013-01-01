package be.kdg.reference.backingBeans;

import be.kdg.reference.model.SessionId;
import be.kdg.reference.util.FacesContextMock;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Iterator;

public class TestLoginBean extends AbstractDependencyInjectionSpringContextTests {
    private LoginBean loginBean;

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
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

    public void testLoginSuccess() throws Exception {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("validPassword");
        String result = loginBean.login();
        assertEquals("valid login should return 'success'", "success", result);
        SessionId sessionId = loginBean.getSessionId();
        assertTrue(sessionId.isValid());
        FacesContext facesContext = FacesContextHelper.getCurrentFacesContext();
        Iterator<FacesMessage> it = facesContext.getMessages("form");
        assertFalse("there should be no message in the facesContext after a successfull login", it.hasNext());
    }

    public void testLoginWrongPassword() throws Exception {
        loginBean.setUsername("validUsername");
        loginBean.setPassword("wrongPassword");
        String result = loginBean.login();
        assertEquals("login with wrong password should return 'failure'", "failure", result);
        SessionId sessionId = loginBean.getSessionId();
        assertFalse(sessionId.isValid());
        FacesContext facesContext = FacesContextHelper.getCurrentFacesContext();
        Iterator<FacesMessage> it = facesContext.getMessages("form");
        assertTrue("there should be an error message in the facesContext after an unsuccessfull login", it.hasNext());
        assertEquals("wrong username or password, please try again", it.next().getDetail());
    }

    public void testLoginWrongUsername() throws Exception {
        loginBean.setUsername("wrongUsername");
        loginBean.setPassword("validPassword");
        String result = loginBean.login();
        assertEquals("login with wrong username should return 'failure'", "failure", result);
        SessionId sessionId = loginBean.getSessionId();
        assertFalse(sessionId.isValid());
        FacesContext facesContext = FacesContextHelper.getCurrentFacesContext();
        Iterator<FacesMessage> it = facesContext.getMessages("form");
        assertTrue("there should be an error message in the facesContext after an unsuccessfull login", it.hasNext());
        assertEquals("wrong username or password, please try again", it.next().getDetail());
    }
}
