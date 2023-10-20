package dio.me.mapper;//package me.dio.mapper;
//
//import br.com.rh.microservice.api.request.RelatorioRequest;
//import br.com.rh.microservice.api.response.RelatorioResponse;
//import br.com.rh.microservice.api.response.domain.entity.Relatorio;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class RelatorioMapper {
//
//    private final ModelMapper mapper;
//
//    public Relatorio toRelatorio(RelatorioRequest request){
//        return mapper.map(request, Relatorio.class);
//    }
//
//    public RelatorioResponse toRelatorioResponse(Relatorio relatorio){
//        return mapper.map(relatorio,RelatorioResponse.class);
//    }
//
//    public List<RelatorioResponse> toRelatorioResponse(List<Relatorio> relatorios){
//        return relatorios.stream()
//                .map(this::toRelatorioResponse)
//                .collect(Collectors.toList());
//    }
//}
