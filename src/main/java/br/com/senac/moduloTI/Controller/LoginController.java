package br.com.senac.moduloTI.Controller;

import br.com.senac.moduloTI.Entity.Login;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Darlan.Silva
 */
@Controller
@RequestMapping("/ModuloTI")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class LoginController {

    @GetMapping("/Login")
    @PostMapping("/Login")
    public ModelAndView loginForm() {
        return new ModelAndView("login/Login-Page").addObject("login", new Login());
    }
}
