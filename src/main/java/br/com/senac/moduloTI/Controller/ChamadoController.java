package br.com.senac.moduloTI.Controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Darlan Silva
 */

@Controller
@RequestMapping("/TechMode/Painel")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ChamadoController {

    @GetMapping("/Chamados")
    public ModelAndView chamadoForm() {
        return new ModelAndView("techMode/index");
    }
    
    @GetMapping("/Editar/Chamado")
    public ModelAndView editarChamando() {
        return new ModelAndView("techMode/index");
    }

}
