package com.imooc.ad.dao.unit_condition;

import com.imooc.ad.entity.unit_condition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : zhouyajun
 * @date : 2020-04-17
 */
public interface AdUnitKeywordRepository extends JpaRepository<AdUnitKeyword, Long> {

    Long findKeywordByUnitId(Long unitId);
}
