package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.Apontamento;
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
public interface ApontamentoRepository extends JpaRepository<Apontamento, Integer>{
    
    @Query("Select a from Apontamento a"
             + " where a.dhInclusao >= :dhInclusaoIni and a.dhInclusao < :dhInclusaoFin")
    public List<Apontamento> findAllByDhInclusao(LocalDateTime dhInclusaoIni, LocalDateTime  dhInclusaoFin);

    @Query("Select a from Apontamento a"
            + " where a.dhInclusao >= :dhInclusaoIni")
    public List<Apontamento> findAllByDhInclusaoIni(LocalDateTime  dhInclusaoIni);

    @Query("Select a from Apontamento a"
            + " where a.dhInclusao <= :dhInclusaoFin")
    public List<Apontamento> findAllByDhInclusaoFin(LocalDateTime  dhInclusaoFin);
    
    @Query("Select a from Apontamento a where os.id = :osID")
    public List<Apontamento> findAllByOS(Integer  osID);
}
