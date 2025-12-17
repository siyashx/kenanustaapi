FROM eclipse-temurin:17-jdk

# Uygulama JAR dosyasını kopyala
ADD target/kenanustaapi-0.0.1-SNAPSHOT.jar kenanustaapi.jar

# HTTPS portunu aç
EXPOSE 6677

# Spring Boot uygulamasını başlat
ENTRYPOINT ["java", "-jar", "kenanustaapi.jar"]
