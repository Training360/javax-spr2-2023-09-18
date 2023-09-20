# Employees kiindulási projekt

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432  --name employees-postgres postgres
```

```shell
docker run -d -p 1025:1025 -p 8025:8025 --name mailhog mailhog/mailhog 
```