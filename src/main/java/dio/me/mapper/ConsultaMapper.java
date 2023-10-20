package dio.me.mapper;//package me.dio.mapper;
//
//import br.com.rh.microservice.api.request.AgendaRequest;
//import br.com.rh.microservice.api.response.AgendaResponse;
//import br.com.rh.microservice.api.response.domain.entity.Agenda;
//import lombok.RequiredArgsConstructor;
//import me.dio.domain.model.Consulta;
//import me.dio.request.ConsultaRequest;
//import me.dio.response.ConsultaResponse;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class ConsultaMapper {
//
//    public Consulta toAgenda(ConsultaRequest request) {
//        return mapper.map(request, Consulta.class);
//    }
//
//    public ConsultaResponse toAgendaResponse(Consulta agenda){
//        return mapper.map(agenda, ConsultaResponse.class);
//    }
//
//    public List<ConsultaResponse> toAgendaResponse(List<Consulta> agendas){
//        return agendas.stream()
//                .map(this::toAgendaResponse)
//                .collect(Collectors.toList());
//    }
//}
