package dio.me.mapper;


import dio.me.domain.model.Paciente;
import jakarta.validation.constraints.NotBlank;



public record PacienteDto(
        @NotBlank Long id,
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank String cpf,
        @NotBlank String email,
        @NotBlank String telefone) {

    public PacienteDto(Paciente model) {
        this(
                model.getId(),
                model.getNome(),
                model.getSobrenome(),
                model.getCpf(),
                model.getEmail(),
                model.getTelefone()
        );
    }
    public Paciente toModel(){
        Paciente model = new Paciente();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setSobrenome(this.sobrenome);
        model.setCpf(this.cpf);
        model.setEmail(this.email);
        model.setTelefone(this.telefone);
        return model;
    }
}

