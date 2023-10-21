package dio.me.service.impl.mapper;


import dio.me.domain.model.Paciente;


public record PacienteDto(
         Long id,
         String nome,
         String sobrenome,
         String cpf,
         String email,
         String telefone) {

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

