-- SQL Manager Lite for PostgreSQL 5.9.1.49393
-- ---------------------------------------
-- Host      : localhost
-- Database  : property
-- Version   : PostgreSQL 10.1 on x86_64-pc-mingw64, compiled by gcc.exe (Rev5, Built by MSYS2 project) 4.9.2, 64-bit



SET search_path = public, pg_catalog;
DROP TABLE IF EXISTS public.assets;
DROP TABLE IF EXISTS public.service_provider;
DROP TABLE IF EXISTS public.finance_year;
DROP TABLE IF EXISTS public.fixed_anual_recurring;
DROP TABLE IF EXISTS public.facilities;
DROP TABLE IF EXISTS public.parking_bay;
DROP TABLE IF EXISTS public.block_street;
DROP TABLE IF EXISTS public.prop_details;
SET check_function_bodies = false;
--
-- Structure for table prop_details (OID = 16386) : 
--
CREATE TABLE public.prop_details (
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
-- Structure for table block_street (OID = 16394) : 
--
CREATE TABLE public.block_street (
    prop_id integer NOT NULL,
    area char(50) NOT NULL
)
WITH (oids = false);
--
-- Structure for table parking_bay (OID = 16397) : 
--
CREATE TABLE public.parking_bay (
    prop_id integer NOT NULL,
    area char(50) NOT NULL,
    area_description char(60) NOT NULL,
    floor char(40) NOT NULL,
    bay_count integer NOT NULL
)
WITH (oids = false);
--
-- Structure for table facilities (OID = 16400) : 
--
CREATE TABLE public.facilities (
    prop_id integer NOT NULL,
    facility_type char(100) NOT NULL
)
WITH (oids = false);
--
-- Structure for table fixed_anual_recurring (OID = 16403) : 
--
CREATE TABLE public.fixed_anual_recurring (
    prop_id integer NOT NULL,
    description char(100) NOT NULL,
    expiry_date date NOT NULL,
    remind_days_before integer NOT NULL,
    remind_time time without time zone NOT NULL
)
WITH (oids = false);
--
-- Structure for table finance_year (OID = 16406) : 
--
CREATE TABLE public.finance_year (
    prop_id integer NOT NULL,
    fyear integer NOT NULL,
    fyear_start date NOT NULL,
    fyear_end date NOT NULL,
    remind_days integer NOT NULL,
    remind_time time without time zone NOT NULL
)
WITH (oids = false);
--
-- Structure for table service_provider (OID = 16421) : 
--
CREATE TABLE public.service_provider (
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
    person_incharge char(100) NOT NULL,
    person_mobile char(15) NOT NULL,
    email_address char(100) NOT NULL,
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
-- Structure for table assets (OID = 16433) : 
--
CREATE TABLE public.assets (
    prop_id integer NOT NULL,
    as_id integer NOT NULL,
    as_name char(100) NOT NULL,
    category char(100) NOT NULL,
    as_description text NOT NULL,
    manufacturer char(100) NOT NULL,
    as_location char(80) NOT NULL,
    serial_no char(40) NOT NULL,
    purchase_date date NOT NULL,
    warranty_start date NOT NULL,
    warranty_end date NOT NULL,
    remind_before char(10) NOT NULL,
    reminder_time time without time zone NOT NULL,
    sp_name char(100) NOT NULL,
    sp_category char(100) NOT NULL,
    address text NOT NULL,
    post_code integer NOT NULL,
    city char(100) NOT NULL,
    state char(100) NOT NULL,
    country char(100) NOT NULL,
    office_phone char(20) NOT NULL,
    person_incharge char(100) NOT NULL,
    person_mobile char(15) NOT NULL,
    email_address char(100) NOT NULL
)
WITH (oids = false);
--
-- Definition for index prop_details_pkey (OID = 16392) : 
--
ALTER TABLE ONLY prop_details
    ADD CONSTRAINT prop_details_pkey
    PRIMARY KEY (prop_id);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
