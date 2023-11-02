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
public class RelatorioResponse {
    private Long id;
    private String descricaoRelatorio;
    private LocalDateTime dataSessao;
    private PacienteResponse paciente;
}
