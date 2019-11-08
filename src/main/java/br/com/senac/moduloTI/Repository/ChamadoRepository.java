package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Darlan.Silva
 */

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
    
}
