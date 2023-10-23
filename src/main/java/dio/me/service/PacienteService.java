package dio.me.service;

import dio.me.domain.model.Paciente;
import dio.me.domain.repository.PacienteRepository;
import dio.me.exception.EntidadeNaoEncontradaException;
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
        boolean existeCpf = repository.findByCpf(paciente.getCpf())
                .stream()
                .anyMatch(pacienteExistente -> !pacienteExistente.equals(paciente));;

        if(existeCpf){
            throw new NegocioException("Cpf já cadastrado.");
        }
        if(repository.existsByEmail(paciente.getEmail())){
            throw new NegocioException("Email já cadastrado.");
        }
        return repository.save(paciente);
    }

    public Paciente alterar(Long id, Paciente paciente) {
        Optional<Paciente> optPaciente = this.buscarPorId(id);

        if(optPaciente.isPresent()) {
            Paciente pacienteExistente = optPaciente.get();
            pacienteExistente.setNome(paciente.getNome());
            pacienteExistente.setSobrenome(paciente.getSobrenome());
            pacienteExistente.setCpf(paciente.getCpf());
            pacienteExistente.setEmail(paciente.getEmail());
            pacienteExistente.setTelefone(paciente.getTelefone());

            return repository.save(pacienteExistente);
        }else{
            throw new EntidadeNaoEncontradaException("Paciente com ID: "+id+" não cadastrado!");
        }

    }

    public List<Paciente> findAll(){
        log.info("Buscando todos os pacientes.");
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id){

        Optional<Paciente> optPaciente = repository.findById(id);

        if(optPaciente.isPresent()) {
            log.info("Buscando paciente com id: {}", id);
            return repository.findById(id);
        }else{
            throw new EntidadeNaoEncontradaException("Paciente com ID: "+id+" não encontrado.");
        }
    }

    public void delete(Long id){
        log.info("delete: {}", id);
        Optional<Paciente> optPaciente = repository.findById(id);

        if(optPaciente.isPresent()) {
            log.info("Buscando paciente com id: {}", id);
            repository.deleteById(id);;
        }else{
            throw new EntidadeNaoEncontradaException("Paciente com ID: "+id+" não encontrado.");
        }
    }

}
