package dio.me.controller;

import dio.me.domain.model.Paciente;
import dio.me.mapper.PacienteDto;
import dio.me.service.impl.PacienteService;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/paciente")
@AllArgsConstructor
public class PacienteController {
    private final PacienteService service;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new patient and return the created patient's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid patient data provided")
    })
    public ResponseEntity<PacienteDto> salvar(@Valid @RequestBody PacienteDto pacienteDto) {
        var paciente = service.salvar(pacienteDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();
        log.info("Paciente salvo: {}", paciente);
        return ResponseEntity.created(location).body(new PacienteDto(paciente));
    }

    @GetMapping
    @Operation(summary = "Get all patient", description = "Retrieve a list of all registered patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")})
    public ResponseEntity<List<PacienteDto>> listAll() {
        log.info("Listando todos os pacientes");
        var pacientes = service.findAll();
        var pacienteDto = pacientes.stream()
                .map(PacienteDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(pacienteDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a patient by ID", description = "Retrieve a specific user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    public ResponseEntity<PacienteDto> findById(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = service.buscarPorId(id);

        if (optionalPaciente.isEmpty()) {
            log.warn("Paciente não encontrado com ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        PacienteDto pacienteDto = new PacienteDto(optionalPaciente.get());
        return ResponseEntity.status(HttpStatus.OK).body(pacienteDto);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update the data of an existing patient based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient updated successfully"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "422", description = "Invalid patient data provided")
    })
    public ResponseEntity<PacienteDto> alterar(@Valid @PathVariable Long id, @RequestBody PacienteDto pacienteDto) {
        var paciente = service.alterar(id, pacienteDto.toModel());
        log.info("Paciente alterado: {}", paciente);
        return ResponseEntity.status(HttpStatus.OK).body(new PacienteDto(paciente));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete an existing patient based on its ID")
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
