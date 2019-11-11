package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.Login;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Darlan.Silva
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{
   
    @Query("Select l from Login l where l.user = :user")
    public Optional<Login> findByUser(String user);
    
    @Query("Select l from Login l where l.user = :userName and l.hashSenha =:password")
    public Optional<Login> findByUserAndPass(String userName, String password);
   
}
