package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.FuncionarioRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaUpdateRequestDto;
import com.hr.training_management_system.domain.model.Funcionario;
import com.hr.training_management_system.domain.model.Turma;
import com.hr.training_management_system.domain.repository.interfaces.ICursoRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaRepository;
import com.hr.training_management_system.domain.service.IServiceGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.cert.TrustAnchor;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TurmaServiceTest {
    @Mock
    private ITurmaRepository _repository;
    @Mock
    private IServiceGateway _serviceGateway;

    @InjectMocks
    private TurmaService _service;

    @Test
    void listAll() {
        //Arrange
        var turmaOne = new Turma();
        turmaOne.setCodigo(1);
        turmaOne.setInicio(LocalDate.now().minusYears(1));
        turmaOne.setFim(LocalDate.now());
        turmaOne.setLocal("Indaiatuba");
        turmaOne.setCurso(2);

        var turmaTwo = new Turma();
        turmaTwo.setCodigo(2);
        turmaTwo.setInicio(LocalDate.now().minusMonths(6));
        turmaTwo.setFim(LocalDate.now().plusMonths(6));
        turmaTwo.setLocal("Remoto");
        turmaTwo.setCurso(2);

        when(_repository.listAll()).thenReturn(Arrays.asList(turmaOne, turmaTwo));

        //Act
        var result = _service.listAll();

        //Assert
        assertFalse(result.isEmpty());
        verify(_repository, times(1)).listAll();
    }

    @Test
    void save() {
        //Arrange
        var input = new TurmaRequestDto(
                LocalDate.now().minusYears(1),
                LocalDate.now(),
                "Remoto",
                1);

        var turma = new Turma();
        turma.setInicio(input.inicio());
        turma.setFim(input.fim());
        turma.setLocal(input.local());
        turma.setCurso(input.curso());


        when(_repository.save(any(Turma.class))).thenReturn(true);

        //Act
        var result = _service.save(input);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).save(any(Turma.class));
    }

    @Test
    void update() {
        //Arrange
        var input = new TurmaUpdateRequestDto(
                LocalDate.now().minusYears(2),
                LocalDate.now(),
                "Remoto");

        var turma = new Turma();
        turma.setCodigo(1);
        turma.setInicio(input.inicio());
        turma.setFim(input.fim());
        turma.setLocal(input.local());
        turma.setCurso(1);

        when(_repository.findByCodigo(turma.getCodigo())).thenReturn(Optional.of(turma));
        when(_repository.update(any(Turma.class))).thenReturn(true);

        //Act
        var result = _service.update(1,input);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).update(any(Turma.class));
    }

    @Test
    void delete() {
        var turma = new Turma();
        turma.setCodigo(1);
        turma.setInicio(LocalDate.now().minusYears(2));
        turma.setFim(LocalDate.now());
        turma.setLocal("Remoto");
        turma.setCurso(1);

        when(_repository.findByCodigo(turma.getCodigo())).thenReturn(Optional.of(turma));
        when(_serviceGateway.deleteTurmaAndReferences(turma.getCodigo())).thenReturn(true);
        when(_repository.delete(turma.getCodigo())).thenReturn(true);

        //Act
        var result = _service.delete(1);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).delete(turma.getCodigo());
    }
}