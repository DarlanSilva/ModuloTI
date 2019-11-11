package br.com.senac.moduloTI.Entity;

import br.com.senac.moduloTI.Entity.Login;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Darlan Silva
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionAtribute implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Login login;

    public SessionAtribute() {
    }
    
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
}
