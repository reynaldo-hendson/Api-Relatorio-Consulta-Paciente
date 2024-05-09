# Projeto Dio Santander Backend 2023

Projeto API Restful para controle de consultas, Pacientes e Relatórios..

## Índice
- <a href="#diagrama">Diagrama de classe</a>
- <a href="#funcionalidades">Funcionalidades do projeto</a>
- <a href="#demonstracao">Demonstração</a>
- <a href="#rodar">Como rodar este projeto?</a> 
- <a href="#tecnologias">Tecnologias Utilizadas</a>
- <a href="#autoras">Pessoas Autoras</a>
- <a href="#proximospassos">Próximos passos</a>

<h2 id="diagrama">Diagrama de classes</h2>

```mermaid
classDiagram
  class Paciente {
    -String nome
    -String sobrenome
    -String cpf
    -String email
    -String telefone
  }

  class Consulta {
    -String descricao
    -DateTime horario
    -DateTime dataCriacao
    -Paciente paciente
  }

  class Relatorio {
    -DateTime dataSessao
    -DateTime dataCriacao
    -String descricaoRelatorio
    -Paciente paciente
  }

  Paciente "1..1"<-->"0..N" Relatorio
  Paciente "1..1"<-->"0..N" Consulta
  Consulta "1..1"<-->"1..1" Relatorio
```

<h2 id = "funcionalidades"> 🖥️Funcionalidades do Projeto </h2>

- [x] Cadastro de Paciente
- [x] Consulta de Paciente
- [x] Exclusao do Paciente
- [x] Atualização de Paciente
- [x] Cadastro de Consulta
- [x] Buscar Consulta
- [x] Cadastro de Relatórios por consulta
- [x] Consultar Relatório por nome do Paciente

## 📺Layout

![interface](https://github.com/reynaldo86/Dio-Api-Restful/assets/80369346/0144875d-c41c-4006-9a87-3c8971861fb9)

<h2 id="demonstracao"> Demonstração </h2>

[Projeto](https://rhapi-restful-production.up.railway.app/swagger-ui.html)


<h2 id="rodar">💿Como Rodar este projeto</h2>

```bash
# Clone o repositório 
$ git clone https://github.com/reynaldo86/Dio-Api-Restful.git

# Acesse a pasta do projeto
$ cd Dio-Api-Restful

# Instale as dependências
$ mvn clean install

# Execute o projeto
Abra o projeto na IDE de sua preferência.

# Configurações Adicionais:
Se o projeto usa um banco de dados, certifique-se de configurar as informações de conexão no arquivo de configuração.

Consulte o arquivo application.properties (ou application.yml) para configurar outras propriedades da aplicação, como porta, URL da base de dados, é os ambientes.

```
<h2 id="tecnologias">🛠️Tecnologias Utilizadas</h2>

1. [Java 17](https://www.java.com/pt-BR/)
2. [Spring Boot 3](https://spring.io/projects/spring-boot)
3. [OpenAPI (Swagger)](https://springdoc.org/)
4. [Railway](https://railway.app/)

<h2 id="autoras">👤Pessoas Autoras</h2>

<img src="https://media.licdn.com/dms/image/C4D03AQFM8O2ABfbPvQ/profile-displayphoto-shrink_200_200/0/1662041284290?e=1703721600&v=beta&t=JAqN8On0SSRgLiEgsUFo2hp9wv8UCzumg-4ft6sGVrk" alt="imagem do desenvolvedor"></img>

[Linkedin](https://www.linkedin.com/in/reynaldo-hendson/)

<h2 id="proximospassos"> 🖊️Próximos passos </h2>

- [] Refartorar projeto
- [] Implementar camada de segurança.
- [] Conectar com Api externa de endereço
- [] Implementar pagamentos
- [] Implementar notificação via email 
- [] Implementar Frontend
