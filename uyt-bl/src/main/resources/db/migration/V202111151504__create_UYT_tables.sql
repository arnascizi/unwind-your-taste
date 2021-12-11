create sequence if not exists lytis_id_seq maxvalue 2147483647;
alter sequence lytis_id_seq owner to postgres;
drop table if exists lytis;
create table if not exists lytis
(
    id          integer default nextval('lytis_id_seq'::regclass) not null
        constraint lytis_id_pkey primary key,
    pavadinimas character varying                                 not null
);
comment on table lytis is 'Lytį aprašanti lentelė';
comment on column lytis.id is 'Prasminis lentelės raktas';
comment on column lytis.pavadinimas is 'Lytį nusakantis pavadinimas';

create sequence if not exists salis_id_seq maxvalue 2147483647;
alter sequence salis_id_seq owner to postgres;
drop table if exists salis;
create table if not exists salis
(
    id           integer default nextval('salis_id_seq'::regclass) not null
        constraint salis_id_pkey primary key,
    pavadinimas  character varying                                 not null,
    salies_kodas character varying                                 not null
);
comment on table salis is 'Šalį aprašanti lentelė';
comment on column salis.id is 'Prasminis lentelės raktas';
comment on column salis.pavadinimas is 'Šalies pavadinimas';
comment on column salis.salies_kodas is 'Šalies kodas pagal iso-3166-1 standartą';

create sequence if not exists kontaktai_id_seq maxvalue 2147483647;
alter sequence kontaktai_id_seq owner to postgres;
drop table if exists kontaktai;
create table if not exists kontaktai
(
    id          integer default nextval('kontaktai_id_seq'::regclass) not null
        constraint kontaktai_id_pkey primary key,
    savivaldybe character varying                                     null,
    gatve       character varying                                     null,
    namo_nr     character varying                                     null,
    buto_nr     character varying                                     null,
    pasto_kodas decimal(10)                                           null,
    salis_id    integer
        constraint salis_id_fkey references salis on delete no action on update no action
);
comment on table kontaktai is 'Gyvenamąją vietą aprašanti lentelė';
comment on column kontaktai.id is 'Prasminis lentelės raktas';
comment on column kontaktai.salis_id is 'Nuorodą į šalies prasminį raktą';
comment on column kontaktai.savivaldybe is 'Kontaktinio asmens gyvenamoji savivaldybė';
comment on column kontaktai.pasto_kodas is 'Gyvenamosios vietos pašto kodas';
comment on column kontaktai.gatve is 'Gyvenamosios vietos adresas';
comment on column kontaktai.namo_nr is 'Gyvenamosios vietos namo numeris';
comment on column kontaktai.buto_nr is 'Gyvenamosios vietos buto numeris';

create sequence if not exists vartotojo_grupe_id_seq maxvalue 2147483647;
alter sequence vartotojo_grupe_id_seq owner to postgres;
drop table if exists vartotojo_grupe;
create table if not exists vartotojo_grupe
(
    id          integer default nextval('vartotojo_grupe_id_seq'::regclass) not null
        constraint vartotojo_grupe_id_pkey primary key,
    pavadinimas character varying                                          not null
);
comment on table vartotojo_grupe is 'Vartotojo grupes aprašanti lentelė';
comment on column vartotojo_grupe.id is 'Prasminis lentelės raktas';
comment on column vartotojo_grupe.pavadinimas is 'Vartotojo grupės pavadinimas';

create sequence if not exists vartotojas_id_seq maxvalue 2147483647;
alter sequence vartotojas_id_seq owner to postgres;
drop table if exists vartotojas;
create table if not exists vartotojas
(
    id                  integer default nextval('vartotojas_id_seq'::regclass) not null
        constraint vartotojas_id_pkey primary key,
    prisijungimo_vardas character varying                                      not null,
    el_pastas           character varying                                      not null,
    slaptazodis         character varying                                      not null,
    aktyvuotas          boolean                                                not null,
    vartotojo_grupe_id  integer
        constraint vartotojo_grupe_id_fkey references vartotojo_grupe on delete no action on update no action
);
comment on table vartotojas is 'Vartotoją aprašanti lentelė';
comment on column vartotojas.id is 'Prasminis lentelės raktas';
comment on column vartotojas.prisijungimo_vardas is 'Vartotojo prisijungimo vardas';
comment on column vartotojas.el_pastas is 'Vartotojo elektroninis pašto adresas';
comment on column vartotojas.slaptazodis is 'Užšifruotas vartotojo slaptažodis';
comment on column vartotojas.aktyvuotas is 'Požymis nurodantis ar vartotojas aktyvuotas';
comment on column vartotojas.vartotojo_grupe_id is 'Nuorodą į vartotojo grupių prasminį raktą';

