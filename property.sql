-- SQL Manager Lite for PostgreSQL 5.9.1.49393
-- ---------------------------------------
-- Host      : localhost
-- Database  : property
-- Version   : PostgreSQL 10.1 on x86_64-pc-mingw64, compiled by gcc.exe (Rev5, Built by MSYS2 project) 4.9.2, 64-bit



SET search_path = bms_property_details, pg_catalog;
DROP TABLE IF EXISTS bms_property_details.service_entry;
DROP TABLE IF EXISTS bms_property_details.service_contract;
DROP TABLE IF EXISTS bms_property_details.service_schedule;
DROP TABLE IF EXISTS bms_property_details.assets;
DROP TABLE IF EXISTS bms_property_details.service_provider_master;
DROP TABLE IF EXISTS bms_property_details.finance_year;
DROP TABLE IF EXISTS bms_property_details.fixed_anual_recurring;
DROP TABLE IF EXISTS bms_property_details.facilities;
DROP TABLE IF EXISTS bms_property_details.parking_bay;
DROP TABLE IF EXISTS bms_property_details.block_street;
DROP TABLE IF EXISTS bms_property_details.prop_details_master;
SET check_function_bodies = false;
--
-- Structure for table prop_details_master (OID = 16441) : 
--
CREATE TABLE bms_property_details.prop_details_master (
    prop_id integer NOT NULL,
    name text NOT NULL,
    total_units integer NOT NULL,
    type char(50) NOT NULL,
    email_address text NOT NULL,
    state char(50) NOT NULL,
    jmb_mc char(3) NOT NULL,
    logo bytea NOT NULL
)
WITH (oids = false);
--
-- Structure for table block_street (OID = 16447) : 
--
CREATE TABLE bms_property_details.block_street (
    prop_id integer NOT NULL,
    area char(50) NOT NULL
)
WITH (oids = false);
--
-- Structure for table parking_bay (OID = 16450) : 
--
CREATE TABLE bms_property_details.parking_bay (
    prop_id integer NOT NULL,
    area char(50) NOT NULL,
    area_description char(60) NOT NULL,
    floor char(40) NOT NULL,
    bay_count integer NOT NULL
)
WITH (oids = false);
--
-- Structure for table facilities (OID = 16453) : 
--
CREATE TABLE bms_property_details.facilities (
    prop_id integer NOT NULL,
    facility_type char(100) NOT NULL
)
WITH (oids = false);
--
-- Structure for table fixed_anual_recurring (OID = 16456) : 
--
CREATE TABLE bms_property_details.fixed_anual_recurring (
    prop_id integer NOT NULL,
    description char(100) NOT NULL,
    expiry_date date NOT NULL,
    remind_days_before integer NOT NULL,
    remind_time time without time zone NOT NULL
)
WITH (oids = false);
--
-- Structure for table finance_year (OID = 16459) : 
--
CREATE TABLE bms_property_details.finance_year (
    prop_id integer NOT NULL,
    fyear integer NOT NULL,
    fyear_start date NOT NULL,
    fyear_end date NOT NULL,
    remind_days integer NOT NULL,
    remind_time time without time zone NOT NULL
)
WITH (oids = false);
--
-- Structure for table service_provider_master (OID = 16462) : 
--
CREATE TABLE bms_property_details.service_provider_master (
    sp_id integer NOT NULL,
    sp_name char(100) NOT NULL,
    category char(100) NOT NULL,
    contractual boolean NOT NULL,

    address text NOT NULL,
    post_code integer NOT NULL,
    city char(100) NOT NULL,
    state char(100) NOT NULL,
    country char(100) NOT NULL,
    office_phone char(20) NOT NULL,
    office_fax char(20) NOT NULL,
    person_mobile char(15) NOT NULL,
    email_address char(100) NOT NULL,.

    person_incharge char(100) NOT NULL,  
    
    password char(50),
    contract_start date,
    contract_end date,
    contract_exp_reminder integer,
    reminder_time time without time zone,
    head_count integer,
    duration char(20),   
    monthly_payment integer,
    annual_payment integer,
    job_scope text,
    attachments bytea
)
WITH (oids = false);
--
-- Structure for table assets (OID = 16484) : 
--
CREATE TABLE bms_property_details.assets (
    prop_id integer NOT NULL,
    ast_id integer NOT NULL,
    ast_name char(100) NOT NULL,
    category char(100) NOT NULL,
    ast_description text NOT NULL,
    sp_id integer NOT NULL,
    manufacturer char(100),
    ast_location char(80) NOT NULL,
    serial_no char(40),
    purchase_date date,
    warranty_start date,
    warranty_end date,
    remind_before char(10) NOT NULL,
    reminder_time time without time zone NOT NULL
)
WITH (oids = false);
--
-- Structure for table service_schedule (OID = 16490) : 
--
CREATE TABLE bms_property_details.service_schedule (
    service_id integer NOT NULL,
    ast_id integer NOT NULL,
    ast_description char(80),
    warranty_contract char(30) NOT NULL,
    sp_id integer NOT NULL,
    sp_name char(150),
    srv_schedule_date date NOT NULL,
    srv_schedule_entrydate date NOT NULL,
    remind_days char(2) NOT NULL,
    remind_time time(2) without time zone
)
WITH (oids = false);
ALTER TABLE ONLY bms_property_details.service_schedule ALTER COLUMN ast_description SET STATISTICS 0;
ALTER TABLE ONLY bms_property_details.service_schedule ALTER COLUMN sp_name SET STATISTICS 0;
--
-- Structure for table service_contract (OID = 16494) : 
--
CREATE TABLE bms_property_details.service_contract (
    contract_id integer,
    sp_id integer,
    sp_name char(150),
    contract_startdate date NOT NULL,
    contract_enddate date NOT NULL,
    remind_days char(2),
    remind_time char(2),
    contract_attachment bytea
)
WITH (oids = false);
--
-- Structure for table service_entry (OID = 16500) : 
--
CREATE TABLE bms_property_details.service_entry (
    srvid integer
)
WITH (oids = false);
--
-- Definition for index prop_details_pkey (OID = 16474) : 
--
ALTER TABLE ONLY prop_details_master
    ADD CONSTRAINT prop_details_pkey
    PRIMARY KEY (prop_id);
--
-- Comments
--
COMMENT ON SCHEMA bms_property_details IS 'standard bms_property_details schema';
COMMENT ON COLUMN bms_property_details.service_schedule.warranty_contract IS 'Service under warranty or contract';
COMMENT ON COLUMN bms_property_details.service_schedule.srv_schedule_date IS 'Actual schedule date for service';
COMMENT ON COLUMN bms_property_details.service_schedule.srv_schedule_entrydate IS 'All service schedules entered date';
