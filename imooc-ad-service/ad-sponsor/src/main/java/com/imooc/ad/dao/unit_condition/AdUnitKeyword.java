package com.imooc.ad.dao.unit_condition;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : zhouyajun
 * @date : 2020-04-17
 */
public interface AdUnitKeyword extends JpaRepository<AdUnitKeyword, Long> {

    Long findKeywordByUnitId(Long unitId);
}
