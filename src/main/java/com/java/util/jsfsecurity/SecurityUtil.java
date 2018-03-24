package com.java.util.jsfsecurity;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SecurityUtil {

    public static String logIn(String defaultViewId) {

        String viewId = defaultViewId;
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            session.setAttribute(SecurityController.JSF_SECURITY_KEY, "1");
            String s = (String)session.getAttribute(SecurityController.VIEWID_KEY);
            if (s != null) {
                viewId = s;
            }
        }
        return viewId;
    }

    public static boolean isLoggedIn() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            String s = (String)session.getAttribute(SecurityController.JSF_SECURITY_KEY);
            if (s != null && s.equals("1")) {
                return true;
            }
        }
        return false;
    }

    public static void logOut() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(false);
        if (session != null) {
            session.removeAttribute(SecurityController.JSF_SECURITY_KEY);
            session.removeAttribute("usuarioLogado");
            session.removeAttribute("nomeCompletoUsuarioLogado");
        }
    }
}