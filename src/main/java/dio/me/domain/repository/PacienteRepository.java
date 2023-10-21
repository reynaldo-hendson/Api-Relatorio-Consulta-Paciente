package dio.me.domain.repository;


import dio.me.domain.model.Paciente;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

   Optional<Paciente> findByCpf(String cpf);

   boolean existsByEmail(String email);

   //   @Query("SELECT p FROM Paciente p WHERE p.nome = :nome")
//   Optional<Paciente> buscarPorNome(String nome);
}
