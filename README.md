# Démarrage en local

## 1. Mise à jour de la conf

Mettre à jour le fichier [application-local.yml](backend/src/main/resources/application-local.yml) pour renseigner:

- les identifiants des clients OAuth2 Google et Github
- le secret pour signer les tokens JWT
- une adresse mail pour identifier l'utilisateur ADMIN

(Cf. la Présentation de la couche sécurité du projet Backend)

## 2. Démarrage de la base de données

```sh
cd database/
docker compose up
```

## 3. Démarrage du projet Backend

```sh
cd backend/
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

## 4. Démarrage du projet Frontend

```sh
cd frontend/
npm ci # Pour télécharger les dépendances du projet frontend. A faire uniquement la première fois.
npm start
```

Le projet est accessible via l'URL: http://localhost:3000

# Démarrage dans AWS

## 1. Installer Docker

```sh
ssh -i <ec2-ssh-key> ubuntu@<ec2-host>
# Exécuter le script get-docker.sh qu’on récupère depuis le site get.docker.com
curl -fsSL https://get.docker.com -o get-docker.sh && sh get-docker.sh

# Ajouter l'utilisateur actuel au groupe docker pour éviter de lancer les commandes docker avec sudo
sudo usermod -aG docker $USER

# Mettre à jour les groupes de l'utilisateur actuel pour éviter de se reconnecter
newgrp docker 
```

## 2. Copier les fichiers docker-compose.yml et nginx.http.conf

```sh
scp -i <ec2-ssh-key> aws/docker-compose.yml ubuntu@<ec2-host>:docker-compose.yml
ssh -i <ec2-ssh-key> ubuntu@<ec2-host> "mkdir -p nginx/conf"
scp -i <ec2-ssh-key> aws/nginx.http.conf ubuntu@<ec2-host>:nginx/conf/nginx.http.conf
```

*Le fichier **nginx.http.conf** est à ajouter une fois notre certificat SSL a été généré*

## 3. Démarrer docker-compose et générer le certificat SSL

```sh
ssh -i <ec2-ssh-key> ubuntu@<ec2-host>
docker compose up -d
docker compose run certbot certonly --webroot --webroot-path /var/www/certbot/ -d quizzapp.xyz
sudo ls -al certbot/conf/live/quizzapp.xyz/
```

## 4. Copier le fichier nginx.https.conf

```sh
scp -i <ec2-ssh-key> aws/nginx.https.conf ubuntu@<ec2-host>:nginx/conf/nginx.https.conf
```

## 5. Redémarrer docker-compose

```sh
ssh -i <ec2-ssh-key> ubuntu@<ec2-host>
docker compose restart
```

