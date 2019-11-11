package br.com.senac.moduloTI.Controller;

import br.com.senac.moduloTI.Entity.Chamado;
import br.com.senac.moduloTI.Entity.FilterRel;
import br.com.senac.moduloTI.Entity.OrdemServico;
import br.com.senac.moduloTI.Entity.SenderMail;
import br.com.senac.moduloTI.Entity.StatusChamado;
import br.com.senac.moduloTI.Entity.Tecnico;
import br.com.senac.moduloTI.Repository.ChamadoRepository;
import br.com.senac.moduloTI.Repository.OrdemServicoRepository;
import br.com.senac.moduloTI.Repository.StatusChamadoRepository;
import br.com.senac.moduloTI.Repository.TecnicoRepository;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepo;

    @Autowired
    private StatusChamadoRepository statusRepo;

    @Autowired
    private TecnicoRepository tecnicoRepo;

    @Autowired
    private OrdemServicoRepository osRepo;

    @Autowired
    private MailSender sender;

    @GetMapping("/Chamados")
    public ModelAndView chamadoForm() {
        FilterRel filter = new FilterRel();
        SenderMail senderMail = new SenderMail();
        List<Chamado> listaChamados = chamadoRepo.findAllChamados();
        ModelAndView mv = new ModelAndView("techMode/index");
        mv.addObject("tableData", listaChamados);
        mv.addObject("filterRel", filter);
        mv.addObject("senderMail", senderMail);

        return mv;
    }

    @PostMapping("/Chamados/Filtrar")
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

        List<Chamado> listaChamados;

        if (dtInicio != null && dtFinal != null) {
            listaChamados = chamadoRepo.findAllByDhInclusao(dtInicio, dtFinal);
        } else if (dtInicio != null && dtFinal == null) {
            listaChamados = chamadoRepo.findAllByDhInclusaoIni(dtInicio);
        } else if (dtInicio == null && dtFinal != null) {
            listaChamados = chamadoRepo.findAllByDhInclusaoFin(dtFinal);
        } else {
            listaChamados = chamadoRepo.findAllChamados();
        }

        FilterRel filter = new FilterRel();
        ModelAndView mv = new ModelAndView("techMode/index");
        mv.addObject("tableData", listaChamados);
        mv.addObject("filterRel", filter);

        return mv;
    }

    @GetMapping("/Chamado/Novo")
    public ModelAndView adicionarNovo() {
        getStatusChamado();
        getTecnico();
        return new ModelAndView("techMode/chamado-adicionar")
                .addObject("chamado", new Chamado());
    }

    @GetMapping("/{id}/Chamados/Editar")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        Optional<Chamado> listChamando = chamadoRepo.findById(id);
        Chamado chamando = listChamando.get();
        getStatusChamado();
        getTecnico();

        return new ModelAndView("techMode/chamado-adicionar")
                .addObject("chamado", chamando);
    }

    @PostMapping("/Chamados/Salvar")
    public ModelAndView salvar(@ModelAttribute("chamado")
            @Valid Chamado chamado, BindingResult result, RedirectAttributes redirectAttributes) {

        Optional<Chamado> verificachamado = null;
        Chamado chamadoSalvo = new Chamado();
        int osCriada = 0;
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("techMode/chamado-adicionar");
            mv.addObject("chamado", chamado);

            return mv;
        }

        //VERIFICA SE É UMA ALTERAÇÃO PARA SALVA A DATA HORA DA ALTERAÇÃO
        if (chamado.getId() != null) {
            verificachamado = chamadoRepo.findById(chamado.getId());
            chamado.setDhAlteracao(LocalDateTime.now());
            chamado.setDhInclusao(verificachamado.get().getDhAlteracao());
            osCriada = 1;
        } else {
            chamado.setDhInclusao(LocalDateTime.now());
            chamado.setInativo(0);
        }
        
        // Chamando finalizado
        if (chamado.getStatusChamado().getStatus().equalsIgnoreCase("FINALIZADO")) {
            chamado.setDhEncerrado(LocalDateTime.now());
        }

        chamadoSalvo = chamadoRepo.save(chamado);
        chamado.setId(chamadoSalvo.getId());
        chamadoEmail(chamado);

        if (osCriada == 0) {
            getNewOS(chamadoSalvo);
        }

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Chamado " + chamado.getTitulo() + " salvo com sucesso");

        return new ModelAndView("redirect:/TechMode/Painel/Chamados");
    }

    @GetMapping("/{id}/Chamados/Deletar")
    public ModelAndView remover(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

        chamadoRepo.deleteById(id);

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Chamado excluido com sucesso");

        return new ModelAndView("redirect:/TechMode/Painel/Chamados");
    }

    @PostMapping("/Chamados/Enviar/E-mail")
    public ModelAndView contato(@ModelAttribute("email") @Valid SenderMail mail, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("redirect:/TechMode/Painel/Chamados");
            mv.addObject("email", mail);
            return mv;

        }
        // ENVIA EMAIL DE CONTATO
        contatoMail(mail);

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Email enviado com sucesso.");

        return new ModelAndView("redirect:/TechMode/Painel/Chamados");
    }

    private void chamadoEmail(Chamado chamado) {
        SimpleMailMessage contato = new SimpleMailMessage();

        contato.setSubject("Acompanhamento do Chamado " + chamado.getId());
        contato.setTo(chamado.getEmailResponsavel());
        contato.setSubject("Olá " + chamado.getResponsavel());
        contato.setText("CHAMADO NRº" + chamado.getId() + "\n"
                + "Segue dados do chamado referente ao assunto " + chamado.getTitulo() + "\n" + "\n"
                + "Status do chamado: " + chamado.getStatusChamado().getStatus() + "\n"
                + "Técnico resposável: " + chamado.getTecnico().getNome() + "\n"
                + "Descrição do Chamado: " + chamado.getDescricao() + "\n"
                + "Data de abertura: " + chamado.getDhInclusao() + "\n"
                + "Data de encerramento: " + chamado.getDhEncerrado() + "\n" + "\n"
                + "Logo você receberá mais informação referente ao seu chamado." + "\n" + "\n" + "\n" + "\n" + "\n"
                + "A TECHMODE AGRADEÇE E TENHA UM ÓTIMO DIA." + "\n" + "Atenciosamente.");
        contato.setFrom("TechMode");

        sender.send(contato);
    }

    private void contatoMail(SenderMail email) {
        SimpleMailMessage contato = new SimpleMailMessage();

        contato.setSubject(email.getAssunto());
        contato.setTo(email.getEmail());
        contato.setSubject("Olá " + email.getNome());
        contato.setText("Mensagem: " + email.getMensagem());
        contato.setFrom("OpenBeer.com.br");

        sender.send(contato);
    }

    private void getNewOS(Chamado chamado) {
        OrdemServico os = new OrdemServico();

        os.setChamado(chamado);
        os.setDhInclusao(LocalDateTime.now());
        os.setTitulo("O.S ABERTA P/ CHAMADO DE NRº " + chamado.getId());

        osRepo.save(os);
    }

    @ModelAttribute("statusChamado")
    public List<StatusChamado> getStatusChamado() {

        return statusRepo.findAll();
    }

    @ModelAttribute("tecnico")
    public List<Tecnico> getTecnico() {

        return tecnicoRepo.findAll();
    }

}
