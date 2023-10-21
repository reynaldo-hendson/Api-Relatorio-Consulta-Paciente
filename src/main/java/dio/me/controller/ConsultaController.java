package dio.me.controller;//package me.dio.controller;


import dio.me.domain.model.Consulta;
import dio.me.mapper.ConsultaDto;
import dio.me.service.impl.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/sessoes")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @PostMapping
    @Operation(summary = "Create a new session", description = "Create a new session and return the created session's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Session created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid session data provided")
    })
    public ResponseEntity<Consulta> salvar(@RequestBody Consulta consultaToCreate) {
        log.info("Consulta salva: {}", consultaToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(consultaToCreate));
    }

    @GetMapping
    @Operation(summary = "Get all sessions", description = "Retrieve a list of all registered sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")})
    public ResponseEntity<List<Consulta>> buscarTodos() {
        log.info("Listando todos os agendamentos.");
        List<Consulta> agendaList = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(agendaList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a session by ID", description = "Retrieve a specific session based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    public ResponseEntity<ConsultaDto> buscarPorId(@PathVariable Long id) {
        var optionalConsulta = service.buscarPorId(id);

        if (optionalConsulta == null) {
            log.warn("Agendamento não encontrado com ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalConsulta);
    }
}
