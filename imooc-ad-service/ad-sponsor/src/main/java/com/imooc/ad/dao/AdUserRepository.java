package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Long主键类型
 */
public interface AdUserRepository  extends JpaRepository<AdUser, Long> {

    /**
     * 根据用户名查询用户记录
     * @param username
     * @return
     */
    AdUser findByUsername(String username);

}
