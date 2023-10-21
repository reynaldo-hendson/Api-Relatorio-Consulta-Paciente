package dio.me.mapper;

import dio.me.domain.model.Consulta;
import dio.me.domain.model.Paciente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
@Getter
@Setter
public class ConsultaDto {
    private Long id;

    private String descricao;

    private LocalDateTime horario;

    private LocalDateTime dataCriacao;

    private Paciente paciente;

    public ConsultaDto(Consulta entity) {
        BeanUtils.copyProperties(entity, this);
    }



}







