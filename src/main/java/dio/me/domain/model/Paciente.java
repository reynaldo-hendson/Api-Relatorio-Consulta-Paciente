package dio.me.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "tb_paciente")
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 50)
    private String nome;
    @Column(length = 50)
    private String sobrenome;

    @Column(length = 11,unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String telefone;

}
