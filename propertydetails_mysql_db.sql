-- MySQL dump 10.13  Distrib 5.6.36, for Win64 (x86_64)
--
-- Host: localhost    Database: bms_property_details
-- ------------------------------------------------------
-- Server version	5.6.36-log

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
-- Table structure for table `prop_annual_reminders`
--

DROP TABLE IF EXISTS `prop_annual_reminders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_annual_reminders` (
  `par_id` varchar(45) NOT NULL,
  `par_days` int(11) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `par_time` time DEFAULT NULL,
  PRIMARY KEY (`par_id`),
  UNIQUE KEY `par_days_UNIQUE` (`par_days`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_annual_reminders`
--

LOCK TABLES `prop_annual_reminders` WRITE;
/*!40000 ALTER TABLE `prop_annual_reminders` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_annual_reminders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_assets`
--

DROP TABLE IF EXISTS `prop_assets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_assets` (
  `pa_id` varchar(45) NOT NULL,
  `pa_name` varchar(250) NOT NULL,
  `pac_id` varchar(45) DEFAULT NULL,
  `pa_desc` text,
  `psp_id` varchar(45) DEFAULT NULL,
  `pspc_id` varchar(45) DEFAULT NULL,
  `pcd_id` varchar(45) DEFAULT NULL,
  `pa_manufacturer` varchar(250) DEFAULT NULL,
  `pa_location` varchar(250) DEFAULT NULL,
  `pa_serial_no` varchar(250) NOT NULL,
  `pa_purchase_date` date DEFAULT NULL,
  `pa_warranty_start` date DEFAULT NULL,
  `pa_warranty_end` date DEFAULT NULL,
  `par_id` varchar(45) DEFAULT NULL,
  `par_person_incharge` varchar(250) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`pa_id`),
  UNIQUE KEY `pa_name_UNIQUE` (`pa_name`),
  UNIQUE KEY `pa_serial_no_UNIQUE` (`pa_serial_no`),
  KEY `fk_pa_psp_id` (`psp_id`),
  KEY `fk_pa_pspc_id` (`pspc_id`),
  KEY `fk_pa_pcd_id` (`pcd_id`),
  KEY `fk_ps_par_id` (`par_id`),
  CONSTRAINT `fk_pa_pcd_id` FOREIGN KEY (`pcd_id`) REFERENCES `prop_contact_details` (`pcd_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pa_psp_id` FOREIGN KEY (`psp_id`) REFERENCES `prop_service_provider` (`psp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pa_pspc_id` FOREIGN KEY (`pspc_id`) REFERENCES `prop_service_provider_category` (`pspc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ps_par_id` FOREIGN KEY (`par_id`) REFERENCES `prop_annual_reminders` (`par_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_assets`
--

LOCK TABLES `prop_assets` WRITE;
/*!40000 ALTER TABLE `prop_assets` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_assets_category`
--

DROP TABLE IF EXISTS `prop_assets_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_assets_category` (
  `pac_id` varchar(45) NOT NULL,
  `pac_type` varchar(250) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`pac_id`),
  UNIQUE KEY `pac_name_UNIQUE` (`pac_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_assets_category`
--

LOCK TABLES `prop_assets_category` WRITE;
/*!40000 ALTER TABLE `prop_assets_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_assets_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_block_details`
--

DROP TABLE IF EXISTS `prop_block_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_block_details` (
  `pbd_id` varchar(45) NOT NULL,
  `pdb_no_floors` int(11) NOT NULL,
  `pdb_block_name` varchar(45) NOT NULL,
  `pbd_no_units` int(11) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `pdm_id` varchar(45) NOT NULL,
  PRIMARY KEY (`pbd_id`),
  UNIQUE KEY `pdb_block_name_UNIQUE` (`pdb_block_name`),
  KEY `fk_pdm_id` (`pdm_id`),
  CONSTRAINT `fk_pdm_id` FOREIGN KEY (`pdm_id`) REFERENCES `prop_details_master` (`pdm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_block_details`
--

LOCK TABLES `prop_block_details` WRITE;
/*!40000 ALTER TABLE `prop_block_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_block_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_contact_details`
--

DROP TABLE IF EXISTS `prop_contact_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_contact_details` (
  `pcd_id` varchar(45) NOT NULL,
  `pcd_address_line1` varchar(250) NOT NULL,
  `pcd_address_line2` varchar(150) DEFAULT NULL,
  `pcd_poscode` varchar(15) NOT NULL,
  `ps_id` varchar(45) DEFAULT NULL,
  `pcd_city` varchar(250) DEFAULT NULL,
  `pc_id` varchar(45) DEFAULT NULL,
  `pcd_office_phno` varchar(15) DEFAULT NULL,
  `pcd_office_faxno` varchar(20) DEFAULT NULL,
  `pcd_mobileno` varchar(15) NOT NULL,
  `pcd_emailid` varchar(250) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`pcd_id`),
  KEY `fk_pcd_ps_id` (`ps_id`),
  KEY `fk_pcd_pc_id` (`pc_id`),
  CONSTRAINT `fk_pcd_pc_id` FOREIGN KEY (`pc_id`) REFERENCES `prop_country` (`pc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pcd_ps_id` FOREIGN KEY (`ps_id`) REFERENCES `prop_state` (`ps_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_contact_details`
--

LOCK TABLES `prop_contact_details` WRITE;
/*!40000 ALTER TABLE `prop_contact_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_contact_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_country`
--

DROP TABLE IF EXISTS `prop_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_country` (
  `pc_id` varchar(45) NOT NULL,
  `pc_name` varchar(150) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`pc_id`),
  UNIQUE KEY `pc_name_UNIQUE` (`pc_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_country`
--

LOCK TABLES `prop_country` WRITE;
/*!40000 ALTER TABLE `prop_country` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_details_master`
--

DROP TABLE IF EXISTS `prop_details_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_details_master` (
  `pdm_id` varchar(50) NOT NULL,
  `pdm_name` varchar(250) NOT NULL,
  `pdm_total_units` int(11) NOT NULL,
  `pdm_email` varchar(200) NOT NULL,
  `pdm_jmb_mc` varchar(50) DEFAULT NULL,
  `pdm_logo` blob,
  `pdm_description` text,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `pt_id` varchar(50) NOT NULL,
  `ps_id` varchar(45) NOT NULL,
  PRIMARY KEY (`pdm_id`),
  UNIQUE KEY `pdm_name_UNIQUE` (`pdm_name`),
  KEY `fk_pt_id` (`pt_id`),
  KEY `fk_ps_id` (`ps_id`),
  CONSTRAINT `fk_ps_id` FOREIGN KEY (`ps_id`) REFERENCES `prop_state` (`ps_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pt_id` FOREIGN KEY (`pt_id`) REFERENCES `prop_type` (`pt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_details_master`
--

LOCK TABLES `prop_details_master` WRITE;
/*!40000 ALTER TABLE `prop_details_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_details_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_duration`
--

DROP TABLE IF EXISTS `prop_duration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_duration` (
  `pd_id` varchar(45) NOT NULL,
  `pd_type` varchar(250) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`pd_id`),
  UNIQUE KEY `pd_type_UNIQUE` (`pd_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_duration`
--

LOCK TABLES `prop_duration` WRITE;
/*!40000 ALTER TABLE `prop_duration` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_duration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_facilities`
--

DROP TABLE IF EXISTS `prop_facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_facilities` (
  `pf_id` varchar(45) NOT NULL,
  `pf_type` varchar(250) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `pdm_id` varchar(45) NOT NULL,
  PRIMARY KEY (`pf_id`),
  UNIQUE KEY `pf_type_UNIQUE` (`pf_type`),
  KEY `fk_pf_pdm_id` (`pdm_id`),
  CONSTRAINT `fk_pf_pdm_id` FOREIGN KEY (`pdm_id`) REFERENCES `prop_details_master` (`pdm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_facilities`
--

LOCK TABLES `prop_facilities` WRITE;
/*!40000 ALTER TABLE `prop_facilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_finance_year`
--

DROP TABLE IF EXISTS `prop_finance_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_finance_year` (
  `pfy_id` varchar(45) NOT NULL,
  `pfy_year` int(11) DEFAULT NULL,
  `pfy_year_start` date DEFAULT NULL,
  `pfy_year_end` date DEFAULT NULL,
  `par_id` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `pdm_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pfy_id`),
  KEY `fk_pfy_pdm_id` (`pdm_id`),
  KEY `fk_pfy_par_id` (`par_id`),
  CONSTRAINT `fk_pfy_par_id` FOREIGN KEY (`par_id`) REFERENCES `prop_annual_reminders` (`par_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pfy_pdm_id` FOREIGN KEY (`pdm_id`) REFERENCES `prop_details_master` (`pdm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_finance_year`
--

LOCK TABLES `prop_finance_year` WRITE;
/*!40000 ALTER TABLE `prop_finance_year` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_finance_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_fixed_anual_recurring`
--

DROP TABLE IF EXISTS `prop_fixed_anual_recurring`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_fixed_anual_recurring` (
  `pfar_id` varchar(45) NOT NULL,
  `pfar_description` varchar(250) NOT NULL,
  `pfar_expiry_date` date NOT NULL,
  `par_id` varchar(45) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `pdm_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pfar_id`),
  KEY `fk_pfar_pdm_id` (`pdm_id`),
  KEY `fk_pfar_par_id` (`par_id`),
  CONSTRAINT `fk_pfar_par_id` FOREIGN KEY (`par_id`) REFERENCES `prop_annual_reminders` (`par_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pfar_pdm_id` FOREIGN KEY (`pdm_id`) REFERENCES `prop_details_master` (`pdm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_fixed_anual_recurring`
--

LOCK TABLES `prop_fixed_anual_recurring` WRITE;
/*!40000 ALTER TABLE `prop_fixed_anual_recurring` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_fixed_anual_recurring` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_parking_bay_details`
--

DROP TABLE IF EXISTS `prop_parking_bay_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_parking_bay_details` (
  `ppbd_id` varchar(45) NOT NULL,
  `ppbd_floor` int(11) NOT NULL,
  `ppbd_no_bays` int(11) NOT NULL,
  `ppbd_desc` varchar(150) DEFAULT NULL,
  `pbd_id` varchar(45) NOT NULL,
  `pdm_id` varchar(45) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`ppbd_id`),
  KEY `fk_pbd_id` (`pbd_id`),
  KEY `fk_pdm_if` (`pdm_id`),
  CONSTRAINT `fk_pbd_id` FOREIGN KEY (`pbd_id`) REFERENCES `prop_block_details` (`pbd_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pdm_if` FOREIGN KEY (`pdm_id`) REFERENCES `prop_details_master` (`pdm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_parking_bay_details`
--

LOCK TABLES `prop_parking_bay_details` WRITE;
/*!40000 ALTER TABLE `prop_parking_bay_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_parking_bay_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_service_provider`
--

DROP TABLE IF EXISTS `prop_service_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_service_provider` (
  `psp_id` varchar(45) NOT NULL,
  `psp_name` varchar(250) NOT NULL,
  `pspc_id` varchar(45) NOT NULL,
  `psp_contractual` tinyint(1) DEFAULT NULL,
  `pcd_id` varchar(45) NOT NULL,
  `psp_person_incharge` varchar(250) NOT NULL,
  `psp_password` text,
  `psp_contract_start` date NOT NULL,
  `psp_contract_end` date NOT NULL,
  `par_id` varchar(45) DEFAULT NULL,
  `psp_head_count` int(11) DEFAULT NULL,
  `pd_id` varchar(45) DEFAULT NULL,
  `psp_monthly_payment` double NOT NULL,
  `psp_annual_payment` double NOT NULL,
  `psp_job_scope` text,
  `psp_documents` blob,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`psp_id`),
  UNIQUE KEY `psp_name_UNIQUE` (`psp_name`),
  KEY `fk_pcd_id` (`pcd_id`),
  KEY `fk_pspc_id` (`pspc_id`),
  KEY `fk_pd_id` (`pd_id`),
  KEY `fk_par_id` (`par_id`),
  CONSTRAINT `fk_par_id` FOREIGN KEY (`par_id`) REFERENCES `prop_annual_reminders` (`par_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pcd_id` FOREIGN KEY (`pcd_id`) REFERENCES `prop_contact_details` (`pcd_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pd_id` FOREIGN KEY (`pd_id`) REFERENCES `prop_duration` (`pd_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pspc_id` FOREIGN KEY (`pspc_id`) REFERENCES `prop_service_provider_category` (`pspc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_service_provider`
--

LOCK TABLES `prop_service_provider` WRITE;
/*!40000 ALTER TABLE `prop_service_provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_service_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_service_provider_category`
--

DROP TABLE IF EXISTS `prop_service_provider_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_service_provider_category` (
  `pspc_id` varchar(45) NOT NULL,
  `pspc_name` varchar(250) NOT NULL,
  `pspc_desc` text,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  PRIMARY KEY (`pspc_id`),
  UNIQUE KEY `pspc_name_UNIQUE` (`pspc_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_service_provider_category`
--

LOCK TABLES `prop_service_provider_category` WRITE;
/*!40000 ALTER TABLE `prop_service_provider_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_service_provider_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_state`
--

DROP TABLE IF EXISTS `prop_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_state` (
  `ps_id` varchar(45) NOT NULL,
  `ps_name` varchar(250) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `pc_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ps_id`),
  UNIQUE KEY `ps_name_UNIQUE` (`ps_name`),
  KEY `fk_pc_id` (`pc_id`),
  CONSTRAINT `fk_pc_id` FOREIGN KEY (`pc_id`) REFERENCES `prop_country` (`pc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_state`
--

LOCK TABLES `prop_state` WRITE;
/*!40000 ALTER TABLE `prop_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prop_type`
--

DROP TABLE IF EXISTS `prop_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prop_type` (
  `pt_id` varchar(50) NOT NULL,
  `pt_type` varchar(150) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_dt` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_dt` date DEFAULT NULL,
  `pt_desc` text,
  PRIMARY KEY (`pt_id`),
  UNIQUE KEY `pt_type_UNIQUE` (`pt_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prop_type`
--

LOCK TABLES `prop_type` WRITE;
/*!40000 ALTER TABLE `prop_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `prop_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-02 23:15:22
