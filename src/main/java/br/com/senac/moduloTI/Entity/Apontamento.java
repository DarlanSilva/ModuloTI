package br.com.senac.moduloTI.Entity;

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
 * @author Darlan Silva
 */
@Entity
@Table(name = "TB_APONTAMENTO")
public class Apontamento {

    @Id
    @Column(name = "PK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 60)
    @NotBlank(message = "CAMPO TITULO DO APONTAMENTO É OBRIGATÓRIO")
    @Column(name = "DS_TITULO")
    private String titulo;

    @Size(min = 1, max = 40)
    @NotBlank(message = "RESPONSÁVEL DO APONTAMENTO É OBRIGATÓRIO")
    @Column(name = "DS_RESPONSAVEL")
    private String responsavel;
    
    @ManyToOne
    @JoinColumn(name = "FK_OS")
    private OrdemServico os;
    
    @Column(name = "QT_HORAS")
    private int qtHoras;
    
    @Size(min = 1, max = 1000)
    @NotBlank(message = "DESCRIÇÃO DO APONTAMENTO É OBRIGATÓRIO")
    @Column(name = "DS_APONTAMENTO")
    private String descricao;
    
    @Column(name = "TG_INATIVO")
    private int inativo;

    @Column(name = "DH_INCLUSAO", nullable = false, insertable = true, updatable = false)
    private LocalDateTime dhInclusao;

    @Column(name = "DH_ALTERACAO", nullable = true, insertable = true, updatable = true)
    private LocalDateTime dhAlteracao;

    public Apontamento() {
    }

    public Apontamento(Integer id, String titulo, String responsavel, OrdemServico os, int qtHoras, String descricao, int inativo, LocalDateTime dhInclusao, LocalDateTime dhAlteracao) {
        this.id = id;
        this.titulo = titulo;
        this.responsavel = responsavel;
        this.os = os;
        this.qtHoras = qtHoras;
        this.descricao = descricao;
        this.inativo = inativo;
        this.dhInclusao = dhInclusao;
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }

    public int getQtHoras() {
        return qtHoras;
    }

    public void setQtHoras(int qtHoras) {
        this.qtHoras = qtHoras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public LocalDateTime getDhAlteracao() {
        return dhAlteracao;
    }

    public void setDhAlteracao(LocalDateTime dhAlteracao) {
        this.dhAlteracao = dhAlteracao;
    }
    
    
    
}
