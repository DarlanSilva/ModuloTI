package br.com.senac.moduloTI.Controller;

import br.com.senac.moduloTI.Entity.FilterRel;
import br.com.senac.moduloTI.Entity.Login;
import br.com.senac.moduloTI.Entity.SenderMail;
import br.com.senac.moduloTI.Entity.SessionAtribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Darlan Silva
 */
@ControllerAdvice(annotations = Controller.class)
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ControllerActiver {

    @Autowired
    private SessionAtribute sessionAtribute;

    @ModelAttribute("sessionAtribute")
    public SessionAtribute getSessionAtribute() {
        return sessionAtribute;
    }

    @ModelAttribute("senderMail")
    public SenderMail getMail() {

        return new SenderMail();
    }
    
     @ModelAttribute("filterRel")
    public FilterRel getFilter() {

        return new FilterRel();
    }
}
