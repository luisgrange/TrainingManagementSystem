package com.hr.training_management_system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
    public int  codigo;
    public LocalDate inicio;
    public LocalDate fim;
    public String local;

    public int  curso; //sugestão de melhoria: utilizar nomes mais descritivos


    public void updateData(LocalDate inicio, LocalDate fim, String local){
        this.inicio = inicio;
        this.fim = fim;
        this.local   = local;
    }

    public boolean turmaAtiva(){
        return fim.isAfter(LocalDate.now());
    }
}
