package dio.me.service.impl;

import dio.me.domain.model.Paciente;
import dio.me.domain.repository.PacienteRepository;
import dio.me.exception.NegocioException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class PacienteService {

    public final PacienteRepository repository;

    public Paciente salvar(Paciente paciente){
        boolean existeCpf = false;

        Optional<Paciente> optionalPaciente = repository.findByCpf(paciente.getCpf());

        if(optionalPaciente.isPresent()){
            if(!optionalPaciente.get().getId().equals(paciente.getId())){
                existeCpf = true;
            }
        }
        if(existeCpf){
            throw new NegocioException("Cpf já cadastrado.");
        }

        return repository.save(paciente);
    }

    public Paciente alterar(Long id, Paciente paciente) {
        Optional<Paciente> optPaciente = this.buscarPorId(id);

        if (optPaciente.isEmpty()) {
            throw new NegocioException("Paciente não cadastrado!");
        }

        paciente.setId(id);

        return salvar(paciente);
    }

    public List<Paciente> findAll(){
        log.info("Buascando todos os pacientes.");
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id){
        log.info("buscarPorId: {}", id);
        return repository.findById(id);

    }

    public void delete(Long id){
        log.info("delete: {}", id);
        repository.deleteById(id);

    }

//    public List<Paciente> buscarPorNome(String nome){
//        log.info("buscarPorNome: {}", nome);
//        return repository.findByNome(nome);
//    }

}
