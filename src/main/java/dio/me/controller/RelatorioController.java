package dio.me.controller;

import dio.me.domain.model.Relatorio;
import dio.me.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/relatorios")
@AllArgsConstructor
public class RelatorioController {

    private final RelatorioService service;

    @GetMapping("/{id}/paciente")
    @Operation(summary = "Get a report by ID", description = "Retrieve a specific report based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<List<Relatorio>> buscarRelatorios(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.listarRelatorios(id));
    }

    @PostMapping
    @Operation(summary = "Create a new report", description = "Create a new report and return the created patient's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid report data provided")
    })
    public ResponseEntity<Relatorio> salvar(@RequestBody Relatorio relatorio) {
        log.info("Salvando relátorio");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(relatorio));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a report by ID", description = "Retrieve a specific report based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        Optional<Relatorio> optionalRelatorio = service.buscarPorId(id);
        if (optionalRelatorio.isEmpty()) {
            log.warn("Relátorio de ID {} não encontrado.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalRelatorio);
    }

    @GetMapping("/paciente/{nome}")
    @Operation(summary = "Get a report by name patient", description = "Retrieve a specific report based on its name patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<List<Relatorio>> buscarRelatoriosPorNomePaciente(@PathVariable String nome) {
        List<Relatorio> relatorios = service.buscarRelatoriosPorNomePaciente(nome);

        if (relatorios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(relatorios);
        }
    }



}
