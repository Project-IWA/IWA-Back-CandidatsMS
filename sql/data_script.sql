-- Insérer des données dans la table `candidat`
INSERT INTO candidat (email, city, complement, country, street, street_num, zip_code, birth_date, citizenship, cv, etat, first_name, gender, last_name, phone, photo, short_bio)
VALUES
    ('jean.dupont@example.com', 'Paris', 'Appartement 5', 'France', 'Rue de Rivoli', '15', '75001', '1985-06-15', 'Française', 'cv-jeandupont.pdf', 'DISPONIBLE', 'Jean', 1, 'Dupont', '0123456789', 'jeandupont-photo.jpg', 'Caissier Auchan'),
('marie.curie@example.com', 'Lyon', 'Bâtiment B', 'France', 'Avenue Jean Jaurès', '128', '69007', '1990-11-07', 'Française', 'cv-mariecurie.pdf', 'INDISPONIBLE', 'Marie', 0, 'Curie', '0987654321', 'mariecurie-photo.jpg', 'Chercheuse en physique et chimie.');

-- Insérer des données dans la table `disponibilite`
INSERT INTO disponibilite (ends_at, job_category, starts_at, candidat_email)
VALUES
('2023-12-31', 'Commerce, Achats, Vente', '2023-01-01', 'jean.dupont@example.com'),
('2023-12-31', 'Recherche, Sciences', '2023-01-01', 'marie.curie@example.com');

-- Insérer des données dans la table `disponibilite_places`
INSERT INTO disponibilite_places (disponibilite_id, place)
VALUES
(1, 'Paris'),
(2, 'Lyon');

-- Insérer des données dans la table `etablissement`
INSERT INTO etablissement (city, complement, country, street, street_num, zip_code, establishment_name)
VALUES
('Paris', 'Immeuble Haussmannien', 'France', 'Rue de la Paix', '22', '75002', 'Société Parisienne'),
('Lyon', 'Bâtiment moderne', 'France', 'Cours Lafayette', '55', '69003', 'Entreprise Lyonnaise');

-- Insérer des données dans la table `experience`
INSERT INTO experience (ended_at, job, job_category, started_at, etablissement_id, candidat_email)
VALUES
('2022-12-31', 'Vendeur spécialisé', 'Commerce, Achats, Vente', '2022-01-01', 1, 'jean.dupont@example.com'),
('2022-12-31', 'Chercheur', 'Recherche, Sciences', '2022-01-01', 2, 'marie.curie@example.com');

-- Insérer des données dans la table `opinion`
INSERT INTO opinion (employer_id, message, provided_at, score, candidat_email)
VALUES
(1, 'Très compétent et engagé.', '2023-01-01', 4.5, 'jean.dupont@example.com'),
(2, 'Excellente dans son domaine de recherche.', '2023-01-01', 4.8, 'marie.curie@example.com');

-- Insérer des données dans la table `reference`
INSERT INTO reference (city, complement, country, street, street_num, zip_code, ref_email, ref_name, ref_phone, candidat_email)
VALUES
('Marseille', 'Résidence du Port', 'France', 'Quai des Belges', '10', '13001', 'ref.marseille@example.com', 'Entreprise Marseillaise', '0123456789', 'jean.dupont@example.com'),
('Bordeaux', 'Quartier des Chartrons', 'France', 'Cours du Médoc', '89', '33000', 'ref.bordeaux@example.com', 'Laboratoire Bordelais', '0987654321', 'marie.curie@example.com');
