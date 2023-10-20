package dio.me.service.impl;//package me.dio.service.impl;
//
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import me.dio.domain.model.Consulta;
//import me.dio.domain.model.Paciente;
//import me.dio.domain.repository.ConsultaRepository;
//import me.dio.exception.NegocioException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//@Slf4j
//@Service
//@AllArgsConstructor
//@Transactional
//public class ConsultaService {
//
//    private final ConsultaRepository repository;
//    private final PacienteService pacienteService;
//
//    public List<Consulta> listarTodos() {
//        log.info("findAll");
//        return repository.findAll();
//    }
//
//    public Optional<Consulta> buscarPorId(Long id) {
//        log.info("buscarPorId: {}", id);
//        return repository.findById(id);
//    }
//
//    public Consulta salvar(Consulta consulta) {
//
//        //1. validar se o Paciente existe
//        Optional<Paciente> optPaciente = pacienteService.buscarPorId(consulta.getPaciente().getId());
//        if (optPaciente.isEmpty()) {
//            log.error("Paciente não encontrado. Lançando NegocioException.");
//            throw new NegocioException("Paciente não encontrado");
//        }
//        //2. Validar o horario
//
//        Optional<Consulta> optHorario = repository.findByHorario(consulta.getHorario());
//        if (optHorario.isPresent()) {
//            log.error("Já existe agendamento para este horário. Lançando NegocioException.");
//            throw new NegocioException("Já existe agendamento para este horário");
//        }
//        consulta.setPaciente(optPaciente.get());
//        consulta.setDataCriacao(LocalDateTime.now());
//
//        log.info("Salvando a agenda.");
//        return repository.save(consulta);
//    }
//
//}
