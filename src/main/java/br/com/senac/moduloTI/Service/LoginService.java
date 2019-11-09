package br.com.senac.moduloTI.Service;

import br.com.senac.moduloTI.Entity.Login;
import br.com.senac.moduloTI.Repository.LoginRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Darlan.Silva
 */
@Service
public class LoginService  implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Login> usuario = loginRepository.findByLogin(login);
        
        if(usuario.isPresent() == false){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return usuario.get();
    }
}
