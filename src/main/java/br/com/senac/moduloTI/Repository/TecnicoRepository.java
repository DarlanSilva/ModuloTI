package br.com.senac.moduloTI.Repository;

import br.com.senac.moduloTI.Entity.Tecnico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Wesley Santos_2
 */
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
    
    @Query("Select t from Tecnico t where t.nome like %?1%")
    public List<Tecnico> findByNome(String nome);
}