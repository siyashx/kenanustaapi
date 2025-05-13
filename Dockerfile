FROM openjdk:17-jdk

# Sertifika dosyasını container içine kopyala
COPY keystore.p12 /app/keystore.p12

# Uygulama JAR dosyasını kopyala
ADD target/kenanustaapi-0.0.1-SNAPSHOT.jar kenanustaapi.jar

# HTTPS portunu aç
EXPOSE 6677

# Sağlık kontrolü (HTTPS isteği)
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail --insecure https://localhost:6677/ || exit 1

# Spring Boot uygulamasını başlat
ENTRYPOINT ["java", "-jar", "kenanustaapi.jar"]
