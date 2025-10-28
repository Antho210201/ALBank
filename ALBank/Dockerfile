
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY . .


RUN javac -cp "lib/*" -d out $(find src -name "*.java")


CMD ["java", "-cp", "out:lib/*", "main.Main"]