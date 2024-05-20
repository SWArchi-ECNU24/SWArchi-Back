FROM openjdk:17-oracle

WORKDIR /docker/virtualPetHospital/build

COPY /target/eureka-?.?.?-SNAPSHOT.jar /docker/virtualPetHospital/build/eureka.jar
COPY /target/auth-?.?.?-SNAPSHOT.jar /docker/virtualPetHospital/build/auth.jar
COPY /target/confjour-?.?.?-SNAPSHOT.jar /docker/virtualPetHospital/build/confjour.jar
COPY /target/intermediator-?.?.?-SNAPSHOT.jar /docker/virtualPetHospital/build/intermediator.jar

COPY /run_jar.sh /docker/virtualPetHospital/build

RUN chmod 755 run_jar.sh
EXPOSE 8085-8090

ENTRYPOINT ["./run_jar.sh", "start"]
