package br.com.senac.moduloTI.Controller;

import br.com.senac.moduloTI.Entity.SessionAtribute;
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

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<Login> usuario = loginRepo.findByUser(username);
        // GUARDA O USUÁRIO LOGADO NA SESSÃO
        if (usuario.isPresent() == true) {
            System.out.println(usuario.get().getId() + usuario.get().getUser());
            sessionAtribute.setLogin(usuario.get());
        } else {
            //redirectAttributes.addFlashAttribute("mensagem", "Usúario ou Senha Inválidos!!");
            return new ModelAndView("redirect:/TechMode/Login");
        }
        session.setAttribute("sessionAtribute", sessionAtribute);

        return new ModelAndView("redirect:/TechMode/Painel/Chamados");
    }

    @GetMapping("/Logout")
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("home");

        return mv;
    }

    @GetMapping("/Painel/{id}/Editar")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        Optional<Login> User = loginRepo.findById(id);
        Login login = User.get();

        return new ModelAndView("login/Login-Editar").addObject("login", login);
    }

    @PostMapping("/Painel/Salvar")
    public ModelAndView salvar(@ModelAttribute("login") @Valid Login login, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("login/login-cadastro");
            mv.addObject(login);
            return mv;
        }
        Optional<Login> verificarLogin = loginRepo.findByUser(login.getUser());

        if (verificarLogin.isPresent() == true) {
            login.setDhAlteracao(LocalDateTime.now());
        }

        loginRepo.save(login);

        return new ModelAndView("redirect:/TechMode/Painel/Chamados");

    }

}
