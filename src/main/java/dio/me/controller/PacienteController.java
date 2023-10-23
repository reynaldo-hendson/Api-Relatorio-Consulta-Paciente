package dio.me.controller;

import dio.me.controller.mapper.PacienteMapper;
import dio.me.domain.model.Paciente;
import dio.me.request.PacienteRequest;
import dio.me.response.PacienteResponse;
import dio.me.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/v1/pacientes")
@AllArgsConstructor
public class PacienteController {
    private final PacienteService service;
    private final PacienteMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new patient", description = "Create a new patient and return the created patient's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient created successfully"),
            @ApiResponse(responseCode = "400", description = "One or more parameters are incorrect, check and try again."),
            @ApiResponse(responseCode = "422", description = "Invalid patient data provided")
    })
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest request) {
        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.salvar(paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();
        log.info("Paciente salvo: {}", paciente);
        return ResponseEntity.created(location).body(pacienteResponse);
    }

    @GetMapping
    @Operation(summary = "Get all patient", description = "Retrieve a list of all registered patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")})
    public ResponseEntity<List<PacienteResponse>> listAll() {
        log.info("Listando todos os pacientes");
        List<Paciente> pacientes = service.findAll();
        List<PacienteResponse> pacienteResponses = mapper.toPacienteResponseList(pacientes);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a patient by ID", description = "Retrieve a specific patient based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    public ResponseEntity<PacienteResponse> findById(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = service.buscarPorId(id);

        if (optionalPaciente.isEmpty()) {
            log.warn("Paciente com ID {} não encontrado", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(optionalPaciente.get()));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient", description = "Update the data of an existing patient based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient updated successfully"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "422", description = "Invalid patient data provided")
    })
    public ResponseEntity<PacienteResponse> alterar(@Valid @PathVariable Long id, @RequestBody PacienteRequest request) {
        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.alterar(id, paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);
        log.info("Paciente alterado: {}", paciente);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Delete an existing patient based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Patient deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Excluindo paciente com ID {}", id);
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
