
# 🌍 ProjetWebTrekSwap

Projet académique réalisé dans le cadre du module **PIDEV 3A** à **Esprit School of Engineering**.  
Ce projet Symfony 6.4 propose une plateforme complète pour la gestion de voyages, combinant plusieurs services comme la gestion des destinations, missions, abonnements, réclamations, paiements, recommandations et bien plus.

## 🚀 Aperçu

**TrekSwap** est une plateforme web innovante dédiée à la digitalisation du secteur touristique. Elle permet aux utilisateurs d’explorer des destinations, de s’abonner à des offres personnalisées, d’interagir avec des partenaires et de gérer facilement leurs activités de voyage à travers une interface intuitive.

Pensé comme un système modulaire et évolutif, le projet repose sur une architecture Symfony robuste et intègre des services avancés tels que la génération de documents, les paiements numériques, les QR codes, les statistiques décisionnelles, ainsi qu’un assistant intelligent basé sur l’IA générative (RAG).

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
ProjetWebTrekSwap/
│
├── assets/                  # Fichiers front-end (JS/CSS)
├── bin/                     # Commandes Symfony
├── config/                  # Fichiers de configuration
├── migrations/              # Fichiers de migration Doctrine
├── public/                  # Dossier public (entrée de l'app)
├── Service/                 # Services personnalisés PHP
├── src/                     # Code source (Contrôleurs, Entités…)
├── templates/               # Templates Twig
├── tests/                   # Tests automatisés
├── translations/            # Fichiers de traduction
├── var/                     # Cache, logs…
├── vendor/                  # Dépendances Composer
├── .env                     # Configuration des variables d’environnement
├── composer.json            # Dépendances et métadonnées PHP
└── symfony.lock             # Verrouillage des dépendances
```

## ⚙️ Installation rapide

1. **Cloner le projet**
```bash
git clone https://github.com/Aich4/PI2025.git
cd ProjetWebTrekSwap
```

2. **Installer les dépendances**
```bash
composer install
```

3. **Configurer l’environnement**
```bash
cp .env .env.local
# Modifier les paramètres de base de données dans .env.local
```

4. **Créer la base de données**
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

5. **Démarrer le serveur**
```bash
symfony server:start
```

## 🔧 Technologies utilisées

- Symfony 6.4
- Doctrine ORM
- Twig
- Bootstrap
- JavaScript (AJAX)
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
