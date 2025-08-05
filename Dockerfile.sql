FROM mcr.microsoft.com/mssql/server:2022-latest

ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=Test!@#1234

# Install mssql-tools & dependencies
USER root
RUN apt-get update && \
    apt-get install -y curl apt-transport-https gnupg && \
    curl https://packages.microsoft.com/keys/microsoft.asc | apt-key add - && \
    curl https://packages.microsoft.com/config/debian/10/prod.list > /etc/apt/sources.list.d/mssql-release.list && \
    apt-get update && ACCEPT_EULA=Y apt-get install -y mssql-tools unixodbc-dev && \
    rm -rf /var/lib/apt/lists/*

# Make sqlcmd accessible from shell
ENV PATH="$PATH:/opt/mssql-tools/bin"

RUN mkdir -p /var/opt/mssql/backup
COPY PersonalFinance_DB.bak /var/opt/mssql/backup/

# Restore the database inside the image
RUN (/opt/mssql/bin/sqlservr & \
    sleep 25 && \
    /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "Test!@#1234" \
    -Q "RESTORE DATABASE [PersonalFinance_DB] FROM DISK = '/var/opt/mssql/backup/PersonalFinance_DB.bak' WITH MOVE 'PersonalFinance_DB' TO '/var/opt/mssql/data/PersonalFinance_DB.mdf', MOVE 'PersonalFinance_DB_log' TO '/var/opt/mssql/data/PersonalFinance_DB_log.ldf'" \
    && pkill sqlservr)
