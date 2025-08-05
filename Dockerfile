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



# docker exec -it sql2022 /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Test!@#1234" -N -C
# RESTORE DATABASE PersonalFinance_DB
# FROM DISK = '/var/opt/mssql/backup/PersonalFinance_DB.bak'
# WITH MOVE 'PersonalFinance_DB' TO '/var/opt/mssql/data/PersonalFinance_DB.mdf',
# MOVE 'PersonalFinance_DB_log' TO '/var/opt/mssql/data/PersonalFinance_DB_log.ldf';
# GO

# docker network create myapp-net

# docker rm -f sql2022
# docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Test!@#1234" `
#   -p 1437:1433 --name sql2022 `
#   --network myapp-net `
#   -v sql2022data:/var/opt/mssql `
#   -v "D:\GitHub_D:/var/opt/mssql/backup" `
#   -d mcr.microsoft.com/mssql/server:2022-latest







# # 1️⃣ Remove old container if it exists 
# docker rm -f sql2022

# # 2️⃣ Run a new SQL Server container (NO volume for /var/opt/mssql, so DB stays in image)
# docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Test!@#1234" `
#   -p 1437:1433 --name sql2022 `
#   --network myapp-net `
#   -d mcr.microsoft.com/mssql/server:2022-latest

# # 3️⃣ Copy your .bak file into the container
# docker cp "D:/GitHub_D/PersonalFinance_DB.bak" sql2022:/var/opt/mssql/backup/PersonalFinance_DB.bak

# # 4️⃣ Wait a bit for SQL Server to start
# echo "⏳ Waiting for SQL Server to start..."
# sleep 20

# # 5️⃣ Restore the database from .bak
# docker exec -it sql2022 /opt/mssql-tools18/bin/sqlcmd -S localhost -U SA -P "Test!@#1234" -N -C `
#   -Q "RESTORE DATABASE PersonalFinance_DB FROM DISK = '/var/opt/mssql/backup/PersonalFinance_DB.bak' `
#   WITH MOVE 'PersonalFinance_DB' TO '/var/opt/mssql/data/PersonalFinance_DB.mdf', `
#   MOVE 'PersonalFinance_DB_log' TO '/var/opt/mssql/data/PersonalFinance_DB_log.ldf';"








# docker build -t springboot-app .  

# docker rm -f springboot-run

# docker run -d --name springboot-run `
#   --network myapp-net `
#   -p 90:8080 springboot-app