create sequence if not exists vartotojo_duomenys_id_seq maxvalue 2147483647;
alter sequence vartotojo_duomenys_id_seq owner to postgres;
drop table if exists vartotojo_duomenys;
create table if not exists vartotojo_duomenys
(
    id               integer default nextval('vartotojo_duomenys_id_seq'::regclass) not null
        constraint vartotojo_duomenys_id_pkey primary key,
    vardas           character varying                                              not null,
    pavarde          character varying                                              not null,
    sukurimo_data    timestamp                                                      not null,
    atnaujinimo_data timestamp                                                      not null,
    lytis_id         integer
        constraint lytis_id_fkey references lytis on delete no action
            on update no action,
    kontaktai_id     integer
        constraint kontaktai_id_fkey references kontaktai on delete no action
            on update no action,
    vartotojas_id    integer
        constraint vartotojas_id_fkey references vartotojas on delete no action
            on update no action
);
comment on table vartotojo_duomenys is 'Vartotojo duomenis aprašanti lentelė';
comment on column vartotojo_duomenys.id is 'Prasminis lentelės raktas';
comment on column vartotojo_duomenys.vardas is 'Vartotojo vardas';
comment on column vartotojo_duomenys.pavarde is 'Vartotojo pavardė';
comment on column vartotojo_duomenys.sukurimo_data is 'Vartotojo sukūrimo data';
comment on column vartotojo_duomenys.atnaujinimo_data is 'Vartotojo duomenų atnaujinimo data';
comment on column vartotojo_duomenys.lytis_id is 'Nuorodą į lyties prasminį raktą';
comment on column vartotojo_duomenys.kontaktai_id is 'Nuorodą į kontaktų prasminį raktą';
comment on column vartotojo_duomenys.vartotojas_id is 'Nuorodą į vartotojo prasminį raktą';

create sequence if not exists matavimo_vienetas_id_seq maxvalue 2147483647;
alter sequence matavimo_vienetas_id_seq owner to postgres;
drop table if exists matavimo_vienetas;
create table if not exists matavimo_vienetas
(
    id          integer default nextval('matavimo_vienetas_id_seq') not null
        constraint matavimo_vienetas_id_pkey primary key,
    pavadinimas character varying                                   not null,
    vienetas    character varying                                   not null
);
comment on table matavimo_vienetas is 'Matavimo vienetą aprašanti lentelė';
comment on column matavimo_vienetas.id is 'Prasminis lentelės raktas';
comment on column matavimo_vienetas.pavadinimas is 'Matavimo vieneto pavadinimas';
comment on column matavimo_vienetas.vienetas is 'Matavimo vieneto žymėjimas';

create sequence if not exists kategorijos_tipas_id_seq maxvalue 2147483647;
alter sequence kategorijos_tipas_id_seq owner to postgres;
drop table if exists kategorijos_tipas;
create table if not exists kategorijos_tipas
(
    id          integer default nextval('kategorijos_tipas_id_seq') not null
        constraint kategorijos_tipas_id_pkey primary key,
    pavadinimas character varying                                   not null
);
comment on table kategorijos_tipas is 'Kategorijos tipą aprašanti lentelė';
comment on column kategorijos_tipas.id is 'Prasminis lentelės raktas';
comment on column kategorijos_tipas.pavadinimas is 'Kategorijos tipo pavadinimas';

create sequence if not exists kokteilio_kategorija_id_seq maxvalue 2147483647;
alter sequence kokteilio_kategorija_id_seq owner to postgres;
drop table if exists kokteilio_kategorija;
create table if not exists kokteilio_kategorija
(
    id                   integer default nextval('kokteilio_kategorija_id_seq') not null
        constraint kokteilio_kategorija_id_pkey primary key,
    pavadinimas          character varying                                      not null,
    aprasymas            character varying                                      not null,
    kategorijos_tipas_id integer
        constraint kategorijos_tipas_id_fkey references kategorijos_tipas on delete no action on update no action
);
comment on table kokteilio_kategorija is 'Kokteilio kategoriją aprašanti lentelė';
comment on column kokteilio_kategorija.id is 'Prasminis lentelės raktas';
comment on column kokteilio_kategorija.pavadinimas is 'Kokteilio kategorijos pavadinimas';
comment on column kokteilio_kategorija.aprasymas is 'Trumpas aprašymas apie kokteilio kategoriją';
comment on column kokteilio_kategorija.kategorijos_tipas_id is 'Nuoroda į kategorijos tipo prasminį raktą';

create sequence if not exists sudetingumas_id_seq maxvalue 2147483647;
alter sequence sudetingumas_id_seq owner to postgres;
drop table if exists sudetingumas;
create table if not exists sudetingumas
(
    id          integer default nextval('sudetingumas_id_seq') not null
        constraint sudetingumas_id_pkey primary key,
    pavadinimas character varying                              not null
);
comment on table sudetingumas is 'Sudėtingumą aprašanti lentelė';
comment on column sudetingumas.id is 'Prasminis lentelės raktas';
comment on column sudetingumas.pavadinimas is 'Sudėtingumo reikšmės pavadinimas';

