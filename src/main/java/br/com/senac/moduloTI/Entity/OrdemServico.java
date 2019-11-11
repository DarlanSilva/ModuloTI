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
@Table(name="OS_ORDEMSERVICO")
public class OrdemServico {
    
    @Id
    @Column(name = "PK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 60)
    @NotBlank(message = "CAMPO TITULO DA OS É OBRIGATÓRIO")
    @Column(name = "DS_TITULO")
    private String titulo;
    
    @ManyToOne
    @JoinColumn(name = "FK_CHAMADO")
    private Chamado chamado;
    
    @Column(name = "TG_INATIVO")
    private int inativo;

    @Column(name = "DH_INCLUSAO", nullable = false, insertable = true, updatable = false)
    private LocalDateTime dhInclusao;

    @Column(name = "DH_ALTERACAO", nullable = true, insertable = true, updatable = true)
    private LocalDateTime dhAlteracao;

    public OrdemServico() {
    }

    public OrdemServico(Integer id, String titulo, Chamado chamado, int inativo, LocalDateTime dhInclusao, LocalDateTime dhAlteracao) {
        this.id = id;
        this.titulo = titulo;
        this.chamado = chamado;
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

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
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
