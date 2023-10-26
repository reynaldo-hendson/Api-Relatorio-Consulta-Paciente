package dio.me.controller;//package me.dio.controller;


import dio.me.controller.mapper.ConsultaMapper;
import dio.me.domain.model.Consulta;
import dio.me.request.ConsultaRequest;
import dio.me.response.ConsultaResponse;
import dio.me.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sessoes")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;
    private final ConsultaMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new session", description = "Create a new session and return the created session's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Session created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid session data provided")
    })
    public ResponseEntity<ConsultaResponse> salvar(@RequestBody ConsultaRequest consultaToCreate) {
        Consulta consulta = mapper.toConsulta(consultaToCreate);
        Consulta consultaSalva = service.salvar(consulta);
        ConsultaResponse consultaResponse = mapper.toConsultaResponse(consultaSalva);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consulta.getId())
                .toUri();
        log.info("Consulta salva: {}", consultaToCreate);
        return ResponseEntity.created(location).body(consultaResponse);
    }

    @GetMapping
    @Operation(summary = "Get all sessions", description = "Retrieve a list of all registered sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")})
    public ResponseEntity<List<ConsultaResponse>> buscarTodos() {
        log.info("Listando todos os agendamentos.");
        List<Consulta> consultaLista = service.listarTodos();
        List<ConsultaResponse> consultaResponses = mapper.toConsultaResponseList(consultaLista);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a session by ID", description = "Retrieve a specific session based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    public ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable Long id) {
        var optionalConsulta = service.buscarPorId(id);

        if (optionalConsulta.isEmpty()) {
            log.warn("Consulta não encontrada com ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ConsultaResponse consultaResponse = mapper.toConsultaResponse(optionalConsulta.get());
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a session", description = "Update the data of an existing session based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session updated successfully"),
            @ApiResponse(responseCode = "404", description = "Session not found"),
            @ApiResponse(responseCode = "422", description = "Invalid session data provided")
    })
    public ResponseEntity<ConsultaResponse> alterar(@Valid @PathVariable Long id, @RequestBody ConsultaRequest request) {
        Consulta consulta = mapper.toConsulta(request);
        Consulta consultaSalva = service.alterar(id, consulta);
        ConsultaResponse consultaResponse = mapper.toConsultaResponse(consultaSalva);
        log.info("Paciente alterado: {}", consulta);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a session", description = "Delete an existing sesseion based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Session deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var optionalConsulta = service.buscarPorId(id);

        if (optionalConsulta.isEmpty()) {
            log.warn("Consulta não encontrada com ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            log.info("Excluindo consulta com ID {}", id);
            service.delete(id);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
