<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250405222930 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            ALTER TABLE avis DROP FOREIGN KEY id_dest
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
            ALTER TABLE user_abonnement DROP FOREIGN KEY user_abonnement_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement DROP FOREIGN KEY user_abonnement_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite DROP FOREIGN KEY user_activite_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite DROP FOREIGN KEY user_activite_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY user_mission_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY user_mission_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE avis
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE destination_activite
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE mission
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE password_reset
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE recompense
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
            ALTER TABLE abonnement DROP FOREIGN KEY fk_abonnn
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_abonnn ON abonnement
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement CHANGE statut statut VARCHAR(20) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite DROP FOREIGN KEY fk_act
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_act ON activite
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite CHANGE id_destination id_destination INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination CHANGE description description VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack CHANGE id_utilisateur id_utilisateur INT NOT NULL, CHANGE description description LONGTEXT NOT NULL, CHANGE avantages avantages LONGTEXT NOT NULL, CHANGE statut statut VARCHAR(50) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP FOREIGN KEY fk_part
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_part ON partenaire
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP id_categorie
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation CHANGE date_rec date_rec DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', CHANGE etat_rec etat_rec VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse DROP FOREIGN KEY fk_rep
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_rep ON reponse
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse CHANGE date_rep date_rep DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)'
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user ADD is_active TINYINT(1) NOT NULL, ADD last_login DATETIME DEFAULT NULL, ADD created_at DATETIME NOT NULL, ADD deleted_at DATETIME DEFAULT NULL
        SQL);
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            CREATE TABLE avis (id INT AUTO_INCREMENT NOT NULL, id_des INT NOT NULL, description_av VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, INDEX id_dest (id_des), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE destination_activite (id_destination INT NOT NULL, id_activite INT NOT NULL, INDEX id_activite (id_activite), INDEX IDX_E6C5952426D4F35D (id_destination), PRIMARY KEY(id_destination, id_activite)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE mission (id INT AUTO_INCREMENT NOT NULL, id_recompense INT DEFAULT NULL, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, points_recompense INT NOT NULL, statut VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, INDEX id_recompense (id_recompense), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE password_reset (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, token VARCHAR(1000) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, expiration_time DATETIME NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE recompense (id INT AUTO_INCREMENT NOT NULL, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, cout_en_points INT NOT NULL, disponibilite VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
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
            ALTER TABLE avis ADD CONSTRAINT id_dest FOREIGN KEY (id_des) REFERENCES destination (id) ON UPDATE NO ACTION ON DELETE CASCADE
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
            ALTER TABLE user_abonnement ADD CONSTRAINT user_abonnement_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement ADD CONSTRAINT user_abonnement_ibfk_2 FOREIGN KEY (id_abonnement) REFERENCES abonnement (id_abonnement) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite ADD CONSTRAINT user_activite_ibfk_2 FOREIGN KEY (id_activite) REFERENCES activite (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite ADD CONSTRAINT user_activite_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT user_mission_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT user_mission_ibfk_2 FOREIGN KEY (id_mission) REFERENCES mission (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement CHANGE statut statut VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD CONSTRAINT fk_abonnn FOREIGN KEY (id_Pack) REFERENCES pack (id_pack) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_abonnn ON abonnement (id_Pack)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite CHANGE id_destination id_destination INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite ADD CONSTRAINT fk_act FOREIGN KEY (id_destination) REFERENCES destination (id) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_act ON activite (id_destination)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination CHANGE description description LONGTEXT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL, CHANGE description description VARCHAR(255) NOT NULL, CHANGE avantages avantages VARCHAR(255) NOT NULL, CHANGE statut statut VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD id_categorie INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD CONSTRAINT fk_part FOREIGN KEY (id_categorie) REFERENCES categorie (id) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_part ON partenaire (id_categorie)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation CHANGE date_rec date_rec DATE NOT NULL, CHANGE etat_rec etat_rec TINYINT(1) DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse CHANGE date_rep date_rep DATE NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse ADD CONSTRAINT fk_rep FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_rep ON reponse (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE `user` DROP is_active, DROP last_login, DROP created_at, DROP deleted_at
        SQL);
    }
}
