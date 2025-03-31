package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.CursoRequestDto;
import com.hr.training_management_system.application.dto.request.FuncionarioRequestDto;
import com.hr.training_management_system.domain.model.Curso;
import com.hr.training_management_system.domain.model.Funcionario;
import com.hr.training_management_system.domain.repository.interfaces.ICursoRepository;
import com.hr.training_management_system.domain.repository.interfaces.IFuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {
    @Mock
    private IFuncionarioRepository _repository;
    @InjectMocks
    private FuncionarioService _service;

    @Test
    void listAll() {
        var funcionarioOne = new Funcionario();
        funcionarioOne.setNome("teste 1");
        funcionarioOne.setCpf("teste 1");
        funcionarioOne.setNascimento(LocalDate.now().minusYears(20));
        funcionarioOne.setCargo("teste 1");
        funcionarioOne.setAdmissao(LocalDate.now());
        funcionarioOne.setStatus(true);

        var funcionarioTwo = new Funcionario();
        funcionarioTwo.setNome("teste 2");
        funcionarioTwo.setCpf("teste 2");
        funcionarioTwo.setNascimento(LocalDate.now().minusYears(20));
        funcionarioTwo.setCargo("teste 2");
        funcionarioTwo.setAdmissao(LocalDate.now());
        funcionarioTwo.setStatus(true);

        //when(_repository.listAll()).thenReturn(Arrays.asList(funcionarioOne, funcionarioTwo));
        when(_repository.findByStatus(true)).thenReturn(Arrays.asList(funcionarioOne, funcionarioTwo));


        //Act
        var result = _service.listAll(Optional.of(true));

        //Assert
        assertFalse(result.isEmpty());
        verify(_repository, times(1)).findByStatus(true);
    }

    @Test
    void save() {
        //Arrange
        var input = new FuncionarioRequestDto(
                "Teste",
                "111.111.111-11",
                LocalDate.now().minusYears(20),
                "Desenvolvedor",
                LocalDate.now(),
                true);

        var funcionario = new Funcionario();
        funcionario.setNome(input.nome());
        funcionario.setCpf(input.cpf());
        funcionario.setNascimento(input.nascimento());
        funcionario.setCargo(input.cargo());
        funcionario.setAdmissao(input.admissao());
        funcionario.setStatus(input.status());

        when(_repository.save(any(Funcionario.class))).thenReturn(true);

        //Act
        var result = _service.save(input);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).save(any(Funcionario.class));
    }
    @Test
    void update() {
        //Arrange
        var input = new FuncionarioRequestDto(
                "Teste",
                "111.111.111-11",
                LocalDate.now().minusYears(20),
                "Desenvolvedor",
                LocalDate.now(),
                true);

        var funcionario = new Funcionario();
        funcionario.setCodigo(1);
        funcionario.setNome(input.nome());
        funcionario.setCpf(input.cpf());
        funcionario.setNascimento(input.nascimento());
        funcionario.setCargo(input.cargo());
        funcionario.setAdmissao(input.admissao());
        funcionario.setStatus(input.status());

        when(_repository.findByCodigo(funcionario.getCodigo())).thenReturn(Optional.of(funcionario));
        when(_repository.update(any(Funcionario.class))).thenReturn(true);

        //Act
        var result = _service.update(1,input);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).update(any(Funcionario.class));
    }

    @Test
    void delete() {
        //Arrange
        var funcionario = new Funcionario();
        funcionario.setCodigo(1);
        funcionario.setNome("Teste");
        funcionario.setCpf("111.111.111-11");
        funcionario.setNascimento(LocalDate.now().minusYears(20));
        funcionario.setCargo("Desenvolvedor");
        funcionario.setAdmissao(LocalDate.now());
        funcionario.setStatus(true);

        when(_repository.findByCodigo(funcionario.getCodigo())).thenReturn(Optional.of(funcionario));
        when(_repository.delete(funcionario.getCodigo())).thenReturn(true);

        //Act
        var result = _service.delete(1);

        //Assert
        assertTrue(result);
        verify(_repository, times(1)).delete(funcionario.getCodigo());
    }
}