FROM registry.njuics.cn/library/maven


COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/listener-0.0.1-SNAPSHOT.jar"]