create sequence if not exists komentaras_id_seq maxvalue 2147483647;
alter sequence komentaras_id_seq owner to postgres;
drop table if exists komentaras;
create table if not exists komentaras
(
    id            integer                default nextval('komentaras_id_seq'::regclass) not null
        constraint komentaras_id_pkey primary key,
    tekstas       character varying null,
    vertinimas    decimal(3)        null default 0,
    vartotojas_id integer
        constraint vartotojas_id_fkey references vartotojas on delete no action on update no action
);
comment on table komentaras is 'Komentarą aprašanti lentelė';
comment on column komentaras.id is 'Prasminis lentelės raktas';
comment on column komentaras.tekstas is 'Komentaros tekstas';
comment on column komentaras.vertinimas is 'Komentaros vertinimas';
comment on column komentaras.vartotojas_id is 'Nuorodą į vartotojo prasminį raktą';

create sequence if not exists receptas_id_seq maxvalue 2147483647;
alter sequence receptas_id_seq owner to postgres;
drop table if exists receptas;
create table if not exists receptas
(
    id                      integer                    default nextval('receptas_id_seq') not null
        constraint receptas_id_pkey primary key,
    pavadinimas             character varying not null,
    gaminimo_instrukcija    character varying not null,
    patiekimas              character varying not null,
    patalpinimo_laikas      timestamp         not null default current_timestamp,
    atnaujinimo_laikas      timestamp         not null default current_timestamp,
    paveiksliukas           bytea             null,
    vartotojas_id           integer
        constraint vartotojas_id_fkey references vartotojas on delete no action
            on update no action,
    kokteilio_kategorija_id integer
        constraint kokteilio_kategorija_id_fkey references kokteilio_kategorija on delete no action
            on update no action,
    sudetingumas_id         integer
        constraint sudetingumas_id_fkey references sudetingumas on delete no action
            on update no action,
    komentaras_id           integer
        constraint komentaras_id_fkey references komentaras on delete no action
            on update no action
);
comment on table receptas is 'Receptą aprašanti lentelė';
comment on column receptas.id is 'Prasminis lentelės raktas';
comment on column receptas.pavadinimas is 'Receptui priskirtas pavadinimas';
comment on column receptas.paveiksliukas is 'Kokteilio nuotrauka';
comment on column receptas.gaminimo_instrukcija is 'Kokteilio gaminimo instrukcija';
comment on column receptas.patiekimas is 'Rekomenduojamas kokteilio patiekimas';
comment on column receptas.patalpinimo_laikas is 'Recepto patalpinimo laikas';
comment on column receptas.atnaujinimo_laikas is 'Recepto koregavimo laikas';
comment on column receptas.vartotojas_id is 'Nuorodą į vartotojo prasminį raktą';
comment on column receptas.kokteilio_kategorija_id is 'Nuorodą į kokteilio kategorijos prasminį raktą';
comment on column receptas.sudetingumas_id is 'Nuorodą į sudėtingumo prasminį raktą';
comment on column receptas.komentaras_id is 'Nuorodą į komentaro prasminį raktą';

create sequence if not exists produkto_rusis_id_seq maxvalue 2147483647;
alter sequence produkto_rusis_id_seq owner to postgres;
drop table if exists produkto_rusis;
create table if not exists produkto_rusis
(
    id          integer default nextval('produkto_rusis_id_seq'::regclass) not null
        constraint produkto_rusis_id_pkey primary key,
    pavadinimas character varying                                          not null
);
comment on table produkto_rusis is 'Produkto rušį aprašanti lentelė';
comment on column produkto_rusis.id is 'Prasminis lentelės raktas';
comment on column produkto_rusis.pavadinimas is 'Produkto rūšies pavadinimas';

create sequence if not exists produktas_id_seq maxvalue 2147483647;
alter sequence produktas_id_seq owner to postgres;
drop table if exists produktas;
create table if not exists produktas
(
    id                   integer default nextval('produktas_id_seq'::regclass) not null
        constraint produktas_id_pkey primary key,
    pavadinimas          character varying                                     not null,
    produkto_rusis_id    integer
        constraint produkto_rusis_id_fkey references produkto_rusis on delete no action on update no action,
    matavimo_vienetas_id integer
        constraint matavimo_vienetas_id_fkey references matavimo_vienetas on delete no action on update no action
);
comment on table produktas is 'Produktą aprašanti lentelė';
comment on column produktas.id is 'Prasminis lentelės raktas';
comment on column produktas.pavadinimas is 'Produkto pavadinimas';
comment on column produktas.produkto_rusis_id is 'Nuorodą į produkto rūšies prasminį raktą';
comment on column produktas.matavimo_vienetas_id is 'Nuorodą į matavimo vieneto prasminį raktą';

drop table if exists sudetis;
create table if not exists sudetis
(
    receptas_id  integer
        constraint receptas_id_fkey references receptas on delete no action on update no action,
    produktas_id integer
        constraint produktas_id_fkey references produktas on delete no action on update no action,
    kiekis               decimal(5, 2)                                         not null
);
comment on table sudetis is 'Sudėtį aprašanti lentelė';
comment on column sudetis.receptas_id is 'Nuorodą į recepto prasminį raktą';
comment on column sudetis.produktas_id is 'Nuorodą į produkto prasminį raktą';
comment on column sudetis.kiekis is 'Produkto kiekis';
