package com.hr.training_management_system.application.dto.response;

import java.time.LocalDate;

public record TurmaParticipanteResponseDto(int codigo, int codigoTurma, int codigoFuncionario, String nomeFuncionario, Boolean cursoAtivo) {
}
