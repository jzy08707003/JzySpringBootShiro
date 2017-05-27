DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `permission` (`id`, `available`, `name`, `parent_id`, `parent_ids`, `permission`, `resource_type`, `url`) VALUES ('1', '\0', 'UserView', NULL, NULL, 'user:view', NULL, '/user/userList');
INSERT INTO `permission` (`id`, `available`, `name`, `parent_id`, `parent_ids`, `permission`, `resource_type`, `url`) VALUES ('2', '\0', 'UserAdd', NULL, '', 'user:add', '', '/user/userAdd');
INSERT INTO `permission` (`id`, `available`, `name`, `parent_id`, `parent_ids`, `permission`, `resource_type`, `url`) VALUES ('3', '\0', 'ADMIN', NULL, '', 'admin', '', 'ALL');

INSERT INTO `role` (`id`, `available`, `description`, `role`) VALUES ('1', '\0', '查看', 'view');
INSERT INTO `role` (`id`, `available`, `description`, `role`) VALUES ('2', '\0', '添加', 'add');
INSERT INTO `role` (`id`, `available`, `description`, `role`) VALUES ('3', '\0', '超级管理员', 'admin');

INSERT INTO `role_permission` (`permission_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `role_permission` (`permission_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `role_permission` (`permission_id`, `role_id`) VALUES ('3', '3');

INSERT INTO `user` (`id`, `name`, `password`, `salt`, `state`, `username`) VALUES ('1', 'jzy', '096d3a932573c22e8b4f9f72b0e481d7', NULL, '1', 'Jzy');
INSERT INTO `user` (`id`, `name`, `password`, `salt`, `state`, `username`) VALUES ('2', 'add', 'aab236aba3aec51f8cc6ab51b5dd6ef5', NULL, '1', 'add');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '3');
