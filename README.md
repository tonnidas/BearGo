# BearGo

Baylor Software Engineering Project

## Run tests

```
$ mvn test
```

## Run the application

```
$ mvn spring-boot:run
```

## Swagger UI

http://localhost:8080/swagger-ui.html


## NPM Modules

- npm install react-rounded-image --legacy-peer-deps
- npm install react-stomp-client --legacy-peer-deps
- npm install moment react-moment

## Docker Compose Deployment 

- Build and publish images in you local machine

```
$ export BEARGO_ADMIN_PASSWORD={replace}
$ docker compose build
$ docker compose push
```

- Create required directories and env files in server (one time)

```
$ ssh root@beargo.live
$ cd beargo
$ mkdir -p backend frontend/React/project_react

$ nano .env
BEARGO_ADMIN_PASSWORD={replace}
SENDGRID_API_KEY={replace}
```

- Pull and deploy the images in server (without erasing DB)

```
$ ssh root@beargo.live
$ cd beargo

$ curl -LJO https://raw.githubusercontent.com/tonnidas/BearGo/main/docker-compose.yml

$ docker compose pull
$ docker compose down
$ docker compose up -d
$ docker compose logs -f
```

- Erase DB and deploy (Alert: backup DB before doing this)

```
$ docker compose down
$ docker volume rm beargo_db-data
$ docker compose up -d
```