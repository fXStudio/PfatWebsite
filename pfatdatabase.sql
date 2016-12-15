/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.24 : Database - pfatdatabase
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pfatdatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pfatdatabase`;

/*Table structure for table `os_dept` */

DROP TABLE IF EXISTS `os_dept`;

CREATE TABLE `os_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(30) NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级部门',
  `dept_type` char(1) NOT NULL DEFAULT '' COMMENT '部门类型  1: 部/处  2： 办公室',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `dept_name` (`dept_name`),
  UNIQUE KEY `dept_name_2` (`dept_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `os_dept` */

insert  into `os_dept`(`id`,`dept_name`,`parent_id`,`dept_type`,`remark`) values (1,'总部',NULL,'1','最高层级'),(2,'部门1',1,'2','职能部门'),(3,'部门2',1,'2','职能部门'),(5,'部门3',1,'2','职能部门'),(6,'部门4',1,'2','职能部门'),(7,'部门5',1,'2','职能部门'),(8,'部门6',1,'2','职能部门'),(9,'部门7',1,'2','职能部门'),(10,'部门8',1,'2','职能部门'),(12,'部门9',1,'2','职能部门');

/*Table structure for table `os_dictionary` */

DROP TABLE IF EXISTS `os_dictionary`;

CREATE TABLE `os_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(30) NOT NULL DEFAULT '' COMMENT '字典项名称',
  `item_val` varchar(255) NOT NULL DEFAULT '' COMMENT '字典项数值',
  `remark` varchar(255) DEFAULT NULL COMMENT '字典项描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统数据字典';

/*Data for the table `os_dictionary` */

/*Table structure for table `os_groupmenu` */

DROP TABLE IF EXISTS `os_groupmenu`;

CREATE TABLE `os_groupmenu` (
  `usergroupid` int(11) NOT NULL COMMENT '用户组ID',
  `menuid` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`usergroupid`,`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组合菜单的对应关系';

/*Data for the table `os_groupmenu` */

insert  into `os_groupmenu`(`usergroupid`,`menuid`) values (1,1),(4,3);

/*Table structure for table `os_menu` */

DROP TABLE IF EXISTS `os_menu`;

CREATE TABLE `os_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(30) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='菜单信息';

/*Data for the table `os_menu` */

insert  into `os_menu`(`id`,`menu_name`,`remark`) values (1,'系统管理','用户管理、组管理、菜单管理、菜单项管理'),(3,'基础数据管理','管理系统内的基础数据。包括：数据字典、部门、绩效分类信息');

/*Table structure for table `os_menu_menuitem` */

DROP TABLE IF EXISTS `os_menu_menuitem`;

CREATE TABLE `os_menu_menuitem` (
  `menuid` int(11) NOT NULL COMMENT '菜单ID',
  `itemid` int(11) NOT NULL COMMENT '菜单项id',
  PRIMARY KEY (`menuid`,`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单和菜单项的对应关系';

/*Data for the table `os_menu_menuitem` */

insert  into `os_menu_menuitem`(`menuid`,`itemid`) values (1,1),(1,2),(1,3),(1,4),(3,32),(3,33);

/*Table structure for table `os_menuitem` */

DROP TABLE IF EXISTS `os_menuitem`;

CREATE TABLE `os_menuitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(30) NOT NULL DEFAULT '' COMMENT '菜单项名称',
  `item_path` varchar(255) NOT NULL COMMENT '菜单项访问地址',
  `islock` char(1) DEFAULT '0' COMMENT '菜单项是否锁定 1: 锁定 0: 不锁定\r\n锁定的菜单项，用户无法访问',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='菜单项信息';

/*Data for the table `os_menuitem` */

insert  into `os_menuitem`(`id`,`item_name`,`item_path`,`islock`) values (1,'用户管理','systemUser',NULL),(2,'用户组管理','userGroup',NULL),(3,'菜单管理','menu',NULL),(4,'菜单项管理','menuItem','0'),(32,'数据字典管理','dataDictionary','0'),(33,'部门信息维护','deptManage','0');

/*Table structure for table `os_user` */

DROP TABLE IF EXISTS `os_user`;

CREATE TABLE `os_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名称',
  `pass_word` varchar(32) NOT NULL DEFAULT '' COMMENT '用户密码',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dept_id` int(11) DEFAULT NULL COMMENT '用户所属部门, 决定用户能看到哪些数据',
  `islock` char(1) DEFAULT '0' COMMENT '用户是否锁定 1: 锁定 0: 不锁定\r\n锁定用户无法登陆系统',
  `group_id` int(11) DEFAULT NULL COMMENT '所属用户组ID, 决定用户能访问哪些菜单',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `os_user` */

insert  into `os_user`(`id`,`user_name`,`pass_word`,`created`,`dept_id`,`islock`,`group_id`) values (1,'admin','0cc175b9c0f1b6a831c399e269772661','2016-12-16 00:09:38',1,'0',1),(7,'werwer','0cc175b9c0f1b6a831c399e269772661','2016-12-16 01:11:28',2,'0',4);

/*Table structure for table `os_usergroup` */

DROP TABLE IF EXISTS `os_usergroup`;

CREATE TABLE `os_usergroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户组名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '用户组描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `os_usergroup` */

insert  into `os_usergroup`(`id`,`group_name`,`remark`) values (1,'系统管理员','用户系统的完全控制权限'),(4,'普通用户','数据维护，查询等基础操作');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
