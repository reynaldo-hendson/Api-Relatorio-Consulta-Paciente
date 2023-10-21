package dio.me.service.impl;


import dio.me.domain.model.Consulta;
import dio.me.domain.model.Paciente;
import dio.me.domain.repository.ConsultaRepository;
import dio.me.exception.NegocioException;
import dio.me.mapper.ConsultaDto;
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

    public ConsultaDto buscarPorId(Long id) {
        Consulta result = repository.findById(id).get();
        log.info("buscarPorId: {}", id);
        return new ConsultaDto(result);
    }

    public Consulta salvar(Consulta consulta) {

        //1. validar se o Paciente existe
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(consulta.getPaciente().getId());
        if (optPaciente.isEmpty()) {
            log.error("Paciente não encontrado. Lançando NegocioException.");
            throw new NegocioException("Paciente não encontrado");
        }
        //2. Validar o horario

        Optional<Consulta> optHorario = repository.findByHorario(consulta.getHorario());
        if (optHorario.isPresent()) {
            log.error("Já existe agendamento para este horário. Lançando NegocioException.");
            throw new NegocioException("Já existe agendamento para este horário");
        }
        consulta.setPaciente(optPaciente.get());
        consulta.setDataCriacao(LocalDateTime.now());

        log.info("Salvando a agenda.");
        return repository.save(consulta);
    }

}
