FROM ubuntu/jdk:25-26.04_stable
EXPOSE 8080
LABEL authors="gaurav0850"
ADD target/RetailBankingSystem-0.0.1-SNAPSHOT.jar RetailBankingSystem.jar
ENTRYPOINT ["java", "-jar", "/RetailBankingSystem.jar"]