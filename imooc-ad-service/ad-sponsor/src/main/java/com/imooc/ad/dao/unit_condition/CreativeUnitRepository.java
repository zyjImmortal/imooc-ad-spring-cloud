package com.imooc.ad.dao.unit_condition;

import com.imooc.ad.entity.unit_condition.CreativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : zhouyajun
 * @date : 2020-04-17
 */
public interface CreativeUnitRepository extends JpaRepository<CreativeUnit, Long> {
    List<Long> findCreativeByUnitId(Long unitId);

    List<Long> findUnitByCreativeId(Long creativeId);
}
