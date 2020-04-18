package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author : zhouyajun
 * @date : 2020-04-18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanGetRequest {
    private Long userId;

    /**
     * 支持获取多个
     */
    private List<Long> ids;

    public boolean validate() {
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
