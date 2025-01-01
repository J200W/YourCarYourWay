INSERT INTO Utilisateur (ID, nom, email, mot_de_passe) VALUES
(1, 'Service technique YCYW', 'technique@openclassrooms.fr', '$2a$15$9mS35jQpvK0HkYQ2bYFsOO4efOi7WMcDDyCahOI0QspjlplFv4S9i'),
(2, 'Client', 'test@openclassrooms.fr', '$2a$15$9mS35jQpvK0HkYQ2bYFsOO4efOi7WMcDDyCahOI0QspjlplFv4S9i');

INSERT INTO ROLE (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO USER_ROLE (id_user, id_role) VALUES (1, 1);
INSERT INTO USER_ROLE (id_user, id_role) VALUES (2, 2);

INSERT INTO Vehicule (ID, nom, marque, couleur, longueur, largeur) VALUES
(1, 'Voiture', 'Peugeot', 'rouge', 4.5, 1.8),
(2, 'Camion', 'Renault', 'bleu', 6.5, 2.5);

INSERT INTO Offre (ID, categorie, tarif, ville_depart, ville_retour, dates, vehicule_id) VALUES
(1, 'déménagement', 100.00, 'Paris', 'Lyon', '2021-06-01', 2),
(2, 'livraison', 50.00, 'Lille', 'Marseille', '2021-06-15', 1);

INSERT INTO Reservation (ID, statut, utilisateur_id, offre_id) VALUES
(1, 'confirmée', 2, 1),
(2, 'en attente', 1, 2);


INSERT INTO Message (ID, contenu, utilisateur_id) VALUES

(999, 'Bonjour, j''aimerais que vous ajoutiez une fonctionnalité à mon application pour permettre aux utilisateurs de publier des messages avec une date et une heure.', 2);
-- (1000, 'D''accord, cela semble faisable. Nous allons créer une table messages avec un champ date pour stocker l''heure de publication.', 1),
-- (1001, 'Super, et combien de temps cela prendra-t-il ?', 2),
-- (1002, 'Cela devrait prendre environ 1 à 2 heures pour tout mettre en place.', 1),
-- (1003, 'Parfait ! Et pouvez-vous également ajouter une option pour trier les messages par date ?', 2),
-- (1004, 'Oui, nous pourrons ajouter une fonctionnalité de tri des messages par date en utilisant une requête SQL.', 1),
-- (1005, 'Génial ! Est-ce que vous pouvez également m''ajouter un filtre pour rechercher des messages par utilisateur ?', 2),
-- (1006, 'Oui, cela peut être fait en ajoutant un champ utilisateur_id dans la table messages et en effectuant une recherche sur ce champ.', 1),
-- (1007, 'Est-ce que vous avez d''autres questions concernant cette fonctionnalité ?', 1),
-- (1008, 'Non, je pense que c''est tout pour l''instant. Merci pour votre aide !', 2),
-- (1009, 'Avec plaisir ! N''hésitez pas à revenir si vous avez d''autres demandes.', 1);


