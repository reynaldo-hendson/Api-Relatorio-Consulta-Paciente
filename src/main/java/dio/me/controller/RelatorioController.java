package dio.me.controller;//package me.dio.controller;
//
//
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import me.dio.domain.model.Relatorio;
//import me.dio.request.RelatorioRequest;
//import me.dio.response.RelatorioResponse;
//import me.dio.service.impl.RelatorioService;
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
//@RequestMapping("/relatorios")
//@AllArgsConstructor
//public class RelatorioController {
//
//    private final RelatorioService service;
//    private final RelatorioMapper mapper;
//
//    @GetMapping("/{id}/paciente")
//    public ResponseEntity<List<Relatorio>> buscarRelatorios(@PathVariable Long id) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(service.listarRelatorios(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<RelatorioResponse> salvar(@Valid @RequestBody RelatorioRequest request) {
//        Relatorio relatorio = mapper.toRelatorio(request);
//        relatorio.setId(null);
//        Relatorio relatorioSalvo = service.salvar(relatorio);
//        RelatorioResponse relatorioResponse = mapper.toRelatorioResponse(relatorioSalvo);
//        log.info("Salvando relátorio");
//        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioResponse);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<RelatorioResponse>> buscarPorId(@PathVariable Long id) {
//        Optional<Relatorio> optionalRelatorio = service.buscarPorId(id);
//        if (optionalRelatorio.isEmpty()) {
//            log.warn("Relátorio de ID {} não encontrado.", id);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        RelatorioResponse relatorioResponse = mapper.toRelatorioResponse(optionalRelatorio.get());
//
//        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(relatorioResponse));
//    }
//
//    @GetMapping("/paciente/{nome}")
//    // TODO: 07/08/2023
//    public ResponseEntity<List<Relatorio>> buscarRelatoriosPorNomePaciente(@PathVariable String nome) {
//        List<Relatorio> relatorios = service.buscarRelatoriosPorNomePaciente(nome);
//
//        if (relatorios.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(relatorios);
//        }
//    }
//
//
//
//}
