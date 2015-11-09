-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 07 Octobre 2015 à 16:42
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `pjee`
--

-- --------------------------------------------------------

--
-- Structure de la table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(256) NOT NULL,
  `authority` varchar(256) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('tata', 'ROLE_USER'),
('toto', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `category_forum`
--

CREATE TABLE IF NOT EXISTS `category_forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme_id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `posts_number` int(11) DEFAULT NULL,
  `profile` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `theme` (`theme_id`),
  KEY `theme_id` (`theme_id`),
  KEY `profile` (`profile`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `category_forum`
--

INSERT INTO `category_forum` (`id`, `theme_id`, `name`, `posts_number`, `profile`) VALUES
(1, 1, 'Planètes du Système Solaire', 0, 1),
(2, 2, 'Étoiles de la Voie Lactée', 0, 2),
(3, 3, 'Discussions sur tout et rien', 3, 1),
(4, 3, 'Insultes pour toto', 10, 1),
(5, 1, 'Zodiac', 1, 2),
(6, 3, 'Lorem Ipsum', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `post_forum`
--

CREATE TABLE IF NOT EXISTS `post_forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `title` varchar(256) NOT NULL,
  `content` longtext NOT NULL,
  `profile` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryname` (`category_id`),
  KEY `profile` (`profile`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `post_forum`
--

INSERT INTO `post_forum` (`id`, `category_id`, `title`, `content`, `profile`, `timestamp`, `type`) VALUES
(1, 3, 'Pour dire quelque chose !', '<p>Et &ccedil;a ne sert &agrave; rien !</p>\r\n', 1, '2015-09-17 07:59:07', 'success'),
(2, 3, 'Super, et ?', '<p>Pffff</p>\r\n', 2, '2015-09-17 08:26:49', 'danger'),
(3, 4, 'Sale con', '<p>Sale con !</p>\r\n', 2, '2015-09-17 11:32:53', 'danger'),
(4, 4, 'Euh ?', '<p>Je ne te connais pas, vas-t-en !</p>\r\n', 1, '2015-09-18 14:30:37', 'primary'),
(7, 3, 'Pas grave', '<p>C&#39;est pas grave ! :D</p>\r\n', 1, '2015-09-17 13:24:24', 'default'),
(8, 4, 'lol', '<p>Trou du cul</p>\r\n', 2, '2015-09-17 13:35:32', 'warning'),
(9, 4, 'Mais quoi ?', '<p>Hey, c&#39;est quoi ton probl&egrave;me ?</p>\r\n', 1, '2015-09-18 14:46:27', 'info'),
(10, 5, 'Jupiter', '<p>Je ne me souviens plus quel est le signe du zodiac associ&eacute; &agrave; Jupiter, quelqu&#39;un a une id&eacute;e ?</p>\r\n', 2, '2015-09-17 13:54:02', 'warning'),
(11, 4, 'Hey', '<p>Je fais chier les gens !!!!!!</p>\r\n', 2, '2015-09-29 09:25:11', 'default'),
(12, 4, 'Oh yeah', '<p>Et &ccedil;a marche !</p>\r\n', 2, '2015-09-29 09:25:37', 'info'),
(13, 4, 'Mais !', '<p>J&#39;y crois pas....</p>\r\n', 1, '2015-09-29 09:26:02', 'warning'),
(14, 4, 'Insultes pour toto', '<p>Tu devrais !</p>\r\n', 2, '2015-09-29 09:40:04', 'success'),
(15, 4, '', '<p>Damned</p>\r\n', 1, '2015-09-29 09:40:49', 'danger'),
(16, 6, '', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.</p>\r\n\r\n<p>Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna.</p>\r\n\r\n<p>Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.fff</p>\r\n', 2, '2015-09-29 13:13:56', 'default');

-- --------------------------------------------------------

--
-- Structure de la table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(256) NOT NULL,
  `lastname` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `user` varchar(256) NOT NULL,
  `lastlogindate` timestamp NOT NULL,
  `image` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`,`user`),
  KEY `username` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `profile`
--

INSERT INTO `profile` (`id`, `firstname`, `lastname`, `email`, `age`, `user`, `lastlogindate`, `image`) VALUES
(1, 'PrénomTo', 'NomT', 'toto@toto.com', 9, 'toto', '2015-10-07 13:08:27', 'dG90b0B0b3RvLmNvbQ=='),
(2, 'Tata', 'MynameIsTATA', 'tata@tata.com', 1, 'tata', '2015-10-07 13:09:16', 'dGF0YUB0YXRhLmNvbQ==');

-- --------------------------------------------------------

--
-- Structure de la table `theme_forum`
--

CREATE TABLE IF NOT EXISTS `theme_forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `categories_number` int(11) NOT NULL,
  `posts_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `theme_forum`
--

INSERT INTO `theme_forum` (`id`, `name`, `categories_number`, `posts_number`) VALUES
(1, 'Système Solaire', 1, 2),
(2, 'Voie Lactée', 0, 0),
(3, 'Discussions Aléatoires', 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `unread_post_forum`
--

CREATE TABLE IF NOT EXISTS `unread_post_forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile` int(11) NOT NULL,
  `post_forum` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `profile` (`profile`),
  KEY `post` (`post_forum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('tata', 'tata', 1),
('toto', 'toto', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `category_forum`
--
ALTER TABLE `category_forum`
  ADD CONSTRAINT `category_theme_fk` FOREIGN KEY (`theme_id`) REFERENCES `theme_forum` (`id`),
  ADD CONSTRAINT `FK_1bivafk70h8fspimw0odbuogf` FOREIGN KEY (`profile`) REFERENCES `profile` (`id`);

--
-- Contraintes pour la table `post_forum`
--
ALTER TABLE `post_forum`
  ADD CONSTRAINT `post_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category_forum` (`id`),
  ADD CONSTRAINT `post_forum_profile_fk` FOREIGN KEY (`profile`) REFERENCES `profile` (`id`);

--
-- Contraintes pour la table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `username_fk` FOREIGN KEY (`user`) REFERENCES `users` (`username`);

--
-- Contraintes pour la table `unread_post_forum`
--
ALTER TABLE `unread_post_forum`
  ADD CONSTRAINT `fk_unread_post_post_forum` FOREIGN KEY (`post_forum`) REFERENCES `post_forum` (`id`),
  ADD CONSTRAINT `fk_unread_post_profile` FOREIGN KEY (`profile`) REFERENCES `profile` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
