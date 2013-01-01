package be.kdg.reference.util;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import java.util.*;

public class FacesContextMock extends FacesContext {
    private Map<String, List<FacesMessage>> messagesByComponent;

    public FacesContextMock() {
        this.messagesByComponent = new HashMap<String, List<FacesMessage>>();
    }

    @Override
    public Application getApplication() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator<String> getClientIdsWithMessages() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ExternalContext getExternalContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FacesMessage.Severity getMaximumSeverity() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator<FacesMessage> getMessages() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator<FacesMessage> getMessages(String s) {
        List<FacesMessage> messages = messagesByComponent.get(s);
        if (messages == null) {
            messages = Collections.emptyList();
        }
        return messages.iterator();
    }

    @Override
    public RenderKit getRenderKit() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getRenderResponse() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getResponseComplete() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseStream getResponseStream() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setResponseStream(ResponseStream responseStream) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseWriter getResponseWriter() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setResponseWriter(ResponseWriter responseWriter) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UIViewRoot getViewRoot() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setViewRoot(UIViewRoot uiViewRoot) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addMessage(String s, FacesMessage facesMessage) {
        List<FacesMessage> messages = messagesByComponent.get(s);
        if (messages == null) {
            messages = new ArrayList<FacesMessage>();
        }
        messages.add(facesMessage);
        this.messagesByComponent.put(s, messages);
    }

    @Override
    public void release() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void renderResponse() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void responseComplete() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
