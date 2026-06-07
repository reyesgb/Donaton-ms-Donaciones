# 1. Usamos una imagen de Java ligera
FROM eclipse-temurin:17-jdk-alpine

# 2. Definimos dónde estará el código dentro del contenedor
WORKDIR /app

# 3. Copiamos el archivo JAR que genera Maven al contenedor
# (Asegúrate de haber corrido 'mvn clean package' antes)
COPY target/*.jar app.jar

# 4. Exponemos el puerto donde corre tu app (por defecto 8080 en Spring Boot)
EXPOSE 8080

# 5. Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]