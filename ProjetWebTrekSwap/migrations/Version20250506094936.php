<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250506094936 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            CREATE TABLE reclamation_event (id INT AUTO_INCREMENT NOT NULL, reclamation_id INT NOT NULL, start DATETIME NOT NULL, end DATETIME DEFAULT NULL, title VARCHAR(255) NOT NULL, background_color VARCHAR(255) DEFAULT NULL, border_color VARCHAR(255) DEFAULT NULL, INDEX IDX_E6A239E92D6BA2D9 (reclamation_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation_event ADD CONSTRAINT FK_E6A239E92D6BA2D9 FOREIGN KEY (reclamation_id) REFERENCES reclamation (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis DROP FOREIGN KEY id_dest
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite DROP FOREIGN KEY user_activite_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite DROP FOREIGN KEY user_activite_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE avis
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE password_reset
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE user_activite
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement DROP FOREIGN KEY fk_abonnn
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement DROP FOREIGN KEY fk_abonnn
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement CHANGE statut statut VARCHAR(20) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD CONSTRAINT FK_351268BB1CFE4221 FOREIGN KEY (id_pack) REFERENCES pack (id_pack)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_abonnn ON abonnement
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_351268BB1CFE4221 ON abonnement (id_pack)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD CONSTRAINT fk_abonnn FOREIGN KEY (id_Pack) REFERENCES pack (id_pack) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite DROP FOREIGN KEY fk_act
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite DROP FOREIGN KEY fk_act
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite CHANGE id_destination id_destination INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite ADD CONSTRAINT FK_B875551526D4F35D FOREIGN KEY (id_destination) REFERENCES destination (id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_act ON activite
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_B875551526D4F35D ON activite (id_destination)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite ADD CONSTRAINT fk_act FOREIGN KEY (id_destination) REFERENCES destination (id) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY fk1_dest
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY fk1_us
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY fk1_dest
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY fk1_us
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT FK_612F7305A76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT FK_612F7305816C6140 FOREIGN KEY (destination_id) REFERENCES destination (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk1_us ON avis_destination
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_612F7305A76ED395 ON avis_destination (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk1_dest ON avis_destination
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_612F7305816C6140 ON avis_destination (destination_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT fk1_dest FOREIGN KEY (destination_id) REFERENCES destination (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT fk1_us FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE categorie CHANGE description description VARCHAR(500) NOT NULL, CHANGE logo logo VARCHAR(255) DEFAULT NULL, CHANGE nbr_partenaire nbr_partenaire INT DEFAULT NULL, CHANGE views views INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination CHANGE description description VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE historique_abonnement CHANGE abonnement_id abonnement_id INT NOT NULL, CHANGE action action VARCHAR(20) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission DROP FOREIGN KEY fk_mission_recompense
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission DROP FOREIGN KEY fk_mission_recompense
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission CHANGE id_recompense id_recompense INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission ADD CONSTRAINT FK_9067F23CBB114009 FOREIGN KEY (id_recompense) REFERENCES recompense (id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX id_recompense ON mission
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_9067F23CBB114009 ON mission (id_recompense)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission ADD CONSTRAINT fk_mission_recompense FOREIGN KEY (id_recompense) REFERENCES recompense (id) ON UPDATE CASCADE ON DELETE SET NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack CHANGE id_utilisateur id_utilisateur INT NOT NULL, CHANGE description description LONGTEXT NOT NULL, CHANGE avantages avantages LONGTEXT NOT NULL, CHANGE statut statut VARCHAR(50) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP FOREIGN KEY fk_part
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP FOREIGN KEY fk_part
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire CHANGE id_categorie id_categorie INT NOT NULL, CHANGE logo logo VARCHAR(255) DEFAULT NULL, CHANGE num_tel num_tel VARCHAR(20) DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD CONSTRAINT FK_32FFA373C9486A13 FOREIGN KEY (id_categorie) REFERENCES categorie (id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_part ON partenaire
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_32FFA373C9486A13 ON partenaire (id_categorie)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD CONSTRAINT fk_part FOREIGN KEY (id_categorie) REFERENCES categorie (id) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation DROP FOREIGN KEY fk_ahdha
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation DROP FOREIGN KEY fk_ahdha
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation ADD CONSTRAINT FK_CE606404A76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_ahdha ON reclamation
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_CE606404A76ED395 ON reclamation (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation ADD CONSTRAINT fk_ahdha FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse DROP FOREIGN KEY fk_rep
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse DROP FOREIGN KEY fk_rep
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse CHANGE id_rec id_rec INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse ADD CONSTRAINT FK_5FB6DEC7FAA12276 FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_rep ON reponse
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_5FB6DEC7FAA12276 ON reponse (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse ADD CONSTRAINT fk_rep FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user CHANGE is_active is_active TINYINT(1) NOT NULL, CHANGE reset_token reset_token VARCHAR(100) DEFAULT NULL, CHANGE reset_token_expires_at reset_token_expires_at DATETIME DEFAULT NULL COMMENT '(DC2Type:datetime_immutable)'
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement DROP FOREIGN KEY user_abonnement_ibfk_1
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement DROP FOREIGN KEY user_abonnement_ibfk_2
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX id_abonnement ON user_abonnement
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX IDX_9275AE576B3CA4B ON user_abonnement
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT FK_C86AEC36A76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT FK_C86AEC36BE6CAE90 FOREIGN KEY (mission_id) REFERENCES mission (id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_user_mission_user_id ON user_mission
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_C86AEC36A76ED395 ON user_mission (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_user_mission_mission_id ON user_mission
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_C86AEC36BE6CAE90 ON user_mission (mission_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire DROP FOREIGN KEY fk_la
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire DROP FOREIGN KEY fk_al
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_al ON user_partenaire
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire DROP FOREIGN KEY fk_la
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire ADD CONSTRAINT FK_9598659FA76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_la ON user_partenaire
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_9598659FA76ED395 ON user_partenaire (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire ADD CONSTRAINT fk_la FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY fk_destinationId
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY fk_userId
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY fk_destinationId
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY fk_userId
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT FK_2E936C6DA76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT FK_2E936C6D816C6140 FOREIGN KEY (destination_id) REFERENCES destination (id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_userid ON whishlist
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_2E936C6DA76ED395 ON whishlist (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_destinationid ON whishlist
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_2E936C6D816C6140 ON whishlist (destination_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT fk_destinationId FOREIGN KEY (destination_id) REFERENCES destination (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT fk_userId FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            CREATE TABLE avis (id INT AUTO_INCREMENT NOT NULL, id_des INT NOT NULL, description_av VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, INDEX id_dest (id_des), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE password_reset (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, token VARCHAR(1000) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, expiration_time DATETIME NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE user_activite (id_user INT NOT NULL, id_activite INT NOT NULL, INDEX id_activite (id_activite), INDEX IDX_58F8B1156B3CA4B (id_user), PRIMARY KEY(id_user, id_activite)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis ADD CONSTRAINT id_dest FOREIGN KEY (id_des) REFERENCES destination (id) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite ADD CONSTRAINT user_activite_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_activite ADD CONSTRAINT user_activite_ibfk_2 FOREIGN KEY (id_activite) REFERENCES activite (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation_event DROP FOREIGN KEY FK_E6A239E92D6BA2D9
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE reclamation_event
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement DROP FOREIGN KEY FK_351268BB1CFE4221
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement DROP FOREIGN KEY FK_351268BB1CFE4221
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement CHANGE statut statut VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD CONSTRAINT fk_abonnn FOREIGN KEY (id_Pack) REFERENCES pack (id_pack) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_351268bb1cfe4221 ON abonnement
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_abonnn ON abonnement (id_Pack)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD CONSTRAINT FK_351268BB1CFE4221 FOREIGN KEY (id_pack) REFERENCES pack (id_pack)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite DROP FOREIGN KEY FK_B875551526D4F35D
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite DROP FOREIGN KEY FK_B875551526D4F35D
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite CHANGE id_destination id_destination INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite ADD CONSTRAINT fk_act FOREIGN KEY (id_destination) REFERENCES destination (id) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_b875551526d4f35d ON activite
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_act ON activite (id_destination)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite ADD CONSTRAINT FK_B875551526D4F35D FOREIGN KEY (id_destination) REFERENCES destination (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY FK_612F7305A76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY FK_612F7305816C6140
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY FK_612F7305A76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination DROP FOREIGN KEY FK_612F7305816C6140
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT fk1_dest FOREIGN KEY (destination_id) REFERENCES destination (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT fk1_us FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_612f7305a76ed395 ON avis_destination
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk1_us ON avis_destination (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_612f7305816c6140 ON avis_destination
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk1_dest ON avis_destination (destination_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT FK_612F7305A76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE avis_destination ADD CONSTRAINT FK_612F7305816C6140 FOREIGN KEY (destination_id) REFERENCES destination (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE categorie CHANGE description description VARCHAR(255) NOT NULL, CHANGE logo logo VARCHAR(255) NOT NULL, CHANGE nbr_partenaire nbr_partenaire INT NOT NULL, CHANGE views views INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination CHANGE description description LONGTEXT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE historique_abonnement CHANGE action action VARCHAR(255) NOT NULL, CHANGE abonnement_id abonnement_id INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission DROP FOREIGN KEY FK_9067F23CBB114009
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission DROP FOREIGN KEY FK_9067F23CBB114009
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission CHANGE id_recompense id_recompense INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission ADD CONSTRAINT fk_mission_recompense FOREIGN KEY (id_recompense) REFERENCES recompense (id) ON UPDATE CASCADE ON DELETE SET NULL
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_9067f23cbb114009 ON mission
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX id_recompense ON mission (id_recompense)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission ADD CONSTRAINT FK_9067F23CBB114009 FOREIGN KEY (id_recompense) REFERENCES recompense (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL, CHANGE description description VARCHAR(255) NOT NULL, CHANGE avantages avantages VARCHAR(255) NOT NULL, CHANGE statut statut VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP FOREIGN KEY FK_32FFA373C9486A13
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP FOREIGN KEY FK_32FFA373C9486A13
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire CHANGE id_categorie id_categorie INT DEFAULT NULL, CHANGE logo logo VARCHAR(255) NOT NULL, CHANGE num_tel num_tel VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD CONSTRAINT fk_part FOREIGN KEY (id_categorie) REFERENCES categorie (id) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_32ffa373c9486a13 ON partenaire
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_part ON partenaire (id_categorie)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD CONSTRAINT FK_32FFA373C9486A13 FOREIGN KEY (id_categorie) REFERENCES categorie (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation DROP FOREIGN KEY FK_CE606404A76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation DROP FOREIGN KEY FK_CE606404A76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation ADD CONSTRAINT fk_ahdha FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_ce606404a76ed395 ON reclamation
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_ahdha ON reclamation (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation ADD CONSTRAINT FK_CE606404A76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse DROP FOREIGN KEY FK_5FB6DEC7FAA12276
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse DROP FOREIGN KEY FK_5FB6DEC7FAA12276
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse CHANGE id_rec id_rec INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse ADD CONSTRAINT fk_rep FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec) ON UPDATE NO ACTION ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_5fb6dec7faa12276 ON reponse
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_rep ON reponse (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse ADD CONSTRAINT FK_5FB6DEC7FAA12276 FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE `user` CHANGE is_active is_active TINYINT(1) DEFAULT 1, CHANGE reset_token reset_token VARCHAR(1500) DEFAULT NULL, CHANGE reset_token_expires_at reset_token_expires_at DATETIME DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement ADD CONSTRAINT user_abonnement_ibfk_1 FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_abonnement ADD CONSTRAINT user_abonnement_ibfk_2 FOREIGN KEY (id_abonnement) REFERENCES abonnement (id_abonnement) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX id_abonnement ON user_abonnement (id_abonnement)
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_9275AE576B3CA4B ON user_abonnement (id_user)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY FK_C86AEC36A76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY FK_C86AEC36BE6CAE90
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY FK_C86AEC36A76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission DROP FOREIGN KEY FK_C86AEC36BE6CAE90
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_c86aec36be6cae90 ON user_mission
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_USER_MISSION_MISSION_ID ON user_mission (mission_id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_c86aec36a76ed395 ON user_mission
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX IDX_USER_MISSION_USER_ID ON user_mission (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT FK_C86AEC36A76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_mission ADD CONSTRAINT FK_C86AEC36BE6CAE90 FOREIGN KEY (mission_id) REFERENCES mission (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire DROP FOREIGN KEY FK_9598659FA76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire DROP FOREIGN KEY FK_9598659FA76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire ADD CONSTRAINT fk_la FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire ADD CONSTRAINT fk_al FOREIGN KEY (partenaire_id) REFERENCES partenaire (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_al ON user_partenaire (partenaire_id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_9598659fa76ed395 ON user_partenaire
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_la ON user_partenaire (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user_partenaire ADD CONSTRAINT FK_9598659FA76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY FK_2E936C6DA76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY FK_2E936C6D816C6140
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY FK_2E936C6DA76ED395
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist DROP FOREIGN KEY FK_2E936C6D816C6140
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT fk_destinationId FOREIGN KEY (destination_id) REFERENCES destination (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT fk_userId FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_2e936c6d816c6140 ON whishlist
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_destinationId ON whishlist (destination_id)
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX idx_2e936c6da76ed395 ON whishlist
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_userId ON whishlist (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT FK_2E936C6DA76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE whishlist ADD CONSTRAINT FK_2E936C6D816C6140 FOREIGN KEY (destination_id) REFERENCES destination (id)
        SQL);
    }
}
