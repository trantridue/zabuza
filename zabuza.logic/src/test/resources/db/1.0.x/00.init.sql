-- phpMyAdmin SQL Dump
-- version 2.9.1.1
-- http://www.phpmyadmin.net
-- 
-- Serveur: localhost
-- Généré le : Mercredi 01 Juin 2016 à 08:54
-- Version du serveur: 5.1.73
-- Version de PHP: 5.2.0
-- 
-- Base de données: `test_zabuza`
-- 

-- --------------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
drop table if exists connection_data_base;
drop table if exists query;
drop table if exists query_result;
drop table if exists rule;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE  `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `create_date` DATETIME,
  `update_date` DATETIME,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

