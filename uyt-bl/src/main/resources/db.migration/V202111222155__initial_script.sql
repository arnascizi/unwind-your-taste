-- -----------------------------------------------------
-- Table LYTIS
-- -----------------------------------------------------
DROP TABLE IF EXISTS LYTIS;

CREATE TABLE IF NOT EXISTS LYTIS
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE LYTIS IS 'Lytį aprašanti lentelė';
COMMENT ON COLUMN LYTIS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN LYTIS.PAVADINIMAS IS 'Lytį nusakantis pavadinimas';

-- -----------------------------------------------------
-- Table SALIS
-- -----------------------------------------------------
DROP TABLE IF EXISTS SALIS;

CREATE TABLE IF NOT EXISTS SALIS
(
    ID           BIGINT            NOT NULL,
    PAVADINIMAS  CHARACTER VARYING NOT NULL,
    SALIES_KODAS CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE SALIS IS 'Šalį aprašanti lentelė';
COMMENT ON COLUMN SALIS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN SALIS.PAVADINIMAS IS 'Šalies pavadinimas';
COMMENT ON COLUMN SALIS.SALIES_KODAS IS 'Šalies kodas pagal ISO-3166-1 standartą';

-- -----------------------------------------------------
-- Table KONTAKTAI
-- -----------------------------------------------------
DROP TABLE IF EXISTS KONTAKTAI;

CREATE TABLE IF NOT EXISTS KONTAKTAI
(
    ID          BIGINT            NOT NULL,
    SALIS_ID    BIGINT            NOT NULL,
    SAVIVALDYBE CHARACTER VARYING NULL,
    PASTO_KODAS DECIMAL(10)       NULL,
    GATVE       CHARACTER VARYING NULL,
    NAMO_NR     CHARACTER VARYING NULL,
    BUTO_NR     CHARACTER VARYING NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_SALIS_ID
        FOREIGN KEY (SALIS_ID)
            REFERENCES SALIS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE KONTAKTAI IS 'Gyvenamąją vietą aprašanti lentelė';
COMMENT ON COLUMN KONTAKTAI.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN KONTAKTAI.SALIS_ID IS 'Nuorodą į šalies prasminį raktą';
COMMENT ON COLUMN KONTAKTAI.SAVIVALDYBE IS 'Kontaktinio asmens gyvenamoji savivaldybė';
COMMENT ON COLUMN KONTAKTAI.PASTO_KODAS IS 'Gyvenamosios vietos pašto kodas';
COMMENT ON COLUMN KONTAKTAI.GATVE IS 'Gyvenamosios vietos adresas';
COMMENT ON COLUMN KONTAKTAI.NAMO_NR IS 'Gyvenamosios vietos namo numeris';
COMMENT ON COLUMN KONTAKTAI.BUTO_NR IS 'Gyvenamosios vietos buto numeris';

-- -----------------------------------------------------
-- Table VARTOTOJO_DUOMENYS
-- -----------------------------------------------------
DROP TABLE IF EXISTS VARTOTOJO_DUOMENYS;

CREATE TABLE IF NOT EXISTS VARTOTOJO_DUOMENYS
(
    ID               BIGINT            NOT NULL,
    VARDAS           CHARACTER VARYING NOT NULL,
    PAVARDE          CHARACTER VARYING NOT NULL,
    SUKURIMO_DATA     timestamp         NOT NULL,
    ATNAUJINIMO_DATA TIMESTAMP         NOT NULL,
    LYTIS_ID         BIGINT            NOT NULL,
    KONTAKTAI_ID     BIGINT            NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_KONTAKTAI_ID
        FOREIGN KEY (KONTAKTAI_ID)
            REFERENCES KONTAKTAI (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_LYTIS_ID
        FOREIGN KEY (LYTIS_ID)
            REFERENCES LYTIS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE VARTOTOJO_DUOMENYS IS 'Vartotojo duomenis aprašanti lentelė';
COMMENT ON COLUMN VARTOTOJO_DUOMENYS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN VARTOTOJO_DUOMENYS.VARDAS IS 'Vartotojo vardas';
COMMENT ON COLUMN VARTOTOJO_DUOMENYS.PAVARDE IS 'Vartotojo pavardė';
COMMENT ON COLUMN VARTOTOJO_DUOMENYS.SUKURIMO_DATA IS 'Vartotojo sukūrimo data';
COMMENT ON COLUMN VARTOTOJO_DUOMENYS.ATNAUJINIMO_DATA IS 'Vartotojo duomenų atnaujinimo data';
COMMENT ON COLUMN VARTOTOJO_DUOMENYS.LYTIS_ID IS 'Nuorodą į lyties prasminį raktą';
COMMENT ON COLUMN VARTOTOJO_DUOMENYS.KONTAKTAI_ID IS 'Nuorodą į kontaktų prasminį raktą';


-- -----------------------------------------------------
-- Table VARTOTOJO_GUPE
-- -----------------------------------------------------
DROP TABLE IF EXISTS VARTOTOJO_GUPE;

CREATE TABLE IF NOT EXISTS VARTOTOJO_GUPE
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE VARTOTOJO_GUPE IS 'Vartotojo grupes aprašanti lentelė';
COMMENT ON COLUMN VARTOTOJO_GUPE.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN VARTOTOJO_GUPE.PAVADINIMAS IS 'Vartotojo grupės pavadinimas';

-- -----------------------------------------------------
-- Table VARTOTOJAS
-- -----------------------------------------------------
DROP TABLE IF EXISTS VARTOTOJAS;

CREATE TABLE IF NOT EXISTS VARTOTOJAS
(
    ID                    BIGINT            NOT NULL,
    PRISIJUNGIMO_VARDAS   CHARACTER VARYING NOT NULL,
    EL_PASTAS             CHARACTER VARYING NOT NULL,
    SLAPTAZODIS           CHARACTER VARYING NOT NULL,
    VARTOTOJO_GRUPE_ID    BIGINT            NOT NULL,
    VARTOTOJO_DUOMENYS_ID BIGINT            NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_VARTOTOJO_DUOMENYS_ID
        FOREIGN KEY (VARTOTOJO_DUOMENYS_ID)
            REFERENCES VARTOTOJO_DUOMENYS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_VARTOTOJO_GRUPE
        FOREIGN KEY (VARTOTOJO_GRUPE_ID)
            REFERENCES VARTOTOJO_GUPE (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE VARTOTOJAS IS 'Vartotoją aprašanti lentelė';
COMMENT ON COLUMN VARTOTOJAS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN VARTOTOJAS.PRISIJUNGIMO_VARDAS IS 'Vartotojo prisijungimo vardas';
COMMENT ON COLUMN VARTOTOJAS.EL_PASTAS IS 'Vartotojo elektroninis pašto adresas';
COMMENT ON COLUMN VARTOTOJAS.SLAPTAZODIS IS 'Užšifruotas vartotojo slaptažodis';
COMMENT ON COLUMN VARTOTOJAS.VARTOTOJO_GRUPE_ID IS 'Nuorodą į vartotojo grupių prasminį raktą';
COMMENT ON COLUMN VARTOTOJAS.VARTOTOJO_DUOMENYS_ID IS 'Nuorodą į vartotojo duomenų prasminį raktą';

-- -----------------------------------------------------
-- Table KOMENTARAS
-- -----------------------------------------------------
DROP TABLE IF EXISTS KOMENTARAS;

CREATE TABLE IF NOT EXISTS KOMENTARAS
(
    ID            BIGINT            NOT NULL,
    VARTOTOJAS_ID BIGINT            NOT NULL,
    TEKSTAS       CHARACTER VARYING NULL,
    VERTINIMAS    DECIMAL(3)        NULL DEFAULT 0,
    PRIMARY KEY (ID),
    CONSTRAINT FK_VARTOTOJAS_ID
        FOREIGN KEY (VARTOTOJAS_ID)
            REFERENCES VARTOTOJAS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE KOMENTARAS IS 'Komentarą aprašanti lentelė';
COMMENT ON COLUMN KOMENTARAS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN KOMENTARAS.VARTOTOJAS_ID IS 'Nuorodą į vartotojo prasminį raktą';
COMMENT ON COLUMN KOMENTARAS.TEKSTAS IS 'Komentaros tekstas';
COMMENT ON COLUMN KOMENTARAS.VERTINIMAS IS 'Komentaros vertinimas';

-- -----------------------------------------------------
-- Table MATAVIMO_VIENETAS
-- -----------------------------------------------------
DROP TABLE IF EXISTS MATAVIMO_VIENETAS;

CREATE TABLE IF NOT EXISTS MATAVIMO_VIENETAS
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    VIENETAS    CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE MATAVIMO_VIENETAS IS 'Matavimo vienetą aprašanti lentelė';
COMMENT ON COLUMN MATAVIMO_VIENETAS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN MATAVIMO_VIENETAS.PAVADINIMAS IS 'Matavimo vieneto pavadinimas';
COMMENT ON COLUMN MATAVIMO_VIENETAS.VIENETAS IS 'Matavimo vieneto žymėjimas';

-- -----------------------------------------------------
-- Table TIPAS
-- -----------------------------------------------------
DROP TABLE IF EXISTS TIPAS;

CREATE TABLE IF NOT EXISTS TIPAS
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE TIPAS IS 'Tipą aprašanti lentelė';
COMMENT ON COLUMN TIPAS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN TIPAS.PAVADINIMAS IS 'Tipo pavadinimas';

-- -----------------------------------------------------
-- Table KATEGORIJA
-- -----------------------------------------------------
DROP TABLE IF EXISTS KATEGORIJA;

CREATE TABLE IF NOT EXISTS KATEGORIJA
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    APRASYMAS   CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE KATEGORIJA IS 'Kategoriją aprašanti lentelė';
COMMENT ON COLUMN KATEGORIJA.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN KATEGORIJA.PAVADINIMAS IS 'Kategorijos pavadinimas';
COMMENT ON COLUMN KATEGORIJA.APRASYMAS IS 'Trumpas aprašymas apie kategoriją';

-- -----------------------------------------------------
-- Table SUDETINGUMAS
-- -----------------------------------------------------
DROP TABLE IF EXISTS SUDETINGUMAS;

CREATE TABLE IF NOT EXISTS SUDETINGUMAS
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE SUDETINGUMAS IS 'Sudėtingumą aprašanti lentelė';
COMMENT ON COLUMN SUDETINGUMAS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN SUDETINGUMAS.PAVADINIMAS IS 'Sudėtingumo reikšmės pavadinimas';

-- -----------------------------------------------------
-- Table RECEPTAS
-- -----------------------------------------------------
DROP TABLE IF EXISTS RECEPTAS;

CREATE TABLE IF NOT EXISTS RECEPTAS
(
    ID                   BIGINT            NOT NULL,
    PAVADINIMAS          CHARACTER VARYING NOT NULL,
    PARUOSIMO_LAIKAS     CHARACTER VARYING NOT NULL,
    PAVEIKSLIUKAS        BYTEA             NOT NULL,
    GAMINIMO_INSTRUKCIJA CHARACTER VARYING NOT NULL,
    PATIEKIMAS           CHARACTER VARYING NOT NULL,
    PATALPINIMO_LAIKAS   TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ATNAUJINIMO_LAIKAS   TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    VARTOTOJAS_ID        BIGINT            NOT NULL,
    TIPAS_ID             BIGINT            NOT NULL,
    KATEGORIJA_ID        BIGINT            NOT NULL,
    SUDETINGUMAS_ID      BIGINT            NOT NULL,
    KOMENTARAS_ID        BIGINT            NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_KOMENTARAS_ID
        FOREIGN KEY (KOMENTARAS_ID)
            REFERENCES KOMENTARAS (ID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_TIPAS_ID
        FOREIGN KEY (TIPAS_ID)
            REFERENCES TIPAS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_KATEGORIJA_ID
        FOREIGN KEY (KATEGORIJA_ID)
            REFERENCES KATEGORIJA (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_SUDETINGUMAS_ID
        FOREIGN KEY (SUDETINGUMAS_ID)
            REFERENCES SUDETINGUMAS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE RECEPTAS IS 'Receptą aprašanti lentelė';
COMMENT ON COLUMN RECEPTAS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN RECEPTAS.PAVADINIMAS IS 'Receptui priskirtas pavadinimas';
COMMENT ON COLUMN RECEPTAS.PARUOSIMO_LAIKAS IS 'Koketeilio paruošimo laikas';
COMMENT ON COLUMN RECEPTAS.PAVEIKSLIUKAS IS 'Kokteilio nuotrauka';
COMMENT ON COLUMN RECEPTAS.GAMINIMO_INSTRUKCIJA IS 'Kokteilio gaminimo instrukcija';
COMMENT ON COLUMN RECEPTAS.PATIEKIMAS IS 'Rekomenduojamas kokteilio patiekimas';
COMMENT ON COLUMN RECEPTAS.PATALPINIMO_LAIKAS IS 'Recepto patalpinimo laikas';
COMMENT ON COLUMN RECEPTAS.ATNAUJINIMO_LAIKAS IS 'Recepto koregavimo laikas';
COMMENT ON COLUMN RECEPTAS.VARTOTOJAS_ID IS 'Nuorodą į vartotojo prasminį raktą';
COMMENT ON COLUMN RECEPTAS.TIPAS_ID IS 'Nuorodą į tipo prasminį raktą';
COMMENT ON COLUMN RECEPTAS.KATEGORIJA_ID IS 'Nuorodą į kategorijos prasminį raktą';
COMMENT ON COLUMN RECEPTAS.SUDETINGUMAS_ID IS 'Nuorodą į sudėtingumo prasminį raktą';
COMMENT ON COLUMN RECEPTAS.KOMENTARAS_ID IS 'Nuorodą į komentaro prasminį raktą';

-- -----------------------------------------------------
-- Table PRODUKTO_RUSIS
-- -----------------------------------------------------
DROP TABLE IF EXISTS PRODUKTO_RUSIS;

CREATE TABLE IF NOT EXISTS PRODUKTO_RUSIS
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE PRODUKTO_RUSIS IS 'Produkto rušį aprašanti lentelė';
COMMENT ON COLUMN PRODUKTO_RUSIS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN PRODUKTO_RUSIS.PAVADINIMAS IS 'Produkto rūšies pavadinimas';

-- -----------------------------------------------------
-- Table PRODUKTAS
-- -----------------------------------------------------
DROP TABLE IF EXISTS PRODUKTAS;

CREATE TABLE IF NOT EXISTS PRODUKTAS
(
    ID                   BIGINT            NOT NULL,
    PAVADINIMAS          CHARACTER VARYING NOT NULL,
    KIEKIS               DECIMAL(5, 2)     NOT NULL,
    PRODUKTO_RUSIS_ID    BIGINT            NOT NULL,
    MATAVIMO_VIENETAS_ID BIGINT            NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_PRODUKTO_RUSIS_ID
        FOREIGN KEY (PRODUKTO_RUSIS_ID)
            REFERENCES PRODUKTO_RUSIS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_MATAVIMO_VIENETAS_ID
        FOREIGN KEY (MATAVIMO_VIENETAS_ID)
            REFERENCES MATAVIMO_VIENETAS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE PRODUKTAS IS 'Produktą aprašanti lentelė';
COMMENT ON COLUMN PRODUKTAS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN PRODUKTAS.PAVADINIMAS IS 'Produkto pavadinimas';
COMMENT ON COLUMN PRODUKTAS.KIEKIS IS 'Produkto kiekis';
COMMENT ON COLUMN PRODUKTAS.PRODUKTO_RUSIS_ID IS 'Nuorodą į produkto rūšies prasminį raktą';
COMMENT ON COLUMN PRODUKTAS.MATAVIMO_VIENETAS_ID IS 'Nuorodą į matavimo vieneto prasminį raktą';

-- -----------------------------------------------------
-- Table SUDETIS
-- -----------------------------------------------------
DROP TABLE IF EXISTS SUDETIS;

CREATE TABLE IF NOT EXISTS SUDETIS
(
    RECEPTAS_ID  BIGINT NOT NULL,
    PRODUKTAS_ID BIGINT NOT NULL,
    CONSTRAINT FK_RECEPTAS_ID
        FOREIGN KEY (RECEPTAS_ID)
            REFERENCES RECEPTAS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_PRODUKTAS_ID
        FOREIGN KEY (PRODUKTAS_ID)
            REFERENCES PRODUKTAS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE SUDETIS IS 'Sudėtį aprašanti lentelė';
COMMENT ON COLUMN SUDETIS.RECEPTAS_ID IS 'Nuorodą į recepto prasminį raktą';
COMMENT ON COLUMN SUDETIS.PRODUKTAS_ID IS 'Nuorodą į produkto prasminį raktą';

-- -----------------------------------------------------
-- Table RUSIS
-- -----------------------------------------------------
DROP TABLE IF EXISTS RUSIS;

CREATE TABLE IF NOT EXISTS RUSIS
(
    ID          BIGINT            NOT NULL,
    PAVADINIMAS CHARACTER VARYING NOT NULL,
    PRIMARY KEY (ID)
);

COMMENT ON TABLE RUSIS IS 'Rušį aprašanti lentelė';
COMMENT ON COLUMN RUSIS.ID IS 'Prasminis lentelės raktas';
COMMENT ON COLUMN RUSIS.PAVADINIMAS IS 'Rūšies pavadinimas';

-- -----------------------------------------------------
-- Table KOKTEILIO_RUSIS
-- -----------------------------------------------------
DROP TABLE IF EXISTS KOKTEILIO_RUSIS;

CREATE TABLE IF NOT EXISTS KOKTEILIO_RUSIS
(
    RECEPTAS_ID BIGINT NOT NULL,
    RUSIS_ID    BIGINT NOT NULL,
    CONSTRAINT FK_RECEPTAS_ID
        FOREIGN KEY (RECEPTAS_ID)
            REFERENCES RECEPTAS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_RUSIS_ID
        FOREIGN KEY (RUSIS_ID)
            REFERENCES RUSIS (ID)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

COMMENT ON TABLE KOKTEILIO_RUSIS IS 'Koketeilio rušį aprašanti lentelė';
COMMENT ON COLUMN KOKTEILIO_RUSIS.RECEPTAS_ID IS 'Nuorodą į recepto prasminį raktą';
COMMENT ON COLUMN KOKTEILIO_RUSIS.RUSIS_ID IS 'Nuorodą į rūšies prasminį raktą';