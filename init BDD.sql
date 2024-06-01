-- Drop tables if they exist
DROP TABLE IF EXISTS "Resultat" CASCADE;
DROP TABLE IF EXISTS "Epreuve" CASCADE;
DROP TABLE IF EXISTS "Athlete" CASCADE;
DROP TABLE IF EXISTS "Discipline" CASCADE;

-- Create Discipline table
CREATE TABLE "Discipline" (
    "id" SERIAL PRIMARY KEY,
    "Nom" TEXT NOT NULL
);

-- Create Athlete table
CREATE TABLE "Athlete" (
    "id" SERIAL PRIMARY KEY,
    "Nom" VARCHAR(255) NOT NULL,
    "Sexe" BOOLEAN NOT NULL,
    "Pays" VARCHAR(255) NOT NULL,
    "Date de naissance" DATE NOT NULL,
    "Discipline_ID" INTEGER,
    FOREIGN KEY ("Discipline_ID") REFERENCES "Discipline" ("id") ON DELETE CASCADE
);

-- Create Epreuve table
CREATE TABLE "Epreuve" (
    "id" SERIAL PRIMARY KEY,
    "Date" DATE NOT NULL,
    "Location" VARCHAR(255) NOT NULL,
    "Discipline" VARCHAR(255) NOT NULL,
    "nom" VARCHAR(255) NOT NULL,
    "Discipline_ID" INTEGER NOT NULL,
    "Athlete_ID_List" VARCHAR(255) NOT NULL,
    FOREIGN KEY ("Discipline_ID") REFERENCES "Discipline" ("id") ON DELETE CASCADE
);

-- Create Resultat table
CREATE TABLE "Resultat" (
    "id" SERIAL PRIMARY KEY,
    "Médaille" CHAR(1) CHECK ("Médaille" IN ('B', 'S', 'G')) NOT NULL,
    "Validation" BOOLEAN NOT NULL,
    "Athlete_ID" INTEGER NOT NULL,
    "Score" TEXT NOT NULL,
    "Epreuve_ID" INTEGER NOT NULL,
    FOREIGN KEY ("Athlete_ID") REFERENCES "Athlete" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("Epreuve_ID") REFERENCES "Epreuve" ("id") ON DELETE CASCADE
);

-- Insert random values into Discipline table
INSERT INTO "Discipline" ("Nom") VALUES
('100m Sprint'),
('Marathon'),
('Long Jump');

-- Insert random values into Athlete table
INSERT INTO "Athlete" ("Nom", "Sexe", "Pays", "Date de naissance", "Discipline_ID") VALUES
('John Doe', TRUE, 'USA', '1990-01-15', 1),
('Jane Smith', FALSE, 'UK', '1985-06-22', 2),
('Pierre Dupont', TRUE, 'France', '1992-03-18', 3),
('Maria Garcia', FALSE, 'Spain', '1988-11-05', 1),
('Liu Wei', TRUE, 'China', '1995-07-30', 2);

-- Insert random values into Epreuve table
INSERT INTO "Epreuve" ("Date", "Location", "Discipline", "nom", "Discipline_ID", "Athlete_ID_List") VALUES
('2024-06-15', 'Paris', '100m Sprint', 'Summer Games 2024', 1, '{1,3}'),
('2024-07-20', 'London', 'Marathon', 'World Marathon', 2, '{2,5}'),
('2024-08-25', 'New York', 'Long Jump', 'NYC Championship', 3, '{4}'),
('2024-09-10', 'Tokyo', '100m Sprint', 'Tokyo Open', 1, '{1,2,4}'),
('2024-10-05', 'Berlin', 'Marathon', 'Berlin Marathon', 2, '{2,3,5}');

-- Insert random values into Resultat table
INSERT INTO "Resultat" ("Médaille", "Validation", "Athlete_ID", "Score", "Epreuve_ID") VALUES
('G', TRUE, 1, '10.12s', 1),
('S', TRUE, 3, '10.15s', 1),
('B', FALSE, 2, '2:35:30', 2),
('G', TRUE, 5, '2:37:15', 2),
('G', TRUE, 4, '7.85m', 3),
('G', TRUE, 1, '10.10s', 4),
('S', FALSE, 2, '10.22s', 4),
('B', TRUE, 4, '10.30s', 4),
('G', TRUE, 2, '2:34:20', 5),
('S', TRUE, 3, '2:36:10', 5),
('B', TRUE, 5, '2:39:05', 5);

-- To check the values inserted
SELECT * FROM "Discipline";
SELECT * FROM "Athlete";
SELECT * FROM "Epreuve";
SELECT * FROM "Resultat";
