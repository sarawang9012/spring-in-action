package com.sia.tacos.repository;

import com.sia.tacos.model.Ingredient;
import com.sia.tacos.model.IngredientRef;
import com.sia.tacos.model.Taco;
import com.sia.tacos.model.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: JdbcOrderRepository
 * Package: com.sia.tacos.repository
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/21 10:59
 * @Version 1.0
 */
@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Iterable<TacoOrder> getAll() {
       return jdbcOperations.query("select " +
                        "ID, DELIVERY_NAME,DELIVERY_STREET,DELIVERY_CITY,DELIVERY_STATE," +
                        "DELIVERY_ZIP,CC_NUMBER,CC_EXPIRATION,CC_CVV,PLACED_AT from TACO_ORDER",
                this::mapToTacoOrder);
    }

    private TacoOrder mapToTacoOrder(ResultSet row, int rowNum) throws SQLException {
        TacoOrder order = new TacoOrder ();
        order.setId(row.getLong("id"));
        order.setDeliveryName(row.getString("DELIVERY_NAME"));
        order.setDeliveryStreet(row.getString("DELIVERY_STREET"));
        order.setDeliveryCity(row.getString("DELIVERY_CITY"));
        order.setDeliveryState(row.getString("DELIVERY_STATE"));
        order.setDeliveryZip(row.getString("DELIVERY_ZIP"));
        order.setCcNumber(row.getString("CC_NUMBER"));
        order.setCcExpiration(row.getString("CC_EXPIRATION"));
        order.setCcCVV(row.getString("CC_CVV"));
        order.setPlacedAt(row.getTimestamp("PLACED_AT").toLocalDateTime());
        return order;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco_Order "
                        + "(delivery_name, delivery_street, delivery_city, delivery_state, "
                        + "delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) "
                        + "values (?,?,?,?,?,?,?,?,?)", Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(LocalDateTime.now());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return order;
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(LocalDateTime.now());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into TACO "
                        + "(NAME, CREATED_AT,TACO_ORDER,TACO_ORDER_KEY)"
                        + "values (?,?,?,?)",
                Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                taco.getName(),
                taco.getCreatedAt(),
                orderId,
                orderKey
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredients());

        return tacoId;

    }

    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        int key = 0;
        for (Ingredient ingredient : ingredients) {
            jdbcOperations.update(
                    "insert into INGREDIENT_REF(ingredient, taco, taco_key) values (?,?,?)",
                    ingredient.getId(), tacoId, key++);
        }
    }
}
