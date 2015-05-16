package cz.cvut.forum.helper;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class FacesUtil {

    public static String getRequestParameter(String name) {
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(map.containsKey(name))
            return (String) map.get(name);
        return null;
    }

    public static String getRequestURL() {
        return (String) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
    }

    /**
     * Prida message do contextu uzivatele zdroj == @null
     * @param string
     */
    public static void addMessage(String string) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(string));
    }

    public static void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Get message from the specified bundle
     * @param bundleName budle baseName
     * @param key key of the message
     * @return message
     */
    public static String getMessage(String bundleName, String key) {
        return ResourceBundle.getBundle(bundleName).getString(key);
    }

    public static Object getSessionAttribute(String key) {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute(key);
    }

    public static void setSessionAttribute(String key, Object value) {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(key, value);
    }

    /**
     * Returns remote address for this request
     * @return remote address
     */
    public static String getRemoteAddress() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
    }
}
