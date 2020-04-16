package com.imooc.ad.entity.unit_condition;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad_unit_creative")
public class CreativeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Column(name = "creative_id", nullable = false)
    private Long creativeId;

    public CreativeUnit(Long creativeId, Long unitId){
        this.creativeId = creativeId;
        this.unitId = unitId;
    }


}
