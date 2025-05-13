# 🌍 ProjetTrekSwap (Web & Desktop)

Projet académique réalisé dans le cadre du module **PIDEV 3A** à **Esprit School of Engineering**.  
**TrekSwap** est une solution complète combinant **application web Symfony 6.4** et **application desktop JavaFX** pour la gestion intelligente du tourisme.  
Elle propose une plateforme unifiée pour explorer des destinations, gérer des missions, s'abonner à des offres, suivre des activités et bien plus.

Pensé comme un système modulaire et évolutif, le projet repose sur une architecture **multi-plateforme (Web + Desktop)** intégrant :
- des services avancés tels que génération de documents, paiements numériques, QR codes, chatbot IA, statistiques
- une application JavaFX conviviale pour une expérience utilisateur en bureau sans navigateur web

## 🚀 Aperçu

**TrekSwap** est une plateforme **web et desktop** innovante dédiée à la digitalisation du secteur touristique. Elle permet aux utilisateurs d’explorer des destinations, de s’abonner à des offres personnalisées, d’interagir avec des partenaires et de gérer facilement leurs activités de voyage à travers une interface intuitive.

Cette solution vise à améliorer l’expérience utilisateur, automatiser les processus administratifs et offrir une vitrine moderne pour les partenaires touristiques.

## 🧩 Fonctionnalités principales

- 🔹 **Destinations & Activités** : navigation, affichage et filtrage des destinations avec leurs activités associées  
- 🔹 **Missions & Récompenses** : gestion de missions liées aux destinations avec attribution de récompenses  
- 🔹 **Abonnements & Packs** : inscription à des packs touristiques personnalisés  
- 🔹 **Partenaires & Catégories** : gestion des partenaires touristiques selon leurs catégories  
- 🔹 **Wishlist** : ajout de destinations aux favoris pour un accès rapide  
- 🔹 **Réclamations & Calendrier** : soumission et suivi des réclamations avec planification dans un calendrier  
- 🔹 **Paiement & QR Code** : gestion des paiements et génération automatique de QR codes  
- 🔹 **Utilisateurs** : gestion des profils (utilisateur, administrateur, partenaire)  
- 🔹 **Chatbot IA** : intégration d’un assistant intelligent basé sur la technologie RAG (Retrieval-Augmented Generation)  
- 🔹 **Statistiques** : visualisation graphique des indicateurs clés du système  
- 🔹 **Réinitialisation de mot de passe**, **tests d’envoi d’email**, etc.  

## 📁 Structure du projet

```text
Root/
│
├── ProjetWebTrekSwap/       # Application web Symfony
│   ├── assets/              # Fichiers front-end (JS/CSS)
│   ├── bin/                 # Commandes Symfony
│   ├── config/              # Fichiers de configuration
│   ├── migrations/          # Fichiers de migration Doctrine
│   ├── public/              # Dossier public (entrée de l'app)
│   ├── Service/             # Services personnalisés PHP
│   ├── src/                 # Code source (Contrôleurs, Entités…)
│   ├── templates/           # Templates Twig
│   ├── tests/               # Tests automatisés
│   ├── translations/        # Fichiers de traduction
│   ├── var/                 # Cache, logs…
│   ├── vendor/              # Dépendances Composer
│   ├── .env                 # Configuration des variables d’environnement
│   ├── composer.json        # Dépendances et métadonnées PHP
│   └── symfony.lock         # Verrouillage des dépendances
│
├── PI2025_DESKTOP/          # Application desktop JavaFX (interface riche pour utilisateurs hors navigateur)
│   ├── src/                 # Code source Java
│   ├── lib/                 # Librairies Java externes
│   ├── resources/           # Fichiers FXML, images, etc.
│   └── pidev.sql            # Script SQL partagé
│
├── pidev.sql                # Script SQL commun aux deux modules
├── .gitignore               # Fichier gitignore
└── README.md                # Documentation globale
```

## ⚙️ Installation rapide

### 1. **Cloner le projet**
```bash
git clone https://github.com/Aich4/PI2025.git
cd ProjetWebTrekSwap
```

### 2. **Installer les dépendances Symfony**
```bash
composer install
```

### 3. **Configurer l’environnement**
```bash
cp .env .env.local
# Modifier les paramètres de base de données dans .env.local
```

### 4. **Créer la base de données**
Deux possibilités :
- Automatique via Doctrine :
```bash
php bin/console doctrine:database:create
php bin/console doctrine:migrations:migrate
```
- **OU** manuellement via le fichier SQL :
```bash
# Assurez-vous d'avoir créé une base nommée pidev
mysql -u root -p pidev < pidev.sql
```

### 5. **Démarrer le serveur Symfony**
```bash
symfony server:start
```

### 6. **Lancer l’application desktop**
Ouvrir le projet dans un IDE Java (comme IntelliJ ou NetBeans)  
et exécuter la classe principale dans `PI2025_DESKTOP/src`.

## 🔧 Technologies utilisées

- Symfony 6.4
- Doctrine ORM
- Twig
- Bootstrap
- JavaScript (AJAX)
- JavaFX
- MySQL
- API externes : OpenStreetMap, OpenWeather, Gemini AI ...

## 🤝 Contribution

1. Fork le repo  
2. Crée ta branche (`git checkout -b feature/NouvelleFonction`)  
3. Commit (`git commit -m "Ajout d'une nouvelle fonction"`)  
4. Push (`git push origin feature/NouvelleFonction`)  
5. Ouvre une Pull Request  

## 📄 Licence

Ce projet est open-source sous licence MIT.

---

> Ce projet est développé dans un cadre académique à **Esprit School of Engineering**.