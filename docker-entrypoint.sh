#!/bin/sh
# Arranque del contenedor. Plataformas como Render montan el disco persistente con dueño root, y la
# aplicación no debe correr como root: se da permiso a la carpeta de imágenes y se baja de privilegios.
set -e

if [ "$(id -u)" = "0" ]; then
    chown -R viaja:viaja "${UPLOADS_DIR:-/var/www/viaja/uploads}"
    # shellcheck disable=SC2086  # JAVA_OPTS lleva varias opciones separadas por espacios
    exec runuser -u viaja -- java $JAVA_OPTS -jar /app/app.jar
fi

# shellcheck disable=SC2086
exec java $JAVA_OPTS -jar /app/app.jar
