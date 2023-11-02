package dio.me.controller;

import dio.me.controller.mapper.RelatorioMapper;
import dio.me.domain.model.Paciente;
import dio.me.domain.model.Relatorio;
import dio.me.request.RelatorioRequest;
import dio.me.response.RelatorioResponse;
import dio.me.service.RelatorioService;
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
@RequestMapping("/relatorios")
@AllArgsConstructor
public class RelatorioController {

    private final RelatorioService service;
    private final RelatorioMapper mapper;

    @GetMapping("/{id}/paciente")
    @Operation(summary = "Get a report by ID Patient", description = "Retrieve a specific report based on its ID patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<List<Relatorio>> buscarRelatorios(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(service.listarRelatoriosPorIdPaciente(id));
    }

    @PostMapping
    @Operation(summary = "Create a new report", description = "Create a new report and return the created patient's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid report data provided")
    })
    public ResponseEntity<RelatorioResponse> salvar(@RequestBody @Valid RelatorioRequest relatorioRequest) {
        Relatorio relatorio = mapper.toRelatorio(relatorioRequest);
        relatorio.setId(null);
        Relatorio relatorioSalvo = service.salvar(relatorio);
        RelatorioResponse relatorioResponse = mapper.toRelatorioResponse(relatorioSalvo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(relatorio.getId())
                        .toUri();
        log.info("Salvando relatório");
        return ResponseEntity.created(location).body(relatorioResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a report by ID", description = "Retrieve a specific report based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<RelatorioResponse> buscarPorId(@PathVariable Long id) {
        Optional<Relatorio> optionalRelatorio = service.buscarPorId(id);

        if (optionalRelatorio.isEmpty()) {
            log.warn("Relátorio de ID {} não encontrado.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toRelatorioResponse(optionalRelatorio.get()));
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
