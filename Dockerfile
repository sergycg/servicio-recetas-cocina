FROM openjdk:8
VOLUME /tmp
ADD servicio-recetas-cocina-0.0.1-SNAPSHOT.jar servicio-recetas-cocina.jar
ENTRYPOINT ["java","-jar","/servicio-recetas-cocina.jar"]