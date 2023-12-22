package com.sia.tacos.repository;

import com.sia.tacos.model.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: JdbcTacoRepository
 * Package: com.sia.tacos.repository
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/22 11:13
 * @Version 1.0
 */
@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcOperations jdbcOperations;

    @Autowired(required = false)
    public JdbcTacoRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    public Taco getById(Long tacoId) {
        return jdbcOperations.queryForObject("select ID, NAME, TACO_ORDER, TACO_ORDER_KEY," +
                "CREATED_AT " +
                "from" +
                " TACO where id=?", this::mapToTaco,tacoId);
    }

    @Override
    public Iterable<Taco> getByOrderId(Long orderId) {
        return jdbcOperations.query("select ID, NAME, TACO_ORDER, TACO_ORDER_KEY," +
                "CREATED_AT " +
                "from" +
                " TACO where TACO_ORDER=?", this::mapToTaco,orderId);
    }

    private Taco mapToTaco(ResultSet row, int rowNum) throws SQLException{
        Taco taco =  new Taco();
        taco.setName(row.getString("NAME"));
        taco.setId(row.getLong("ID"));
        taco.setTacoOrder(row.getLong("TACO_ORDER"));
        taco.setTacoOrderKey(row.getLong("TACO_ORDER_KEY"));
        taco.setCreatedAt(row.getTimestamp("CREATED_AT").toLocalDateTime());
        return taco;
    }
}
