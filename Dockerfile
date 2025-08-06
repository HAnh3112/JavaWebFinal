# Stage 1: Build WAR
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Deploy WAR to Tomcat
FROM tomcat:10.1-jdk21
WORKDIR /usr/local/tomcat

# Remove default webapps
RUN rm -rf webapps/*

# Copy the WAR file and rename it to ROOT.war (optional)
COPY --from=build /app/target/*.war webapps/ROOT.war

EXPOSE 8080
# Tomcat's built-in startup script will run


#(Need to run if cant retrieve database)
# docker exec -it sql2022 /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Test!@#1234" -N -C
# RESTORE DATABASE PersonalFinance_DB
# FROM DISK = '/var/opt/mssql/backup/PersonalFinance_DB.bak'
# WITH MOVE 'PersonalFinance_DB' TO '/var/opt/mssql/data/PersonalFinance_DB.mdf',
# MOVE 'PersonalFinance_DB_log' TO '/var/opt/mssql/data/PersonalFinance_DB_log.ldf';
# GO



#(Fully automatic with Jenkins pipeline)
# docker network create myapp-net

# docker rm -f sql2022
# docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Test!@#1234" `
#   -p 1437:1433 --name sql2022 `
#   --network myapp-net `
#   -v sql2022data:/var/opt/mssql `
#   -v "D:\GitHub_D:/var/opt/mssql/backup" `
#   -d mcr.microsoft.com/mssql/server:2022-latest



#(Fully automatic in Jenkins pipeline)
# docker build -t springboot-app .  

# docker rm -f springbootapp-run

# docker run -d --name springbootapp-run `
#   --network myapp-net `
#   -p 8091:8080 springboot-app
