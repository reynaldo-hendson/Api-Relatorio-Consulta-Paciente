package dio.me.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data_sessao")
    private LocalDateTime dataSessao;

    @Column(name="data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "descricao_relatorio", columnDefinition = "TEXT")
    private String descricaoRelatorio;

    @ManyToOne
    private Paciente paciente;



}
