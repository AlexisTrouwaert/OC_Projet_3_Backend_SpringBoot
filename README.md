## ChâTop project (english version)

# How to Set Up This Project

First, you need to fork and clone these two repositories: 

[Backend](https://github.com/AlexisTrouwaert/OC_Projet_3_Backend_SpringBoot.git)
[Frontend](https://github.com/AlexisTrouwaert/Projet_3_backend_Java_OC.git)

Once done, follow these steps:

1 - Navigate to the Angular project, open the resources directory, and go to the sql folder to find "script.sql".

2 - Open your SQL command-line client and log in.

3 - Create your database by running: 
    `CREATE DATABASE name_of_your_database;`
    (You can choose any name you want for your database.)

4 - Verify that your database has been created by running:
    `SHOW DATABASES;`

5 - Select your database:
    `USE name_of_your_database;`

6 - Run your SQL script with the following command (on Windows CMD):
    `mysql --user="your_username" --database="name_of_your_database" --password="your_password" < path/to/script.sql`

7 - To ensure everything is set up correctly, run:
    `SHOW TABLES`
    You should see three new tables: rentals, messages, users.

# Finalizing the Setup

Now that your database is set up, follow these steps:

1 - Open the terminal in your IDE, navigate to the root of the Angular project, and run:
    `npm install`

2 - In another terminal, navigate to the root of the Spring Boot project and run:
    `mvn install`

Configuring Environment Variables

3 - Create a .env file in the root of the Spring Boot project by running:
    `touch .env`

4 - Add the following environment variables to the .env file:
    DB_URL=
    DB_USER=
    DB_PASSWORD=
    JWT_KEY=
    UPLOAD_DIR=

5 - The database url should looks like this : jdbc:mysql://localhost:3306/name_of_your_database

6 - To generate a JWT key, go to [8gwifi.org](https://8gwifi.org/jwsgen.jsp), select HS256, generate the signature, and copy it into the JWT_KEY variable.

7 - For file uploads, create a directory anywhere on your system and paste its path into the UPLOAD_DIR variable.

8 - Enter your MySQL credentials in DB_USER and DB_PASWWORD

# Run the project

1 - Open the terminal in your IDE, navigate to the root of the Angular project, and run:
    `ng serve`

2 - In another terminal, navigate to the root of the Spring Boot project and run:
    `mvn spring-boot:run`

3 - In your browser go to http://localhost:4200/

# Description of the project

ChâTop is an application that allows users to list rental properties and facilitates connections between tenants and landlords.
With this application, a user can publish a rental listing, and a tenant can contact the landlord through messages sent directly from the platform.
Additionally, the application implements security measures for its users through the integration of Spring Security and the use of tokens.

# Routes documentation

To see the documentation, you can go to :
    http://localhost:9000/swagger-ui/index.html#/
    or
    https://demos.springdoc.org/demo-spring-boot-3-webmvc/swagger-ui/index.html



## Projet ChâTop (version française)

# Comment configurer ce projet

Tout d'abord, vous devez forker et cloner ces deux dépôts :

[Backend](https://github.com/AlexisTrouwaert/OC_Projet_3_Backend_SpringBoot.git)
[Frontend](https://github.com/AlexisTrouwaert/Projet_3_backend_Java_OC.git)

Une fois cela fait, suivez ces étapes :

1 - Accédez au projet Angular, ouvrez le dossier resources, puis rendez-vous dans le dossier sql pour trouver "script.sql".

2 - Ouvrez votre client SQL en ligne de commande et connectez-vous.

3 - Créez votre base de données en exécutant la commande suivante :
    `CREATE DATABASE name_of_your_database;`
    (Vous pouvez choisir n'importe quel nom pour votre base de données.)

4 - Vérifiez que votre base de données a bien été créée en exécutant :
    `SHOW DATABASES;`

5 - Sélectionnez votre base de données :
    `USE name_of_your_database;`

6 - Exécutez votre script SQL avec la commande suivante (sur Windows CMD) :
    `mysql --user="your_username" --database="name_of_your_database" --password="your_password" < chemin/vers/script.sql`

7 - Pour vérifier que tout est bien configuré, exécutez :
    `SHOW TABLES`
    Vous devriez voir trois nouvelles tables : rentals, messages, users.

# Finalisation de l'installation

Maintenant que votre base de données est configurée, suivez ces étapes :

1 - Ouvrez le terminal dans votre IDE, naviguez à la racine du projet Angular, puis exécutez :
    `npm install`

2 - Dans un autre terminal, naviguez à la racine du projet Spring Boot, puis exécutez :
    `mvn install`

Configuration des variables d'environnement

3 - Créez un fichier .env à la racine du projet Spring Boot en exécutant :
    `touch .env`

4 - Ajoutez les variables d'environnement suivantes dans le fichier .env :
    DB_URL=
    DB_USER=
    DB_PASSWORD=
    JWT_KEY=
    UPLOAD_DIR=

5 - L'URL de la base de donnée ressemble à cela : jdbc:mysql://localhost:3306/name_of_your_database

6 - Pour générer une clé JWT, rendez-vous sur [8gwifi.org](https://8gwifi.org/jwsgen.jsp), sélectionnez HS256, générez la signature, puis copiez-la dans la variable JWT_KEY.

7 - Pour la gestion des fichiers envoyés, créez un dossier à l'endroit de votre choix sur votre système et copiez son chemin dans la variable UPLOAD_DIR.

8 - Entrer vos identifiants MySQL dans DB_USER et DB_PASSWORD

# Lancer le projet

1 - Ouvrez le terminal dans votre IDE, naviguez à la racine du projet Angular, puis exécutez :
    `npm install`
    and
    `ng serve`

2 - Dans un autre terminal, naviguez à la racine du projet Spring Boot, puis exécutez :
    `mvn install`
    and
    `mvn spring-boot:run`

3 - Dans votre navigateur, accédez à http://localhost:4200/.

# Description du projet

ChâTop est une application qui permet aux utilisateurs de proposer des biens immobiliers à la location et de faciliter la mise en relation entre locataires et propriétaires.
Grâce à cette plateforme, un propriétaire peut publier une annonce pour son bien, et un locataire peut le contacter directement via un système de messagerie intégré.
De plus, l’application met en place des mesures de sécurité pour ses utilisateurs grâce à l’intégration de Spring Security et à l’utilisation de tokens.

# Documentation des routes

Pour consulter la documentation, rendez-vous sur :
    http://localhost:9000/swagger-ui/index.html#/
    ou
    https://demos.springdoc.org/demo-spring-boot-3-webmvc/swagger-ui/index.html
