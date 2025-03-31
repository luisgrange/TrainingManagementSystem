package com.hr.training_management_system.domain.service.interfaces;

import com.hr.training_management_system.application.dto.request.FuncionarioRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaUpdateRequestDto;
import com.hr.training_management_system.application.dto.response.FuncionarioResponseDto;
import com.hr.training_management_system.application.dto.response.TurmaResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITurmaService {
    List<TurmaResponseDto> listAll();
    boolean save(TurmaRequestDto novaTurma);
    boolean delete(int codigo);
    boolean update(int codigo, TurmaUpdateRequestDto turmaAtualizada);
}
