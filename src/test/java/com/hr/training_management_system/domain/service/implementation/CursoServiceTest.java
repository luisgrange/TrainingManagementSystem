package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.CursoRequestDto;
import com.hr.training_management_system.domain.model.Curso;
import com.hr.training_management_system.domain.model.Turma;
import com.hr.training_management_system.domain.model.TurmaParticipante;
import com.hr.training_management_system.domain.repository.interfaces.ICursoRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaParticipanteRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaRepository;
import com.hr.training_management_system.domain.service.IServiceGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private ICursoRepository _repository;
    @Mock
    private ITurmaRepository _turmaRepository;
    @Mock
    private ITurmaParticipanteRepository _turmaParticipanteRepository;
    @Mock
    private IServiceGateway _serviceGateway;

    @InjectMocks
    private CursoService cursoService;

    @Test
    @DisplayName("Should create curso successfully")
    void shouldCreateCurso() {
        //Arrange
        var input = new CursoRequestDto(
                "Teste",
                "Curso de testes",
                90);

        var curso = new Curso();
        curso.setNome(input.nome());
        curso.setDescricao(input.descricao());
        curso.setDuracao(input.duracao());

        when(_repository.save(any(Curso.class))).thenReturn(true);

        //Act
        var result = cursoService.save(input);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).save(any(Curso.class));
    }

    @Test
    @DisplayName("Should update curso successfully")
    void shouldUpdateSuccessfully() {
        //Arrange
        var input = new CursoRequestDto(
                "Teste",
                "Curso de testes",
                90);

        var curso = new Curso();
        curso.setCodigo(1);
        curso.setNome(input.nome());
        curso.setDescricao(input.descricao());
        curso.setDuracao(input.duracao());

        when(_repository.findByCodigo(curso.getCodigo())).thenReturn(Optional.of(curso));
        when(_repository.update(any(Curso.class))).thenReturn(true);

        //Act
        var result = cursoService.update(1, input);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).update(any(Curso.class));
    }

    @Test
    @DisplayName("Should delete curso successfully")
    void shouldDeleteSuccessfully() {
        //Arrange
        var input = new CursoRequestDto(
                "Teste",
                "Curso de testes",
                90);

        var curso = new Curso();
        curso.setCodigo(1);
        curso.setNome(input.nome());
        curso.setDescricao(input.descricao());
        curso.setDuracao(input.duracao());

        var turma = new Turma();
        turma.setCodigo(1);
        turma.setInicio(LocalDate.now().minusYears(1));
        turma.setFim(LocalDate.now());
        turma.setLocal("Indaiatuba");
        turma.setCurso(1);

        var turmaParticipante = new TurmaParticipante();
        turmaParticipante.setCodigo(1);
        turmaParticipante.setTurma(1);
        turmaParticipante.setFuncionario(1);

        when(_serviceGateway.deleteCursoAndReferences(curso.getCodigo())).thenReturn(true);
        when(_repository.findByCodigo(curso.getCodigo())).thenReturn(Optional.of(curso));

        when(_repository.delete(curso.getCodigo())).thenReturn(true);

        // Act
        var success = _serviceGateway.deleteCursoAndReferences(curso.getCodigo());
        var result = success && cursoService.delete(curso.getCodigo());

        // Assert
        assertTrue(result);
        verify(_repository, times(1)).delete(curso.getCodigo());
    }
}