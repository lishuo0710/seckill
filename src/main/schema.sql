CREATE DATABASE  seckill;


CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT  COMMENT'商品库存id',
`name` varchar(120) NOT null COMMENT'商品名称',
`number` INT NOT NULL COMMENT'库存',
`start_time` TIMESTAMP NOT NULL COMMENT'秒杀开始时间',
`end_time` TIMESTAMP NOT NULL COMMENT'秒杀结束时间',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'秒杀创建时间',
PRIMARY KEY (seckill_Id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT '秒杀库存表';

--初始化数据

INSERT into seckill(name,number,start_time,end_time)
VALUES
  ('1000元秒杀iphoneX',100,'2017-10-11 00:00:00','2017-10-12 00:00:00'),
  ('100元秒杀小米5',99,'2017-10-11 00:00:00','2017-10-12 00:00:00'),
  ('500元秒杀联想笔记本',200,'2017-10-11 00:00:00','2017-10-12 00:00:00'),
  ('666元秒杀夏威夷7日游',300,'2017-10-11 00:00:00','2017-10-12 00:00:00'),
  ('500元秒杀电饭煲',500,'2017-10-11 00:00:00','2017-10-12 00:00:00'),
  ('1元秒杀京东会员',20,'2017-10-11 00:00:00','2017-10-12 00:00:00');

--创建秒杀明细表
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT'明细表id',
`user_phone` bigint NOT NULL COMMENT'用户手机',
`state` tinyint NOT NULL DEFAULT -1 COMMENT'-1:无效，0：成功，1：已付款',
`create_time` TIMESTAMP NOT NULL COMMENT'创建时间',
PRIMARY KEY (seckill_id,user_phone),
KEY idx_create_time(create_time)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT '秒杀明细表';