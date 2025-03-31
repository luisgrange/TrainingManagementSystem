package com.hr.training_management_system.data.repository;

import com.hr.training_management_system.domain.enums.Tables;
import com.hr.training_management_system.domain.repository.interfaces.IGenericRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public abstract class GenericRepository<T, IndexType> implements IGenericRepository<T, IndexType> {
    protected JdbcTemplate jdbcTemplate;
    private final String table;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public GenericRepository(String table, JdbcTemplate jdbcTemplate){
        this.table = table;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<T> listAll() {
        String sql = table.equals(Tables.FUNCIONARIOS.getTable()) ?
                ("SELECT * FROM " + table + " ORDER BY nome ASC") :
                ("SELECT * FROM " + table);
        logger.debug(sql);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(getEntityClass()));
    }

    @Override
    public Optional<T> findByCodigo(IndexType codigo) {
        String sql = "SELECT * FROM " + table + " WHERE codigo = ?";
        logger.debug("SELECT * FROM {} WHERE codigo = {}", table, codigo);


        List<T> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(getEntityClass()), codigo);
        return result.stream().findFirst();
    }

    @Override
    public boolean delete(IndexType codigo) {
        String sql = "DELETE FROM " + table + " WHERE codigo = ?";
        logger.debug("DELETE FROM {} WHERE codigo = {}", table, codigo);

        return jdbcTemplate.update(sql, codigo) > 0;
    }

    protected abstract Class<T> getEntityClass();
}
