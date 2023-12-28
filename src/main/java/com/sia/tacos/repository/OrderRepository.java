package com.sia.tacos.repository;

import com.sia.tacos.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ClassName: OrderRepository
 * Package: com.sia.tacos.repository
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/21 10:51
 * @Version 1.0
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
