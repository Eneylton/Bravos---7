package com.java.util.jsfsecurity;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class SecurityController implements PhaseListener {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SecurityController.class.getName());

    public static final String JSF_SECURITY_KEY = "__jsfsecurity__";
    public static final String VIEWID_KEY = "__viewId";

    private static String[] protectedPaths = null;

    @Override
    public void beforePhase(PhaseEvent event) {

        FacesContext ctx = event.getFacesContext();
        String viewId = ctx.getViewRoot().getViewId();
        if (logger.isLoggable(Level.FINE)) {
            logger.info("Phase=" + ctx.getCurrentPhaseId().toString() + ", viewId=" + viewId);
        }
        for (String s : getProtectedPaths(ctx)) {
            String[] p = s.split("=");
            if (viewId.indexOf(p[0]) > -1) {
                HttpSession httpSession = (HttpSession)ctx.getExternalContext().getSession(false);
                if (httpSession == null || httpSession.getAttribute(JSF_SECURITY_KEY) == null) {
                    httpSession.setAttribute(VIEWID_KEY, viewId);
                    String login = ctx.getExternalContext().getInitParameter("jsfsecurity.login.page");
                    ctx.getApplication().getNavigationHandler().handleNavigation(
                            ctx, null, (login == null ? "/login/Login.xhtml" : login)
                            );
                    ctx.addMessage(null, new FacesMessage(
                                           "A página solicitada requer autenticação."));

                } else {
                    String roles = (String)httpSession.getAttribute("__roles");
                    if (roles != null && roles.indexOf(p[1]) == -1) {
                        // faz o usuário permanecer na mesma página...
                        ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, "");
                        ctx.addMessage(null, new FacesMessage(
                                         "Página solicitada não está autorizada."));
                    }
                }
                break;
            }
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    private String[] getProtectedPaths(FacesContext ctx) {

        if (protectedPaths == null) {
            logger.config("loading jsfsecurity.protected ...");
            String s = ctx.getExternalContext().getInitParameter("jsfsecurity.protected");
            if (s != null) {
                protectedPaths = s.split(",");
            }
        }
        return protectedPaths;
    }
}
