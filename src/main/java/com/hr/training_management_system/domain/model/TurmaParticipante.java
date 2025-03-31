package com.hr.training_management_system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurmaParticipante {
    public int  codigo;
    public int  turma;
    public int  funcionario;
}
