package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Wesley Santos_2
 */
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
    
}
