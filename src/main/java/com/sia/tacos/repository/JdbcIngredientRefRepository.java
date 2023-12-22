package com.sia.tacos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: JdbcIngredientRefRepository
 * Package: com.sia.tacos.repository
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/22 11:42
 * @Version 1.0
 */
@Repository
public class JdbcIngredientRefRepository implements IngredientRefRepository{
    private final IngredientRepository ingredientRepository;
    private final JdbcOperations jdbcOperations;
    @Autowired(required = false)
    public JdbcIngredientRefRepository(IngredientRepository ingredientRepository,
                                       JdbcOperations jdbcOperations){
        this.ingredientRepository = ingredientRepository;
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    public Iterable<String> getIngredientId(Long tacoId) {
        Iterable<String> ingredientIds = jdbcOperations.query("select ingredient from " +
                "ingredient_ref where taco=? order by TACO_KEY",this::mapIngredientId,tacoId);

        return ingredientIds;
    }

    private String mapIngredientId(ResultSet row, int rowNum) throws SQLException {
        return new String(row.getString("ingredient"));
    }
}
