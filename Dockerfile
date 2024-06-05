FROM openjdk:17-oracle

WORKDIR /docker/swArchi/build

COPY /target/eureka-?.?.?-SNAPSHOT.jar /docker/swArchi/build/eureka.jar
COPY /target/auth-?.?.?-SNAPSHOT.jar /docker/swArchi/build/auth.jar
COPY /target/confjour-?.?.?-SNAPSHOT.jar /docker/swArchi/build/confjour.jar
COPY /target/intermediator-?.?.?-SNAPSHOT.jar /docker/swArchi/build/intermediator.jar

COPY /run_jar.sh /docker/swArchi/build

RUN chmod 755 run_jar.sh
EXPOSE 8085-8088

ENTRYPOINT ["./run_jar.sh", "start"]
