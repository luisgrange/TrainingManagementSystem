package com.hr.training_management_system.domain.service.interfaces;

import com.hr.training_management_system.application.dto.request.FuncionarioRequestDto;
import com.hr.training_management_system.application.dto.response.FuncionarioResponseDto;

import java.util.List;
import java.util.Optional;

public interface IFuncionarioService {
    List<FuncionarioResponseDto> listAll(Optional<Boolean> status);
    boolean save(FuncionarioRequestDto novoFuncionario);
    boolean delete(int codigo);
    boolean update(int codigo, FuncionarioRequestDto funcionario);
}
