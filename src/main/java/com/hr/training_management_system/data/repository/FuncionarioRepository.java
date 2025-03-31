package com.hr.training_management_system.data.repository;

import com.hr.training_management_system.domain.enums.Tables;
import com.hr.training_management_system.domain.model.Funcionario;
import com.hr.training_management_system.domain.repository.interfaces.IFuncionarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class FuncionarioRepository extends GenericRepository<Funcionario, Integer>
implements IFuncionarioRepository {

    public FuncionarioRepository(JdbcTemplate jdbcTemplate){
        super(Tables.FUNCIONARIOS.getTable(), jdbcTemplate);
    }

    public boolean save(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionarios (nome, cpf, nascimento, cargo, admissao, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        var keyHolder = new GeneratedKeyHolder();

        logger.debug("INSERT INTO Funcionarios (nome, cpf, nascimento, cargo, admissao, status) " +
                "VALUES ({}, {}, {}, {}, {}, {})",
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getNascimento(),
                funcionario.getCargo(),
                funcionario.getAdmissao(),
                funcionario.getStatus());

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"codigo"});
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDate(3, Date.valueOf(funcionario.getNascimento()));
            ps.setString(4, funcionario.getCargo());
            ps.setDate(5, Date.valueOf(funcionario.getAdmissao()));
            ps.setBoolean(6, funcionario.getStatus());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null)
            funcionario.setCodigo(keyHolder.getKey().intValue());

        return true;
    }

    public boolean update(Funcionario funcionario) {
        String sql = "UPDATE Funcionarios SET nome = ?, cpf = ?, nascimento = ?, cargo = ?, admissao = ?, status = ?" +
                " WHERE codigo = ?";

        logger.debug("UPDATE Funcionarios SET nome = {}, cpf = {}, nascimento = {}, cargo = {}, admissao = {}, status = {}" +
                        " WHERE codigo = {}",
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getNascimento(),
                funcionario.getCargo(),
                funcionario.getAdmissao(),
                funcionario.getStatus(),
                funcionario.getCodigo());

        int changedRows = jdbcTemplate.update(
                sql,
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getNascimento(),
                funcionario.getCargo(),
                funcionario.getAdmissao(),
                funcionario.getStatus(),
                funcionario.getCodigo());

        return changedRows > 0;
    }

    @Override
    public List<Funcionario> findByStatus(Boolean status) {
        String sql = "SELECT * FROM Funcionarios WHERE status = ? ORDER BY nome ASC";

        logger.debug("SELECT * FROM Funcionarios WHERE status = {} ORDER BY nome ASC",
                status);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(getEntityClass()), status);
    }

    @Override
    protected Class<Funcionario> getEntityClass() {
        return Funcionario.class;
    }
}
