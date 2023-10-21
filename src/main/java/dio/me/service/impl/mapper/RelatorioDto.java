package dio.me.service.impl.mapper;

import dio.me.domain.model.Paciente;
import dio.me.domain.model.Relatorio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
public class RelatorioDto {

    private Long id;

    private LocalDateTime dataSessao;

    private LocalDateTime dataCriacao;

    private String descricaoRelatorio;

    private Paciente paciente;

    public RelatorioDto(Relatorio entity){
        BeanUtils.copyProperties(entity, this);
    }



}
