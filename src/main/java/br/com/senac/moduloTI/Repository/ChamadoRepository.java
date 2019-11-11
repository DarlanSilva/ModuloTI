package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.Chamado;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Darlan.Silva
 */

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
    
    @Query("Select c from Chamado c left join Tecnico t on t.id = c.tecnico.id left join StatusChamado s on s.id = c.statusChamado.id")
    public List<Chamado> findAllChamados();
    
     @Query("Select c from Chamado c left join Tecnico t on t.id = c.tecnico.id left join StatusChamado s on s.id = c.statusChamado.id"
             + " where c.dhInclusao >= :dhInclusaoIni and c.dhInclusao < :dhInclusaoFin")
    public List<Chamado> findAllByDhInclusao(Date  dhInclusaoIni, Date  dhInclusaoFin);

    @Query("Select c from Chamado c left join Tecnico t on t.id = c.tecnico.id left join StatusChamado s on s.id = c.statusChamado.id"
            + " where c.dhInclusao >= :dhInclusaoIni")
    public List<Chamado> findAllByDhInclusaoIni(Date  dhInclusaoIni);

    @Query("Select c from Chamado c left join Tecnico t on t.id = c.tecnico.id left join StatusChamado s on s.id = c.statusChamado.id"
            + " where c.dhInclusao <= :dhInclusaoFin")
    public List<Chamado> findAllByDhInclusaoFin(Date  dhInclusaoFin);
    
    
}
