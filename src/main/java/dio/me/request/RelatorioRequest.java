package dio.me.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioRequest {

    @NotBlank
    private String descricaoRelatorio;

    @NotNull
    //@DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataSessao;

    @NotNull
    private Long pacienteId;

}
