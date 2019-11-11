package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Darlan Silva
 */
@Repository
public interface StatusChamadoRepository extends JpaRepository<StatusChamado, Integer> {

}
