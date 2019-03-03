CREATE TABLE `md_rpc_server` (
  `id`               INT         NOT NULL AUTO_INCREMENT
  COMMENT '主键id',
  `ip`               CHAR(16)    NOT NULL,
  `rpc_port`         INT         NOT NULL,
  `status`           TINYINT     NOT NULL
  COMMENT '服务器状态：0启动；1停止；',
  `start_time`       DATETIME    NOT NULL
  COMMENT '启动时间',
  `last_update_time` TIMESTAMP   NOT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后更新时间',
  `instance_id`      VARCHAR(20) NULL,
  PRIMARY KEY (`id`)
)
/

