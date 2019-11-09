package br.com.senac.moduloTI.Entity;

import br.com.senac.moduloTI.Configuration.SecurityConfig;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Darlan.Silva
 */
@Entity
@Table(name = "TS_LOGIN")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Login implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "EMAIL INVÁLIDO")
    @Column(unique = true, name = "DS_LOGIN")
    private String login;

    @NotBlank(message = "CAMPO SENHA OBRIGATÓRIO")
    @Column(name = "DS_SENHA")
    private String hashSenha;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TS_LOGIN_PERMISSAOACESSO")
    private List<Permissao> permissaoAcesso = new ArrayList<Permissao>();

    @Column(name = "TG_INATIVO")
    private int inativo;

    @Column(name = "DH_INCLUSAO", nullable = false, insertable = true, updatable = false)
    private LocalDateTime dhInclusao;

    @Column(name = "DH_ALTERACAO", nullable = true, insertable = true, updatable = true)
    private LocalDateTime dhAlteracao;

    public Login() {
    }

    public Login(Integer id, String login, String hashSenha, int inativo, LocalDateTime dhInclusao, LocalDateTime dhAlteracao) {
        this.id = id;
        this.login = login;
        this.hashSenha = hashSenha;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String senhaAberta) {
        senhaAberta = senhaAberta.trim();

        if (senhaAberta.equalsIgnoreCase(",") || senhaAberta.equalsIgnoreCase("") || senhaAberta == null) {
            this.hashSenha = null;
        } else {
            this.hashSenha = SecurityConfig.bcryptPasswordEncoder().encode(senhaAberta);
        }
    }

    public List<Permissao> getPermissaoAcesso() {
        return permissaoAcesso;
    }

    public void setPermissaoAcesso(List<Permissao> permissaoAcesso) {
        this.permissaoAcesso = permissaoAcesso;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissaoAcesso;
    }
    
    @Override
    public String getPassword() {
        return getHashSenha();
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
