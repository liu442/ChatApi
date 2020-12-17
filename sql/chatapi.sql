/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : chatapi

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-02-18 17:24:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat_history
-- ----------------------------
DROP TABLE IF EXISTS `chat_history`;
CREATE TABLE `chat_history` (
  `id` varchar(32) NOT NULL,
  `my_id` varchar(32) DEFAULT NULL,
  `accept_id` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat_history
-- ----------------------------

-- ----------------------------
-- Table structure for chat_msg
-- ----------------------------
DROP TABLE IF EXISTS `chat_msg`;
CREATE TABLE `chat_msg` (
  `id` varchar(64) NOT NULL,
  `send_user_id` varchar(64) NOT NULL,
  `accept_user_id` varchar(64) NOT NULL,
  `msg` varchar(255) NOT NULL,
  `sign_flag` int(1) NOT NULL DEFAULT '0' COMMENT '消息是否签收状态\r\n1：签收\r\n0：未签收\r\n',
  `create_time` datetime NOT NULL COMMENT '发送请求的事件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of chat_msg
-- ----------------------------
INSERT INTO `chat_msg` VALUES ('48007fc86d4b4d8b90ce52157fa11d0b', '5743c2525cd64be0a0ac0644e80c25d0', 'f3aecf2877ed40738971f95a5481069b', '你好我是八百', '0', '2020-02-03 14:29:44');
INSERT INTO `chat_msg` VALUES ('645c9a5742b645ccb9d984a7c1946644', '5743c2525cd64be0a0ac0644e80c25d0', 'f3aecf2877ed40738971f95a5481069b', '1', '0', '2020-02-13 13:35:49');
INSERT INTO `chat_msg` VALUES ('7f1efe7a222a46389c3662915b7b4301', 'f3aecf2877ed40738971f95a5481069b', '5743c2525cd64be0a0ac0644e80c25d0', '你好我是四月', '0', '2020-02-03 14:29:35');
INSERT INTO `chat_msg` VALUES ('90e0a999bf844ab3a8b6fc6badede17c', 'f3aecf2877ed40738971f95a5481069b', '5743c2525cd64be0a0ac0644e80c25d0', '我们去玩吧', '0', '2020-02-03 14:29:50');
INSERT INTO `chat_msg` VALUES ('a53cede2cf68474eb41c532b86d192da', '5743c2525cd64be0a0ac0644e80c25d0', 'f3aecf2877ed40738971f95a5481069b', '好的记得带上滑板', '0', '2020-02-03 14:30:02');
INSERT INTO `chat_msg` VALUES ('cad1a0f3ccc445148b22e7c3cea50740', 'f3aecf2877ed40738971f95a5481069b', '5743c2525cd64be0a0ac0644e80c25d0', '1', '0', '2020-02-03 14:29:17');
INSERT INTO `chat_msg` VALUES ('d095472aa9b94859ad8af571f2f88fca', 'f3aecf2877ed40738971f95a5481069b', '5743c2525cd64be0a0ac0644e80c25d0', '1', '0', '2020-02-04 11:45:14');
INSERT INTO `chat_msg` VALUES ('d62991bc50d8410b8edfa9e46921eb2b', 'f3aecf2877ed40738971f95a5481069b', '5743c2525cd64be0a0ac0644e80c25d0', '嗯嗯', '0', '2020-02-03 14:30:11');

-- ----------------------------
-- Table structure for friends_request
-- ----------------------------
DROP TABLE IF EXISTS `friends_request`;
CREATE TABLE `friends_request` (
  `id` varchar(64) NOT NULL,
  `send_user_id` varchar(64) NOT NULL,
  `accept_user_id` varchar(64) NOT NULL,
  `request_date_time` datetime NOT NULL COMMENT '发送请求的事件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of friends_request
-- ----------------------------

-- ----------------------------
-- Table structure for friend_circle
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle`;
CREATE TABLE `friend_circle` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '发布者',
  `content` longtext COMMENT '发布内容',
  `image` longtext COMMENT '图片(使用json存储地址)',
  `location` varchar(100) DEFAULT NULL COMMENT '定位',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_circle
-- ----------------------------
INSERT INTO `friend_circle` VALUES ('1984bdf2dd6645e3b1a167f0b82ac3f0', '5743c2525cd64be0a0ac0644e80c25d0', '八百里呢1', null, '合肥', '2020-02-13 13:35:49');
INSERT INTO `friend_circle` VALUES ('4200b8426fcc4abcb7ac8cccd7c60035', 'f3aecf2877ed40738971f95a5481069b', '哈哈哈', null, '合肥', '2020-02-12 16:04:59');
INSERT INTO `friend_circle` VALUES ('613d918ef5bd4a7d96e5d1633ce44f53', '2edb7435eccb458cb62cc26ccbb18e53', '我是小小', null, '合肥', '2020-02-13 13:36:44');
INSERT INTO `friend_circle` VALUES ('9d960658debe4a4d83e2eaace1ef2678', 'f3aecf2877ed40738971f95a5481069b', '朋友送了我个手机，真开心', null, '合肥', '2020-02-12 16:17:49');
INSERT INTO `friend_circle` VALUES ('c9274bb682f64066a38893007c4749b4', 'f3aecf2877ed40738971f95a5481069b', '我又发说说了呢', null, '合肥', '2020-02-12 16:13:20');
INSERT INTO `friend_circle` VALUES ('dbc6d6ad6aa04a5fb4538da9fe0b2f19', '5bfd8ff4e318496ebb8e454e050a8ef9', '我是刘9', null, '合肥', '2020-02-13 14:08:46');
INSERT INTO `friend_circle` VALUES ('fc540141016b4f8e97e5228ae904df93', 'f3aecf2877ed40738971f95a5481069b', '111', null, '合肥', '2020-02-12 16:04:57');

-- ----------------------------
-- Table structure for friend_circle_comment
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_comment`;
CREATE TABLE `friend_circle_comment` (
  `id` varchar(32) NOT NULL,
  `circle_id` varchar(32) DEFAULT NULL COMMENT '发表朋友圈id',
  `comment_id` varchar(32) DEFAULT NULL COMMENT '评论者',
  `content` longtext COMMENT '评论内容',
  `reply_id` varchar(32) DEFAULT NULL COMMENT '回复id',
  `type` int(11) DEFAULT NULL COMMENT '类型(1,点赞,2,评论,3回复)',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend_circle_comment
-- ----------------------------
INSERT INTO `friend_circle_comment` VALUES ('1', '9d960658debe4a4d83e2eaace1ef2678', '2edb7435eccb458cb62cc26ccbb18e53', '是啥手机?', null, '2', '2020-02-14 15:13:38');
INSERT INTO `friend_circle_comment` VALUES ('2', '9d960658debe4a4d83e2eaace1ef2678', '2edb7435eccb458cb62cc26ccbb18e53', null, null, '1', '2020-02-14 12:13:47');
INSERT INTO `friend_circle_comment` VALUES ('3', '9d960658debe4a4d83e2eaace1ef2678', '5743c2525cd64be0a0ac0644e80c25d0', '是小米呢', '2edb7435eccb458cb62cc26ccbb18e53', '3', '2020-02-14 15:13:43');
INSERT INTO `friend_circle_comment` VALUES ('35df8a88434541e3a79846e163868d0c', 'dbc6d6ad6aa04a5fb4538da9fe0b2f19', 'f3aecf2877ed40738971f95a5481069b', '知道啦', null, '2', '2020-02-18 17:19:25');
INSERT INTO `friend_circle_comment` VALUES ('4', '9d960658debe4a4d83e2eaace1ef2678', '5743c2525cd64be0a0ac0644e80c25d0', '', null, '1', '2020-02-14 15:19:29');
INSERT INTO `friend_circle_comment` VALUES ('eeaf7e7963214e888b9a4596a3e63380', 'dbc6d6ad6aa04a5fb4538da9fe0b2f19', 'f3aecf2877ed40738971f95a5481069b', null, null, '1', '2020-02-18 16:31:10');

-- ----------------------------
-- Table structure for my_friends
-- ----------------------------
DROP TABLE IF EXISTS `my_friends`;
CREATE TABLE `my_friends` (
  `my_user_id` varchar(64) NOT NULL COMMENT '用户id',
  `my_friend_user_id` varchar(64) NOT NULL COMMENT '用户的好友id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`my_user_id`,`my_friend_user_id`),
  UNIQUE KEY `my_user_id` (`my_user_id`,`my_friend_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of my_friends
-- ----------------------------
INSERT INTO `my_friends` VALUES ('2edb7435eccb458cb62cc26ccbb18e53', 'f3aecf2877ed40738971f95a5481069b', '2020-02-02 13:56:52');
INSERT INTO `my_friends` VALUES ('48ed1ad84c254575ae43b0f9c9667966', 'f3aecf2877ed40738971f95a5481069b', '2020-02-02 13:56:48');
INSERT INTO `my_friends` VALUES ('5743c2525cd64be0a0ac0644e80c25d0', 'f3aecf2877ed40738971f95a5481069b', '2020-02-02 13:56:45');
INSERT INTO `my_friends` VALUES ('bf5d7ab08a15488aae5eb36392936bbb', 'f3aecf2877ed40738971f95a5481069b', '2020-01-15 13:46:28');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '0228b6b63e2545c7bb6a3ff618a4dbeb', '2020-01-16 13:46:34');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '2edb7435eccb458cb62cc26ccbb18e53', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '33308ba04ac84cf0a8cf00ea029ac4d1', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '33d5f24697814f33b92be4d65a255923', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '3a8b39f912594ef8b7eadba10e62fed3', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '43489b50b7a04a05aad524258b4fb642', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '48ed1ad84c254575ae43b0f9c9667966', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '5743c2525cd64be0a0ac0644e80c25d0', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '5bfd8ff4e318496ebb8e454e050a8ef9', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '68da46777d874a3c8a520ee19d695a33', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '88f2b663eb3d4a7ea78fe05113ea78a5', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', '8f1f219a8967498aad784ec364e0930e', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', 'a86ceed528734634a29750fe7fb50b0e', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', 'bf5d7ab08a15488aae5eb36392936bbb', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', 'e987f329bce94d72aac32933ccc60d3d', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', 'f7a91772d19b4d0383adf067cd9bde0a', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f3aecf2877ed40738971f95a5481069b', 'fe8f5fe791604a98b200d1e892df1761', '2020-01-16 13:46:38');
INSERT INTO `my_friends` VALUES ('f7a91772d19b4d0383adf067cd9bde0a', 'f3aecf2877ed40738971f95a5481069b', '2020-01-17 11:15:31');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '用户名，账号，慕信号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `face_image` varchar(255) NOT NULL COMMENT '我的头像，如果没有默认给一张',
  `face_image_big` varchar(255) NOT NULL,
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
  `qrcode` varchar(255) NOT NULL COMMENT '新用户注册后默认后台生成二维码，并且上传到fastdfs',
  `cid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0228b6b63e2545c7bb6a3ff618a4dbeb', 'liu@1', null, '602123fc74694f7d8063f9889c47a9a7', '1', '1', '醉里挑灯看剑', '1', '1', '2020-01-01 13:58:11');
INSERT INTO `user` VALUES ('2edb7435eccb458cb62cc26ccbb18e53', 'huang', null, 'e7fa8ff3faef04e9dab651d9f0a4c5bc', '2', '2', '小小', '1', '1', '2020-01-02 13:58:17');
INSERT INTO `user` VALUES ('33308ba04ac84cf0a8cf00ea029ac4d1', 'liu@2', null, 'f2c481d38b8865144e3b87bee6c2e69b', '1', '1', '梦回吹角连营', '1', '1', '2020-01-01 13:58:22');
INSERT INTO `user` VALUES ('33d5f24697814f33b92be4d65a255923', 'liu@8', null, '16de826b4492a814db35029d9ffd374f', '1', '1', '弓如霹雳弦惊', '1', '1', '2020-01-01 13:58:22');
INSERT INTO `user` VALUES ('3a8b39f912594ef8b7eadba10e62fed3', 'liu@7', null, '34eef2cb71ccbe043c93e5df17b3c9b3', '1', '1', '马作的卢飞快', '1', '1', '2020-01-03 13:58:22');
INSERT INTO `user` VALUES ('43489b50b7a04a05aad524258b4fb642', 'liu@17', null, 'ccd65ea572d2521abe9668e73c4f705b', '1', '1', '月落乌啼霜满天', '1', '1', '2020-01-04 13:58:22');
INSERT INTO `user` VALUES ('48ed1ad84c254575ae43b0f9c9667966', 'test', null, 'e6cf41b91d60d3c6a4970ee8ef3c53b4', '1', '1', '夜半钟声到客船', '1', '1', '2020-01-05 13:58:22');
INSERT INTO `user` VALUES ('5743c2525cd64be0a0ac0644e80c25d0', 'liu@3', null, 'e42ae57d4a1b45a027ba9bf7a1f1b266', '1', '1', '八百里分麾下炙', '1', '1', '2020-01-06 13:58:22');
INSERT INTO `user` VALUES ('5bfd8ff4e318496ebb8e454e050a8ef9', 'liu@9', null, 'dd6af7109d24fca9d264e79281772c84', '1', '1', '了却君王天下事', '1', '1', '2020-01-07 13:58:22');
INSERT INTO `user` VALUES ('68da46777d874a3c8a520ee19d695a33', 'liu@6', null, '8d0d4bbfeca034ca55bacf26c35c660c', '1', '1', '沙场秋点兵', '1', '1', '2020-01-08 13:58:22');
INSERT INTO `user` VALUES ('88f2b663eb3d4a7ea78fe05113ea78a5', 'liu@15', null, '517d34386c1c516d32d3c8e3a7d4dc23', '1', '1', '三十功名尘与土', '1', '1', '2020-01-09 13:58:22');
INSERT INTO `user` VALUES ('8f1f219a8967498aad784ec364e0930e', 'liu@16', null, '79e439a23d8b6701140a1ec0a22d33c6', '1', '1', '八千里路云和月', '1', '1', '2020-01-10 13:58:22');
INSERT INTO `user` VALUES ('a86ceed528734634a29750fe7fb50b0e', 'liu@10', null, '25e759c8d7497ea79bd97bd5fb20c57e', '1', '1', '赢得生前身后名', '1', '1', '2020-01-11 13:58:22');
INSERT INTO `user` VALUES ('bf5d7ab08a15488aae5eb36392936bbb', 'liu@18', null, '0414bb9e7f760f4cc182a71bae81d08b', '1', '1', '江枫渔火对愁眠', '1', '1', '2020-01-01 13:58:22');
INSERT INTO `user` VALUES ('e987f329bce94d72aac32933ccc60d3d', 'liu@19', null, '37d08e18bf61a70930208710d4dbf1a2', '1', '1', '姑苏城外寒山寺', '1', '1', '2020-01-12 13:58:22');
INSERT INTO `user` VALUES ('f3aecf2877ed40738971f95a5481069b', 'liu', null, '6daf8b69c83cbee0d162e4950c69d3d5', '1', '1', '四月是你的谎言', '1', '1', '2020-01-12 13:58:22');
INSERT INTO `user` VALUES ('f7a91772d19b4d0383adf067cd9bde0a', 'zhang', null, 'f55fedd1523820d1779322bbb75c54ab', '1', '1', '五十弦翻塞外声', '1', '1', '2020-01-01 13:58:22');
INSERT INTO `user` VALUES ('fe8f5fe791604a98b200d1e892df1761', 'liu@12', null, 'ea557c7af3cf7704bdca7fed3e97eabc', '1', '1', '可怜白发生', '1', '1', '2020-01-01 13:58:22');
