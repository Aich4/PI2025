-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2025 at 04:08 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pidev`
--

-- --------------------------------------------------------

--
-- Table structure for table `abonnement`
--

CREATE TABLE `abonnement` (
  `id_abonnement` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `statut` varchar(255) NOT NULL,
  `id_Pack` int(11) NOT NULL,
  `date_Souscription` date NOT NULL,
  `date_Expiration` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `activite`
--

CREATE TABLE `activite` (
  `id` int(11) NOT NULL,
  `nom_activite` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `heure` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `id_destination` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `activite`
--

INSERT INTO `activite` (`id`, `nom_activite`, `date`, `heure`, `statut`, `id_destination`) VALUES
(11, 'discoveringg aaaa', '2025-02-21', '12:30', 'disponible', 12);

-- --------------------------------------------------------

--
-- Table structure for table `avis`
--

CREATE TABLE `avis` (
  `id` int(11) NOT NULL,
  `description_av` varchar(255) NOT NULL,
  `id_des` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `nbr_partenaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `description`, `logo`, `nbr_partenaire`) VALUES
(2, 'Sport', 'aaaa', 'C:\\Users\\MSI\\Downloads\\81k+OmLc5EL._AC_SL1500_.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `destination`
--

CREATE TABLE `destination` (
  `id` int(11) NOT NULL,
  `nom_destination` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `image_destination` varchar(255) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `temperature` double NOT NULL,
  `rate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `destination`
--

INSERT INTO `destination` (`id`, `nom_destination`, `description`, `image_destination`, `latitude`, `longitude`, `temperature`, `rate`) VALUES
(12, 'Beja', 'c\'est une place dhabdabd', 'file:/C:/Users/MSI/Downloads/Spirale_(Boehm,_1988).svg.png', 15, 14, 15, 2);

-- --------------------------------------------------------

--
-- Table structure for table `destination_activite`
--

CREATE TABLE `destination_activite` (
  `id_destination` int(11) NOT NULL,
  `id_activite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20250212175244', '2025-02-12 18:53:25', 164);

-- --------------------------------------------------------

--
-- Table structure for table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext NOT NULL,
  `headers` longtext NOT NULL,
  `queue_name` varchar(190) NOT NULL,
  `created_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `available_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `delivered_at` datetime DEFAULT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mission`
--

CREATE TABLE `mission` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `points_recompense` int(11) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `id_recompense` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `mission`
--

INSERT INTO `mission` (`id`, `description`, `points_recompense`, `statut`, `id_recompense`) VALUES
(3, 'azsdsdds', 40, 'En cours', 2);

-- --------------------------------------------------------

--
-- Table structure for table `pack`
--

CREATE TABLE `pack` (
  `id_pack` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `nom_pack` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `prix` float NOT NULL,
  `duree` int(11) NOT NULL,
  `avantages` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `pack`
--

INSERT INTO `pack` (`id_pack`, `id_utilisateur`, `nom_pack`, `description`, `prix`, `duree`, `avantages`, `statut`) VALUES
(1, 0, 'Pack2', 'hadhabhda', 17, 20, 'Aventure', 'actif');

-- --------------------------------------------------------

--
-- Table structure for table `partenaire`
--

CREATE TABLE `partenaire` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date_ajout` date NOT NULL,
  `id_categorie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `partenaire`
--

INSERT INTO `partenaire` (`id`, `nom`, `email`, `adresse`, `description`, `date_ajout`, `id_categorie`) VALUES
(2, 'test1', 'test@gmail.com', '2 rue kaka 2040', 'hhhh', '2025-02-21', 2);

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `description_rec` varchar(255) NOT NULL,
  `date_rec` date NOT NULL,
  `type_rec` varchar(255) NOT NULL,
  `etat_rec` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `description_rec`, `date_rec`, `type_rec`, `etat_rec`) VALUES
(1, 'hhhhhhhhhhhhhhh', '2025-02-19', 'bug', NULL),
(2, 'il ya un bug dans ...', '2025-02-20', 'bug', NULL),
(3, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '2025-02-20', 'bug', NULL),
(4, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '2025-02-20', 'bug', NULL),
(5, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '2025-02-20', 'bug', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `recompense`
--

CREATE TABLE `recompense` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `cout_en_points` int(11) NOT NULL,
  `disponibilite` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `recompense`
--

INSERT INTO `recompense` (`id`, `description`, `cout_en_points`, `disponibilite`) VALUES
(2, 'ahjdahdbaaaaaaaa', 78, 'Indisponible'),
(3, 'ystgtgdstgtggt', 12, 'Indisponible');

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `id_rep` int(11) NOT NULL,
  `id_rec` int(11) NOT NULL,
  `date_rep` date NOT NULL,
  `contenu_rep` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reponse`
--

INSERT INTO `reponse` (`id_rep`, `id_rec`, `date_rep`, `contenu_rep`) VALUES
(1, 1, '2025-02-10', 'bb'),
(3, 1, '2025-02-21', 'okkkkkkkkkk');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `photo_profil` varchar(255) DEFAULT NULL,
  `type_user` varchar(255) NOT NULL,
  `photo_carte_f1` varchar(255) DEFAULT NULL,
  `photo_carte_f2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `photo_profil`, `type_user`, `photo_carte_f1`, `photo_carte_f2`) VALUES
(1, 'ahah', 'ajaja', 'ahla@gmail.com', 'ahla1234', 'uploads/profile_1739990043918_Spirale_(Boehm,_1988).svg.png', 'Touriste', NULL, NULL),
(2, 'netej', 'ghodbane', 'netejghodbane@gmail.com', '12345678', 'uploads/profile_1740040017439_Spirale_(Boehm,_1988).svg.png', 'Touriste', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_abonnement`
--

CREATE TABLE `user_abonnement` (
  `id_user` int(11) NOT NULL,
  `id_abonnement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_activite`
--

CREATE TABLE `user_activite` (
  `id_user` int(11) NOT NULL,
  `id_activite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_mission`
--

CREATE TABLE `user_mission` (
  `id_user` int(11) NOT NULL,
  `id_mission` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`id_abonnement`),
  ADD KEY `fk_abonnn` (`id_Pack`);

--
-- Indexes for table `activite`
--
ALTER TABLE `activite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_act` (`id_destination`);

--
-- Indexes for table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_dest` (`id_des`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `destination_activite`
--
ALTER TABLE `destination_activite`
  ADD PRIMARY KEY (`id_destination`,`id_activite`),
  ADD KEY `id_activite` (`id_activite`);

--
-- Indexes for table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Indexes for table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Indexes for table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_recompense` (`id_recompense`) USING BTREE;

--
-- Indexes for table `pack`
--
ALTER TABLE `pack`
  ADD PRIMARY KEY (`id_pack`);

--
-- Indexes for table `partenaire`
--
ALTER TABLE `partenaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_part` (`id_categorie`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_rec`);

--
-- Indexes for table `recompense`
--
ALTER TABLE `recompense`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_rep`),
  ADD KEY `fk_rep` (`id_rec`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_abonnement`
--
ALTER TABLE `user_abonnement`
  ADD PRIMARY KEY (`id_user`,`id_abonnement`),
  ADD KEY `id_abonnement` (`id_abonnement`);

--
-- Indexes for table `user_activite`
--
ALTER TABLE `user_activite`
  ADD PRIMARY KEY (`id_user`,`id_activite`),
  ADD KEY `id_activite` (`id_activite`);

--
-- Indexes for table `user_mission`
--
ALTER TABLE `user_mission`
  ADD PRIMARY KEY (`id_user`,`id_mission`),
  ADD KEY `id_mission` (`id_mission`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `id_abonnement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `activite`
--
ALTER TABLE `activite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `destination`
--
ALTER TABLE `destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mission`
--
ALTER TABLE `mission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pack`
--
ALTER TABLE `pack`
  MODIFY `id_pack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `partenaire`
--
ALTER TABLE `partenaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `recompense`
--
ALTER TABLE `recompense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_rep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `fk_abonnn` FOREIGN KEY (`id_Pack`) REFERENCES `pack` (`id_pack`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `fk_act` FOREIGN KEY (`id_destination`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `id_dest` FOREIGN KEY (`id_des`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `destination_activite`
--
ALTER TABLE `destination_activite`
  ADD CONSTRAINT `destination_activite_ibfk_1` FOREIGN KEY (`id_destination`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `destination_activite_ibfk_2` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `fk_mission_recompense` FOREIGN KEY (`id_recompense`) REFERENCES `recompense` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `partenaire`
--
ALTER TABLE `partenaire`
  ADD CONSTRAINT `fk_part` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fk_rep` FOREIGN KEY (`id_rec`) REFERENCES `reclamation` (`id_rec`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `user_abonnement`
--
ALTER TABLE `user_abonnement`
  ADD CONSTRAINT `user_abonnement_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_abonnement_ibfk_2` FOREIGN KEY (`id_abonnement`) REFERENCES `abonnement` (`id_abonnement`) ON DELETE CASCADE;

--
-- Constraints for table `user_activite`
--
ALTER TABLE `user_activite`
  ADD CONSTRAINT `user_activite_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_activite_ibfk_2` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_mission`
--
ALTER TABLE `user_mission`
  ADD CONSTRAINT `user_mission_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_mission_ibfk_2` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
