<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250413212014 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            DROP TABLE avis
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD id_utilisateur INT DEFAULT NULL, ADD date_souscription DATE NOT NULL, ADD date_expiration DATE NOT NULL, ADD statut VARCHAR(20) NOT NULL, DROP nom_abonnement, DROP avantages, DROP niveau, DROP prix, DROP duree
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite ADD id_destination INT DEFAULT NULL, CHANGE date date DATE NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE categorie CHANGE description description VARCHAR(500) NOT NULL, CHANGE logo logo VARCHAR(255) DEFAULT NULL, CHANGE nbr_partenaire nbr_partenaire INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination DROP FOREIGN KEY fk_dest
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_dest ON destination
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination DROP id_activite, CHANGE description description VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission ADD id_recompense INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack ADD id_utilisateur INT NOT NULL, ADD avantages LONGTEXT NOT NULL, ADD statut VARCHAR(50) NOT NULL, DROP categories_incluses, CHANGE description description LONGTEXT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire DROP FOREIGN KEY fk_part
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_part ON partenaire
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire CHANGE id_categorie id_categorie INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation ADD etat_rec VARCHAR(255) NOT NULL, CHANGE date_rec date_rec DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)'
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse DROP FOREIGN KEY fk_id_rec
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX fk_id_rec ON reponse
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse CHANGE date_rep date_rep DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', CHANGE contenu_rec contenu_rep VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE user ADD is_active TINYINT(1) NOT NULL, ADD last_login DATETIME DEFAULT NULL, ADD created_at DATETIME DEFAULT NULL, ADD deleted_at DATETIME DEFAULT NULL, DROP question_securite, DROP reponse_securite, DROP date_creation, DROP id_reclamation, CHANGE photo_profil photo_profil VARCHAR(255) DEFAULT NULL, CHANGE photo_carte_f1 photo_carte_f1 VARCHAR(255) DEFAULT NULL, CHANGE photo_carte_f2 photo_carte_f2 VARCHAR(255) DEFAULT NULL
        SQL);
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            CREATE TABLE avis (id INT AUTO_INCREMENT NOT NULL, rating_av INT NOT NULL, description_av VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = '' 
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE abonnement ADD nom_abonnement VARCHAR(255) NOT NULL, ADD avantages VARCHAR(255) NOT NULL, ADD niveau VARCHAR(255) NOT NULL, ADD prix DOUBLE PRECISION NOT NULL, ADD duree INT NOT NULL, DROP id_utilisateur, DROP date_souscription, DROP date_expiration, DROP statut
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE activite DROP id_destination, CHANGE date date VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE categorie CHANGE description description VARCHAR(255) NOT NULL, CHANGE logo logo VARCHAR(255) NOT NULL, CHANGE nbr_partenaire nbr_partenaire INT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination ADD id_activite INT NOT NULL, CHANGE description description LONGTEXT NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE destination ADD CONSTRAINT fk_dest FOREIGN KEY (id_activite) REFERENCES activite (id) ON UPDATE NO ACTION ON DELETE NO ACTION
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_dest ON destination (id_activite)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE mission DROP id_recompense
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE pack ADD categories_incluses VARCHAR(255) NOT NULL, DROP id_utilisateur, DROP avantages, DROP statut, CHANGE description description VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire CHANGE id_categorie id_categorie INT DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE partenaire ADD CONSTRAINT fk_part FOREIGN KEY (id_categorie) REFERENCES categorie (id) ON UPDATE NO ACTION ON DELETE NO ACTION
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_part ON partenaire (id_categorie)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reclamation DROP etat_rec, CHANGE date_rec date_rec DATE NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse CHANGE date_rep date_rep DATE NOT NULL, CHANGE contenu_rep contenu_rec VARCHAR(255) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reponse ADD CONSTRAINT fk_id_rec FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX fk_id_rec ON reponse (id_rec)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE `user` ADD question_securite VARCHAR(255) NOT NULL, ADD reponse_securite VARCHAR(255) NOT NULL, ADD date_creation DATE NOT NULL, ADD id_reclamation INT NOT NULL, DROP is_active, DROP last_login, DROP created_at, DROP deleted_at, CHANGE photo_profil photo_profil VARCHAR(255) NOT NULL, CHANGE photo_carte_f1 photo_carte_f1 VARCHAR(255) NOT NULL, CHANGE photo_carte_f2 photo_carte_f2 VARCHAR(255) NOT NULL
        SQL);
    }
}
