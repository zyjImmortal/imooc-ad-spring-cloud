package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zhouyajun
 * @date : 2020-04-18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanResponse {
    private Long id;
    private String planName;
}
