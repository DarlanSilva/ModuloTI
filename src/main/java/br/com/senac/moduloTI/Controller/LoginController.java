package br.com.senac.moduloTI.Controller;

import br.com.senac.moduloTI.Entity.Login;
import br.com.senac.moduloTI.Repository.LoginRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Darlan.Silva
 */
@Controller
@RequestMapping("/TechMode")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class LoginController {

    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    private SessionAtribute sessionAtribute;
    
    @GetMapping("/Login")
    @PostMapping("/Login")
    public ModelAndView loginForm() {
        return new ModelAndView("login/Login-Page").addObject("login", new Login());
    }

    @GetMapping("/Sucess")
    public ModelAndView loginSucesso(HttpSession session) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        String password;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            password = ((UserDetails) principal).getPassword();
        } else {
            username = principal.toString();
            password = principal.toString();
        }

        Optional<Login> usuario = loginRepo.findByUserAndPass(username, password);

        // GUARDA O USUÁRIO LOGADO NA SESSÃO
        if (usuario.isPresent() == true) {
            sessionAtribute.setLogin(usuario.get());
            session.setAttribute("sessionAtribute", sessionAtribute);
        }

        return new ModelAndView("redirect:/TechMode/Painel/Chamados");
    }

    @GetMapping("/Logout")
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("home");

        return mv;
    }

    @GetMapping("/{id}/Editar")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        Optional<Login> User = loginRepo.findById(id);
        Login login = User.get();

        return new ModelAndView("login/login-cadastro").addObject("User", login);
    }

    @PostMapping("/Salvar")
    public ModelAndView salvar(@ModelAttribute("login") @Valid Login login, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("login/login-cadastro");
            mv.addObject(login);
            return mv;
        }
        Optional<Login> verificarLogin = loginRepo.findByLogin(login.getLogin());

        if (verificarLogin.isPresent() == true) {
            verificarLogin.get().setDhAlteracao(LocalDateTime.now());
        }

        loginRepo.save(verificarLogin.get());

        return new ModelAndView("redirect:/TechMode/Painel/Chamados");

    }

}
