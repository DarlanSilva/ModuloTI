package br.com.senac.moduloTI.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Darlan.Silva
 */
@Entity
@Table(name = "TB_CHAMADOS")
public class Chamado {

    @Id
    @Column(name = "PK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 60)
    @NotBlank(message = "CAMPO TITULO DO CHAMANDO É OBRIGATÓRIO")
    @Column(name = "DS_TITULO")
    private String titulo;

    @Size(min = 1, max = 1000)
    @NotBlank(message = "DESCRIÇÃO DO CHAMANDO É OBRIGATÓRIO")
    @Column(name = "DS_CHAMADO")
    private String descricao;

    @Size(min = 1, max = 40)
    @NotBlank(message = "RESPONSÁVEL DO CHAMANDO É OBRIGATÓRIO")
    @Column(name = "DS_RESPONSAVEL")
    private String responsavel;

    @Size(min = 1, max = 40)
    @NotBlank(message = "EMAIL DO RESPONSÁVEL DO CHAMANDO É OBRIGATÓRIO")
    @Column(name = "DS_EMAILRESPONSAVEL")
    private String emailResponsavel;

    @ManyToOne
    @JoinColumn(name = "FK_STATUSCHAMADO")
    private StatusChamado statusChamado;

    @ManyToOne
    @JoinColumn(name = "FK_TECNICO")
    private Tecnico tecnico;

    @Column(name = "TG_INATIVO")
    private int inativo;

    @Column(name = "DH_INCLUSAO", nullable = false, insertable = true, updatable = false)
    private LocalDateTime dhInclusao;

    @Column(name = "DH_ENCERRADO", nullable = true, insertable = true, updatable = false)
    private LocalDateTime dhEncerrado;

    @Column(name = "DH_ALTERACAO", nullable = true, insertable = true, updatable = true)
    private LocalDateTime dhAlteracao;

    public Chamado() {
    }

    public Chamado(Integer id, String titulo, String descricao, String responsavel, String emailResponsavel, StatusChamado statusChamado, Tecnico tecnico, int inativo, LocalDateTime dhInclusao, LocalDateTime dhEncerrado, LocalDateTime dhAlteracao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.emailResponsavel = emailResponsavel;
        this.statusChamado = statusChamado;
        this.tecnico = tecnico;
        this.inativo = inativo;
        this.dhInclusao = dhInclusao;
        this.dhEncerrado = dhEncerrado;
        this.dhAlteracao = dhAlteracao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public StatusChamado getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(StatusChamado statusChamado) {
        this.statusChamado = statusChamado;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }

    public LocalDateTime getDhInclusao() {
        return dhInclusao;
    }

    public void setDhInclusao(LocalDateTime dhInclusao) {
        this.dhInclusao = dhInclusao;
    }

    public LocalDateTime getDhEncerrado() {
        return dhEncerrado;
    }

    public void setDhEncerrado(LocalDateTime dhEncerrado) {
        this.dhEncerrado = dhEncerrado;
    }

    public LocalDateTime getDhAlteracao() {
        return dhAlteracao;
    }

    public void setDhAlteracao(LocalDateTime dhAlteracao) {
        this.dhAlteracao = dhAlteracao;
    }

    

}
