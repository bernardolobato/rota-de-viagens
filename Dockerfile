FROM openjdk:8

# api
COPY ./target/rota-de-viagens-1.0-SNAPSHOT.jar /opt/java/rota.jar
COPY ./rotas.csv /opt/java/rotas.csv

# Ports we will expose
EXPOSE 7000

CMD ["java", "-cp", "/opt/java/rota.jar", "com.bernardolobato.backendtest.AppServer", "/opt/java/rotas.csv"]