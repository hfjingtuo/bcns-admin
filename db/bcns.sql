SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS chart;
DROP TABLE IF EXISTS loans_info;
DROP TABLE IF EXISTS operation;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS workflow_info;
DROP TABLE IF EXISTS workflow_order;




/* Create Tables */

CREATE TABLE chart
(
	-- id
	id varchar(32) COMMENT 'id',
	-- 图标名称
	name varchar(50) COMMENT '图标名称',
	value text
);


CREATE TABLE loans_info
(
	id char(32) NOT NULL,
	user_id varchar(100),
	uscc varchar(100),
	company varchar(100),
	corporation varchar(50),
	workflow_id char(32),
	money varchar(50),
	deadline varchar(20),
	num int(4),
	workflow_size int(4),
	process_id char(32),
	-- 备注
	remarks varchar(255) COMMENT '备注',
	-- 创建者
	create_by char(32) COMMENT '创建者',
	-- 创建时间
	create_date timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	-- 更新者
	update_by char(32) COMMENT '更新者',
	-- 更新时间
	update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	-- 删除标志
	del_flag boolean COMMENT '删除标志',
	service_id varchar(32),
	PRIMARY KEY (id)
);


CREATE TABLE operation
(
	id char(32) NOT NULL,
	process_id varchar(32),
	workflow_id char(32),
	service_id varchar(32),
	-- 该用户的唯一标示.
	user_id varchar(100) COMMENT '该用户的唯一标示.',
	-- 用户名称
	user_name varchar(100) COMMENT '用户名称',
	reporter varchar(255),
	timestamp timestamp,
	-- 备注
	remarks varchar(255) COMMENT '备注',
	-- 创建者
	create_by char(32) COMMENT '创建者',
	-- 创建时间
	create_date timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	-- 更新者
	update_by char(32) COMMENT '更新者',
	-- 更新时间
	update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	-- 删除标志
	del_flag boolean COMMENT '删除标志',
	description varchar(255),
	code varchar(100),
	PRIMARY KEY (id)
);


CREATE TABLE service
(
	-- 唯一id
	id char(32) NOT NULL COMMENT '唯一id',
	ip varchar(50),
	name varchar(255),
	url varchar(255),
	service varchar(255),
	version varchar(100),
	vendor varchar(255),
	description varchar(255),
	request text,
	response text,
	-- 备注
	remarks varchar(255) COMMENT '备注',
	-- 创建者
	create_by char(32) COMMENT '创建者',
	-- 创建时间
	create_date timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	-- 更新者
	update_by char(32) COMMENT '更新者',
	-- 更新时间
	update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	-- 删除标志
	del_flag boolean COMMENT '删除标志',
	PRIMARY KEY (id)
);


CREATE TABLE user_info
(
	-- id
	id varchar(32) NOT NULL COMMENT 'id',
	-- 用户名称
	user_name varchar(100) NOT NULL COMMENT '用户名称',
	-- 密码
	password varchar(100) NOT NULL COMMENT '密码',
	-- 备注
	remarks varchar(255) COMMENT '备注',
	-- 创建者
	create_by char(32) COMMENT '创建者',
	-- 创建时间
	create_date timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	-- 更新者
	update_by char(32) COMMENT '更新者',
	-- 更新时间
	update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	-- 删除标志
	del_flag boolean COMMENT '删除标志',
	PRIMARY KEY (id)
);


CREATE TABLE workflow_info
(
	id char(32) NOT NULL,
	name varchar(100),
	vendor varchar(255),
	audit varchar(100),
	description varchar(255),
	-- 备注
	remarks varchar(255) COMMENT '备注',
	-- 创建者
	create_by char(32) COMMENT '创建者',
	-- 创建时间
	create_date timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	-- 更新者
	update_by char(32) COMMENT '更新者',
	-- 更新时间
	update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	-- 删除标志
	del_flag boolean COMMENT '删除标志',
	PRIMARY KEY (id)
);


CREATE TABLE workflow_order
(
	id char(32) NOT NULL,
	workflow_id varchar(32),
	num int(4),
	service_id varchar(32),
	description varchar(255),
	PRIMARY KEY (id)
);



