package com.hr.training_management_system.application.dto.request;

import java.time.LocalDate;

public record FuncionarioRequestDto(String nome, String cpf, LocalDate nascimento,
                                    String cargo, LocalDate admissao, Boolean status) {
}
