-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Dim 01 Janvier 2017 à 20:34
-- Version du serveur :  5.6.20-log
-- Version de PHP :  5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";





/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `geocatching`
--

-- --------------------------------------------------------

--
-- Structure de la table `areas`
--

CREATE TABLE IF NOT EXISTS `areas` (
`z_id` int(11) NOT NULL,
  `z_lat` double DEFAULT NULL,
  `z_lng` double DEFAULT NULL,
  `z_rot` int(11) DEFAULT NULL,
  `z_niveau` int(11) NOT NULL DEFAULT '1',
  `z_nom` varchar(45) DEFAULT NULL,
  `z_id_forme` varchar(45) DEFAULT NULL,
  `z_nbJoueurInZone` int(11) DEFAULT NULL,
  `z_fk_e_id` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `areas`
--

INSERT INTO `areas` (`z_id`, `z_lat`, `z_lng`, `z_rot`, `z_niveau`, `z_nom`, `z_id_forme`, `z_nbJoueurInZone`, `z_fk_e_id`) VALUES
(4, 43.70614939132079, 7.26421058177948, 50, 4, 'IUT Sophia Antipolis', 'Retangle', NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `equipes`
--

CREATE TABLE IF NOT EXISTS `equipes` (
`e_id` int(11) NOT NULL,
  `e_nom` varchar(45) NOT NULL,
  `e_couleur` varchar(45) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `equipes`
--

INSERT INTO `equipes` (`e_id`, `e_nom`, `e_couleur`) VALUES
(2, 'Trompette', 'Rouge'),
(3, 'Café', 'Bleu');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `areas`
--
ALTER TABLE `areas`
 ADD PRIMARY KEY (`z_id`), ADD KEY `z_fk_e_id` (`z_fk_e_id`);

--
-- Index pour la table `equipes`
--
ALTER TABLE `equipes`
 ADD PRIMARY KEY (`e_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `areas`
--
ALTER TABLE `areas`
MODIFY `z_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `equipes`
--
ALTER TABLE `equipes`
MODIFY `e_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `areas`
--
ALTER TABLE `areas`
ADD CONSTRAINT `areas_ibfk_1` FOREIGN KEY (`z_fk_e_id`) REFERENCES `equipes` (`e_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
