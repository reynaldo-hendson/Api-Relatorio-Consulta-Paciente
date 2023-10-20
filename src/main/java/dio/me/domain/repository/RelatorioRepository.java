package dio.me.domain.repository;

import dio.me.domain.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {


    @Query("select r from Relatorio r join r.paciente p where p.id = :pacienteId")
    List<Relatorio> findByRelatorios(@Param("pacienteId") Long pacienteId);

    @Query("SELECT r FROM Relatorio r JOIN r.paciente p WHERE p.nome = :nome")
    List<Relatorio> findByNomePaciente(@Param("nome") String nome);
}
