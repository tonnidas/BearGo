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

- Pull and deploy the images in server (without erasing DB)

```
$ ssh root@beargo.live
$ cd beargo
$ export BEARGO_ADMIN_PASSWORD={replace}
$ export SENDGRID_API_KEY={replace}
$ docker compose pull
$ docker compose up -d
$ docker compose logs -f
```

- Erase DB and deploy (Alert: backup DB before doing this)

```
$ docker compose down; docker volume rm beargo_db-data
$ docker compose up -d
```