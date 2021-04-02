-- Requetes sql--

select ligne_facture.id_facture, concat(quantity*"prixHT", ' €') as prix_total
from "Facture"
                join ligne_facture on "Facture".id_facture=ligne_facture.id_facture
                join plats on ligne_facture.id_plat = plats.id_plat;

select ligne_facture.id_facture, concat(sum(quantity*"prixHT"), ' €') as prix_total
from "Facture" join ligne_facture on "Facture".id_facture=ligne_facture.id_facture
               join plats on ligne_facture.id_plat = plats.id_plat group by ligne_facture.id_facture;

select "Facture".id_table_restaurant as "numero table", sum(quantity*"prixHT") as CA_total
from "Facture" join ligne_facture on "Facture".id_facture = ligne_facture.id_facture
               join plats on ligne_facture.id_plat = plats.id_plat group by "Facture".id_table_restaurant order by CA_total desc;

select plats.nom_plat, plats."prixHT" as prix_Unitaire, sum(ligne_facture.quantity) as "quantité", sum(quantity*"prixHT") as CA_total
from "Facture" join ligne_facture on "Facture".id_facture = ligne_facture.id_facture
               join plats on ligne_facture.id_plat = plats.id_plat group by ligne_facture.id_plat, plats.nom_plat, plats."prixHT" order by CA_total desc;


-- Script BDD--

create table serveurs
(
    id_serveurs    serial not null
        constraint serveurs_pk
            primary key,
    nom_serveur    varchar,
    prenom_serveur varchar
);

alter table serveurs
    owner to postgres;

create table table_restaurant
(
    id_table     serial not null
        constraint table_restaurant_pk
            primary key,
    nom_table    varchar(30),
    nbre_convive integer
);

alter table table_restaurant
    owner to postgres;

create table plats
(
    id_plat  serial not null
        constraint plats_pk
            primary key,
    nom_plat varchar(50),
    "prixHT" double precision
);

alter table plats
    owner to postgres;

create table "Facture"
(
    id_facture          serial not null
        constraint facture_pk
            primary key,
    id_table_restaurant integer
        constraint nom_table_fk
            references table_restaurant,
    id_serveur          integer
        constraint nom_serveur_fk
            references serveurs
);

alter table "Facture"
    owner to postgres;

create table ligne_facture
(
    id_commandes serial not null
        constraint commandes_pk
            primary key,
    id_facture   integer
        constraint facture_fk
            references "Facture",
    id_plat      integer
        constraint plat_fk
            references plats,
    quantity     integer
);

alter table ligne_facture
    owner to postgres;



