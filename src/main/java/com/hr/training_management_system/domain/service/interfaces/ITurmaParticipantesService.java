package com.hr.training_management_system.domain.service.interfaces;

import com.hr.training_management_system.application.dto.request.TurmaParticipanteRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaRequestDto;
import com.hr.training_management_system.application.dto.response.TurmaParticipanteResponseDto;
import com.hr.training_management_system.application.dto.response.TurmaResponseDto;

import java.util.List;

public interface ITurmaParticipantesService {
    List<TurmaParticipanteResponseDto> listAll();
    boolean save(TurmaParticipanteRequestDto novaTurma);
    boolean delete(int codigo);

}
