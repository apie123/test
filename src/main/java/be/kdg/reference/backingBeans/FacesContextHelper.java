package be.kdg.reference.backingBeans;

import javax.faces.context.FacesContext;

/**
 * This class permits testing backing beans.
 * Use this class in your backing beans to obtain a reference to the FacesContext.
 * A testcase can than change the FacesContext, using the setter.
 */
public class FacesContextHelper {
    private static FacesContext context;

    private FacesContextHelper() {
    }

    public static FacesContext getCurrentFacesContext() {
        return context != null ? context : FacesContext.getCurrentInstance();
    }

    public static void setFacesContext(FacesContext facesContext) {
        context = facesContext;
    }

    public static void reset() {
        context = null;
    }
}

