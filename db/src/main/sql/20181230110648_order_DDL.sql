CREATE TABLE `s_order` (
  `id`  int NOT NULL ,
  `user`  int NULL COMMENT '用户id' ,
  `num`  int NULL COMMENT '数量' ,
  `amount`  decimal NULL COMMENT '金额' ,
  `comment`  varchar(200) NULL COMMENT '备注' ,
  PRIMARY KEY (`id`)
)