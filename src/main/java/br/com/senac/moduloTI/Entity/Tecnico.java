package br.com.senac.moduloTI.Entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Wesley Santos_2
 */
@Entity
@Table(name = "TB_TECNICOS")
public class Tecnico {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DS_NOME")
    @Size(min = 1, max = 40)
    @NotBlank(message = "NOME É UM CAMPO OBRIGATÓRIO")
    private String nome;

    @Column(name = "DS_RG")
    @Size(min = 1, max = 9)
    @NotBlank(message = "RG É UM CAMPO OBRIGATÓRIO")
    private String rg;

    @Column(name = "CPF")
    //@Size(min = 1, max = 11)
    @NotNull(message = "CPF É UM CAMPO OBRIGATÓRIO")
    private int cpf;

    @Column(name = "CONTATO")
    @NotNull(message = "CONTATO É UM CAMPO OBRIGATÓRIO")
    private int contato;

    @Column(name = "DS_EMAIL")
    @NotBlank(message = "E-MAIL É UM CAMPO OBRIGATÓRIO")
    private String email;

    @Column(name = "DS_EQUIPE")
    @NotBlank(message = "EQUIPE É UM CAMPO OBRIGATÓRIO")
    private String equipe;

    @Column(name = "TG_INATIVO")
    private int inativo;

    @Column(name = "DH_INCLUSAO", nullable = false, insertable = true, updatable = false)
    private LocalDateTime dhInclusao;

    @Column(name = "DH_ALTERACAO", nullable = true, insertable = true, updatable = true)
    private LocalDateTime dhAlteracao;

    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String rg, int cpf, int contato, String email, String equipe, int inativo, LocalDateTime dhInclusao, LocalDateTime dhAlteracao) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.contato = contato;
        this.email = email;
        this.equipe = equipe;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getContato() {
        return contato;
    }

    public void setContato(int contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
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