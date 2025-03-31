package com.hr.training_management_system.data.repository;

import com.hr.training_management_system.domain.enums.Tables;
import com.hr.training_management_system.domain.model.Turma;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class TurmaRepository extends GenericRepository<Turma, Integer>
implements ITurmaRepository {

    public TurmaRepository(JdbcTemplate jdbcTemplate){
        super(Tables.TURMAS.getTable(), jdbcTemplate);
    }

    public boolean save(Turma turma) {
        String sql = "INSERT INTO Turmas (inicio, fim, local, curso) " +
                "VALUES (?, ?, ?, ?)";
        var keyHolder = new GeneratedKeyHolder();

        logger.debug("INSERT INTO Turmas (inicio, fim, local, curso) " +
                        "VALUES ({}, {}, {}, {}})",
                turma.getInicio(),
                turma.getFim(),
                turma.getLocal(),
                turma.getCurso());

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"codigo"});
            ps.setDate(1, Date.valueOf(turma.getInicio()));
            ps.setDate(2, Date.valueOf(turma.getFim()));
            ps.setString(3, turma.getLocal());
            ps.setInt(4, turma.getCurso());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null)
            turma.setCodigo(keyHolder.getKey().intValue());

        return true;
    }

    public boolean update(Turma turma) {
        String sql = "UPDATE Turmas SET inicio = ?, fim = ?, local = ?, curso = ?" +
                " WHERE codigo = ?";

        logger.debug("UPDATE Turmas SET inicio = {}, fim = {}, local = {}, curso = {}" +
                        " WHERE codigo = {}",
                turma.getInicio(),
                turma.getFim(),
                turma.getLocal(),
                turma.getCurso(),
                turma.getCodigo());

        int changedRows = jdbcTemplate.update(
                sql,
                turma.getInicio(),
                turma.getFim(),
                turma.getLocal(),
                turma.getCurso(),
                turma.getCodigo());

        return changedRows > 0;
    }

    @Override
    public Optional<Turma> findByCurso(int codigoCurso) {
        var sql = "SELECT * FROM Turmas WHERE curso = ?";
        logger.debug("SELECT * FROM Turmas WHERE codigo = {}", codigoCurso);

        var result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(getEntityClass()), codigoCurso);
        return result.stream().findFirst();
    }

    @Override
    public boolean deleteByCurso(int codigoCurso) {
        var sql = "DELETE FROM Turmas WHERE curso = ?";
        logger.debug("DELETE FROM Turmas WHERE curso = {}", codigoCurso);

        return jdbcTemplate.update(sql, codigoCurso) > 0;
    }

    @Override
    protected Class<Turma> getEntityClass() {
        return Turma.class;
    }
}
