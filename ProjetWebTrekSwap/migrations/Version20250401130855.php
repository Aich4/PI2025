<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250401130855 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            ALTER TABLE destination DROP FOREIGN KEY fk_dest
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination_activite DROP FOREIGN KEY destination_activite_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination_activite DROP FOREIGN KEY destination_activite_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission DROP FOREIGN KEY fk_mission_recompense
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP FOREIGN KEY fk_part
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation DROP FOREIGN KEY fk_reclamation_user
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation DROP FOREIGN KEY fk_reclamation_reponse
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement DROP FOREIGN KEY user_abonnement_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement DROP FOREIGN KEY user_abonnement_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite DROP FOREIGN KEY user_activite_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite DROP FOREIGN KEY user_activite_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY user_mission_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY user_mission_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE activite
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE avis
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE categorie
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE destination
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE destination_activite
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE mission
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE partenaire
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE reclamation
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE recompense
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE reponse
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE user
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE user_abonnement
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE user_activite
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE user_mission
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement DROP FOREIGN KEY abonnement_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_id_Pack ON abonnement
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD id INT AUTO_INCREMENT NOT NULL, DROP statut, CHANGE id_abonnement id_abonnement INT NOT NULL, CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL, DROP PRIMARY KEY, ADD PRIMARY KEY (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack ADD id INT AUTO_INCREMENT NOT NULL, CHANGE id_pack id_pack INT NOT NULL, CHANGE statut statut VARCHAR(255) NOT NULL, DROP PRIMARY KEY, ADD PRIMARY KEY (id)
        SQL);
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            CREATE TABLE activite (id INT AUTO_INCREMENT NOT NULL, nom_activite VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, date VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, heure VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, statut VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE avis (id INT AUTO_INCREMENT NOT NULL, rating_av INT NOT NULL, description_av VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE categorie (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, logo VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, nbr_partenaire INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE destination (id INT AUTO_INCREMENT NOT NULL, id_activite INT NOT NULL, nom_destination VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, description LONGTEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, image_destination VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, latitude DOUBLE PRECISION NOT NULL, longitude DOUBLE PRECISION NOT NULL, temperature DOUBLE PRECISION NOT NULL, rate DOUBLE PRECISION NOT NULL, INDEX fk_dest (id_activite), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE destination_activite (id_destination INT NOT NULL, id_activite INT NOT NULL, INDEX id_activite (id_activite), INDEX IDX_E6C5952426D4F35D (id_destination), PRIMARY KEY(id_destination, id_activite)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE mission (id INT AUTO_INCREMENT NOT NULL, id_recompense INT DEFAULT NULL, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, points_recompense INT NOT NULL, statut VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, UNIQUE INDEX id_recompense (id_recompense), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE partenaire (id INT AUTO_INCREMENT NOT NULL, id_categorie INT DEFAULT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, email VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, adresse VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, date_ajout DATE NOT NULL, INDEX fk_part (id_categorie), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE reclamation (id INT AUTO_INCREMENT NOT NULL, id_reponse INT DEFAULT NULL, id_user INT DEFAULT NULL, type_rec VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, description_rec VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, UNIQUE INDEX id_reponse (id_reponse), INDEX fk_reclamation_user (id_user), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE recompense (id INT AUTO_INCREMENT NOT NULL, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, cout_en_points INT NOT NULL, disponibilite VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE reponse (id INT AUTO_INCREMENT NOT NULL, reponse_rec VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, prenom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, email VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, mot_de_passe VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, question_securite VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, reponse_securite VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, photo_profil VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, type_user VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, date_creation DATE NOT NULL, photo_carte_f1 VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, photo_carte_f2 VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, id_reclamation INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE user_abonnement (id_user INT NOT NULL, id_abonnement INT NOT NULL, INDEX id_abonnement (id_abonnement), INDEX IDX_9275AE576B3CA4B (id_user), PRIMARY KEY(id_user, id_abonnement)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE user_activite (id_user INT NOT NULL, id_activite INT NOT NULL, INDEX id_activite (id_activite), INDEX IDX_58F8B1156B3CA4B (id_user), PRIMARY KEY(id_user, id_activite)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE user_mission (id_user INT NOT NULL, id_mission INT NOT NULL, INDEX id_mission (id_mission), INDEX IDX_C86AEC366B3CA4B (id_user), PRIMARY KEY(id_user, id_mission)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination ADD CONSTRAINT fk_dest FOREIGN KEY (id_activite) REFERENCES activite (id) ON UPDATE NO ACTION ON DELETE NO ACTION
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination_activite ADD CONSTRAINT destination_activite_ibfk_1 FOREIGN KEY (id_destination) REFERENCES destination (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination_activite ADD CONSTRAINT destination_activite_ibfk_2 FOREIGN KEY (id_activite) REFERENCES activite (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission ADD CONSTRAINT fk_mission_recompense FOREIGN KEY (id_recompense) REFERENCES recompense (id) ON UPDATE CASCADE ON DELETE SET NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD CONSTRAINT fk_part FOREIGN KEY (id_categorie) REFERENCES categorie (id) ON UPDATE NO ACTION ON DELETE NO ACTION
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation ADD CONSTRAINT fk_reclamation_user FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation ADD CONSTRAINT fk_reclamation_reponse FOREIGN KEY (id_reponse) REFERENCES reponse (id) ON UPDATE CASCADE ON DELETE SET NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement ADD CONSTRAINT user_abonnement_ibfk_2 FOREIGN KEY (id_abonnement) REFERENCES abonnement (id_abonnement) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement ADD CONSTRAINT user_abonnement_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite ADD CONSTRAINT user_activite_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite ADD CONSTRAINT user_activite_ibfk_2 FOREIGN KEY (id_activite) REFERENCES activite (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT user_mission_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT user_mission_ibfk_2 FOREIGN KEY (id_mission) REFERENCES mission (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement MODIFY id INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX `PRIMARY` ON abonnement
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD statut VARCHAR(255) NOT NULL, DROP id, CHANGE id_abonnement id_abonnement INT AUTO_INCREMENT NOT NULL, CHANGE id_utilisateur id_utilisateur INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD CONSTRAINT abonnement_ibfk_1 FOREIGN KEY (id_Pack) REFERENCES pack (id_pack) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX idx_id_Pack ON abonnement (id_Pack)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD PRIMARY KEY (id_abonnement)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack MODIFY id INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX `PRIMARY` ON pack
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack DROP id, CHANGE id_pack id_pack INT AUTO_INCREMENT NOT NULL, CHANGE statut statut VARCHAR(11) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack ADD PRIMARY KEY (id_pack)
        SQL);
    }
}
