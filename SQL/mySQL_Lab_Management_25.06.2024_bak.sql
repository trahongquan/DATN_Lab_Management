-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: lab_Management
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'trhquan','ROLE_ADMIN'),(2,'admin','ROLE_ADMIN'),(3,'pxp','ROLE_TEACHER'),(5,'pmt','ROLE_MANAGER'),(6,'huynhdanglinh','ROLE_MANAGER'),(7,'nguyenquocmanh','ROLE_TEACHER'),(8,'nguyentheanh','ROLE_TEACHER'),(9,'phamquangduan','ROLE_TEACHER'),(10,'doductrung','ROLE_TEACHER'),(11,'phamminhtrong','ROLE_TEACHER'),(12,'trannguyenminhtuan','ROLE_TEACHER'),(13,'dotunglam','ROLE_TEACHER'),(14,'nguyentiendung','ROLE_TEACHER'),(15,'hoangtuananh','ROLE_TEACHER'),(16,'trantrungkien','ROLE_TEACHER'),(17,'hph','ROLE_RESERVATIONIST'),(18,'hoanglan','ROLE_TEACHERWAIT');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lab_id` int(10) unsigned NOT NULL,
  `content_id` int(10) unsigned NOT NULL,
  `booking_date` date NOT NULL,
  `comfirm_status` varchar(50) NOT NULL,
  `work_times` int(11) NOT NULL,
  `note` varchar(255) NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  `auto` varchar(50) NOT NULL,
  `comfirm_used` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_lab_id_foreign` (`lab_id`),
  KEY `booking_content_id_foreign` (`content_id`),
  CONSTRAINT `booking_content_id_foreign` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  CONSTRAINT `booking_lab_id_foreign` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,12,2,'2024-05-08','Bị từ chối',5,'ko có',0,'Người quản lý duyệt','Đã hủy'),(2,14,3,'2024-05-03','Đã xác nhận',5,'Ghi chú cho lớp học k53',0,'Hệ thống tự động duyệt','Đã sử dụng'),(3,17,4,'2024-05-10','Đã xác nhận',5,'ko có',0,'Người quản lý duyệt','Đã sử dụng'),(4,12,5,'2024-05-04','Đã xác nhận',5,'...',0,'Hệ thống tự động duyệt','Đã sử dụng'),(6,16,7,'2024-05-06','Đã xác nhận',6,'ko có',0,'Hệ thống tự động duyệt','Đã sử dụng'),(7,12,8,'2024-05-07','Đã xác nhận',5,'...',0,'Người quản lý duyệt','Đã sử dụng'),(8,14,9,'2024-05-07','Đã xác nhận',5,'ko có gì',0,'Người quản lý duyệt','Đã sử dụng'),(9,13,10,'2024-05-05','Đã xác nhận',6,'không có gì',0,'Hệ thống tự động duyệt','Đã sử dụng'),(10,20,11,'2024-05-06','Đã xác nhận',10,'không có',0,'Hệ thống tự động duyệt','Đã sử dụng'),(11,13,12,'2024-05-08','Đã xác nhận',9,'Nhóm gồm Hoàng Văn Nam và Nguyễn Hồng Linh, GIảng viên, Thạc sĩ Viện CNTT',0,'Người quản lý duyệt','Đã sử dụng'),(12,19,13,'2024-05-09','Đã xác nhận',10,'ghi chú!',0,'Hệ thống tự động duyệt','Đã sử dụng'),(13,17,14,'2024-05-12','Đã xác nhận',8,'Không có gì',0,'Hệ thống tự động duyệt','Đã sử dụng'),(14,13,15,'2024-05-11','Đã xác nhận',10,'cả ngày',0,'Người quản lý duyệt','Đã sử dụng'),(15,18,16,'2024-05-14','Đã xác nhận',10,'Nhóm nghiên cứu số 3 Học viện KTQS',0,'Hệ thống tự động duyệt','Đã sử dụng'),(16,16,17,'2024-05-15','Đã xác nhận',10,'Quân số đông',0,'Hệ thống tự động duyệt','Đã sử dụng'),(17,14,18,'2024-05-16','Bị từ chối',8,'Nhóm gồm Hoàng Phi Hồng, Nguyễn Hoài Nam, Huỳnh Chiến Thắng, Phòng KHQS/HVKTQS',0,'Người quản lý duyệt','Đã hủy'),(18,14,19,'2024-05-15','Đã xác nhận',10,'không có',0,'Người quản lý duyệt','Đã sử dụng'),(19,17,20,'2024-05-16','Đã xác nhận',10,'không có',0,'Người quản lý duyệt','Đã sử dụng'),(20,18,21,'2024-05-17','Đã xác nhận',8,'đợt 1',0,'Hệ thống tự động duyệt','Đã sử dụng'),(21,20,22,'2024-05-18','Đã xác nhận',8,'100 điểm test',0,'Hệ thống tự động duyệt','Đã sử dụng'),(22,12,23,'2024-05-25','Đã xác nhận',10,'sgs',0,'Hệ thống tự động duyệt','Đã sử dụng'),(23,12,24,'2024-06-22','Bị từ chối',10,'advavav',0,'Hệ thống tự động duyệt','Đã hủy');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_equi`
--

DROP TABLE IF EXISTS `booking_equi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_equi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `equi_id` int(10) unsigned NOT NULL,
  `equi_series` varchar(255) NOT NULL,
  `booking_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_equi_booking_id_foreign` (`booking_id`),
  CONSTRAINT `booking_equi_booking_id_foreign` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_equi`
--

LOCK TABLES `booking_equi` WRITE;
/*!40000 ALTER TABLE `booking_equi` DISABLE KEYS */;
INSERT INTO `booking_equi` VALUES (1,2,'[85R569RF5]',1),(2,1,'[1E569RFF555]',1),(3,7,'[1234-OPQR]',2),(4,10,'[7890-YZAB]',2),(5,25,'[LC-12345]',2),(6,31,'[MN-12345, MN-67890, MN-23456]',3),(7,32,'[BK-12345, BK-67890, BK-23456]',3),(8,33,'[CT-12345, CT-67890, CT-23456]',3),(9,30,'[PC-12345, PC-67890, PC-23456]',3),(10,1,'[1E569RFF555]',4),(11,8,'[9012-WXYZ]',4),(12,9,'[1234-IJKL]',4),(13,29,'[DQ-12345]',4),(16,10,'[3456-UVWX]',6),(17,24,'[MV-12345]',6),(18,7,'[7890-KLMN]',6),(19,3,'[1295ED69]',7),(20,29,'[DQ-12345]',7),(21,7,'[1234-OPQR, 5678-STUV]',8),(22,10,'[7890-YZAB]',8),(23,25,'[LC-12345, LC-67890]',8),(24,1,'[98YIH7Y5]',9),(25,2,'[R8S59ED6]',9),(26,17,'[7890-ABCD]',9),(27,10,'[1234-CDEF]',10),(28,24,'[MV-67890]',10),(29,1,'[98YIH7Y5]',11),(30,2,'[R8S59ED6]',11),(31,17,'[7890-ABCD]',11),(32,23,'[7890-ABCDE]',11),(33,32,'[8DC5EF69, 08PDUC-1]',12),(34,38,'[PC-678903, PC-234567]',12),(35,31,'[MN-12345, MN-67890]',13),(36,32,'[BK-12345, BK-67890]',13),(37,33,'[CT-12345, CT-67890]',13),(38,30,'[PC-12345, PC-67890]',13),(39,17,'[7890-ABCD]',14),(40,23,'[7890-ABCDE]',14),(41,41,'[DL-12345, DL-67890, DL-23456]',15),(42,10,'[3456-UVWX]',16),(43,24,'[MV-12345]',16),(44,7,'[1234-OPQR]',17),(45,10,'[7890-YZAB]',17),(46,25,'[LC-12345, LC-67890]',17),(47,7,'[1234-OPQR, 5678-STUV]',18),(48,10,'[7890-YZAB]',18),(49,25,'[LC-12345]',18),(50,31,'[MN-12345, MN-67890, MN-23456]',19),(51,32,'[BK-12345, BK-67890, BK-23456]',19),(52,33,'[CT-12345, CT-67890, CT-23456]',19),(54,41,'[DL-12345, DL-67890, DL-23456]',20),(55,10,'[1234-CDEF]',21),(56,24,'[MV-67890]',21),(57,2,'[85R569RF5]',22),(58,1,'[1E569RFF555]',22),(59,2,'[85R569RF5]',23),(60,1,'[1E569RFF555]',23),(61,9,'[1234-IJKL]',23),(62,29,'[DQ-12345]',23);
/*!40000 ALTER TABLE `booking_equi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `reservationist_id` int(10) unsigned NOT NULL,
  `experiment_type` int(10) unsigned NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `amount_of_people` int(11) NOT NULL,
  `list_id_Participants` varchar(255) DEFAULT NULL,
  `experiment_report` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `content_reservationist_id_foreign` (`reservationist_id`),
  KEY `content_experiment_type_foreign_idx` (`experiment_type`),
  KEY `content_experiment_report_id` (`experiment_report`),
  CONSTRAINT `content_experiment_report_id` FOREIGN KEY (`experiment_report`) REFERENCES `experiment_report` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `content_experiment_type_foreign` FOREIGN KEY (`experiment_type`) REFERENCES `experiment_type` (`id`),
  CONSTRAINT `content_reservationist_id_foreign` FOREIGN KEY (`reservationist_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (2,'Thực nghiệm tính chất sóng - hạt của ánh sáng',6,1,'Lớp CNTT K54',50,'[]',18),(3,'Thực nghiệm sự thay đổi của chất khi phản ứng hóa học',6,1,'Lớp Hóa học K53',35,'[]',18),(4,'Thực hành sử dụng Phần mềm Microsof ',6,1,'Lớp CNTT K54',30,'[]',18),(5,'Thực hành đo quang phổ của ánh sáng',6,1,'Lớp Vật lí K54',50,'[]',18),(7,'Thực nghiệm kiểm tra tế bào mô',6,1,'Lớp CNTT K54',50,'[]',18),(8,'Thực nghiệm con lắc đơn',6,1,'Lớp CNTT K54',50,'[]',18),(9,'Thực hành Hóa học bài 2',6,1,'Lớp Hóa K54',30,'[]',18),(10,'Thực nghiệm tính chất ánh sáng 2',6,1,'Lớp Vật lí K55',52,'[]',18),(11,'Nghiên cứu sơ đồ gen loài muỗi và ứng dụng tìm giải pháp cho nhóm bệnh truyền nghiễm',8,7,'Nhóm nghiên cứu số 1 Học viện KTQS',3,'[]',4),(12,'Nghiên cứu, cải tiến thuật toán mã hóa ứng dụng vào chữ ký số',6,8,'Hoàng Văn Nam',2,'[]',4),(13,'Làm quen với lập trình java',6,1,'Lớp Vật lí K55',20,'[]',18),(14,'Thực hành lập trình pascal',6,1,'Lớp CNTT K54',29,'[]',18),(15,'Nghiên cứu sự phụ thuộc độ nhớt của chất liệu mới ứng dụng trong bôi trơn chi tiết máy',20,6,'Hoàng Phi Hồng',2,'[]',4),(16,'Thử nghiệm ngôn ngữ lập trình mới',20,8,'Nhóm nghiên cứu số 3',3,'[]',4),(17,'Thực hành quan sát mô biểu bì tế bào thực vật',20,1,'Lớp Hóa K55',50,'[]',18),(18,'Nghiên cứu phương pháp cracking mới cho Dầu thô',20,6,'Nhóm nghiên cứu số 2 / HVKTQS',3,'[]',4),(19,'thực nghiệm phản ứng hữu cơ có chất xúc tác',6,1,'Lớp Vật lí K54',40,'[]',18),(20,'Thực hành lập trình C sharp',6,1,'Lớp CNTT K55',30,'[]',18),(21,'Tìm hiểu về ngôn ngữ của máy tính - ngôn ngữ bậc thấp',20,1,'Lớp CNTT K56',25,'[]',18),(22,'Thực hành sinh học bài 2',10,1,'Lớp Hóa học k23',30,'[]',18),(23,'abc',6,6,'Lớp Hóa học k23',30,'[]',4),(24,'ácascac',6,1,'Lớp Hóa học k23',21,'[]',18);
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `series` text NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `series_fixed` text,
  `origin` varchar(45) DEFAULT NULL,
  `levels` text,
  `usingdate` text,
  `unit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'[232030, 65511TD651, 85559ED6]','Máy hiện sóng','[]',4,0,'[65511TD651]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(2,'[88TFG4S]','Máy tạo dao động','[]',2,0,'[]','Việt Nam','[Cấp 1]','[2020-01-01]','Cái'),(3,'[99ED666VD555]','Con lắc dây','Con lắc dao động dây quả sắt 1kg',3,0,'[99ED666VD555, 1295ED69]','Việt Nam','[Cấp 1]','[2020-01-01]','Bộ'),(4,'[5678-EFGH, 9012-IJKL]','Máy đo độ dẫn điện','Đo độ dẫn điện của dung dịch',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]','Cái'),(5,'[3456-MNOP]','Máy đo độ nhớt','Đo độ nhớt của chất lỏng',3,0,'[]','Việt Nam','[Cấp 1]','[2020-01-01]','Cái'),(6,'[9012-CDEF, 3456-GHIJ]','Máy đo độ brix','Đo nồng độ Brix của dung dịch đường',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]','Cái'),(7,'[]','Máy đo pH','Đo độ pH của dung dịch',3,0,'[]','Việt Nam','[]','[]','Cái'),(8,'[3456-ABCD, 7890-EFGH]','Máy quang phổ hấp thụ nguyên tử','Xác định thành phần nguyên tố trong mẫu',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]','Cái'),(9,'[9012-QRST]','Máy quang phổ huỳnh quang','Phân tích các hợp chất huỳnh quang',3,0,'[]','Việt Nam','[Cấp 1]','[2020-01-01]','Cái'),(10,'[1234-CDEFG]','Kính hiển vi điện tử','Quan sát vi sinh vật và cấu trúc tế bào',1,0,'[]','Việt Nam','[Cấp 1]','[2020-01-01]','Cái'),(11,'[5678-GHIJ, 9012-KLMN, 3456-OPQR]','Máy đo điện thế','Đo điện thế giữa hai điểm',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(12,'[7890-STUV, 1234-WXYZ, 5678-ABCD]','Máy đo điện trở','Đo điện trở của vật liệu',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(13,'[9012-EFGH, 3456-IJKL, 7890-MNOPQ]','Máy đo cường độ dòng điện','Đo cường độ dòng điện',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(14,'[1234-QRST, 5678-UVWX, 9012-YZABE]','Máy đo điện áp','Đo điện áp giữa hai điểm',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(15,'[3456-CDEF, 7890-GHIJ, 1234-KLMNS]','Máy đo công suất','Đo công suất điện',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(16,'[5678-OPQR, 9012-STUV, 3456-WXYZA]','Máy đo năng lượng','Đo năng lượng điện',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(17,'[1234-EFGH, 5678-IJKL]','Máy đo thời gian','Đo thời gian',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]','Cái'),(18,'[9012-MNOP, 3456-QRST, 7890-UVWX]','Máy đo tần số','Đo tần số của tín hiệu',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(19,'[1234-WXYZ, 5678-ABCD, 9012-EFGH]','Máy đo áp suất','Đo áp suất của chất khí hoặc chất lỏng',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(20,'[3456-IJKL, 7890-MNOP, 1234-QRST]','Máy đo chân không','Đo mức độ chân không',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(21,'[5678-UVWX, 9012-YZAB, 3456-CDEF]','Máy đo độ ẩm','Đo độ ẩm của không khí',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(22,'[7890-GHIJ, 1234-KLMN, 5678-OPQR]','Máy đo lưu lượng','Đo lưu lượng của chất lỏng hoặc chất khí',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(23,'[9012-STUV, 3456-WXYZ]','Máy đo tốc độ','Đo tốc độ của vật thể chuyển',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]','Cái'),(24,'[MV-67890]','Kính hiển vi cơ học','Quan sát vi sinh vật và cấu trúc tế bào',3,0,'[]','Việt Nam','[Cấp 1]','[2020-01-01]','Cái'),(25,'[LC-23456]','Máy ly tâm','Phân tách các thành phần trong hỗn hợp',3,0,'[]','Việt Nam','[Cấp 1]','[2020-01-01]','Cái'),(26,'[TO-12345, TO-67890, TO-23456]','Tủ ấm','Giữ mẫu ở nhiệt độ ổn định',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Bộ'),(27,'[PCR-12345, PCR-67890, PCR-23456]','Máy PCR','Khuếch đại DNA',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(28,'[SC-12345, SC-67890, SC-23456]','Máy sắc ký','Phân ly hỗn hợp các chất',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(29,'[DQ-67890, DQ-23456]','Máy đo quang','Đo độ hấp thụ ánh sáng của dung dịch',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]','Cái'),(30,'[]','Máy tính','Xử lý thông tin và chạy phần mềm',3,0,'[]','Việt Nam','[]','[]','Cái'),(31,'[]','Màn hình','Hiển thị thông tin trên máy tính',3,0,'[]','Việt Nam','[]','[]','Cái'),(32,'[888EFF9SS]','Bàn phím','Nhập dữ liệu và điều khiển máy tính',1,0,'[8DC5EF69]','Việt Nam','[Cấp 3]','[2020-01-01]','Bộ'),(33,'[ABC-89700]','Chuột','Di chuyển con trỏ trên màn hình',1,0,'[[]]','Việt Nam','[Cấp 1]','[2020-01-01]','Bộ'),(34,'[IN-12345, IN-67890, IN-23456]','Máy in','In ra giấy các tài liệu và hình ảnh',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(35,'[MC-12345, MC-67890, MC-23456]','Máy chiếu','Chiếu hình ảnh và video lên màn hình',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(36,'[LOA-12345, LOA-67890, LOA-23456]','Loa','Phát âm thanh',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Bộ'),(37,'[WC-12345, WC-67890, WC-23456]','Webcam','Quay video và chụp ảnh',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Cái'),(38,'[PC-12345]','Máy tính','Viết và chạy chương trình máy tính',3,0,'[]','Việt Nam','[Cấp 1]','[2020-01-01]','Cái'),(39,'[BD-12345, BD-67890, BD-23456]','Biên dịch viên','Chuyển đổi mã nguồn thành mã máy',3,0,'[[]]','Việt Nam','[Cấp 1, Cấp 3, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Bộ'),(40,'[GL-12345, GL-67890, GL-23456]','Giả lập','Mô phỏng môi trường thực để thử nghiệm chương trình',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Bộ'),(41,'[]','Gỡ lỗi','Tìm và sửa lỗi trong chương trình',3,0,'[]','Việt Nam','[]','[]','Bộ'),(42,'[CT-12345, CT-678930, CT-234567]','Chạy thử','Kiểm tra hoạt động của chương trình',3,0,'[]','Việt Nam','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]','Bộ'),(43,'[OPRRRTT77, 908YD-5CCD]','Máy X-quang','Chức năng chụp X Quang',2,0,'[]','Việt Nam','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]','Cái'),(44,'[Test-máy-1, Test-máy-111]','Test máy 1','Test máy 1',2,0,'[[]]','Test máy 1','[Cấp 1, Cấp 2]','[2020-01-01, 2020-01-01]','Cái');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_lab`
--

DROP TABLE IF EXISTS `equipment_lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_lab` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lab_id` int(10) unsigned NOT NULL,
  `equi_id` int(10) unsigned NOT NULL,
  `equi_series` text NOT NULL,
  `levels` text,
  `usingdate` text,
  PRIMARY KEY (`id`),
  KEY `equipment_lab_equi_id_foreign` (`equi_id`),
  KEY `equipment_lab_lab_id_foreign` (`lab_id`),
  CONSTRAINT `equipment_lab_equi_id_foreign` FOREIGN KEY (`equi_id`) REFERENCES `equipment` (`id`),
  CONSTRAINT `equipment_lab_lab_id_foreign` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_lab`
--

LOCK TABLES `equipment_lab` WRITE;
/*!40000 ALTER TABLE `equipment_lab` DISABLE KEYS */;
INSERT INTO `equipment_lab` VALUES (8,12,2,'[85R569RF5]','[Cấp 1]','[2020-01-01]'),(11,12,1,'[1E569RFF555]','[Cấp 1]','[2020-01-01]'),(13,12,5,'[3456-MNOP]','[Cấp 1]','[2020-01-01]'),(14,12,3,'[1295ED69]','[Cấp 1]','[2020-01-01]'),(15,16,5,'[7890-QRST, 1234-UVWX]','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]'),(16,16,6,'[5678-YZAB]','[Cấp 1]','[2020-01-01]'),(18,16,10,'[3456-UVWX]','[Cấp 1]','[2020-01-01]'),(19,16,24,'[MV-12345]','[Cấp 1]','[2020-01-01]'),(20,16,7,'[7890-KLMN]','[Cấp 1]','[2020-01-01]'),(21,14,7,'[1234-OPQR, 5678-STUV]','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]'),(22,14,10,'[7890-YZAB]','[Cấp 1]','[2020-01-01]'),(23,14,25,'[LC-12345, LC-67890]','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]'),(24,17,31,'[MN-12345, MN-67890, MN-23456]','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]'),(25,17,32,'[BK-12345, BK-67890, BK-23456]','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]'),(26,17,33,'[CT-12345, CT-67890, CT-23456]','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]'),(28,17,30,'[PC-12345, PC-67890, PC-23456]','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]'),(30,18,41,'[DL-12345, DL-67890, DL-23456]','[Cấp 1, Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01, 2020-01-01]'),(31,12,8,'[9012-WXYZ]','[Cấp 1]','[2020-01-01]'),(32,12,9,'[1234-IJKL]','[Cấp 1]','[2020-01-01]'),(33,12,29,'[DQ-12345]','[Cấp 1]','[2020-01-01]'),(34,20,10,'[1234-CDEF]','[Cấp 1]','[2020-01-01]'),(35,20,24,'[MV-67890]','[Cấp 1]','[2020-01-01]'),(36,13,1,'[98YIH7Y5]','[Cấp 1]','[2020-01-01]'),(37,13,2,'[R8S59ED6]','[Cấp 1]','[2020-01-01]'),(38,13,3,'[88RF96SD]','[Cấp 1]','[2020-01-01]'),(39,13,4,'[1234-ABCD]','[Cấp 1]','[2020-01-01]'),(40,13,9,'[5678-MNOP]','[Cấp 1]','[2020-01-01]'),(41,13,17,'[7890-ABCD]','[Cấp 1]','[2020-01-01]'),(42,13,23,'[7890-ABCDE]','[Cấp 1]','[2020-01-01]'),(43,19,32,'[8DC5EF69, 08PDUC-1]','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]'),(44,19,38,'[PC-678903, PC-234567]','[Cấp 1, Cấp 1]','[2020-01-01, 2020-01-01]'),(45,21,10,'[1234-CDEFG]','[]','[2020-01-01]'),(46,21,14,'[5678-UVWX]','[]','[2020-01-01]'),(47,21,25,'[LC-23456]','[]','[2020-01-01]'),(48,22,21,'[9012-YZAB]','[]','[2020-01-01]'),(49,22,15,'[7890-GHIJ]','[]','[2020-01-01]'),(50,22,26,'[TO-12345]','[]','[2020-01-01]');
/*!40000 ALTER TABLE `equipment_lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipmentlabdto`
--

DROP TABLE IF EXISTS `equipmentlabdto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipmentlabdto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lab_id` int(10) unsigned NOT NULL,
  `year` int(11) DEFAULT NULL,
  `equipment_lab_data` text,
  PRIMARY KEY (`id`),
  KEY `lab_id` (`lab_id`),
  CONSTRAINT `equipmentlabdto_ibfk_1` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipmentlabdto`
--

LOCK TABLES `equipmentlabdto` WRITE;
/*!40000 ALTER TABLE `equipmentlabdto` DISABLE KEYS */;
INSERT INTO `equipmentlabdto` VALUES (1,12,2023,'[{\"id\":8,\"labId\":12,\"equipmentId\":2,\"equipment\":{\"id\":2,\"name\":\"Máy tạo dao động\",\"series\":\"[88TFG4S]\",\"seriesFixed\":\"[]\",\"description\":\"[]\",\"quantity\":2,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[85R569RF5]\",\"equipmentSerieList\":[\"85R569RF5\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":11,\"labId\":12,\"equipmentId\":1,\"equipment\":{\"id\":1,\"name\":\"Máy hiện sóng\",\"series\":\"[232030, 65511TD651, 85559ED6]\",\"seriesFixed\":\"[65511TD651]\",\"description\":\"[]\",\"quantity\":4,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[1E569RFF555]\",\"equipmentSerieList\":[\"1E569RFF555\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":13,\"labId\":12,\"equipmentId\":5,\"equipment\":{\"id\":5,\"name\":\"Máy đo độ nhớt\",\"series\":\"[3456-MNOP]\",\"seriesFixed\":\"[]\",\"description\":\"Đo độ nhớt của chất lỏng\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[3456-MNOP]\",\"equipmentSerieList\":[\"3456-MNOP\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":14,\"labId\":12,\"equipmentId\":3,\"equipment\":{\"id\":3,\"name\":\"Con lắc dây\",\"series\":\"[99ED666VD555]\",\"seriesFixed\":\"[99ED666VD555, 1295ED69]\",\"description\":\"Con lắc dao động dây quả sắt 1kg\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Bộ\"},\"equipmentSeries\":\"[1295ED69]\",\"equipmentSerieList\":[\"1295ED69\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":31,\"labId\":12,\"equipmentId\":8,\"equipment\":{\"id\":8,\"name\":\"Máy quang phổ hấp thụ nguyên tử\",\"series\":\"[3456-ABCD, 7890-EFGH]\",\"seriesFixed\":\"[]\",\"description\":\"Xác định thành phần nguyên tố trong mẫu\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[9012-WXYZ]\",\"equipmentSerieList\":[\"9012-WXYZ\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":32,\"labId\":12,\"equipmentId\":9,\"equipment\":{\"id\":9,\"name\":\"Máy quang phổ huỳnh quang\",\"series\":\"[9012-QRST]\",\"seriesFixed\":\"[]\",\"description\":\"Phân tích các hợp chất huỳnh quang\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[1234-IJKL]\",\"equipmentSerieList\":[\"1234-IJKL\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":33,\"labId\":12,\"equipmentId\":29,\"equipment\":{\"id\":29,\"name\":\"Máy đo quang\",\"series\":\"[DQ-67890, DQ-23456]\",\"seriesFixed\":\"[]\",\"description\":\"Đo độ hấp thụ ánh sáng của dung dịch\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[DQ-12345]\",\"equipmentSerieList\":[\"DQ-12345\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]}]'),(3,18,2023,'[{\"id\":30,\"labId\":18,\"equipmentId\":41,\"equipment\":{\"id\":41,\"name\":\"Gỡ lỗi\",\"series\":\"[]\",\"seriesFixed\":\"[]\",\"description\":\"Tìm và sửa lỗi trong chương trình\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[]\",\"using\":\"[]\",\"unit\":\"Bộ\"},\"equipmentSeries\":\"[DL-12345, DL-67890, DL-23456xxx]\",\"equipmentSerieList\":[\"DL-12345\",\"DL-67890\",\"DL-23456xxx\"],\"levels\":[\"Cấp 1\",\"Cấp 1\",\"Cấp 1\"],\"usingdates\":[\"2020-01-01\",\"2020-01-01\",\"2020-01-01\"]}]'),(4,12,2024,'[{\"id\":8,\"labId\":12,\"equipmentId\":2,\"equipment\":{\"id\":2,\"name\":\"Máy tạo dao động\",\"series\":\"[88TFG4S]\",\"seriesFixed\":\"[]\",\"description\":\"[]\",\"quantity\":2,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[85R569RF5]\",\"equipmentSerieList\":[\"85R569RF5\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":11,\"labId\":12,\"equipmentId\":1,\"equipment\":{\"id\":1,\"name\":\"Máy hiện sóng\",\"series\":\"[232030, 65511TD651, 85559ED6]\",\"seriesFixed\":\"[65511TD651]\",\"description\":\"[]\",\"quantity\":4,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[1E569RFF555]\",\"equipmentSerieList\":[\"1E569RFF555\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":13,\"labId\":12,\"equipmentId\":5,\"equipment\":{\"id\":5,\"name\":\"Máy đo độ nhớt\",\"series\":\"[3456-MNOP]\",\"seriesFixed\":\"[]\",\"description\":\"Đo độ nhớt của chất lỏng\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[3456-MNOP]\",\"equipmentSerieList\":[\"3456-MNOP\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":14,\"labId\":12,\"equipmentId\":3,\"equipment\":{\"id\":3,\"name\":\"Con lắc dây\",\"series\":\"[99ED666VD555]\",\"seriesFixed\":\"[99ED666VD555, 1295ED69]\",\"description\":\"Con lắc dao động dây quả sắt 1kg\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Bộ\"},\"equipmentSeries\":\"[1295ED69]\",\"equipmentSerieList\":[\"1295ED69\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":31,\"labId\":12,\"equipmentId\":8,\"equipment\":{\"id\":8,\"name\":\"Máy quang phổ hấp thụ nguyên tử\",\"series\":\"[3456-ABCD, 7890-EFGH]\",\"seriesFixed\":\"[]\",\"description\":\"Xác định thành phần nguyên tố trong mẫu\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[9012-WXYZ]\",\"equipmentSerieList\":[\"9012-WXYZ\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":32,\"labId\":12,\"equipmentId\":9,\"equipment\":{\"id\":9,\"name\":\"Máy quang phổ huỳnh quang\",\"series\":\"[9012-QRST]\",\"seriesFixed\":\"[]\",\"description\":\"Phân tích các hợp chất huỳnh quang\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[1234-IJKL]\",\"equipmentSerieList\":[\"1234-IJKL\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":33,\"labId\":12,\"equipmentId\":29,\"equipment\":{\"id\":29,\"name\":\"Máy đo quang\",\"series\":\"[DQ-67890, DQ-23456]\",\"seriesFixed\":\"[]\",\"description\":\"Đo độ hấp thụ ánh sáng của dung dịch\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[DQ-12345]\",\"equipmentSerieList\":[\"DQ-12345\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]}]'),(5,18,2024,'[{\"id\":30,\"labId\":18,\"equipmentId\":41,\"equipment\":{\"id\":41,\"name\":\"Gỡ lỗi\",\"series\":\"[]\",\"seriesFixed\":\"[]\",\"description\":\"Tìm và sửa lỗi trong chương trình\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[]\",\"using\":\"[]\",\"unit\":\"Bộ\"},\"equipmentSeries\":\"[DL-12345, DL-67890, DL-23456]\",\"equipmentSerieList\":[\"DL-12345\",\"DL-67890\",\"DL-23456\"],\"levels\":[\"Cấp 1\",\"Cấp 1\",\"Cấp 1\"],\"usingdates\":[\"2020-01-01\",\"2020-01-01\",\"2020-01-01\"]}]'),(6,13,2024,'[{\"id\":36,\"labId\":13,\"equipmentId\":1,\"equipment\":{\"id\":1,\"name\":\"Máy hiện sóng\",\"series\":\"[232030, 65511TD651, 85559ED6]\",\"seriesFixed\":\"[65511TD651]\",\"description\":\"[]\",\"quantity\":4,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[98YIH7Y5]\",\"equipmentSerieList\":[\"98YIH7Y5\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":37,\"labId\":13,\"equipmentId\":2,\"equipment\":{\"id\":2,\"name\":\"Máy tạo dao động\",\"series\":\"[88TFG4S]\",\"seriesFixed\":\"[]\",\"description\":\"[]\",\"quantity\":2,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[R8S59ED6]\",\"equipmentSerieList\":[\"R8S59ED6\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":38,\"labId\":13,\"equipmentId\":3,\"equipment\":{\"id\":3,\"name\":\"Con lắc dây\",\"series\":\"[99ED666VD555]\",\"seriesFixed\":\"[99ED666VD555, 1295ED69]\",\"description\":\"Con lắc dao động dây quả sắt 1kg\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Bộ\"},\"equipmentSeries\":\"[88RF96SD]\",\"equipmentSerieList\":[\"88RF96SD\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":39,\"labId\":13,\"equipmentId\":4,\"equipment\":{\"id\":4,\"name\":\"Máy đo độ dẫn điện\",\"series\":\"[5678-EFGH, 9012-IJKL]\",\"seriesFixed\":\"[]\",\"description\":\"Đo độ dẫn điện của dung dịch\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[1234-ABCD]\",\"equipmentSerieList\":[\"1234-ABCD\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":40,\"labId\":13,\"equipmentId\":9,\"equipment\":{\"id\":9,\"name\":\"Máy quang phổ huỳnh quang\",\"series\":\"[9012-QRST]\",\"seriesFixed\":\"[]\",\"description\":\"Phân tích các hợp chất huỳnh quang\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[5678-MNOP]\",\"equipmentSerieList\":[\"5678-MNOP\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":41,\"labId\":13,\"equipmentId\":17,\"equipment\":{\"id\":17,\"name\":\"Máy đo thời gian\",\"series\":\"[1234-EFGH, 5678-IJKL]\",\"seriesFixed\":\"[]\",\"description\":\"Đo thời gian\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[7890-ABCD]\",\"equipmentSerieList\":[\"7890-ABCD\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]},{\"id\":42,\"labId\":13,\"equipmentId\":23,\"equipment\":{\"id\":23,\"name\":\"Máy đo tốc độ\",\"series\":\"[9012-STUV, 3456-WXYZ]\",\"seriesFixed\":\"[]\",\"description\":\"Đo tốc độ của vật thể chuyển\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1, Cấp 1]\",\"using\":\"[2020-01-01, 2020-01-01]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[7890-ABCDE]\",\"equipmentSerieList\":[\"7890-ABCDE\"],\"levels\":[\"Cấp 1\"],\"usingdates\":[\"2020-01-01\"]}]'),(7,17,2024,'[{\"id\":24,\"labId\":17,\"equipmentId\":31,\"equipment\":{\"id\":31,\"name\":\"Màn hình\",\"series\":\"[]\",\"seriesFixed\":\"[]\",\"description\":\"Hiển thị thông tin trên máy tính\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[]\",\"using\":\"[]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[MN-12345, MN-67890, MN-23456]\",\"equipmentSerieList\":[\"MN-12345\",\"MN-67890\",\"MN-23456\"],\"levels\":[\"Cấp 1\",\"Cấp 1\",\"Cấp 1\"],\"usingdates\":[\"2020-01-01\",\"2020-01-01\",\"2020-01-01\"]},{\"id\":25,\"labId\":17,\"equipmentId\":32,\"equipment\":{\"id\":32,\"name\":\"Bàn phím\",\"series\":\"[888EFF9SS]\",\"seriesFixed\":\"[8DC5EF69]\",\"description\":\"Nhập dữ liệu và điều khiển máy tính\",\"quantity\":1,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 3]\",\"using\":\"[2020-01-01]\",\"unit\":\"Bộ\"},\"equipmentSeries\":\"[BK-12345, BK-67890, BK-23456]\",\"equipmentSerieList\":[\"BK-12345\",\"BK-67890\",\"BK-23456\"],\"levels\":[\"Cấp 1\",\"Cấp 1\",\"Cấp 1\"],\"usingdates\":[\"2020-01-01\",\"2020-01-01\",\"2020-01-01\"]},{\"id\":26,\"labId\":17,\"equipmentId\":33,\"equipment\":{\"id\":33,\"name\":\"Chuột\",\"series\":\"[ABC-89700]\",\"seriesFixed\":\"[[]]\",\"description\":\"Di chuyển con trỏ trên màn hình\",\"quantity\":1,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[Cấp 1]\",\"using\":\"[2020-01-01]\",\"unit\":\"Bộ\"},\"equipmentSeries\":\"[CT-12345, CT-67890, CT-23456]\",\"equipmentSerieList\":[\"CT-12345\",\"CT-67890\",\"CT-23456\"],\"levels\":[\"Cấp 1\",\"Cấp 1\",\"Cấp 1\"],\"usingdates\":[\"2020-01-01\",\"2020-01-01\",\"2020-01-01\"]},{\"id\":28,\"labId\":17,\"equipmentId\":30,\"equipment\":{\"id\":30,\"name\":\"Máy tính\",\"series\":\"[]\",\"seriesFixed\":\"[]\",\"description\":\"Xử lý thông tin và chạy phần mềm\",\"quantity\":3,\"isDeleted\":0,\"origin\":\"Việt Nam\",\"levels\":\"[]\",\"using\":\"[]\",\"unit\":\"Cái\"},\"equipmentSeries\":\"[PC-12345, PC-67890, PC-23456]\",\"equipmentSerieList\":[\"PC-12345\",\"PC-67890\",\"PC-23456\"],\"levels\":[\"Cấp 1\",\"Cấp 1\",\"Cấp 1\"],\"usingdates\":[\"2020-01-01\",\"2020-01-01\",\"2020-01-01\"]}]');
/*!40000 ALTER TABLE `equipmentlabdto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment_group`
--

DROP TABLE IF EXISTS `experiment_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiment_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_group`
--

LOCK TABLES `experiment_group` WRITE;
/*!40000 ALTER TABLE `experiment_group` DISABLE KEYS */;
INSERT INTO `experiment_group` VALUES (1,'Đào tạo'),(2,'Nghiên cứu khoa học'),(3,'Sản xuất chế thử'),(4,'Liên kết đào tạo');
/*!40000 ALTER TABLE `experiment_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment_report`
--

DROP TABLE IF EXISTS `experiment_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiment_report` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `report_type` varchar(50) NOT NULL,
  `experiment_group_id` int(10) unsigned NOT NULL,
  `experiment_type_id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_report`
--

LOCK TABLES `experiment_report` WRITE;
/*!40000 ALTER TABLE `experiment_report` DISABLE KEYS */;
INSERT INTO `experiment_report` VALUES (1,'Chuyên đề',1,'[2, 3, 4]'),(2,'Báo cáo thí nghiệm',1,'[2, 3, 4]'),(3,'Sản phẩm',1,'[2, 3, 4]'),(4,'Đề tài (đã nghiệm thu)',2,'[5, 6, 7, 8, 9]'),(5,'Đề cương',2,'[5, 6, 7, 8, 9]'),(6,'Chuyên đề',2,'[5, 6, 7, 8, 9]'),(7,'Đăng trên tạp chí Quốc tế',2,'[10]'),(8,'Đăng trên tạp chí trong nước',2,'[10]'),(9,'Thuyết minh kỹ thuật thiết kế sản phẩm',3,'[11]'),(10,'Quy trình công nghệ sản xuất, chế thử sản phẩm',3,'[11]'),(11,'Sản phẩm',3,'[11]'),(12,'Thuyết minh thiết kế phần mềm',3,'[12]'),(13,'Phần mềm',3,'[12]'),(14,'Văn bản',1,'[4]'),(17,'Văn bản làm việc liên kết',4,'[17]'),(18,'Giờ khai thác',1,'[1]');
/*!40000 ALTER TABLE `experiment_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment_type`
--

DROP TABLE IF EXISTS `experiment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiment_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) NOT NULL,
  `experiment_group_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `experiment_type_experiment_group_id_foreign` (`experiment_group_id`),
  CONSTRAINT `experiment_type_experiment_group_id_foreign` FOREIGN KEY (`experiment_group_id`) REFERENCES `experiment_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_type`
--

LOCK TABLES `experiment_type` WRITE;
/*!40000 ALTER TABLE `experiment_type` DISABLE KEYS */;
INSERT INTO `experiment_type` VALUES (1,'Hướng dẫn thí nghiệm, thực hành, thực tập, làm bài tập lớn, đồ án môn học cho các loại hình đào tạo.',1),(2,'Luận án tiến sỹ',1),(3,'Luận văn cao học',1),(4,'Đồ án tốt nghiệp',1),(5,'Đề tài cấp Nhà nước',2),(6,'Đề tài cấp Bộ',2),(7,'Đề tài cấp Ngành',2),(8,'Đề tài cấp Học viện',2),(9,'Đề tài của học viên, sinh viên',2),(10,'Viết bài báo',2),(11,'Sản xuất, chế thử vật tư, thiết bị',3),(12,'Viết phầm mềm',3),(13,'Cải tiến trang bị',3),(17,'Cục CNTT, Bộ TTTT',4);
/*!40000 ALTER TABLE `experiment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab`
--

DROP TABLE IF EXISTS `lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lab_name` varchar(255) NOT NULL,
  `capacity` int(11) NOT NULL,
  `location` varchar(255) NOT NULL,
  `lab_managemet_id` int(10) unsigned NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  `managing_unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lab_lab_managemet_id_foreign` (`lab_managemet_id`),
  CONSTRAINT `lab_lab_managemet_id_foreign` FOREIGN KEY (`lab_managemet_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` VALUES (1,'A5 201',60,'Khu A',1,1,NULL),(2,'A5 202',60,'Khu A',1,1,NULL),(3,'A5 203',60,'Khu A',1,1,NULL),(4,'B 101',60,'Khu B',1,1,NULL),(5,'B 201',60,'Khu B',1,1,NULL),(6,'F 206',60,'Khu F1',1,1,NULL),(7,'F 207',60,'Khu F1',1,1,NULL),(8,'D 208',60,'Khu D',1,1,NULL),(9,'D 209',60,'Khu D',1,1,NULL),(12,'Thực hành Vật lý 2',60,'P205, P206 Tòa N3, Khu A',8,0,'Bộ môn Vật lý/K11'),(13,'PTN Vật lý đại cương 1',60,'P 203-204 nhà H4',8,0,'Bộ môn Vật lý/K11'),(14,'PTN Hóa vô cơ',40,'P401, 402 – nhà H4',8,0,'Bộ môn CNHH/K11'),(16,'Thực hành Sinh học',50,'P202 Tòa N3, Khu A',9,0,'Bộ môn Sinh học'),(17,'Thực hành Tin học',30,'P104 Tòa N2, Khu B',9,0,'Bộ môn Tin học'),(18,'Ngôn ngữ máy tính',25,'P103 Tòa N2, Khu B',9,0,'Bộ môn CN phần mềm'),(19,'Thực hành Mạng máy tính',20,'P102 Tòa N2, Khu B',9,0,'Bộ môn CN mạng'),(20,'Thực hành Sinh học 2',60,'P207, P2068 Tòa N4, Khu A',8,0,'Bộ môn Sinh học'),(21,'PTN Hóa hữu cơ',60,'P503, 504 – nhà H4',9,0,'Bộ môn Phòng hóa/K11'),(22,'PTN Hóa hữu cơ',60,'P503, 504 – nhà H4',8,1,NULL),(23,'PTN Kỹ thuật môi trường',30,'P.301 – nhà H4',9,0,'Bộ môn Kỹ thuật môi trường/K11'),(24,'PTN Công nghệ phần mềm',30,'P304 – nhà H2',9,0,'Bộ môn CN phần mềm/V4'),(25,'PTN Hệ thống tích hợp',30,'P306 – nhà H2',8,0,'Bộ môn Hệthống thông tin/V4'),(26,'PTN Vật liệu',30,'P103 – nhà H4',8,0,'Bộ môn CN Vật liệu/K21'),(27,'PTN Gia công áp lực',35,'P102 – nhà H4',9,0,'Bộ môn Gia công áp lực/ K21'),(28,'PTN Nhiệt thủy khí',30,'P212 – nhà H5',8,0,'Bộ môn Nhiệt Thủy khí/K21'),(29,'PTN Kỹ thuật đo',60,'P208 – nhà H5',8,0,'Bộ môn Chế tạo máy/K21'),(30,'PTN Đạn ngòi',40,'P401 – nhà H5',8,0,'Bộ môn Đạn/K22'),(31,'PTN Thiết kế HT Vũ khí',30,'P206 – nhà H5',8,0,'Bộ môn Vũ khí/K22'),(32,'PTN Khí tài quang học',40,'P306 – nhà H5',8,0,'Bộ môn Khí tài quang/K22'),(33,'PTN Thuốc phóng thuốc nổ',40,'P504 - nhà H5',8,0,'Bộ môn TPTN/K22'),(34,'PTN Động cơ',30,'P106-Nhà H5',8,0,'Bộ môn Động cơ/V5'),(35,'PTN Xe máy công binh',60,'Tầng 1 – Bể bơi',8,0,'Bộ môn XMCB/ V5'),(36,'PTN NC và PT Rô bốt quân sự',60,'P105 - nhà H2',8,0,'Bộ môn CĐT và CTM đặc\r\nbiệt/K24'),(37,'PTN Thiết kế, chế tạo tên lửa',30,'P108 - nhà H3',8,0,'Bộ môn Thiết kế hệ thống kết cấu thiết bị bay/K24'),(38,'PTN CIM',60,'P208 – Nhà S1',8,0,'Bộ môn công nghệ Thiết bị và\r\nHKVT/K24'),(39,'PTN Thông tin số, cáp quang',60,'P302 - nhà H3',8,0,'Bộ môn Thông Tin/K31'),(40,'PTN Đo lường',30,'P201 – nhà H3',8,0,'Bộ môn Lý thuyết mạch/K31'),(41,'PTN Điện tử số',30,'P307 – nhà H3',8,0,'Bộ môn Kỹ thuật vi xử lý/K31'),(42,'PTN Kỹ thuật mạch Điện tử và truyền hình',35,'P204 – nhà H3',8,0,'Bộ môn Cơ sở kỹ thuật vô\r\ntuyến/K31'),(43,'PTN Tác chiến điện tử',30,'P 404 – nhà S1',8,0,'Bộ môn Tác chiến điện tử/K31'),(44,'PTN Kỹ thuật viễn thông',40,'P412 – Nhà S1',8,0,'Trung tâm KTVT/K31'),(45,'PTN Kỹ Thuật điện',60,'P309 – nhà H3',8,0,'Bộ môn Kỹ thuật điện/V6'),(46,'PTN Điện tử y sinh',40,'P309 – nhà H3',8,0,'Bộ môn Điện tử y sinh/V6'),(47,'PTN Các hệ thống điều khiển',60,'P208- Nhà H3',8,0,'Bộ môn Tự động và KTT/V6'),(48,'PTN Công tác nổ, ngụy trang',30,'P206 – Nhà H2',8,0,'Bộ môn Xây dựng CTQP/V2'),(49,'PTN Địa Kỹ thuật',30,'P107 – nhà H2',8,0,'Bộ môn Cơ sở KTCT/V2'),(50,'PTN Kết cấu công trình',40,'P402 – nhà H2',8,0,'Bộ môn XD nhà và CTCN/V2'),(51,'PTN Thực hành công nghệ địa hình',30,'P204 – nhà H2',9,0,'Bộ môn Trắc địa bản đồ/V2'),(52,'PTN Khai thác công trình',30,'P102 – nhà H2',8,0,'Bộ môn Cầu đường sân bay/V2'),(53,'PTN Công trình đặc biệt',30,'P 109- nhà H5',8,0,'Trung tâm NCUD & Kiểm định\r\nchất lượng công trình/V2'),(54,'PTN Công nghệ điện tử',40,'P308 – nhà S1',8,0,'Bộ môn CN điên tử/V3');
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `rank` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `military_number` int(40) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'Trần Hồng Quân','Thượng úy','Lớp CNTT12',123456789,'trhquan@gmail.com',0),(2,'Nguyễn Hoàng Nam','Trung úy','Khoa Rada',987654321,'HoangNam@gamil.com',0),(6,'Phạm Xuân Phương','Đại úy','Lớp CNTT12',968574321,'0965362452',0),(8,'Phạm Minh Trọng','Thượng úy','Lớp CNTT 12',963582147,'0365298741',0),(9,'Huỳnh Đăng Linh','Đại úy','Viện CNTT',532146987,'0541236984',0),(10,'NGUYỄN QUỐC MẠNH','Đại úy','Lớp CNTT12',977230693,'0977230693',0),(11,'NGUYỄN THẾ ANH','Đại úy','Lớp CNTT12',966446483,'0966446483',0),(12,'PHẠM QUANG DUẨN','Thượng úy','Lớp CNTT12',357575223,'0357575223',0),(13,'ĐỖ ĐỨC TRUNG','Đại úy','Lớp CNTT12',357476802,'0357476802',0),(14,'PHẠM MINH TRỌNG','Thượng úy','Lớp CNTT12',356599692,'0356599692',0),(15,'TRẦN NGUYỄN MINH TUẤN','Thượng úy','Lớp CNTT12',974187915,'0974187915',0),(16,'ĐỖ TÙNG LÂM','Thượng úy','Lớp CNTT12',365079956,'0365079956',0),(17,'NGUYỄN TIẾN DŨNG','Thượng úy','Lớp CNTT12',376172992,'0376172992',0),(18,'HOÀNG TUẤN ANH','Thượng úy','Lớp CNTT12',364547516,'0364547516',0),(19,'TRẦN TRUNG KIÊN','Thượng úy','Lớp CNTT12',964580285,'0964580285',0),(20,'Hoàng Phi Hồng','Thượng úy','Viện CNTT',552369874,'0326598741',0),(23,'Hoàng Thúy Lan','Thượng úy','Viện CNTT',963582147,'0965362452',1);
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MANAGER'),(3,'ROLE_TEACHER'),(10,'ROLE_TEACHERWAIT'),(12,'ROLE_RESERVATIONIST'),(14,'ROLE_TAECHERWAITCANCEL');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `experoment_group_id` int(10) unsigned NOT NULL,
  `experoment_type_id` int(10) unsigned NOT NULL,
  `experoment_report_id` int(10) unsigned NOT NULL,
  `score` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `score_experoment_group_id` (`experoment_group_id`),
  KEY `score_experoment_type_id` (`experoment_type_id`),
  KEY `score_experoment_report_id` (`experoment_report_id`),
  CONSTRAINT `score_experoment_group_id` FOREIGN KEY (`experoment_group_id`) REFERENCES `experiment_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `score_experoment_report_id` FOREIGN KEY (`experoment_report_id`) REFERENCES `experiment_report` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `score_experoment_type_id` FOREIGN KEY (`experoment_type_id`) REFERENCES `experiment_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,1,1,1,1),(2,1,1,2,1),(3,1,1,3,1),(4,1,2,1,2),(5,1,2,2,1),(6,1,2,3,2),(7,1,3,1,1),(8,1,3,2,0.5),(9,1,3,3,1),(10,1,4,1,0.5),(11,1,4,2,0.2),(12,1,4,3,0.5),(13,2,5,4,10),(14,2,5,5,2),(15,2,5,6,2),(16,2,6,4,7),(17,2,6,5,1.5),(18,2,6,6,1.5),(19,2,7,4,5),(20,2,7,5,1),(21,2,7,6,1),(22,2,8,4,3),(23,2,8,5,0.5),(24,2,8,6,0.5),(25,2,9,4,0.5),(26,3,10,7,10),(27,3,10,8,5),(28,3,11,9,0.5),(29,3,11,10,0.5),(30,3,11,11,0.5),(31,3,12,12,0.5),(32,3,12,13,1),(35,1,1,18,0.0667);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `units`
--

DROP TABLE IF EXISTS `units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `units` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `units`
--

LOCK TABLES `units` WRITE;
/*!40000 ALTER TABLE `units` DISABLE KEYS */;
INSERT INTO `units` VALUES (1,'Cái'),(2,'Chiếc'),(3,'Bộ'),(4,'Lít'),(5,'Cặp');
/*!40000 ALTER TABLE `units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `people_id` int(10) unsigned NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `users_people_id_foreign` (`people_id`),
  CONSTRAINT `users_people_id_foreign` FOREIGN KEY (`people_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'trhquan','$2a$12$EVtrfH74.jqdOAIVAXg4MufISL/.UV6dYGWVEisUr/03TvuEu7D3u',1),(2,2,'admin','$2a$10$HTI8SIV7gwcBz5z1kaKHjeUnZr2mwME24BXjlu29QpmVxDQIZd45q',1),(3,6,'pxp','$2a$10$dX6W0lXtoy6lpsKY0XpUX.STluRC7Grqt2fn85qpuGUB22Y90ZjOq',1),(5,8,'pmt','$2a$10$NZiByPPdk6jbkPFeRgYgWOEvgmsx/IsuEV3LjQfOVGzf7khu64M3W',1),(6,9,'huynhdanglinh','$2a$10$jwbW1km8D5OngHDyInvuzu8FybYL8WW4K/KnhZRJMo4.9nqZ/g1t.',1),(7,10,'nguyenquocmanh','$2a$10$oRKZf4xyHT09Hh0hfdOysusU2Aaw.iT4MOAByonG.i20l2JNY3nB6',1),(8,11,'nguyentheanh','$2a$10$TUhk8a6iCQ2ocuK2BT5uAetp9Gt2NPQhLWyLxy8UPVAH6fu3hGCia',1),(9,12,'phamquangduan','$2a$10$SY.Oz4fCh/L.lLUVIS2fJeI0zYA.uhJ7hFwR.cs6fX8hNYdLsBtS2',1),(10,13,'doductrung','$2a$10$A5EFxTEwKrBdO9RKpncQV.UzCiDRFrdSXDeGSTLw8FYv798QUFO6e',1),(11,14,'phamminhtrong','$2a$10$HjCUkXgDMksT0LmfvFP1ae6apfxzG0MrtyCwx93wA9bzEYIlK0Zsy',1),(12,15,'trannguyenminhtuan','$2a$10$8N1.M3C/Cdj7unxJsKWKcu/0hqcrlk70QyZIALyl40nBqgiIDVnlS',1),(13,16,'dotunglam','$2a$10$xRtRFYJpES5/mLDODwwuae96AI9C733ZImSnnBG6zVXW6D4dZG0Bq',1),(14,17,'nguyentiendung','$2a$10$1bMmgmlcBEK/Mqtv8GdhF.1bTT1j925mxOXVJj68VFatD6vrbN//.',1),(15,18,'hoangtuananh','$2a$10$so.ncQh71gnCgJ/GDmyWs.Ak4Mmf9.NidOQxhN9wzjSHYiM0ZCb9a',1),(16,19,'trantrungkien','$2a$10$vGHjY2B0AFIN1K875eRN1eAmVl/1iIh8uWqMggInYykqtcrAAWThm',1),(17,20,'hph','$2a$10$Ff89KvOaKgN4SsBTUxNhYuPNlBzVZ5VKXfZuj78/vpK6iAcrs.QIO',1),(20,23,'hoanglan','$2a$10$Ucw2oeNkfAUj3b1h5LIdju6vxl4IcUHpIwjvFEjCNFYBS9WzaFxEi',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-25 23:44:53
