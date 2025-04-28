-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 22 avr. 2025 à 12:24
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
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
-- Déchargement des données de la table `abonnement`
--

INSERT INTO `abonnement` (`id_abonnement`, `id_utilisateur`, `statut`, `id_Pack`, `date_Souscription`, `date_Expiration`) VALUES
(3, 0, 'actif', 2, '2025-04-23', '2025-04-29');

-- --------------------------------------------------------

--
-- Structure de la table `activite`
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
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`id`, `nom_activite`, `date`, `heure`, `statut`, `id_destination`) VALUES
(12, 'discoverin theatre_eljam', '2025-04-25', '12:30', 'active', 15),
(13, 'tour en beja', '2025-04-30', '11:03', 'active', 14);

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id` int(11) NOT NULL,
  `description_av` varchar(255) NOT NULL,
  `id_des` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `nbr_partenaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`, `description`, `logo`, `nbr_partenaire`) VALUES
(4, 'Hotel', 'c\'est une categorie consacrée pour les hotels', '680769cd6dadb.png', 2),
(5, 'Restaurant', 'c\'est une categorie consacrée pour les restaurants', '680769f0c0834.jpg', 2);

-- --------------------------------------------------------

--
-- Structure de la table `destination`
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
-- Déchargement des données de la table `destination`
--

INSERT INTO `destination` (`id`, `nom_destination`, `description`, `image_destination`, `latitude`, `longitude`, `temperature`, `rate`) VALUES
(13, 'Tabarka', 'Tabarka est une ville côtière du Nord-Ouest de la Tunisie située à une centaine de kilomètres de Tunis et à quelques kilomètres de la frontière algéro-tunisienne.', '/uploads/68076cac50779.jpg', 36.95442, 8.75801, 17, 2),
(14, 'Beja', 'Béja est une ville du Nord-Ouest de la Tunisie située à une centaine de kilomètres de Tunis et à une cinquantaine de kilomètres de la frontière tuniso-algérienne.', '/uploads/68076d1f42c4e.jpg', 36.7333, 9.1833, 19, 3),
(15, 'theatre_eljam', 'L\'amphithéâtre d\'El Jem est un témoignage exceptionnel de l\'architecture romaine, notamment celle des monuments construits à des fins de spectacle, en Afrique. Situé dans une plaine au centre de la Tunisie, cet amphithéâtre, construit entièrement en pierr', '/uploads/68076d724affe.jpg', 35.2948, 10.7058, 23, 4.5),
(16, 'ichkeul', 'Le parc national de l\'Ichkeul (arabe : المحمية الوطنية إشكل) est un site naturel situé au nord de la Tunisie, plus précisément à 25 kilomètres au sud-ouest de Bizerte et à quinze kilomètres des villes de Menzel Bourguiba et Mateur, sur le territoire du go', '/uploads/68076e0111f16.jpg', 37.1667, 9.6667, 25, 4.3);

-- --------------------------------------------------------

--
-- Structure de la table `destination_activite`
--

CREATE TABLE `destination_activite` (
  `id_destination` int(11) NOT NULL,
  `id_activite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20250212175244', '2025-02-12 18:53:25', 164);

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
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
-- Structure de la table `mission`
--

CREATE TABLE `mission` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `points_recompense` int(11) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `id_recompense` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `mission`
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
-- Structure de la table `pack`
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
-- Déchargement des données de la table `pack`
--

INSERT INTO `pack` (`id_pack`, `id_utilisateur`, `nom_pack`, `description`, `prix`, `duree`, `avantages`, `statut`) VALUES
(2, 0, 'Gold', 'c\'est un pack', 200, 12, 'Gastronomie', 'Actif');

-- --------------------------------------------------------

--
-- Structure de la table `partenaire`
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
-- Déchargement des données de la table `partenaire`
--

INSERT INTO `partenaire` (`id`, `nom`, `email`, `adresse`, `description`, `date_ajout`, `id_categorie`) VALUES
(4, 'el mouradi', 'elmouradi@gmail.com', '8 rue el ward , sousse', 'c\'est un hotel luxe', '2025-04-22', 4),
(5, 'ha food', 'hafood@gmail.com', '8 rue lkhadhra , el ghazela', 'c\'est un restaurant traditionnel', '2025-04-22', 5);

-- --------------------------------------------------------

--
-- Structure de la table `password_reset`
--

CREATE TABLE `password_reset` (
  `email` varchar(255) NOT NULL,
  `token` varchar(1000) NOT NULL,
  `expiration_time` datetime NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `description_rec` varchar(255) NOT NULL,
  `date_rec` date NOT NULL,
  `type_rec` varchar(255) NOT NULL,
  `etat_rec` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `description_rec`, `date_rec`, `type_rec`, `etat_rec`) VALUES
(6, 'il ya un probleme au niveau d\'affichage', '2025-04-22', 'Problème technique', 0);

-- --------------------------------------------------------

--
-- Structure de la table `recompense`
--

CREATE TABLE `recompense` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `cout_en_points` int(11) NOT NULL,
  `disponibilite` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `recompense`
--

