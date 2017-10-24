DROP TABLE IF EXISTS `accident_mirror`;
CREATE TABLE `accident_mirror` (
  `id` varchar(28) NOT NULL,
  `event_name` varchar(64) NOT NULL,
  `recover_context` blob NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `happened_time` datetime DEFAULT NULL,
  `process_time` datetime DEFAULT NULL,
  `close_time` datetime DEFAULT NULL,
  `process_user` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;