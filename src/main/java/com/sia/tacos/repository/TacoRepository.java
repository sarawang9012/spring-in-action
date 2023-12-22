package com.sia.tacos.repository;

import com.sia.tacos.model.Taco;
import org.springframework.stereotype.Repository;

/**
 * ClassName: TacoRepository
 * Package: com.sia.tacos.repository
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/22 11:12
 * @Version 1.0
 */
@Repository
public interface TacoRepository {
    Taco getById(Long tacoId);
    Iterable<Taco> getByOrderId(Long orderId);

}
