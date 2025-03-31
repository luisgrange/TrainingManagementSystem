package com.hr.training_management_system.domain.repository.interfaces;

import com.hr.training_management_system.domain.model.Funcionario;

import java.util.List;

public interface IFuncionarioRepository extends IGenericRepository<Funcionario, Integer> {
    boolean save(Funcionario funcionario);
    boolean update(Funcionario funcionario);
    List<Funcionario> findByStatus(Boolean status);
}
