package dio.me.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConsultaResponse {
    private Long id;
    private String descricao;
    private LocalDateTime horario;
    private PacienteResponse paciente;
}
