create table user
(
    user_id bigint primary key comment '用户id',
    username varchar(50) default '未知' comment '用户名称',
    phone varchar(100) not null comment '电话',
    password varchar(512) not null comment '密码',
    avatar text not null comment '如果用户传入头像，则用用户的；如果没传，则默认',
    description varchar(512) default '该用户比较懒，未介绍' comment '用户介绍',
    roles tinyint default 0 comment '普通用户是0，管理员是1',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP ON UPDATE current_timestamp comment '修改时间',
    is_delete tinyint default 0 comment '逻辑删除字段：0 未删除，1 删除'
)engine = innodb default charset = utf8mb4 comment '用户表';

create table product
(
    product_id bigint primary key comment '商品id',
    product_name varchar(50) comment '商品名称',
    product_picture text not null comment '如果上传了图片，则使用；如果没传，则默认',
    product_des varchar(1000) comment '商品介绍',
    product_price varchar(200) comment '商品价格',
    product_address varchar(100) comment '商品产地',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP ON UPDATE current_timestamp comment '修改时间',
    is_delete tinyint default 0 comment '逻辑删除字段：0 未删除，1 删除'
)engine = innodb default charset = utf8mb4 comment '商品表';

create table assess
(
    assess_id bigint primary key comment '评价id',
    user_id bigint not null comment '用户id',
    assess_content varchar(200) default '该商品暂无评价' comment '评价内容',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP ON UPDATE current_timestamp comment '修改时间',
    is_delete tinyint default 0 comment '逻辑删除字段：0 未删除，1 删除'
)engine = innodb default charset = utf8mb4 comment '评价表';

create table product_assess
(
    record_id bigint primary key comment '商品和评价的中间表id',
    product_id bigint not null comment '商品id',
    assess_id bigint not null comment '评价id',
    assess_content varchar(200) default '该商品暂无评价' comment '评价内容',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP ON UPDATE current_timestamp comment '修改时间',
    is_delete tinyint default 0 comment '逻辑删除字段：0 未删除，1 删除'
)engine = innodb default charset = utf8mb4 comment '商品和评价的中间表';