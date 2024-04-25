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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'trhquan','ROLE_ADMIN'),(2,'admin','ROLE_ADMIN'),(3,'pxp','ROLE_TEACHER'),(5,'pmt','ROLE_MANAGER'),(6,'huynhdanglinh','ROLE_ADMIN'),(7,'nguyenquocmanh','ROLE_TEACHER'),(8,'nguyentheanh','ROLE_TEACHER'),(9,'phamquangduan','ROLE_TEACHER'),(10,'doductrung','ROLE_TEACHER'),(11,'phamminhtrong','ROLE_TEACHER'),(12,'trannguyenminhtuan','ROLE_TEACHER'),(13,'dotunglam','ROLE_TEACHER'),(14,'nguyentiendung','ROLE_TEACHER'),(15,'hoangtuananh','ROLE_TEACHER'),(16,'trantrungkien','ROLE_TEACHER');
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
  `booking_equi_id` int(10) unsigned NOT NULL,
  `content_id` int(10) unsigned NOT NULL,
  `booking_date` date NOT NULL,
  `comfirm_status` tinyint(4) NOT NULL,
  `work_times` int(11) NOT NULL,
  `note` varchar(255) NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_lab_id_foreign` (`lab_id`),
  KEY `booking_booking_equi_id_foreign` (`booking_equi_id`),
  KEY `booking_content_id_foreign` (`content_id`),
  CONSTRAINT `booking_booking_equi_id_foreign` FOREIGN KEY (`booking_equi_id`) REFERENCES `booking_equi` (`id`),
  CONSTRAINT `booking_content_id_foreign` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  CONSTRAINT `booking_lab_id_foreign` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
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
  `booking_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_equi`
--

LOCK TABLES `booking_equi` WRITE;
/*!40000 ALTER TABLE `booking_equi` DISABLE KEYS */;
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
  `experiment_report` int(10) unsigned NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `amount_of_people` int(11) NOT NULL,
  `list_id_Participants` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `content_reservationist_id_foreign` (`reservationist_id`),
  KEY `content_experiment_report_foreign_idx` (`experiment_report`),
  CONSTRAINT `content_experiment_report_foreign` FOREIGN KEY (`experiment_report`) REFERENCES `experiment_report` (`id`),
  CONSTRAINT `content_reservationist_id_foreign` FOREIGN KEY (`reservationist_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
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
  `series` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `series_fixed` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'[98YIH7Y5, 232030, 65511TD651, 85559ED6]','Máy hiện sóng','[]',4,0,'[65511TD651]'),(2,'[R8S59ED6, 88TFG4S]','Máy tạo dao động','[]',2,0,'[]'),(3,'[88RF96SD, 99ED666VD555]','Con lắc dây','Con lắc dao động dây quả sắt 1kg',3,0,'[99ED666VD555, 1295ED69]'),(4,'[1234-ABCD, 5678-EFGH, 9012-IJKL]','Máy đo độ dẫn điện','Đo độ dẫn điện của dung dịch',3,0,'[]'),(5,'[7890-QRST, 1234-UVWX]','Máy đo độ nhớt','Đo độ nhớt của chất lỏng',3,0,'[]'),(6,'[5678-YZAB, 9012-CDEF, 3456-GHIJ]','Máy đo độ brix','Đo nồng độ Brix của dung dịch đường',3,0,'[]'),(7,'[7890-KLMN, 1234-OPQR, 5678-STUV]','Máy đo pH','Đo độ pH của dung dịch',3,0,'[]'),(8,'[9012-WXYZ, 3456-ABCD, 7890-EFGH]','Máy quang phổ hấp thụ nguyên tử','Xác định thành phần nguyên tố trong mẫu',3,0,'[]'),(9,'[1234-IJKL, 5678-MNOP, 9012-QRST]','Máy quang phổ huỳnh quang','Phân tích các hợp chất huỳnh quang',3,0,'[]'),(10,'[3456-UVWX, 7890-YZAB, 1234-CDEF]','Kính hiển vi điện tử','Quan sát vi sinh vật và cấu trúc tế bào',3,0,'[]'),(11,'[5678-GHIJ, 9012-KLMN, 3456-OPQR]','Máy đo điện thế','Đo điện thế giữa hai điểm',3,0,'[]'),(12,'[7890-STUV, 1234-WXYZ, 5678-ABCD]','Máy đo điện trở','Đo điện trở của vật liệu',3,0,'[]'),(13,'[9012-EFGH, 3456-IJKL, 7890-MNOP]','Máy đo cường độ dòng điện','Đo cường độ dòng điện',3,0,'[]'),(14,'[1234-QRST, 5678-UVWX, 9012-YZAB]','Máy đo điện áp','Đo điện áp giữa hai điểm',3,0,'[]'),(15,'[3456-CDEF, 7890-GHIJ, 1234-KLMN]','Máy đo công suất','Đo công suất điện',3,0,'[]'),(16,'[5678-OPQR, 9012-STUV, 3456-WXYZ]','Máy đo năng lượng','Đo năng lượng điện',3,0,'[]'),(17,'[7890-ABCD, 1234-EFGH, 5678-IJKL]','Máy đo thời gian','Đo thời gian',3,0,'[]'),(18,'[9012-MNOP, 3456-QRST, 7890-UVWX]','Máy đo tần số','Đo tần số của tín hiệu',3,0,'[]'),(19,'[1234-WXYZ, 5678-ABCD, 9012-EFGH]','Máy đo áp suất','Đo áp suất của chất khí hoặc chất lỏng',3,0,'[]'),(20,'[3456-IJKL, 7890-MNOP, 1234-QRST]','Máy đo chân không','Đo mức độ chân không',3,0,'[]'),(21,'[5678-UVWX, 9012-YZAB, 3456-CDEF]','Máy đo độ ẩm','Đo độ ẩm của không khí',3,0,'[]'),(22,'[7890-GHIJ, 1234-KLMN, 5678-OPQR]','Máy đo lưu lượng','Đo lưu lượng của chất lỏng hoặc chất khí',3,0,'[]'),(23,'[9012-STUV, 3456-WXYZ, 7890-ABCD]','Máy đo tốc độ','Đo tốc độ của vật thể chuyển',3,0,'[]'),(24,'[MV-12345, MV-67890, MV-23456]','Kính hiển vi cơ học','Quan sát vi sinh vật và cấu trúc tế bào',3,0,'[]'),(25,'[LC-12345, LC-67890, LC-23456]','Máy ly tâm','Phân tách các thành phần trong hỗn hợp',3,0,'[]'),(26,'[TO-12345, TO-67890, TO-23456]','Tủ ấm','Giữ mẫu ở nhiệt độ ổn định',3,0,'[]'),(27,'[PCR-12345, PCR-67890, PCR-23456]','Máy PCR','Khuếch đại DNA',3,0,'[]'),(28,'[SC-12345, SC-67890, SC-23456]','Máy sắc ký','Phân ly hỗn hợp các chất',3,0,'[]'),(29,'[DQ-12345, DQ-67890, DQ-23456]','Máy đo quang','Đo độ hấp thụ ánh sáng của dung dịch',3,0,'[]'),(30,'[PC-12345, PC-67890, PC-23456]','Máy tính','Xử lý thông tin và chạy phần mềm',3,0,'[]'),(31,'[MN-12345, MN-67890, MN-23456]','Màn hình','Hiển thị thông tin trên máy tính',3,0,'[]'),(32,'[BK-12345, BK-67890, BK-23456]','Bàn phím','Nhập dữ liệu và điều khiển máy tính',3,0,'[]'),(33,'[CT-12345, CT-67890, CT-23456]','Chuột','Di chuyển con trỏ trên màn hình',3,0,'[]'),(34,'[IN-12345, IN-67890, IN-23456]','Máy in','In ra giấy các tài liệu và hình ảnh',3,0,'[]'),(35,'[MC-12345, MC-67890, MC-23456]','Máy chiếu','Chiếu hình ảnh và video lên màn hình',3,0,'[]'),(36,'[LOA-12345, LOA-67890, LOA-23456]','Loa','Phát âm thanh',3,0,'[]'),(37,'[WC-12345, WC-67890, WC-23456]','Webcam','Quay video và chụp ảnh',3,0,'[]'),(38,'[PC-12345, PC-67890, PC-23456]','Máy tính','Viết và chạy chương trình máy tính',3,0,'[]'),(39,'[BD-12345, BD-67890, BD-23456]','Biên dịch viên','Chuyển đổi mã nguồn thành mã máy',3,0,'[]'),(40,'[GL-12345, GL-67890, GL-23456]','Giả lập','Mô phỏng môi trường thực để thử nghiệm chương trình',3,0,'[]'),(41,'[DL-12345, DL-67890, DL-23456]','Gỡ lỗi','Tìm và sửa lỗi trong chương trình',3,0,'[]'),(42,'[CT-12345, CT-67890, CT-23456]','Chạy thử','Kiểm tra hoạt động của chương trình',3,0,'[]');
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
  `equi_series` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `equipment_lab_equi_id_foreign` (`equi_id`),
  KEY `equipment_lab_lab_id_foreign` (`lab_id`),
  CONSTRAINT `equipment_lab_equi_id_foreign` FOREIGN KEY (`equi_id`) REFERENCES `equipment` (`id`),
  CONSTRAINT `equipment_lab_lab_id_foreign` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_lab`
--

LOCK TABLES `equipment_lab` WRITE;
/*!40000 ALTER TABLE `equipment_lab` DISABLE KEYS */;
INSERT INTO `equipment_lab` VALUES (8,12,2,'[85R569RF5]'),(11,12,1,'[1E569RFF555]'),(13,12,5,'[3456-MNOP]'),(14,12,3,'[1295ED69]');
/*!40000 ALTER TABLE `equipment_lab` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_group`
--

