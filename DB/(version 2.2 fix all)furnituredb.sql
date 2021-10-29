-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.16-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for furnituredb
CREATE DATABASE IF NOT EXISTS `furnituredb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `furnituredb`;

-- Dumping structure for table furnituredb.category
CREATE TABLE IF NOT EXISTS `category` (
  `cate_id` int(11) NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cate_icon` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cate_description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.category: ~4 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`cate_id`, `cate_name`, `cate_icon`, `cate_description`) VALUES
	(1, 'Decorations', 'decorations.png', 'Gallery of Decorations'),
	(2, 'Sofas', 'sofas.png', 'Gallery of Best Sofas.'),
	(3, 'Chairs', 'chairs.png', 'Gallery of Chairs.'),
	(4, 'Tables', 'tables.png', 'Gallery of  Tables.');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table furnituredb.contact
CREATE TABLE IF NOT EXISTS `contact` (
  `cont_id` int(11) NOT NULL AUTO_INCREMENT,
  `cont_fullname` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cont_email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cont_company` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cont_phone` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cont_address` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cont_comment` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `cont_created_at` timestamp NULL DEFAULT current_timestamp(),
  `cont_status` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`cont_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.contact: ~4 rows (approximately)
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`cont_id`, `cont_fullname`, `cont_email`, `cont_company`, `cont_phone`, `cont_address`, `cont_comment`, `cont_created_at`, `cont_status`) VALUES
	(1, 'Chau Duy Anh', 'duyanh@gmail.com', 'HOME', '0889993839', 'Ninh Kieu - TPCT', 'Contact delivery my goods', '2021-03-16 14:04:43', b'1'),
	(2, 'Chau Duy Anh', 'cdanh@gmail.com', 'Home', '088 999 38 39', 'An Khanh, Ninh Kieu, TP. Can Tho', 'Can tu van mua tron bon cho phong khach !', '2021-04-20 00:08:38', b'1'),
	(3, 'Do Quang Minh', 'quangminh@gmail.com', 'CUSC', '0123 456 777', 'Cai Rang - TP. Can Tho', 'Tu van viec lam doi tac, phan phoi hang cho cac tinh lan can!', '2021-04-20 00:38:57', b'1'),
	(4, 'Tran Nhu Duy', 'nhuduy@gmail.com', 'Home', '0123 456 666', 'Ba Lang, Cai Rang, TPCT', 'Can mua hang so luong lon, bao gia si cac mat hang Sofa!', '2021-04-20 00:41:01', b'0');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;

-- Dumping structure for table furnituredb.feedback
CREATE TABLE IF NOT EXISTS `feedback` (
  `feed_id` int(11) NOT NULL AUTO_INCREMENT,
  `feed_title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `feed_created_at` timestamp NULL DEFAULT current_timestamp(),
  `feed_status` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`feed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.feedback: ~2 rows (approximately)
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` (`feed_id`, `feed_title`, `fullname`, `email`, `phone`, `content`, `feed_created_at`, `feed_status`) VALUES
	(1, 'Path of image on server!', 'Chau Duy Anh', 'cdanh@gmail.com', '0889993839', 'Dear admin,\r\nWhen upload image files howto change path on server.\r\nThanks!', '2021-03-22 00:52:14', b'1'),
	(2, 'UI/UX index', 'Duy Anh', 'duyanh@gmail.com', '088 999 38 39', 'Cach chon san pham, dat hang, thanh toan, phuong, thuc thanh toan...', '2021-04-20 01:15:48', b'0');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;

-- Dumping structure for table furnituredb.news
CREATE TABLE IF NOT EXISTS `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `news_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_intro` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_content` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `news_image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `news_status` bit(1) NOT NULL DEFAULT b'0',
  `news_created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.news: ~5 rows (approximately)
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` (`news_id`, `news_title`, `news_intro`, `news_content`, `news_image`, `news_status`, `news_created_at`) VALUES
	(1, 'Furniture gallery', 'These room decorating ideas are amazing', 'Green makes the space looks fresh and comfortable.', 'slide-img1.jpg', b'1', '2021-03-04 07:50:56'),
	(2, 'LIVING ROOM IDEAS', 'Bright and cozy', 'Warm and inspirational atmosphere!', 'slide-img2.jpg', b'1', '2021-03-04 07:50:56'),
	(3, 'LIVING ROOM VIEWS', 'Warm and beautiful', 'Fresh looking and chilling atmosphere!', 'living_room_view.jpg', b'1', '2021-03-15 08:07:27'),
	(4, 'LIVING ROOM LOOKING', 'Dark but attractive', 'Love this dark but mysterious look ?', 'living_room.png', b'1', '2021-03-15 07:59:47'),
	(5, 'Living room 2', 'Living room 2', 'Living room 2', 'living_room_view2.jpg', b'0', '2021-04-28 22:26:58');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;

-- Dumping structure for table furnituredb.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_id` int(11) DEFAULT 1,
  `user_id` int(11) NOT NULL,
  `prod_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `price` decimal(12,2) NOT NULL DEFAULT 0.00,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `order_status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'waiting',
  `tran_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_order_detail_pay_method` (`pay_id`),
  KEY `FK_order_detail_product` (`prod_id`),
  KEY `FK_order_detail_user` (`user_id`),
  CONSTRAINT `FK_order_detail_pay_method` FOREIGN KEY (`pay_id`) REFERENCES `pay_method` (`pay_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_order_detail_product` FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_order_detail_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.order_detail: ~33 rows (approximately)
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`id`, `pay_id`, `user_id`, `prod_id`, `created_date`, `price`, `quantity`, `order_status`, `tran_id`) VALUES
	(14, 1, 4, 1, '2021-04-18 00:00:00', 45.00, 1, 'pending', 17),
	(15, 1, 4, 2, '2021-04-18 00:00:00', 89.00, 1, 'pending', 18),
	(16, 1, 4, 1, '2021-04-18 00:00:00', 45.00, 1, 'pending', 19),
	(17, 1, 4, 1, '2021-04-18 00:00:00', 45.00, 1, 'pending', 20),
	(18, 1, 4, 2, '2021-04-18 00:00:00', 89.00, 2, 'pending', 20),
	(23, 1, 4, 1, '2021-04-19 00:00:00', 45.00, 3, 'pending', 23),
	(24, 1, 4, 2, '2021-04-19 00:00:00', 89.00, 2, 'pending', 23),
	(25, 1, 4, 1, '2021-04-19 00:00:00', 45.00, 2, 'pending', 24),
	(27, 1, 4, 2, '2021-04-19 00:00:00', 89.00, 5, 'pending', 24),
	(46, 1, 5, 2, '2021-04-19 00:00:00', 89.00, 4, 'pending', 27),
	(47, 1, 5, 3, '2021-04-19 00:00:00', 59.00, 1, 'pending', 27),
	(48, 1, 1, 2, '2021-04-20 00:00:00', 89.00, 1, 'pending', 29),
	(49, 1, 4, 3, '2021-04-20 00:00:00', 59.00, 1, 'pending', 28),
	(50, 1, 4, 4, '2021-04-20 00:00:00', 85.00, 1, 'pending', 30),
	(51, 1, 4, 5, '2021-04-20 00:00:00', 89.00, 1, 'pending', 30),
	(52, 1, 4, 2, '2021-04-20 00:00:00', 89.00, 3, 'pending', 31),
	(54, 1, 4, 11, '2021-04-20 00:00:00', 90.00, 2, 'pending', 31),
	(55, 1, 4, 4, '2021-04-20 00:00:00', 85.00, 2, 'pending', 31),
	(56, 1, 4, 1, '2021-04-20 00:00:00', 45.00, 1, 'pending', 32),
	(57, 1, 4, 9, '2021-04-20 00:00:00', 79.00, 2, 'pending', 32),
	(58, 1, 4, 12, '2021-04-20 00:00:00', 199.00, 1, 'pending', 33),
	(59, 1, 4, 4, '2021-04-20 00:00:00', 85.00, 1, 'pending', 34),
	(62, 1, 1, 4, '2021-04-22 00:00:00', 85.00, 1, 'pending', 35),
	(64, 1, 1, 11, '2021-04-22 00:00:00', 90.00, 1, 'pending', 35),
	(79, 1, 1, 4, '2021-04-23 00:00:00', 85.00, 1, 'pending', 36),
	(84, 1, 1, 1, '2021-04-23 00:00:00', 45.00, 2, 'pending', 37),
	(86, 1, 1, 8, '2021-04-23 00:00:00', 105.00, 1, 'pending', 37),
	(87, 1, 1, 12, '2021-04-23 00:00:00', 199.00, 1, 'pending', 37),
	(88, 1, 1, 5, '2021-04-24 00:00:00', 89.00, 1, 'pending', 38),
	(90, 1, 1, 5, '2021-04-25 00:00:00', 89.00, 1, 'waiting', NULL),
	(91, 1, 4, 3, '2021-04-30 00:00:00', 59.00, 5, 'pending', 39),
	(93, 1, 1, 1, '2021-05-04 00:00:00', 45.00, 2, 'waiting', NULL),
	(94, 1, 1, 8, '2021-05-04 00:00:00', 105.00, 1, 'waiting', NULL);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

-- Dumping structure for table furnituredb.pay_method
CREATE TABLE IF NOT EXISTS `pay_method` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pay_description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.pay_method: ~1 rows (approximately)
/*!40000 ALTER TABLE `pay_method` DISABLE KEYS */;
INSERT INTO `pay_method` (`pay_id`, `pay_name`, `pay_description`) VALUES
	(1, 'COD', 'Receive the goods and pay.');
/*!40000 ALTER TABLE `pay_method` ENABLE KEYS */;

-- Dumping structure for table furnituredb.product
CREATE TABLE IF NOT EXISTS `product` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prod_oldprice` decimal(12,2) NOT NULL,
  `prod_price` decimal(12,2) NOT NULL,
  `prod_shortdes` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `prod_detaildes` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `prod_quantity` int(11) NOT NULL,
  `prod_updated_at` timestamp NULL DEFAULT current_timestamp(),
  `cate_id` int(11) NOT NULL,
  PRIMARY KEY (`prod_id`),
  KEY `cate_id` (`cate_id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`cate_id`) REFERENCES `category` (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.product: ~13 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`prod_id`, `prod_name`, `prod_oldprice`, `prod_price`, `prod_shortdes`, `prod_detaildes`, `prod_quantity`, `prod_updated_at`, `cate_id`) VALUES
	(1, 'Red Chair', 50.00, 45.00, 'Color Red and more', 'Color Red and more description', 12, '2021-03-18 02:56:27', 3),
	(2, 'Brown Sofa', 101.00, 89.00, 'Color: Brown\r\nSize: Midium', 'Details product', 20, '2021-03-18 03:47:31', 2),
	(3, 'Flower Chair', 63.00, 59.00, 'Style: flower\r\nColor: pink', 'Style: flower\r\nColor: pink', 20, '2021-04-14 16:39:38', 3),
	(4, 'White Sofa', 100.00, 85.00, 'Color: White\r\nSize: Large', 'Basic color for simple soul', 3, '2021-04-17 10:18:38', 2),
	(5, 'Grey Sofa', 100.00, 89.00, 'Color: Grey\r\nSize: Large', 'Basic color for simple soul', 7, '2021-04-17 10:36:46', 2),
	(6, 'Red Sofa', 100.00, 70.00, 'Color: Red\r\nSize: Large', 'Red color for energetic and active people', 4, '2021-04-17 10:37:55', 2),
	(7, 'Blue Sofa', 100.00, 79.00, 'Color: Blue\r\nSize: Large', 'This sofa really looks refreshing !', 6, '2021-04-17 10:40:11', 2),
	(8, 'Wallnut and Glass Table', 130.00, 105.00, 'Color: Dark Walnut\r\nSize: Large', 'Nostagic but also modern at the same time\r\nOnly table, chairs are not included', 3, '2021-04-17 10:42:44', 4),
	(9, 'Glass Table', 90.00, 79.00, 'Color: Dark\r\nSize: Large', 'Only table, chairs are not included\r\nRegular glass table top with asthetic legs', 8, '2021-04-17 10:45:00', 4),
	(10, 'Pink Chair', 60.00, 35.00, 'Color: Pink\r\nSize: Medium', 'It\'s cute for female customers !', 10, '2021-04-17 10:47:32', 3),
	(11, 'Asthetic Glass Table', 100.00, 90.00, 'Color: Light\r\nSize: Medium', 'Asthetic glass table top and legs', 4, '2021-04-17 10:49:24', 4),
	(12, 'Late 20th Century Black & Gold Tea Caddy Lamp', 250.00, 199.00, 'Color: Black and Gold\r\nSize: Small', 'Table Lamp with black background and yellow accents, hunters with a hunting dog', 0, '2021-04-17 10:51:12', 1),
	(13, 'Minimalistic Wall Hanging Painting', 30.00, 25.00, 'Color: White and Green\r\nSize: Small', 'Simple painting for minimalist decoration\r\n1 item contains 3 painting', 3, '2021-04-17 10:59:44', 1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table furnituredb.product_image
CREATE TABLE IF NOT EXISTS `product_image` (
  `pro_img_id` int(11) NOT NULL AUTO_INCREMENT,
  `proimg_file` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prod_id` int(11) NOT NULL,
  PRIMARY KEY (`pro_img_id`),
  KEY `FK_product_image_product` (`prod_id`),
  CONSTRAINT `FK_product_image_product` FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.product_image: ~13 rows (approximately)
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` (`pro_img_id`, `proimg_file`, `prod_id`) VALUES
	(1, 'red_chair.jpg', 1),
	(2, 'brown_sofa.jpg', 2),
	(3, 'flower_chair.jpg', 3),
	(4, 'product22.jpg', 4),
	(5, 'product19.jpg', 5),
	(6, 'product17.jpg', 6),
	(7, 'product15.jpg', 7),
	(8, 'product5.jpg', 8),
	(9, 'product6.jpg', 9),
	(10, 'product24.jpg', 10),
	(11, 'product7.jpg', 11),
	(12, 'product26.jpg', 12),
	(13, 'product27.jpg', 13);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;

-- Dumping structure for table furnituredb.promotion
CREATE TABLE IF NOT EXISTS `promotion` (
  `prom_id` int(11) NOT NULL AUTO_INCREMENT,
  `prom_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prom_content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `prom_fromdate` datetime NOT NULL,
  `prom_enddate` datetime NOT NULL,
  `prod_id` int(11) NOT NULL,
  PRIMARY KEY (`prom_id`),
  KEY `prod_id` (`prod_id`),
  CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.promotion: ~1 rows (approximately)
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` (`prom_id`, `prom_name`, `prom_content`, `prom_fromdate`, `prom_enddate`, `prod_id`) VALUES
	(1, 'Sale off 30/4', 'Sale off 15% some Products.', '2021-04-01 00:00:00', '2021-05-01 00:00:00', 1);
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;

-- Dumping structure for table furnituredb.transactions
CREATE TABLE IF NOT EXISTS `transactions` (
  `tran_id` int(11) NOT NULL AUTO_INCREMENT,
  `tran_created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `tran_delivery_date` date NOT NULL,
  `tran_delivery_address` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tran_status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'waiting',
  `tran_total` decimal(12,2) NOT NULL DEFAULT 0.00,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`tran_id`),
  KEY `FK_transactions_user` (`user_id`),
  CONSTRAINT `FK_transactions_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.transactions: ~19 rows (approximately)
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` (`tran_id`, `tran_created_date`, `tran_delivery_date`, `tran_delivery_address`, `tran_status`, `tran_total`, `user_id`) VALUES
	(17, '2021-04-18 00:00:00', '2021-04-25', 'TP. Can Tho', 'pending', 45.00, 4),
	(18, '2021-04-18 00:00:00', '2021-04-25', 'TP. Can Tho', 'pending', 89.00, 4),
	(19, '2021-04-18 00:00:00', '2021-04-25', 'TP. Can Tho', 'pending', 45.00, 4),
	(20, '2021-04-18 00:00:00', '2021-04-25', 'TP. Can Tho', 'pending', 223.00, 4),
	(23, '2021-04-19 00:00:00', '2021-04-26', 'TP. Can Tho', 'pending', 313.00, 4),
	(24, '2021-04-19 00:00:00', '2021-04-26', 'TP. Can Tho', 'pending', 535.00, 4),
	(27, '2021-04-19 00:00:00', '2021-04-26', 'Ninh Kieu - TPCT', 'pending', 415.00, 5),
	(28, '2021-04-20 00:00:00', '2021-04-27', 'TP. Can Tho', 'pending', 59.00, 4),
	(29, '2021-04-20 00:00:00', '2021-04-27', 'NK - TPCT', 'delivered', 89.00, 1),
	(30, '2021-04-20 00:00:00', '2021-04-27', 'TP. Can Tho', 'pending', 174.00, 4),
	(31, '2021-04-20 00:00:00', '2021-04-27', 'TP. Can Tho', 'pending', 617.00, 4),
	(32, '2021-04-20 00:00:00', '2021-04-27', 'TP. Can Tho', 'delivered', 203.00, 4),
	(33, '2021-04-20 00:00:00', '2021-04-27', 'TP. Can Tho', 'delivered', 199.00, 4),
	(34, '2021-04-20 00:00:00', '2021-04-27', 'TP. Can Tho', 'delivered', 85.00, 4),
	(35, '2021-04-22 00:00:00', '2021-04-29', 'NK - TPCT', 'pending', 175.00, 1),
	(36, '2021-04-23 00:00:00', '2021-04-30', 'NK - TPCT', 'pending', 85.00, 1),
	(37, '2021-04-23 00:00:00', '2021-04-30', 'NK - TPCT', 'delivered', 394.00, 1),
	(38, '2021-04-24 00:00:00', '2021-05-01', 'NK - TPCT', 'delivered', 89.00, 1),
	(39, '2021-04-30 00:00:00', '2021-05-07', 'TP. Can Tho', 'delivered', 295.00, 4);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;

-- Dumping structure for table furnituredb.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_fullname` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_gender` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_address` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_phone` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_birthday` date NOT NULL,
  `user_identity` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_status` bit(1) NOT NULL DEFAULT b'1',
  `user_admin` bit(1) NOT NULL DEFAULT b'0',
  `user_created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.user: ~6 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `user_email`, `user_password`, `user_fullname`, `user_gender`, `user_address`, `user_phone`, `user_birthday`, `user_identity`, `user_status`, `user_admin`, `user_created_at`) VALUES
	(1, 'cdanh@gmail.com', 'defac44447b57f152d14f30cea7a73cb', 'Duy Anh', 'Male', 'NK - TPCT', '088 999 38 39', '1979-01-28', '12345679', b'1', b'0', '2021-03-24 21:18:44'),
	(2, 'admin@inspiring.com', 'defac44447b57f152d14f30cea7a73cb', 'Duy Anh', 'Male', 'TPCT', '088 999 38 39', '1979-01-29', '12345679', b'1', b'1', '2021-03-24 22:45:45'),
	(4, 'duyanh@gmail.com', 'defac44447b57f152d14f30cea7a73cb', 'Duy Anh', 'Male', 'TP. Can Tho', '088 999 38 39', '1979-01-29', '12345679', b'1', b'0', '2021-03-28 10:20:02'),
	(5, 'chauanh@gmail.com', '25d55ad283aa400af464c76d713c07ad', 'Chau Anh', 'Male', 'Ninh Kieu - TPCT', '088 999 38 39', '1980-01-29', '12345678', b'1', b'0', '2021-04-19 15:08:10'),
	(6, 'nhuduy@gmail.com', 'defac44447b57f152d14f30cea7a73cb', 'Nhu Duy', 'Male', 'Cai Rang - TPCT', '0123 456 789', '1990-01-01', '12345679', b'1', b'0', '2021-04-19 15:11:32'),
	(7, 'quangminh@gmail.com', 'defac44447b57f152d14f30cea7a73cb', 'Do Quang Minh', 'Male', 'Cai Rang - TP. Can Tho', '0123 456 666', '1989-01-01', '12345679', b'1', b'0', '2021-04-19 15:13:28');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table furnituredb.wishlist
CREATE TABLE IF NOT EXISTS `wishlist` (
  `wish_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`wish_id`),
  KEY `FK_wishlist_user` (`user_id`),
  KEY `FK_wishlist_product` (`prod_id`),
  CONSTRAINT `FK_wishlist_product` FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_wishlist_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table furnituredb.wishlist: ~5 rows (approximately)
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` (`wish_id`, `prod_id`, `user_id`) VALUES
	(5, 5, 4),
	(7, 4, 4),
	(36, 8, 1),
	(37, 7, 1),
	(38, 12, 4);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
