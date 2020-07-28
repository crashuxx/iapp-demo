```
mvn clean package docker:build
DEMOMONGO=demomongo
docker run -d --name $DEMOMONGO  mongo
docker run -it -p 8080:8080 --env MONGO_ADDR=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' $DEMOMONGO` iapp-demo
```

http://localhost:8080/swagger-ui.html#/e-paper-controller