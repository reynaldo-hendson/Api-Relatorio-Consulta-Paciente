package dio.me.service;


import dio.me.domain.model.Consulta;
import dio.me.domain.model.Paciente;
import dio.me.domain.repository.ConsultaRepository;
import dio.me.exception.EntidadeNaoEncontradaException;
import dio.me.exception.NegocioException;
import dio.me.controller.mapper.ConsultaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ConsultaService {

    private final ConsultaRepository repository;
    private final PacienteService pacienteService;

    public List<Consulta> listarTodos() {
        log.info("Buscando todos as consultas.");
        return repository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        Optional<Consulta> result = repository.findById(id);

        if(result.isPresent()) {
            log.info("Buscando consulta por id {}", id);
            return repository.findById(id);
        }else {
            throw new EntidadeNaoEncontradaException("Consulta com id: "+id+" não encontrada.");
        }
    }

    public Consulta salvar(Consulta consulta) {

        //1. validar se o Paciente existe
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(consulta.getPaciente().getId());
        if (optPaciente.isEmpty()) {
            log.error("Paciente não encontrado. Lançando NegocioException.");
            throw new EntidadeNaoEncontradaException("Paciente não encontrado");
        }
        //2. Validar o horario

        Optional<Consulta> optHorario = repository.findByHorario(consulta.getHorario());
        if (optHorario.isPresent()) {
            log.error("Já existe agendamento para este horário. Lançando NegocioException.");
            throw new NegocioException("Já existe agendamento para este horário.");
        }
        consulta.setPaciente(optPaciente.get());
        consulta.setDataCriacao(LocalDateTime.now());

        log.info("Salvando a consulta.");
        return repository.save(consulta);
    }

    public Consulta alterar(Long id, Consulta consultaAtualizada) {
        Optional<Consulta> optionalConsulta = repository.findById(id);

        if (optionalConsulta.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Consulta não encontrada com ID: " + id);
        }

        Consulta consultaExistente = optionalConsulta.get();

        // Atualize os campos desejados
        consultaExistente.setDescricao(consultaAtualizada.getDescricao());
        consultaExistente.setHorario(consultaAtualizada.getHorario());

        // Salve a entidade atualizada
        return repository.save(consultaExistente);
    }
    public void delete(Long id){
        Optional<Consulta> optionalConsulta = repository.findById(id);
        if(optionalConsulta.isPresent()){
            log.info("Buscando consulta com id: {}", id);
            repository.deleteById(id);
        }else{
            throw new EntidadeNaoEncontradaException("Consulta com ID: "+id+" não encontrada.");
        }
    }
}
