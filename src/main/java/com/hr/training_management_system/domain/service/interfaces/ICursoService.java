package com.hr.training_management_system.domain.service.interfaces;

import com.hr.training_management_system.application.dto.request.CursoRequestDto;
import com.hr.training_management_system.application.dto.response.CursoResponseDTO;

import java.util.List;

public interface ICursoService {
    boolean save(CursoRequestDto novoCurso);
    boolean update(int codigo, CursoRequestDto cursoAtualizado);
    boolean delete(int codigoCurso);
    List<CursoResponseDTO> listAllCursos();
}
