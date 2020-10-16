# TP3 Partie 1 Système Client Store Provider Bank

Beucher Maxime

# Lancement de l'application

Sur eclipse:

Aller dans le package tp3 et executer le main de Application.

Le code va executer un client qui demande à un magasin s'il existe un item 'itemA' et va tenter d'en achetter plusieurs grace
aux interfaces auto-générée par @Autowired suivante:

+IJustHaveALook
+IFastLane
+ILane 

Le client possède un identifiant iban paramétré dans le fichier application.properties (src/main/resources).
De même pour le magasin qui possède un identifiant iban mais aussi un Provider et un Bank (auto-généré).

La banque possède deux comptes seulement pour l'example (client et magasin).
