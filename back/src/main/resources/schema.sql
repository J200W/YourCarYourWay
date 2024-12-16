-- Initialize the schema for the database
DROP TABLE IF EXISTS Reservation;

DROP TABLE IF EXISTS Offre;

DROP TABLE IF EXISTS Vehicule;

DROP TABLE IF EXISTS Message;

DROP TABLE IF EXISTS Utilisateur;

-- Table Utilisateur
CREATE TABLE
    Utilisateur (
        ID INT AUTO_INCREMENT PRIMARY KEY,
        nom VARCHAR(255) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL,
        mot_de_passe VARCHAR(255) NOT NULL
    );

-- Table Message
CREATE TABLE
    Message (
        ID INT AUTO_INCREMENT PRIMARY KEY,
        contenu TEXT NOT NULL,
        type VARCHAR(10) NOT NULL,
        utilisateur_id INT NOT NULL,
        date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur (ID) ON DELETE CASCADE
    );

-- Table Véhicule
CREATE TABLE
    Vehicule (
        ID INT AUTO_INCREMENT PRIMARY KEY,
        nom VARCHAR(255) NOT NULL,
        marque VARCHAR(255) NOT NULL,
        couleur VARCHAR(50),
        longueur DECIMAL(5, 2),
        largeur DECIMAL(5, 2)
    );

-- Table Offre
CREATE TABLE
    Offre (
        ID INT AUTO_INCREMENT PRIMARY KEY,
        categorie VARCHAR(50) NOT NULL,
        tarif DECIMAL(10, 2) NOT NULL,
        ville_depart VARCHAR(255) NOT NULL,
        ville_retour VARCHAR(255) NOT NULL,
        dates DATE NOT NULL,
        vehicule_id INT NOT NULL,
        FOREIGN KEY (vehicule_id) REFERENCES Vehicule (ID) ON DELETE CASCADE
    );

-- Table Reservation
CREATE TABLE
    Reservation (
        ID INT AUTO_INCREMENT PRIMARY KEY,
        statut ENUM ('confirmée', 'annulée', 'en attente') NOT NULL,
        utilisateur_id INT NOT NULL,
        offre_id INT NOT NULL,
        FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur (ID) ON DELETE CASCADE,
        FOREIGN KEY (offre_id) REFERENCES Offre (ID) ON DELETE CASCADE
    );