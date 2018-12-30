# 用户
CREATE TABLE `o_user`(
`id` INT(255) AUTO_INCREMENT COMMENT 'ID',
`userName` VARCHAR(255) NOT NULL COMMENT '用户名称',
`tel` VARCHAR(255) COMMENT '手机号码',
`openid` VARCHAR(255) NOT NULL COMMENT '',
`unionid` VARCHAR(255) COMMENT '',
`createTime` datetime COMMENT '创建时间',
`updateTime` datetime COMMENT '更新时间',
`logo` VARCHAR(255) NOT NULL,
`city` VARCHAR(255) NOT NULL,
`sex` VARCHAR(255) NOT NULL,
`version` INT(11) COMMENT '版本',
PRIMARY KEY(`id`)
);

#订单
CREATE TABLE `o_order`(
`id` INT(255) AUTO_INCREMENT COMMENT '订单ID',
`no` VARCHAR(255) NOT NULL COMMENT '系统生成的订单号',
`pay_no` VARCHAR(255) COMMENT '支付平台生成的订单号',
`createTime` datetime NOT NULL COMMENT '订单创建时间',
`payTime` datetime COMMENT '订单支付时间',
`beginTime` datetime COMMENT '订单开始时间',
`endTime` datetime COMMENT '订单结束时间',
`payMoney` DECIMAL(19,2) NOT NULL DEFAULT 0.00 COMMENT '订单实际支付金额',
`orig` DECIMAL(19,2) NOT NULL DEFAULT 0.00 COMMENT '原价',
`discountMoney` DECIMAL(19,2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额',
`payType` INT(5) COMMENT '订单支付方式',
`state` INT(11) NOT NULL COMMENT '订单状态',
`user_id` INT(255) NOT NULL COMMENT '购买用户',
PRIMARY KEY(`id`)
);

#accesstoken
CREATE TABLE `o_access_token`(
`id` INT(255) AUTO_INCREMENT COMMENT '订单ID',
`access_token` VARCHAR(255) NOT NULL COMMENT 'accessToken值',
`expires_in` BIGINT(11) NOT NULL COMMENT '有效时间',
`beginTime` datetime NOT NULL COMMENT '创建更新时间',
PRIMARY KEY(`id`)
);