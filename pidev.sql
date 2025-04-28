-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2025 at 04:51 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

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

--
-- Dumping data for table `abonnement`
--

INSERT INTO `abonnement` (`id_abonnement`, `id_utilisateur`, `statut`, `id_Pack`, `date_Souscription`, `date_Expiration`) VALUES
(3, 0, 'expire', 2, '2025-04-01', '2025-04-22');

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
(12, 'discoverin theatre_eljam', '2025-04-25', '12:30', 'active', 15),
(13, 'tour en beja', '2025-04-30', '11:03', 'active', 14);

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
-- Table structure for table `avis_destination`
--

CREATE TABLE `avis_destination` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `avis_destination`
--

INSERT INTO `avis_destination` (`id`, `user_id`, `destination_id`, `score`) VALUES
(1, 5, 13, 3),
(2, 5, 15, 4),
(3, 5, 14, 4),
(4, 5, 16, 2),
(5, 6, 13, 3),
(6, 6, 15, 3);

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `nbr_partenaire` int(11) NOT NULL,
  `views` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `description`, `logo`, `nbr_partenaire`, `views`) VALUES
(4, 'Hotel', 'c\'est une categorie consacrée pour les hotels', '680769cd6dadb.png', 2, 3),
(5, 'Restaurant', 'c\'est une categorie consacrée pour les restaurants', '680769f0c0834.jpg', 2, NULL);

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
(13, 'Tabarka', 'Tabarka est une ville côtière du Nord-Ouest de la Tunisie située à une centaine de kilomètres de Tunis et à quelques kilomètres de la frontière algéro-tunisienne.', '/uploads/68076cac50779.jpg', 36.95442, 8.75801, 17, 2),
(14, 'Beja', 'Béja est une ville du Nord-Ouest de la Tunisie située à une centaine de kilomètres de Tunis et à une cinquantaine de kilomètres de la frontière tuniso-algérienne.', '/uploads/68076d1f42c4e.jpg', 36.7333, 9.1833, 19, 3),
(15, 'theatre_eljam', 'L\'amphithéâtre d\'El Jem est un témoignage exceptionnel de l\'architecture romaine, notamment celle des monuments construits à des fins de spectacle, en Afrique. Situé dans une plaine au centre de la Tunisie, cet amphithéâtre, construit entièrement en pierr', '/uploads/68076d724affe.jpg', 35.2948, 10.7058, 23, 4.5),
(16, 'ichkeul', 'Le parc national de l\'Ichkeul (arabe : المحمية الوطنية إشكل) est un site naturel situé au nord de la Tunisie, plus précisément à 25 kilomètres au sud-ouest de Bizerte et à quinze kilomètres des villes de Menzel Bourguiba et Mateur, sur le territoire du go', '/uploads/68076e0111f16.jpg', 37.1667, 9.6667, 25, 4.3);

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
-- Table structure for table `historique_abonnement`
--

