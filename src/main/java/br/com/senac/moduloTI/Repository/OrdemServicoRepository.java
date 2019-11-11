package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.OrdemServico;
import java.time.LocalDateTime;
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

    @Query("Select o from OrdemServico o left join Chamado c on c.id = o.chamado"
            + " where c.dhInclusao >= :dhInclusaoIni and c.dhInclusao < :dhInclusaoFin")
    public List<OrdemServico> findAllByDhInclusao(LocalDateTime dhInclusaoIni, LocalDateTime dhInclusaoFin);

    @Query("Select o from OrdemServico o left join Chamado c on c.id = o.chamado"
            + " where c.dhInclusao >= :dhInclusaoIni")
    public List<OrdemServico> findAllByDhInclusaoIni(LocalDateTime dhInclusaoIni);

    @Query("Select o from OrdemServico o left join Chamado c on c.id = o.chamado"
            + " where c.dhInclusao <= :dhInclusaoFin")
    public List<OrdemServico> findAllByDhInclusaoFin(LocalDateTime dhInclusaoFin);

}
