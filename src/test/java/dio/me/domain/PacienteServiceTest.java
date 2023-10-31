package dio.me.domain;


import dio.me.domain.model.Paciente;
import dio.me.domain.repository.PacienteRepository;
import dio.me.exception.EntidadeNaoEncontradaException;
import dio.me.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    public void testBuscarOPacientePorIdExistente() {
        // Criação de um usuário mock
        Long id = 1L;
        Paciente user = new Paciente(id,"John","joão","0999999999","johndoe@example.com","859888888888");

        // Comportamento mock do UserRepository para retornar o usuário pelo ID
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(user));

        // Chamada do método buscarPorId
        Optional<Paciente> result = pacienteService.buscarPorId(1L);

        // Verificação se o resultado não é nulo e se o ID do paciente é o esperado
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1L, result.get().getId());

        // Verificação se o método findById do repository foi chamado com o ID correto
        verify(pacienteRepository, times(2)).findById(1L);
    }

    @Test
    public void testBuscarPorId_NonExistingId() {
        // Cenário de teste: ID inexistente
        Long id = 99L;

        // Verificação se o resultado é vazio
        Optional<Paciente> pacienteOptional = Optional.empty();

        when(pacienteRepository.findById(anyLong())).thenReturn(pacienteOptional);

        // Act and Assert
        assertThrows(EntidadeNaoEncontradaException.class, () -> pacienteService.buscarPorId(id));
    }

    @Test
    public void testBuscarTodos() {
        // Cenário de teste: Lista de pacientes mock
        Paciente paciente1 = new Paciente(1L, "John","joão","0999999999","johndoe@example.com","988888888");
        Paciente paciente2 = new Paciente(2L, "Maria","Clara", "8888888888", "maria@example.com","988888889");
        List<Paciente> pacientesMock = Arrays.asList(paciente1, paciente2);

        // Comportamento mock do repository
        when(pacienteRepository.findAll()).thenReturn(pacientesMock);

        // Chamada do método buscarTodos
        List<Paciente> result = pacienteService.findAll();

        // Verificação se a lista de pacientes retornada não é nula e possui o tamanho esperado
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());

        // Verificação se os pacientes retornados são os esperados
        Assertions.assertEquals(paciente1.getId(), result.get(0).getId());
        Assertions.assertEquals(paciente1.getNome(), result.get(0).getNome());
        Assertions.assertEquals(paciente1.getCpf(), result.get(0).getCpf());
        Assertions.assertEquals(paciente1.getEmail(), result.get(0).getEmail());
        Assertions.assertEquals(paciente1.getTelefone(), result.get(0).getTelefone());

        Assertions.assertEquals(paciente2.getId(), result.get(1).getId());
        Assertions.assertEquals(paciente2.getNome(), result.get(1).getNome());
        Assertions.assertEquals(paciente2.getCpf(), result.get(1).getCpf());
        Assertions.assertEquals(paciente2.getEmail(), result.get(1).getEmail());
        Assertions.assertEquals(paciente2.getTelefone(), result.get(1).getTelefone());

        // Verificação se o método findAll do repository foi chamado
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    public void testDelete_PacienteExistente() {
        // Arrange
        Long pacienteId = 1L;
        Paciente pacienteMock = new Paciente(pacienteId, "John","joão","0999999999","johndoe@example.com","988888888");

        Optional<Paciente> pacienteOptional = Optional.of(pacienteMock);

        when(pacienteRepository.findById(anyLong())).thenReturn(pacienteOptional);

        // Act
        pacienteService.delete(pacienteId);

        // Assert
        verify(pacienteRepository, times(1)).findById(pacienteId);
        verify(pacienteRepository, times(1)).deleteById(pacienteId);
    }

    @Test
    public void testDelete_PacienteNaoExistente() {
        // Arrange
        Long pacienteId = 2L;
        Optional<Paciente> pacienteOptional = Optional.empty();

        when(pacienteRepository.findById(anyLong())).thenReturn(pacienteOptional);

        // Act and Assert
        assertThrows(EntidadeNaoEncontradaException.class, () -> pacienteService.delete(pacienteId));

        // Verify that deleteById method was not called
        verify(pacienteRepository, never()).deleteById(anyLong());
    }
}



