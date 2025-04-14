FROM maven:latest

WORKDIR /mnt

COPY . .

RUN mvn install

CMD ["mvn", "spring-boot:run"]