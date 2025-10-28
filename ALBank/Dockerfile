
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY . .


RUN javac -cp "lib/*" -d out $(find src -name "*.java")


CMD ["java", "-cp", "out:lib/*", "main.Main"]

#Notes: Bien utiliser l'option "-it" lors de l'exécution du Docker pour passer en mode interactif et échapper à une exception !!!
