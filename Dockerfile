FROM openjdk:8-jre-alpine

RUN echo "👻 Start build undercontrol image"

ARG USER=appuser
ARG APPGROUP=appgroup

RUN mkdir -p /home/$USER
RUN addgroup -S $APPGROUP
RUN adduser -S $USER -G $APPGROUP -h /home/$USER
USER $USER
WORKDIR /home/$USER

COPY --chown=$USER:$APPGROUP ./target/*.jar /home/$USER/undercontrol-server.jar

CMD ["java","-jar","undercontrol-server.jar"]

