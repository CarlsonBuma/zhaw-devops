# Docker
docker -v
docker ps

## Datenbank init
docker run --name local-mysql -e MYSQL_ROOT_PASSWORD=12345 -e MYSQL_DATABASE=wordpress -e MYSQL_USER=wordpress -e MYSQL_PASSWORD=wordpress -v mysql-data:/var/lib/mysql -d mysql:9.1.0

## Wordpress
docker run --name local-wordpress-p -p 8082:80 -v wordpress-data:/var/www/html -d wordpress:6.7.0-php8.3

## Netzwerk erstellen
docker network create --attachable wordpress-network

### Container Verknüpfen
docker network connect wordpress-network local-mysql
docker network connect wordpress-network local-wordpress-p
