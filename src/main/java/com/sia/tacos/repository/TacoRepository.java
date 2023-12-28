package com.sia.tacos.repository;

import com.sia.tacos.model.Taco;
import com.sia.tacos.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: TacoRepository
 * Package: com.sia.tacos.repository
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/22 11:12
 * @Version 1.0
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {
//    Iterable<Taco> getByOrderId(Long orderId);

    Iterable<Taco> findByTacoOrder(TacoOrder order);
}
