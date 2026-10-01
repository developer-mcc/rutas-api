# Imagen del backend (Spring Boot, Java 21). Construcción en dos etapas: la imagen final solo lleva el JRE y el JAR.
#   docker build -t rutas-api .

# ---- Etapa 1: compilar ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /src

# Primero lo que cambia poco (wrapper y dependencias) para aprovechar la caché de capas.
COPY gradlew settings.gradle.kts build.gradle.kts ./
COPY gradle gradle
# Por si el repositorio se clonó en Windows con saltos de línea CRLF.
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew \
    && ./gradlew --no-daemon dependencies > /dev/null 2>&1 || true

COPY src src
RUN ./gradlew --no-daemon bootJar \
    && mv build/libs/rutas-api-*.jar /src/app.jar

# ---- Etapa 2: ejecutar ----
FROM eclipse-temurin:21-jre
# Usuario sin privilegios; la carpeta de imágenes es lo único donde puede escribir.
RUN useradd --system --uid 10001 --no-create-home viaja \
    && mkdir -p /var/www/viaja/uploads \
    && chown viaja:viaja /var/www/viaja/uploads
WORKDIR /app
COPY --from=build /src/app.jar app.jar
# El script arranca como root solo para dar permiso a la carpeta de imágenes (los discos de Render se
# montan con dueño root) y enseguida baja al usuario "viaja". Se quitan los CRLF por si se editó en Windows.
COPY docker-entrypoint.sh /app/docker-entrypoint.sh
RUN sed -i 's/\r$//' /app/docker-entrypoint.sh && chmod +x /app/docker-entrypoint.sh

ENV UPLOADS_DIR=/var/www/viaja/uploads \
    SERVE_UPLOADS=false \
    JAVA_OPTS="-Xms256m -Xmx512m"
EXPOSE 8080

HEALTHCHECK --interval=15s --timeout=3s --start-period=40s --retries=5 \
    CMD curl -fs http://localhost:${PORT:-8080}/actuator/health || exit 1

ENTRYPOINT ["/app/docker-entrypoint.sh"]
