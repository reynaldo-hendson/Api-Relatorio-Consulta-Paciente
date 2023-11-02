package dio.me.controller.mapper;

import dio.me.domain.model.Relatorio;
import dio.me.request.RelatorioRequest;
import dio.me.response.RelatorioResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RelatorioMapper {

    private final ModelMapper mapper;

    public Relatorio toRelatorio(RelatorioRequest request){
        return mapper.map(request, Relatorio.class);
    }

    public RelatorioResponse toRelatorioResponse(Relatorio relatorio){
        return mapper.map(relatorio,RelatorioResponse.class);
    }

    public List<RelatorioResponse> toRelatorioResponseList(List<Relatorio> relatorios){
        return relatorios.stream()
                .map(this::toRelatorioResponse)
                .collect(Collectors.toList());
    }
}
