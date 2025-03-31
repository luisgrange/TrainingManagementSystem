package com.hr.training_management_system.application.dto.response;

import java.time.LocalDate;

public record FuncionarioResponseDto(int codigo, String nome, String cpf, LocalDate nascimento,
                                     String cargo, LocalDate admissao, Boolean status) {
}
