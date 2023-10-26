package dio.me.controller.mapper;

import dio.me.domain.model.Consulta;
import dio.me.request.ConsultaRequest;
import dio.me.response.ConsultaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsultaMapper {

    private final ModelMapper mapper;

    public Consulta toConsulta(ConsultaRequest request) {
        return mapper.map(request, Consulta.class);
    }

    public ConsultaResponse toConsultaResponse(Consulta consulta){
        return mapper.map(consulta, ConsultaResponse.class);
    }

    public List<ConsultaResponse> toConsultaResponseList(List<Consulta> consultas){
        return consultas.stream()
                .map(this::toConsultaResponse)
                .collect(Collectors.toList());
    }



}







