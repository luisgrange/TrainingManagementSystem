package com.hr.training_management_system.domain.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface IGenericRepository<T, IndexType> {
    List<T> listAll();
    Optional<T> findByCodigo(IndexType codigo);
    boolean delete(IndexType codigo);
}