LOCK TABLES `experiment_group` WRITE;
/*!40000 ALTER TABLE `experiment_group` DISABLE KEYS */;
INSERT INTO `experiment_group` VALUES (1,'Đào tạo'),(2,'Nghiên cứu khoa học'),(3,'Sản xuất chế thử');
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
  `report_type` varchar(255) NOT NULL,
  `experiment_type_id` int(10) unsigned NOT NULL,
  `scores` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `experiment_report_experiment_type_id_foreign` (`experiment_type_id`),
  CONSTRAINT `experiment_report_experiment_type_id_foreign` FOREIGN KEY (`experiment_type_id`) REFERENCES `experiment_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_report`
--

LOCK TABLES `experiment_report` WRITE;
/*!40000 ALTER TABLE `experiment_report` DISABLE KEYS */;
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
  `experiment_group_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `experiment_type_experiment_group_id_foreign` (`experiment_group_id`),
  CONSTRAINT `experiment_type_experiment_group_id_foreign` FOREIGN KEY (`experiment_group_id`) REFERENCES `experiment_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_type`
--

LOCK TABLES `experiment_type` WRITE;
/*!40000 ALTER TABLE `experiment_type` DISABLE KEYS */;
INSERT INTO `experiment_type` VALUES (1,'Hướng dẫn thí nghiệm, thực hành, thực tập, làm bài tập lớn, đồ án môn học cho các loại hình đào tạo.',1),(2,'Luận án tiến sỹ',1),(3,'Luận văn cao học',1),(4,'Đồ án tốt nghiệp',1),(5,'Đề tài các cấp',2),(6,'Đề tài cấp Nhà nước',2),(7,'Đề tài cấp Bộ',2),(8,'Đề tài cấp Ngành',2),(9,'Đề tài cấp Học viện',2),(10,'Đề tài của học viên, sinh viên',2),(11,'Viết bài báo',2),(12,'Sản xuất, chế thử vật tư, thiết bị',3),(13,'Viết phầm mềm',3);
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
  PRIMARY KEY (`id`),
  KEY `lab_lab_managemet_id_foreign` (`lab_managemet_id`),
  CONSTRAINT `lab_lab_managemet_id_foreign` FOREIGN KEY (`lab_managemet_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` VALUES (1,'A5 201',60,'Khu A',1,1),(2,'A5 202',60,'Khu A',1,1),(3,'A5 203',60,'Khu A',1,1),(4,'B 101',60,'Khu B',1,1),(5,'B 201',60,'Khu B',1,1),(6,'F 206',60,'Khu F1',1,1),(7,'F 207',60,'Khu F1',1,1),(8,'D 208',60,'Khu D',1,1),(9,'D 209',60,'Khu D',1,1),(12,'Thực hành Vật lý 2',60,'P205, P206 Tòa N3, Khu A',2,0),(13,'Thực hành Vật lý 1',60,'P205, P206 Tòa N3, Khu A',1,0),(14,'Thực hành Hoá học',40,'P301 Tòa N3, Khu A',1,0),(16,'Thực hành Sinh học',50,'P202 Tòa N3, Khu A',2,0),(17,'Thực hành Tin học',30,'P104 Tòa N2, Khu B',2,0),(18,'Ngôn ngữ máy tính',25,'P103 Tòa N2, Khu B',2,0),(19,'Thực hành Mạng máy tính',20,'P102 Tòa N2, Khu B',2,0);
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
  `military_number` int(11) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'Trần Hồng Quân','Thượng úy','Lớp CNTT12',123456789,'trhquan@gmail.com',0),(2,'Nguyễn Hoàng Nam','Trung úy','Khoa Rada',987654321,'HoangNam@gamil.com',0),(6,'Phạm Xuân Phương','Đại úy','Lớp CNTT12',968574321,'0965362452',0),(8,'Phạm Minh Trọng','Thượng úy','Lớp CNTT 12',963582147,'0365298741',0),(9,'Huỳnh Đăng Linh','Đại úy','Viện CNTT',532146987,'0541236984',0),(10,'NGUYỄN QUỐC MẠNH','Đại úy','Lớp CNTT12',977230693,'0977230693',0),(11,'NGUYỄN THẾ ANH','Đại úy','Lớp CNTT12',966446483,'0966446483',0),(12,'PHẠM QUANG DUẨN','Thượng úy','Lớp CNTT12',357575223,'0357575223',0),(13,'ĐỖ ĐỨC TRUNG','Đại úy','Lớp CNTT12',357476802,'0357476802',0),(14,'PHẠM MINH TRỌNG','Thượng úy','Lớp CNTT12',356599692,'0356599692',0),(15,'TRẦN NGUYỄN MINH TUẤN','Thượng úy','Lớp CNTT12',974187915,'0974187915',0),(16,'ĐỖ TÙNG LÂM','Thượng úy','Lớp CNTT12',365079956,'0365079956',0),(17,'NGUYỄN TIẾN DŨNG','Thượng úy','Lớp CNTT12',376172992,'0376172992',0),(18,'HOÀNG TUẤN ANH','Thượng úy','Lớp CNTT12',364547516,'0364547516',0),(19,'TRẦN TRUNG KIÊN','Thượng úy','Lớp CNTT12',964580285,'0964580285',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MANAGER'),(3,'ROLE_TEACHER'),(10,'ROLE_TAECHERWAIT'),(12,'ROLE_RESERVATIONIST');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
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
  KEY `users_people_id_foreign` (`people_id`),
  CONSTRAINT `users_people_id_foreign` FOREIGN KEY (`people_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'trhquan','$2a$12$EVtrfH74.jqdOAIVAXg4MufISL/.UV6dYGWVEisUr/03TvuEu7D3u',1),(2,2,'admin','$2a$10$ye0tWfeOFhwOE0alr/iJJ.RiYdi4aBpU1Wkf22DP3xQosVqsKnwFe',1),(3,6,'pxp','$2a$10$r39hMxSDsVXZZRZUKh8sm.uSUKpZX827Ije0J/ZKkXTW7wltqjfGi',1),(5,8,'pmt','$2a$10$NZiByPPdk6jbkPFeRgYgWOEvgmsx/IsuEV3LjQfOVGzf7khu64M3W',1),(6,9,'huynhdanglinh','$2a$10$jwbW1km8D5OngHDyInvuzu8FybYL8WW4K/KnhZRJMo4.9nqZ/g1t.',1),(7,10,'nguyenquocmanh','$2a$10$Ryfo5RdNMKv6l5jHehnHHejM0f7.rxDDjYVbP3eov2tWlPBD6096m',1),(8,11,'nguyentheanh','$2a$10$TUhk8a6iCQ2ocuK2BT5uAetp9Gt2NPQhLWyLxy8UPVAH6fu3hGCia',1),(9,12,'phamquangduan','$2a$10$SY.Oz4fCh/L.lLUVIS2fJeI0zYA.uhJ7hFwR.cs6fX8hNYdLsBtS2',1),(10,13,'doductrung','$2a$10$A5EFxTEwKrBdO9RKpncQV.UzCiDRFrdSXDeGSTLw8FYv798QUFO6e',1),(11,14,'phamminhtrong','$2a$10$HjCUkXgDMksT0LmfvFP1ae6apfxzG0MrtyCwx93wA9bzEYIlK0Zsy',1),(12,15,'trannguyenminhtuan','$2a$10$8N1.M3C/Cdj7unxJsKWKcu/0hqcrlk70QyZIALyl40nBqgiIDVnlS',1),(13,16,'dotunglam','$2a$10$xRtRFYJpES5/mLDODwwuae96AI9C733ZImSnnBG6zVXW6D4dZG0Bq',1),(14,17,'nguyentiendung','$2a$10$1bMmgmlcBEK/Mqtv8GdhF.1bTT1j925mxOXVJj68VFatD6vrbN//.',1),(15,18,'hoangtuananh','$2a$10$so.ncQh71gnCgJ/GDmyWs.Ak4Mmf9.NidOQxhN9wzjSHYiM0ZCb9a',1),(16,19,'trantrungkien','$2a$10$vGHjY2B0AFIN1K875eRN1eAmVl/1iIh8uWqMggInYykqtcrAAWThm',1);
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

-- Dump completed on 2024-04-24 14:51:29