INSERT INTO `recompense` (`id`, `description`, `cout_en_points`, `disponibilite`) VALUES
(4, 'Carte cadeau Amazon 10€', 100, 'Disponible'),
(5, 'Bon d achat Fnac', 150, 'Disponible'),
(6, 'Casque Bluetooth', 300, 'Indisponible'),
(7, 'T-shirt personnalisé', 80, 'Disponible'),
(8, 'Sac à dos écolo', 200, 'Indisponible');

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_rep` int(11) NOT NULL,
  `id_rec` int(11) NOT NULL,
  `date_rep` date NOT NULL,
  `contenu_rep` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id_rep`, `id_rec`, `date_rep`, `contenu_rep`) VALUES
(4, 6, '2025-04-22', 'ok je l\'ai traité');

-- --------------------------------------------------------

--
-- Structure de la table `user`
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
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `mot_de_passe`, `photo_profil`, `type_user`, `photo_carte_f1`, `photo_carte_f2`, `is_active`, `last_login`, `created_at`, `deleted_at`) VALUES
(4, 'admin', 'admin', 'admin@trekswap.tn', '$2y$13$Pbqc8TxOYqfWtSo9Ygbp9usJeFOPlIj1BAw9KMxfsGpzqQLE1zMb6', NULL, 'Admin', NULL, NULL, 1, '2025-04-22 12:08:58', '2025-04-22 11:58:50', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_abonnement`
--

CREATE TABLE `user_abonnement` (
  `id_user` int(11) NOT NULL,
  `id_abonnement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user_activite`
--

CREATE TABLE `user_activite` (
  `id_user` int(11) NOT NULL,
  `id_activite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user_mission`
--

CREATE TABLE `user_mission` (
  `id_user` int(11) NOT NULL,
  `id_mission` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`id_abonnement`),
  ADD KEY `fk_abonnn` (`id_Pack`);

--
-- Index pour la table `activite`
--
ALTER TABLE `activite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_act` (`id_destination`);

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_dest` (`id_des`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `destination_activite`
--
ALTER TABLE `destination_activite`
  ADD PRIMARY KEY (`id_destination`,`id_activite`),
  ADD KEY `id_activite` (`id_activite`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_recompense` (`id_recompense`) USING BTREE;

--
-- Index pour la table `pack`
--
ALTER TABLE `pack`
  ADD PRIMARY KEY (`id_pack`);

--
-- Index pour la table `partenaire`
--
ALTER TABLE `partenaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_part` (`id_categorie`);

--
-- Index pour la table `password_reset`
--
ALTER TABLE `password_reset`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_rec`);

--
-- Index pour la table `recompense`
--
ALTER TABLE `recompense`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_rep`),
  ADD KEY `fk_rep` (`id_rec`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_abonnement`
--
ALTER TABLE `user_abonnement`
  ADD PRIMARY KEY (`id_user`,`id_abonnement`),
  ADD KEY `id_abonnement` (`id_abonnement`);

--
-- Index pour la table `user_activite`
--
ALTER TABLE `user_activite`
  ADD PRIMARY KEY (`id_user`,`id_activite`),
  ADD KEY `id_activite` (`id_activite`);

--
-- Index pour la table `user_mission`
--
ALTER TABLE `user_mission`
  ADD PRIMARY KEY (`id_user`,`id_mission`),
  ADD KEY `id_mission` (`id_mission`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `id_abonnement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `activite`
--
ALTER TABLE `activite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `destination`
--
ALTER TABLE `destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `mission`
--
ALTER TABLE `mission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `pack`
--
ALTER TABLE `pack`
  MODIFY `id_pack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `partenaire`
--
ALTER TABLE `partenaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `password_reset`
--
ALTER TABLE `password_reset`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `recompense`
--
ALTER TABLE `recompense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_rep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `fk_abonnn` FOREIGN KEY (`id_Pack`) REFERENCES `pack` (`id_pack`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `fk_act` FOREIGN KEY (`id_destination`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `id_dest` FOREIGN KEY (`id_des`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `destination_activite`
--
ALTER TABLE `destination_activite`
  ADD CONSTRAINT `destination_activite_ibfk_1` FOREIGN KEY (`id_destination`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `destination_activite_ibfk_2` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `fk_mission_recompense` FOREIGN KEY (`id_recompense`) REFERENCES `recompense` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `partenaire`
--
ALTER TABLE `partenaire`
  ADD CONSTRAINT `fk_part` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fk_rep` FOREIGN KEY (`id_rec`) REFERENCES `reclamation` (`id_rec`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user_abonnement`
--
ALTER TABLE `user_abonnement`
  ADD CONSTRAINT `user_abonnement_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_abonnement_ibfk_2` FOREIGN KEY (`id_abonnement`) REFERENCES `abonnement` (`id_abonnement`) ON DELETE CASCADE;

--
-- Contraintes pour la table `user_activite`
--
ALTER TABLE `user_activite`
  ADD CONSTRAINT `user_activite_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_activite_ibfk_2` FOREIGN KEY (`id_activite`) REFERENCES `activite` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `user_mission`
--
ALTER TABLE `user_mission`
  ADD CONSTRAINT `user_mission_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_mission_ibfk_2` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
