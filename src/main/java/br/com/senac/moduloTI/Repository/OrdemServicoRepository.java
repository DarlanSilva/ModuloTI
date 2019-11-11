package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.OrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Darlan Silva
 */

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Object> {
    
    @Query("Select o from OrdemServico o left join Chamado c on c.id = o.chamado")
    public List<OrdemServico> findAllOS();
}
