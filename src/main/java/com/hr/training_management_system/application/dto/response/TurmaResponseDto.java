package com.hr.training_management_system.application.dto.response;

import java.time.LocalDate;

public record TurmaResponseDto(int codigo, LocalDate inicio, LocalDate fim, String local, int curso) {
}
