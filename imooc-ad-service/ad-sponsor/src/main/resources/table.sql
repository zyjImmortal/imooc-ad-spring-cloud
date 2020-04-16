
create TABLE `ad_user`(
                        id bigint(20) not null auto_increment comment '用户id',
                        username varchar(128) not null default '' comment '用户名',
                        token varchar(256) not null default '' comment '给用户生成的token',
                        user_status tinyint(4) not null default '0' comment '用户状态',
                        create_time datetime not null default '1970-01-01 00:00:00' comment '创建时间',
                        update_time datetime not null default '1970-01-01 00:00:00' comment '更新时间',
                        primary key (`id`),
                        unique key `username` (`username`)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='用户信息表';

create table `ad_plan`(
                        id bigint(20) not null auto_increment comment '计划id',
                        user_id bigint(20) not null default '0' comment '标记当前记录所属用户',
                        plan_name varchar(48) not null comment '推广计划名称',
                        plat_status tinyint(4) not null default '0' comment '推广计划状态',
                        start_date datetime not null comment '推广计划开始时间',
                        end_date datetime not null comment '推广计划结束时间',
                        create_time datetime not null default '1970-01-01 00:00:00' comment '创建时间',
                        update_time datetime not null default '1970-01-01 00:00:00' comment '更新时间',
                        primary key (`id`)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='推广计划表';

create table ad_unit(
                      id bigint(20) not null auto_increment comment '单元id',
                      plan_id bigint(20) not null default '0' comment '关联推广计划id',
                      unit_name varchar(48) not null comment '推广单元名称',
                      unit_status tinyint(4) not null default '0' comment '推广单元状态',
                      position_type tinyint(4) not null default '0' comment '广告类型(开屏、贴片、中贴、暂停贴、后贴)',
                      budget bigint(20) not null comment '预算',
                      create_time datetime not null default '1970-01-01 00:00:00' comment '创建时间',
                      update_time datetime not null default '1970-01-01 00:00:00' comment '更新时间',
                      primary key (`id`)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='推广单元表';


create table ad_creative(
                          id bigint(20) not null auto_increment comment '创意id',
                          name varchar(48) not null comment '创意名称',
                          type tinyint(4) not null default '0' comment '物料类型(图片、视频)',
                          material_type tinyint(4) not null default '0' comment '物料子类型(图片: bm, jpg等)',
                          height int(10) not null default '0' comment '高度',
                          width int(10) not null default '0' comment '宽度',
                          size bigint(20) not null default '0' comment '物料大小，单位是kb',
                          duratuon int(10) not null default '0' comment '持续时长，只有视频才不为0',
                          audit_status tinyint(4) not null default '0' comment '审核状态',
                          user_id bigint(20) not null default '0' comment '标记当前记录所属的用户',
                          url varchar(256) not null comment '物料地址',
                          create_time datetime not null default '1970-01-01 00:00:00' comment '创建时间',
                          update_time datetime not null default '1970-01-01 00:00:00' comment '更新时间',
                          primary key (`id`)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='创意表';

create table creative_unit (
                             id bigint(20) not null auto_increment,
                             creative_id bigint(20) not null default '0' comment '创意id',
                             unit_id bigint(20) not null default '0' comment '推广单元id',
                             primary key (`id`)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='创意h和推广单元关联表';

create table `ad_unit_keyword` (
                                 id int(11) not null auto_increment,
                                 unit_id int(11) not null comment '推广单元id',
                                 keyword varchar(30) not null comment '关键词',
                                 primary key (id)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='推广单元关键词 Feature';

create table `ad_unit_it` (
                            id int(11) not null auto_increment,
                            unit_id int(11) not null comment '推广单元id',
                            it_tag varchar(30) not null comment '兴趣标签',
                            primary key (id)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='推广单元兴趣 Feature';

create table `ad_unit_district` (
                                  id int(11) not null auto_increment,
                                  unit_id int(11) not null comment '推广单元id',
                                  province varchar(30) not null comment '省',
                                  city varchar(30) not null comment '市',
                                  primary key (id)
)engine=InnoDB auto_increment=10 default charset=utf8 comment='推广单元地域 Feature';