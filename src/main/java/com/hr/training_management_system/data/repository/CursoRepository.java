package com.hr.training_management_system.data.repository;

import com.hr.training_management_system.domain.enums.Tables;
import com.hr.training_management_system.domain.repository.interfaces.ICursoRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import com.hr.training_management_system.domain.model.Curso;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class CursoRepository extends GenericRepository<Curso, Integer>
        implements ICursoRepository {

    public CursoRepository(JdbcTemplate jdbcTemplate) {
        super(Tables.CURSOS.getTable(), jdbcTemplate);
    }

    public boolean save(Curso curso) {
            var sql = "INSERT INTO Cursos (nome, descricao, duracao) VALUES (?, ?, ?)";
            var keyHolder = new GeneratedKeyHolder();

            logger.debug("INSERT INTO Cursos (nome, descricao, duracao) VALUES ({}, {}, {})",
                    curso.getNome(), curso.getDescricao(), curso.getDuracao());

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"codigo"});
                ps.setString(1, curso.getNome());
                ps.setString(2, curso.getDescricao());
                ps.setInt(3, curso.getDuracao());
                return ps;
            }, keyHolder);

            curso.setCodigo(Objects.requireNonNull(keyHolder.getKey()).intValue());
            return true;
    }

    @Override
    public boolean update(Curso curso) {
        var sql = "UPDATE Cursos SET nome = ?, descricao = ?, duracao = ? WHERE codigo = ?";

        logger.debug("UPDATE Cursos SET nome = {}, descricao = {}, duracao = {} WHERE codigo = {}"
                , curso.getNome(), curso.getDescricao(), curso.getDuracao(), curso.getCodigo());

        var changedRows = jdbcTemplate.update(
                sql,
                curso.getNome(),
                curso.getDescricao(),
                curso.getDuracao(),
                curso.getCodigo());

        return changedRows > 0;
    }

    @Override
    protected Class<Curso> getEntityClass() {
        return Curso.class;
    }
}
