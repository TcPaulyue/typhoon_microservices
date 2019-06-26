FROM registry.njuics.cn/library/maven


#ADD rain-0.0.1-SNAPSHOT.jar demo.jar
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "target/typhoon-0.0.1-SNAPSHOT.jar"]