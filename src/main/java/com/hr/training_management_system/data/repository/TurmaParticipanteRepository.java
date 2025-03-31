package com.hr.training_management_system.data.repository;

import com.hr.training_management_system.domain.enums.Tables;
import com.hr.training_management_system.domain.model.TurmaParticipante;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaParticipanteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class TurmaParticipanteRepository extends GenericRepository<TurmaParticipante, Integer>
implements ITurmaParticipanteRepository {

    public TurmaParticipanteRepository(JdbcTemplate jdbcTemplate){
        super(Tables.TURMA_PARTICIPANTES.getTable(), jdbcTemplate);
    }

    public boolean save(TurmaParticipante turmaParticipante) {
        String sql = "INSERT INTO Turma_participantes (turma, funcionario) " +
                "VALUES (?, ?)";
        var keyHolder = new GeneratedKeyHolder();

        logger.debug("INSERT INTO Turma_participantes (turma, funcionario) VALUES ({}, {})",
                turmaParticipante.getTurma(),
                turmaParticipante.getFuncionario());

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"codigo"});
            ps.setInt(1, turmaParticipante.getTurma());
            ps.setInt(2, turmaParticipante.getFuncionario());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null)
            turmaParticipante.setCodigo(keyHolder.getKey().intValue());

        return true;
    }

    @Override
    public Optional<TurmaParticipante> findByTurma(int codigoTurma) {
        var sql = "SELECT * FROM Turma_participantes WHERE turma = ?";
        logger.debug("SELECT * FROM Turmas WHERE turma = {}", codigoTurma);

        var result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(getEntityClass()), codigoTurma);
        return result.stream().findFirst();
    }

    @Override
    public boolean deleteByTurma(int codigoTurma) {
        var sql = "DELETE FROM Turma_participantes WHERE turma = ?";
        logger.debug("DELETE FROM Turma_participantes WHERE turma = {}", codigoTurma);

        return jdbcTemplate.update(sql, codigoTurma) > 0;
    }

    @Override
    protected Class<TurmaParticipante> getEntityClass() {
        return TurmaParticipante.class;
    }
}
