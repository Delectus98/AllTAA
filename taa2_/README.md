# Template de projet pour le TP JPA 2019 UniR

Beucher Maxime

# Instructions lancement

## Lancer le serveur base de donnée HSQLDB

Se positionner à la racine du projet

> cd path/to/taa2

Ensuite executer le script

> ./run-hsqldb-server.sh

## Lancer ensuite KabanTest

Sur eclipse:

Run 'fr.istic.jpa.tp1.KabanTest'.

La fonction main va générer par défaut un projet avec plusieurs tableau (Board) et insérer des Cartes (Card) avec certaines etiquettes (Tag).

## Lancer TestApplication

Sur eclipse:

Clic droit sur le projet. run as -> maven build …-> mettre package jetty:run dans le goal.
Je n'ai pas pu essayer chez moi car jetty:run ne s'executait pas en tâche de fond et s'arretait après le build.
