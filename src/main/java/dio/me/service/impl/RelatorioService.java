package dio.me.service.impl;//package me.dio.service.impl;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import me.dio.domain.model.Paciente;
//import me.dio.domain.model.Relatorio;
//import me.dio.domain.repository.RelatorioRepository;
//import me.dio.exception.NegocioException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class RelatorioService {
//
//    private final RelatorioRepository repository;
//    private final PacienteService pacienteService;
//
//    public Relatorio salvar(Relatorio relatorio) {
//
//        //Valida se paciente existe
//        Optional<Paciente> optPaciente = pacienteService.buscarPorId(relatorio.getPaciente().getId());
//        if (optPaciente.isEmpty()) {
//            log.error("Paciente não encontrado. Lançando NegocioException.");
//            throw new NegocioException("Paciente não encontrado.");
//        }
//
//        relatorio.setPaciente(optPaciente.get());
//        relatorio.setDataCriacao(LocalDateTime.now());
//        log.info("Salvando a relatório.");
//
//        return repository.save(relatorio);
//    }
//
//    public Optional<Relatorio> buscarPorId(Long id) {
//        log.info("buscarPorId: {}", id);
//        return repository.findById(id);
//    }
//
//    public List<Relatorio> listarRelatorios(Long id) {
//
//        //Valida se paciente existe
//        Optional<Paciente> optionalPaciente = pacienteService.buscarPorId(id);
//        if (optionalPaciente.isEmpty()) {
//            log.error("Paciente não encontrado. Lançando NegocioException.");
//            throw new NegocioException("Paciente não encontrado.");
//        }
//
//        return repository.findByRelatorios(optionalPaciente.get().getId());
//
//    }
//
//    public List<Relatorio> buscarRelatoriosPorNomePaciente(String nomePaciente) {
//        List<Relatorio> relatorios = repository.findByNomePaciente(nomePaciente);
//
//        // Usando Stream API para filtrar a lista
//        List<Relatorio> relatoriosFiltrados = relatorios.stream()
//                .filter(relatorio -> relatorio.getPaciente().getNome().equals(nomePaciente))
//                .toList();
//        return relatoriosFiltrados;
//
//    }
//}