CREATE TABLE `historique_abonnement` (
  `id` int(11) NOT NULL,
  `abonnement_id` int(11) DEFAULT NULL,
  `action` varchar(255) NOT NULL,
  `date_action` datetime NOT NULL,
  `details` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `historique_abonnement`
--

INSERT INTO `historique_abonnement` (`id`, `abonnement_id`, `action`, `date_action`, `details`) VALUES
(2, 8, 'modification', '2025-04-27 16:26:15', 'Modification d\'un abonnement existant'),
(3, 8, 'suppression', '2025-04-27 16:26:20', 'Suppression d\'un abonnement'),
(4, 9, 'ajout', '2025-04-27 16:35:04', 'Ajout d\'un nouvel abonnement'),
(5, 10, 'ajout', '2025-04-27 18:20:18', 'Ajout d\'un nouvel abonnement'),
(6, 10, 'modification', '2025-04-27 18:24:29', 'Modification d\'un abonnement existant'),
(7, 3, 'modification', '2025-04-28 14:07:25', 'Modification d\'un abonnement existant');

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
(3, 'azsdsdds', 40, 'En cours', NULL),
(4, 'Visitez la ville andalouse de Testour, prenez une photo de la grande horloge inversée et partagez-la.', 100, 'En cours', 4),
(5, 'Faites une randonnée dans les forêts de Ain Draham et documentez votre parcours.Faites une randonnée dans les forêts de Ain Draham et documentez votre parcours.', 120, 'Valide', 5),
(6, 'Découvrez les maisons troglodytes de Matmata et réalisez une courte vidéo descriptive.', 150, 'En cours', 6),
(7, 'Rendez-vous à l’oasis de montagne de Chebika et capturez une photo de la cascade.', 130, 'Valide', 7),
(8, 'Visitez El Kef, explorez ses musées et partagez une anecdote historique.', 90, 'Valide', 8),
(9, 'Découvrez les canyons de Tamerza et partagez une photo de la vue panoramique.', 140, 'En cours', 4),
(10, 'Prenez une photo de la vue depuis le village berbère de Takrouna et publiez-la avec un commentaire.', 125, 'Valide', 5),
(11, 'Prenez une photo de la vue depuis le village berbère de Takrouna et publiez-la avec un commentaire.', 110, 'En cours', 6);

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
(2, 0, 'Gold', 'c\'est un pack', 200, 12, 'Gastronomie', 'Actif');

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
  `id_categorie` int(11) DEFAULT NULL,
  `montant` int(11) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `num_tel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `partenaire`
--

INSERT INTO `partenaire` (`id`, `nom`, `email`, `adresse`, `description`, `date_ajout`, `id_categorie`, `montant`, `logo`, `num_tel`) VALUES
(4, 'el mouradi', 'elmouradi@gmail.com', '8 rue el ward , sousse', 'c\'est un hotel luxe', '2025-04-22', 4, 0, '', ''),
(5, 'ha food', 'hafood@gmail.com', '8 rue lkhadhra , el ghazela', 'c\'est un restaurant traditionnel', '2025-04-22', 5, 0, '', ''),
(6, 'ahahah', 'ajahbahd@gmail.com', 'jhdbahjbdabjda', 'hjdavdhvahdvahjd', '2025-04-28', 4, 1000, '680f73ddb2e8b.png', '56440274');

-- --------------------------------------------------------

--
-- Table structure for table `password_reset`
--

CREATE TABLE `password_reset` (
  `email` varchar(255) NOT NULL,
  `token` varchar(1000) NOT NULL,
  `expiration_time` datetime NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `description_rec` varchar(255) NOT NULL,
  `date_rec` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `type_rec` varchar(255) NOT NULL,
  `etat_rec` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `description_rec`, `date_rec`, `type_rec`, `etat_rec`) VALUES
(6, 'il ya un probleme au niveau d\'affichage', '2025-04-22 00:00:00', 'Problème technique', '0');

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
(4, 'Carte cadeau Amazon 10€', 100, 'Disponible'),
(5, 'Bon d achat Fnac', 150, 'Disponible'),
(6, 'Casque Bluetooth', 300, 'Indisponible'),
(7, 'T-shirt personnalisé', 80, 'Disponible'),
(8, 'Sac à dos écolo', 200, 'Indisponible');

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `id_rep` int(11) NOT NULL,
  `id_rec` int(11) NOT NULL,
  `date_rep` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `contenu_rep` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reponse`
--

INSERT INTO `reponse` (`id_rep`, `id_rec`, `date_rep`, `contenu_rep`) VALUES
(4, 6, '2025-04-22 00:00:00', 'ok je l\'ai traité');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL,
  `creates_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `description` varchar(1000) DEFAULT NULL,
  `priority` varchar(50) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `is_archived` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `photo_carte_f2` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 1,
  `last_login` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `photo_profil`, `type_user`, `photo_carte_f1`, `photo_carte_f2`, `is_active`, `last_login`, `created_at`, `deleted_at`) VALUES
(4, 'admin', 'admin', 'admin@trekswap.tn', '$2y$13$Pbqc8TxOYqfWtSo9Ygbp9usJeFOPlIj1BAw9KMxfsGpzqQLE1zMb6', NULL, 'Admin', NULL, NULL, 1, '2025-04-28 15:05:37', '2025-04-22 11:58:50', NULL),
(5, 'netej', 'ghodbane', 'netejghodbane@yahoo.com', '$2y$13$CTOXK/r6LST.7ZHgBzit5.OckfSQSC87ggCeXnwm7OpPFHDGMP.66', 'hotel-680caf9112043.png', 'Touriste', NULL, NULL, 0, '2025-04-28 15:00:11', '2025-04-26 12:04:01', NULL),
(6, 'ghalia', 'abdelkebir', 'ghaliaabdelkebir@gmail.com', '$2y$13$u9ygT6qFqnvpidt46u3uweIEZZa4RR4ZOvUIQzF.Nimq/eOObAvB.', 'restaurant-680cc5c642de4.jpg', 'Touriste', NULL, NULL, 0, '2025-04-26 17:49:40', '2025-04-26 13:38:46', NULL);

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
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `mission_id` int(11) NOT NULL,
  `validated_at` datetime NOT NULL,
  `points_gagnes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user_mission`
--

INSERT INTO `user_mission` (`id`, `user_id`, `mission_id`, `validated_at`, `points_gagnes`) VALUES
(1, 5, 6, '2025-04-28 16:32:14', 1000),
(2, 6, 4, '2025-04-28 16:32:37', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `user_partenaire`
--

CREATE TABLE `user_partenaire` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `partenaire_id` int(11) NOT NULL,
  `commentaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_partenaire`
--

INSERT INTO `user_partenaire` (`id`, `user_id`, `partenaire_id`, `commentaire`) VALUES
(1, 5, 6, 'zzz');

-- --------------------------------------------------------

--
-- Table structure for table `whishlist`
--

CREATE TABLE `whishlist` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `whishlist`
--

INSERT INTO `whishlist` (`id`, `user_id`, `destination_id`) VALUES
(11, 5, 14),
(12, 5, 13);

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
-- Indexes for table `avis_destination`
--
ALTER TABLE `avis_destination`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk1_us` (`user_id`),
  ADD KEY `fk1_dest` (`destination_id`);

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
-- Indexes for table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Indexes for table `historique_abonnement`
--
ALTER TABLE `historique_abonnement`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `password_reset`
--
ALTER TABLE `password_reset`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_USER_MISSION_USER_ID` (`user_id`),
  ADD KEY `IDX_USER_MISSION_MISSION_ID` (`mission_id`);

--
-- Indexes for table `user_partenaire`
--
ALTER TABLE `user_partenaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_la` (`user_id`),
  ADD KEY `fk_al` (`partenaire_id`);

--
-- Indexes for table `whishlist`
--
ALTER TABLE `whishlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_userId` (`user_id`),
  ADD KEY `fk_destinationId` (`destination_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `id_abonnement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `activite`
--
ALTER TABLE `activite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `avis_destination`
--
ALTER TABLE `avis_destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `destination`
--
ALTER TABLE `destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `historique_abonnement`
--
ALTER TABLE `historique_abonnement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mission`
--
ALTER TABLE `mission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `pack`
--
ALTER TABLE `pack`
  MODIFY `id_pack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `partenaire`
--
ALTER TABLE `partenaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `password_reset`
--
ALTER TABLE `password_reset`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `recompense`
--
ALTER TABLE `recompense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_rep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_mission`
--
ALTER TABLE `user_mission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_partenaire`
--
ALTER TABLE `user_partenaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `whishlist`
--
ALTER TABLE `whishlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

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
-- Constraints for table `avis_destination`
--
ALTER TABLE `avis_destination`
  ADD CONSTRAINT `fk1_dest` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk1_us` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Constraints for table `user_partenaire`
--
ALTER TABLE `user_partenaire`
  ADD CONSTRAINT `fk_al` FOREIGN KEY (`partenaire_id`) REFERENCES `partenaire` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_la` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `whishlist`
--
ALTER TABLE `whishlist`
  ADD CONSTRAINT `fk_destinationId` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_userId` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
