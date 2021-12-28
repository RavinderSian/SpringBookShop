FROM spring-jdk11-base
COPY . /src
WORKDIR /src
RUN mvn package
CMD mvn spring-boot:run
