package br.com.senac.moduloTI.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.senac.moduloTI.Entity.Tecnico;
import br.com.senac.moduloTI.Repository.TecnicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.Cacheable;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Wesley Santos_2
 */

@Controller
@RequestMapping("/TechMode/Painel")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class TecnicoController {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;
    
    @GetMapping("/Cadastrar-Tecnico")
    public ModelAndView cadastroTech(){        
        return new ModelAndView("techMode/cadastrar-tech.html")
                .addObject("tecnico", new Tecnico());
    }
    
    @GetMapping("/Consultar-Tecnico")
    public ModelAndView consultar(){
        List<Tecnico> tech = tecnicoRepository.findAll();
        return new ModelAndView ("techMode/consultar-tech")
                .addObject("tecnico", tech);
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("tecnico") @Valid Tecnico tech, BindingResult result, 
            RedirectAttributes redirectAttributes){
        
        if(result.hasErrors()){
            ModelAndView mv = new ModelAndView("techMode/cadastrar-tech");
            mv.addObject("tecnico", tech);
            return mv;
        }
        
        //Optional<Tecnico> verificarTecnico = tecnicoRepository.findById(tech.getId());
        
        tech.setDhInclusao(LocalDateTime.now());
        tech.setInativo(0);
        
        if (tech.getId() != null) {
            tech.setDhAlteracao(LocalDateTime.now());
        }
        
        tecnicoRepository.save(tech);
        
        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Tecnico " + tech.getNome() + " cadastrado com sucesso!");
        
        return new ModelAndView("redirect:/TechMode/Painel/Consultar-Tecnico");
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        Optional<Tecnico> listTecnico = tecnicoRepository.findById(id);
        Tecnico tecnico = listTecnico.get();
        
        return new  ModelAndView("techMode/cadastrar-tech")
                .addObject("tecnico", tecnico);
    }
    
    @GetMapping("/{busca}/Consultar-Tecnico")
    public ModelAndView buscarTecnico(@PathVariable ("busca") String busca){
        List<Tecnico> tecnico;
        
        if (busca == null || busca.trim().isEmpty()) {
            tecnico = tecnicoRepository.findAll();
        } else {
             tecnico = tecnicoRepository.findByNome(busca);
        }
        
        ModelAndView mv = new ModelAndView("techMode/consultar-tech");
        mv.addObject("tecnico", tecnico);
        return mv;
    }
    
}
