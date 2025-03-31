package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.TurmaParticipanteRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaRequestDto;
import com.hr.training_management_system.domain.model.Funcionario;
import com.hr.training_management_system.domain.model.Turma;
import com.hr.training_management_system.domain.model.TurmaParticipante;
import com.hr.training_management_system.domain.repository.interfaces.IFuncionarioRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaParticipanteRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaRepository;
import com.hr.training_management_system.domain.service.IServiceGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TurmaParticipantesServiceTest {
    @Mock
    private ITurmaParticipanteRepository _repository;
    @Mock
    private IServiceGateway _serviceGateway;

    @InjectMocks
    private TurmaParticipantesService _service;

    @Test
    void listAll() {
        //Arrange
        var funcionarioOne = new Funcionario();
        funcionarioOne.setCodigo(1);
        funcionarioOne.setNome("teste 1");
        funcionarioOne.setCpf("teste 1");
        funcionarioOne.setNascimento(LocalDate.now().minusYears(20));
        funcionarioOne.setCargo("teste 1");
        funcionarioOne.setAdmissao(LocalDate.now());
        funcionarioOne.setStatus(true);

        var turmaOne = new Turma();
        turmaOne.setCodigo(1);
        turmaOne.setInicio(LocalDate.now().minusYears(1));
        turmaOne.setFim(LocalDate.now());
        turmaOne.setLocal("Indaiatuba");
        turmaOne.setCurso(2);

        var turmaParticipante = new TurmaParticipante();
        turmaParticipante.setCodigo(1);
        turmaParticipante.setTurma(1);
        turmaParticipante.setFuncionario(1);

        when(_serviceGateway.findFuncionarioToTurmaParticipantes(funcionarioOne.getCodigo())).thenReturn(Optional.of(funcionarioOne));
        when(_serviceGateway.findTurmaToTurmaParticipantes(turmaOne.getCodigo())).thenReturn(Optional.of(turmaOne));

        when(_repository.listAll()).thenReturn(Arrays.asList(turmaParticipante));

        //Act
        var result = _service.listAll();

        //Assert
        assertFalse(result.isEmpty());
        verify(_repository, times(1)).listAll();
    }

    @Test
    void save() {
        //Arrange
        var input = new TurmaParticipanteRequestDto(
                1,1);

        var turmaParticipante = new TurmaParticipante();
        turmaParticipante.setTurma(input.turma());
        turmaParticipante.setFuncionario(input.funcionario());

        when(_repository.save(any(TurmaParticipante.class))).thenReturn(true);

        //Act
        var result = _service.save(input);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).save(any(TurmaParticipante.class));
    }

    @Test
    void delete() {
        var turmaParticipante = new TurmaParticipante();
        turmaParticipante.setCodigo(1);
        turmaParticipante.setTurma(1);
        turmaParticipante.setFuncionario(1);

        when(_repository.delete(turmaParticipante.getCodigo())).thenReturn(true);

        //Act
        var result = _service.delete(turmaParticipante.getCodigo());

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).delete(turmaParticipante.getCodigo());
    }
}