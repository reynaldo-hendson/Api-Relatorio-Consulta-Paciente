package dio.me.controller;//package me.dio.controller;
//
//import br.com.rh.microservice.api.mapper.AgendaMapper;
//import br.com.rh.microservice.api.request.AgendaRequest;
//import br.com.rh.microservice.api.response.AgendaResponse;
//import br.com.rh.microservice.api.response.domain.entity.Agenda;
//import br.com.rh.microservice.api.response.domain.service.AgendaService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import me.dio.domain.model.Consulta;
//import me.dio.response.ConsultaResponse;
//import me.dio.service.impl.ConsultaService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@RestController
//@RequestMapping("/agenda")
//@RequiredArgsConstructor
//public class ConsultaController {
//
//    private final ConsultaService service;
//    private final AgendaMapper mapper;
//
//    @PostMapping
//    public ResponseEntity<ConsultaResponse> salvar(@Valid @RequestBody ConsultaRequest request) {
//        Agenda agenda = mapper.toAgenda(request);
//        Agenda agendaSalva = service.salvar(agenda);
//        ConsultaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);
//        log.info("Consulta salva: {}", agendaSalva);
//        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ConsultaResponse>> buscarTodos() {
//        log.info("Listando todos os agendamentos.");
//        List<Consulta> agendaList = service.listarTodos();
//        List<AgendaResponse> agendaResponses = mapper.toAgendaResponse(agendaList);
//        return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<ConsultaResponse>> buscarPorId(@PathVariable Long id) {
//        Optional<Consulta> optionalAgenda = service.buscarPorId(id);
//
//        if (optionalAgenda.isEmpty()) {
//            log.warn("Agendamento não encontrado com ID {}", id);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        ConsultaResponse agendaResponse = mapper.toAgendaResponse(optionalAgenda.get());
//
//        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(agendaResponse));
//    }
//
//
//}
