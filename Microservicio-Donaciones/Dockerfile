FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

# Esta es la línea mágica que copia la wallet de tu PC al contenedor Linux
COPY Wallet_donatonBD /app/wallet

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]