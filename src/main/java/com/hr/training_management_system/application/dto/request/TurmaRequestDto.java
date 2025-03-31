package com.hr.training_management_system.application.dto.request;

import java.time.LocalDate;

public record TurmaRequestDto (LocalDate inicio, LocalDate fim, String local, int curso) {
}
