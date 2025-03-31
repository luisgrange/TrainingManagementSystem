package com.hr.training_management_system.domain.enums;

public enum Tables {
    CURSOS("Cursos"),
    FUNCIONARIOS("Funcionarios"),
    TURMAS("Turmas"),
    TURMA_PARTICIPANTES("Turma_participantes");

    private final String table;

    Tables(String table) {
        this.table = table;
    }

    public String getTable(){
        return table;
    }
}
