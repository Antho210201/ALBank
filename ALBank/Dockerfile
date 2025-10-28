# On utilise une image contenant le JDK
FROM openjdk:17-jdk-slim

# On définit le répertoire de travail dans le conteneur
WORKDIR /app

# On copie l'ensemble du code source et des dépendances
COPY . .

# On compile le projet Java (les .java dans src/)
RUN javac -cp "lib/*" -d out $(find src -name "*.java")

# On définit la commande d'exécution 
CMD ["java", "-cp", "out:lib/*", "main.Main"]

#Notes: Bien utiliser l'option "-it" lors de l'exécution du Docker pour passer en mode interactif et échapper à une exception !!!
