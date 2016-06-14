-- phpMyAdmin SQL Dump
-- version 2.9.1.1
-- http://www.phpmyadmin.net
-- 
-- Serveur: localhost
-- Généré le : Mercredi 01 Juin 2016 à 08:54
-- Version du serveur: 5.1.73
-- Version de PHP: 5.2.0
-- 
-- Base de données: `test_application_watcher`
-- 

-- --------------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
drop table if exists connection_data_base;
drop table if exists query;
drop table if exists query_result;
drop table if exists rule;
SET FOREIGN_KEY_CHECKS=1;

-- 
-- Structure de la table `connectiondatabase`
-- 
            
CREATE TABLE `connection_data_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(255) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `schema_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- 
-- Contenu de la table `connectiondatabase`
-- 


-- --------------------------------------------------------

-- 
-- Structure de la table `query`
-- 

CREATE TABLE `query` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sql_query` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- 
-- Contenu de la table `query`
-- 


-- --------------------------------------------------------

-- 
-- Structure de la table `query_result`
-- 

CREATE TABLE `query_result` (
  `query_idFk` int(11) NOT NULL,
  `result` varchar(255) DEFAULT NULL,
  KEY `FK_dgyqngt6d7fsvchvtt6bh1lub` (`query_idFk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Contenu de la table `query_result`
-- 


-- --------------------------------------------------------

-- 
-- Structure de la table `rule`
-- 

CREATE TABLE `rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descriptif` varchar(255) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `level` enum('OK','WARNING','CRITICAL','UNKNOWN') NOT NULL,
  `data_base_idFK` int(11) DEFAULT NULL,
  `parent_rule_idFK` int(11) DEFAULT NULL,
  `query_idFK` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qlu2ym514gp2kg9xwvuqudk4e` (`data_base_idFK`),
  KEY `FK_57ia7ohtjt206y1xbvwj3s1b7` (`query_idFK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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

-- 
-- Contenu de la table `rule`
-- 


-- 
-- Contraintes pour les tables exportées
-- 

-- 
-- Contraintes pour la table `query_result`
-- 
ALTER TABLE `query_result`
  ADD CONSTRAINT `FK_dgyqngt6d7fsvchvtt6bh1lub` FOREIGN KEY (`query_idFk`) REFERENCES `query` (`id`);

-- 
-- Contraintes pour la table `rule`
-- 
ALTER TABLE `rule`
  ADD CONSTRAINT `FK_57ia7ohtjt206y1xbvwj3s1b7` FOREIGN KEY (`query_idFK`) REFERENCES `query` (`id`),
  ADD CONSTRAINT `FK_qlu2ym514gp2kg9xwvuqudk4e` FOREIGN KEY (`data_base_idFK`) REFERENCES `connection_data_base` (`id`);
