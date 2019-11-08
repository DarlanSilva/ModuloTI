package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.Login;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Darlan.Silva
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{
   
    public Optional<Login> findByEmail(String email);
   
}
