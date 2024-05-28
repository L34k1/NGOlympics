
CREATE TABLE "Athlete" (
	"id" serial NOT NULL,
	"Nom" text,
	"Sexe" boolean,
	"Pays" text,
	"Date de naissance" date,
	PRIMARY KEY("id")
);

CREATE TABLE "Discipline" (
	"id" serial NOT NULL,
	"Nom" text,
	PRIMARY KEY("id")
);

CREATE TABLE "Epreuve" (
	"id" serial NOT NULL,
	"Date" timestamp,
	"Lieu" text,
	"Discipline" text,
	PRIMARY KEY("id")
);

CREATE TABLE "Resultat" (
	"id" serial NOT NULL,
	"Médaille" char(1),
	"Validation" boolean,
	PRIMARY KEY("id")
);

ALTER TABLE "Discipline"
ADD FOREIGN KEY("id") REFERENCES "Athlete"("id")
ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "Epreuve"
ADD FOREIGN KEY("id") REFERENCES "Discipline"("id")
ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "Resultat"
ADD FOREIGN KEY("id") REFERENCES "Epreuve"("id")
ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO "Athlete" ("Nom", "Sexe", "Pays", "Date de naissance")
VALUES ('Maria Rodriguez', FALSE, 'Spain', '1995-08-12');

INSERT INTO "Athlete" ("Nom", "Sexe", "Pays", "Date de naissance")
VALUES ('Alexander Smith', TRUE, 'United States', '1990-04-25');

INSERT INTO "Athlete" ("Nom", "Sexe", "Pays", "Date de naissance")
VALUES ('Li Wei', TRUE, 'China', '1992-11-30');

INSERT INTO "Athlete" ("Nom", "Sexe", "Pays", "Date de naissance")
VALUES ('Anna Müller', FALSE, 'Germany', '1988-07-18');

INSERT INTO "Athlete" ("Nom", "Sexe", "Pays", "Date de naissance")
VALUES ('Rafael Dos Santos', TRUE, 'Brazil', '1994-03-10');