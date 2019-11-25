package br.com.senac.moduloTI.Controller;

import br.com.senac.moduloTI.Entity.Apontamento;
import br.com.senac.moduloTI.Entity.Chamado;
import br.com.senac.moduloTI.Entity.FilterRel;
import br.com.senac.moduloTI.Entity.OrdemServico;
import br.com.senac.moduloTI.Entity.SenderMail;
import br.com.senac.moduloTI.Entity.SessionAtribute;
import br.com.senac.moduloTI.Repository.ApontamentoRepository;
import br.com.senac.moduloTI.Repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
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
 * @author Darlan Silva
 */
@Controller
@RequestMapping("/TechMode/Painel")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository osRepo;

    @Autowired
    private SessionAtribute sessionAtr;

    @Autowired
    private ApontamentoRepository apontamentoRepo;

    @GetMapping("/Ordem/Servico")
    public ModelAndView osConsutar() {
        FilterRel filter = new FilterRel();
        List<OrdemServico> listaOS = osRepo.findAllOS();
        ModelAndView mv = new ModelAndView("techMode/ordem-servico");
        mv.addObject("tableData", listaOS);
        mv.addObject("filterRel", filter);

        return mv;
    }

    @PostMapping("/Ordem/Servico/Filrar")
    public ModelAndView filtarChamado(@ModelAttribute("filterRel") FilterRel filterRel) {
        LocalDateTime dtInicio = null;
        LocalDateTime dtFinal = null;

        if (filterRel.getDtInicio() != null) {
            dtInicio = filterRel.getDtInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        if (filterRel.getDtFinal() != null) {
            Date dt = filterRel.getDtFinal();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            calendar.add(Calendar.DATE, 1);
            dt = calendar.getTime();

            dtFinal = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        List<OrdemServico> listaOS;

        if (dtInicio != null && dtFinal != null) {
            listaOS = osRepo.findAllByDhInclusao(dtInicio, dtFinal);
        } else if (dtInicio != null && dtFinal == null) {
            listaOS = osRepo.findAllByDhInclusaoIni(dtInicio);
        } else if (dtInicio == null && dtFinal != null) {
            listaOS = osRepo.findAllByDhInclusaoFin(dtFinal);
        } else {
            listaOS = osRepo.findAllOS();
        }

        FilterRel filter = new FilterRel();
        ModelAndView mv = new ModelAndView("techMode/ordem-servico");
        mv.addObject("tableData", listaOS);
        mv.addObject("filterRel", filter);

        return mv;
    }

    @GetMapping("/Ordem/Servico/Relatorio")
    public ModelAndView relatorio(@ModelAttribute("filterRel") FilterRel filterRel) {
        FilterRel filter = new FilterRel();
        List<OrdemServico> listaOS = osRepo.findAllOS();
        ModelAndView mv = new ModelAndView("techMode/relatorio-os");
        mv.addObject("tableData", listaOS);
        mv.addObject("filterRel", filter);

        return mv;
    }

    @PostMapping("/Ordem/Servico/Relatorio/Filtrar")
    public ModelAndView relatorioFiltrar(@ModelAttribute("filterRel") FilterRel filterRel) {
        LocalDateTime dtInicio = null;
        LocalDateTime dtFinal = null;

        if (filterRel.getDtInicio() != null) {
            dtInicio = filterRel.getDtInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        if (filterRel.getDtFinal() != null) {
            Date dt = filterRel.getDtFinal();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            calendar.add(Calendar.DATE, 1);
            dt = calendar.getTime();

            dtFinal = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        List<OrdemServico> listaOS;

        if (dtInicio != null && dtFinal != null) {
            listaOS = osRepo.findAllByDhInclusao(dtInicio, dtFinal);
        } else if (dtInicio != null && dtFinal == null) {
            listaOS = osRepo.findAllByDhInclusaoIni(dtInicio);
        } else if (dtInicio == null && dtFinal != null) {
            listaOS = osRepo.findAllByDhInclusaoFin(dtFinal);
        } else {
            listaOS = osRepo.findAllOS();
        }

        FilterRel filter = new FilterRel();
        ModelAndView mv = new ModelAndView("techMode/relatorio-os");
        mv.addObject("tableData", listaOS);
        mv.addObject("filterRel", filter);

        return mv;
    }

    @GetMapping("/Ordem/Servico/Apontamentos")
    public ModelAndView apontamentoConsultar() {
        List<Apontamento> listaApontamentos = apontamentoRepo.findAll();
        ModelAndView mv = new ModelAndView("techMode/apontamento-consultar");
        mv.addObject("tableData", listaApontamentos);

        return mv;
    }

    @PostMapping("/Ordem/Servico/Apontamento/Filtrar")
    public ModelAndView apontamentoFiltrar(@ModelAttribute("filterRel") FilterRel filterRel) {
        LocalDateTime dtInicio = null;
        LocalDateTime dtFinal = null;

        if (filterRel.getDtInicio() != null) {
            dtInicio = filterRel.getDtInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        if (filterRel.getDtFinal() != null) {
            Date dt = filterRel.getDtFinal();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            calendar.add(Calendar.DATE, 1);
            dt = calendar.getTime();

            dtFinal = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        List<Apontamento> listaApontamentos;

        if (dtInicio != null && dtFinal != null) {
            listaApontamentos = apontamentoRepo.findAllByDhInclusao(dtInicio, dtFinal);
        } else if (dtInicio != null && dtFinal == null) {
            listaApontamentos = apontamentoRepo.findAllByDhInclusaoIni(dtInicio);
        } else if (dtInicio == null && dtFinal != null) {
            listaApontamentos = apontamentoRepo.findAllByDhInclusaoFin(dtFinal);
        } else {
            listaApontamentos = apontamentoRepo.findAll();
        }

        FilterRel filter = new FilterRel();
        ModelAndView mv = new ModelAndView("techMode/apontamento-consultar");
        mv.addObject("tableData", listaApontamentos);
        mv.addObject("filterRel", filter);

        return mv;
    }

    @GetMapping("/{id}/Ordem/Servico/Incluir/Apontamento")
    public ModelAndView incluirApontamento(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("techMode/apontamento-adicionar");

        Optional<OrdemServico> os = osRepo.findById(id);

        Apontamento apontamento = new Apontamento();
        apontamento.setOs(os.get());
        apontamento.setResponsavel(sessionAtr.getLogin().getUser());

        mv.addObject("apontamento", apontamento);

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Apontamento salvo com sucesso");

        return mv;
    }

    @GetMapping("/{id}/Ordem/Servico/Editar/Apontamento")
    public ModelAndView editar(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Optional<Apontamento> listApontamento = apontamentoRepo.findById(id);
        Apontamento apontamento = listApontamento.get();

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Apontamento editado com sucesso");

        return new ModelAndView("techMode/apontamento-adicionar")
                .addObject("apontamento", apontamento);
    }

    @GetMapping("/{id}/Ordem/Servico/Deletar/Apontamento")
    public ModelAndView remover(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        
/*         List<Apontamento> apontamentos = apontamentoRepo.findAllByOS(id);
        
        if(!apontamentos.isEmpty()){
             redirectAttributes.addFlashAttribute("mensagemSucesso",
                "OS vinculado a apontamentos. Impossível deletar!");
            ModelAndView mv = new ModelAndView("redirect:/TechMode/Painel/Ordem/Servico");
            return mv;
        }
*/
        
        apontamentoRepo.deleteById(id);

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Apontamento excluido com sucesso");

        return new ModelAndView("redirect:/TechMode/Painel/Ordem/Servico/Apontamentos");
    }

    @PostMapping("/Ordem/Servico/Apontamento/Salvar")
    public ModelAndView salvarApontamento(@ModelAttribute("apondamento")
            @Valid Apontamento apondamento, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("techMode/apontamento-adicionar");
            mv.addObject("apondamento", apondamento);

            return mv;
        }

        //VERIFICA SE É UMA ALTERAÇÃO PARA SALVA A DATA HORA DA ALTERAÇÃO
        if (apondamento.getId() != null) {
            apondamento.setDhAlteracao(LocalDateTime.now());
        } else {
            apondamento.setDhInclusao(LocalDateTime.now());
            apondamento.setInativo(0);
        }

        Apontamento apondamentoSalvo = new Apontamento();
        apondamentoSalvo = apontamentoRepo.save(apondamento);

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Apontamento " + apondamentoSalvo.getTitulo() + " salvo com sucesso");

        return new ModelAndView("redirect:/TechMode/Painel/Ordem/Servico/Apontamentos");
    }

}
