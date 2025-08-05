pipeline {
    agent any

    environment {
        TOMCAT_PATH = 'D:\\apache-tomcat-11.0.7'
        WAR_NAME = 'JavaWebFinal.war'

        LANG = 'en_US.UTF-8'
        LC_ALL = 'en_US.UTF-8'
        DOCKERHUB_CREDENTIALS = 'hubdocker'  // Jenkins credential ID
        DOCKER_IMAGE_NAME = 'nha311205/springbootapp'
        DOCKER_TAG = 'latest'

        SQL_IMAGE_LOCAL = 'mcr.microsoft.com/mssql/server:2022-latest' // local SQL Server image
        SQL_IMAGE_REMOTE = 'nha311205/sqlserver2022' // Docker Hub repo for SQL Server
        SQL_TAG = 'latest'
    }

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 21'
    }

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code from GitHub'
                git branch: 'main', url: 'https://github.com/HAnh3112/JavaWebFinal.git'
            }
        }

        stage('Build with Maven') {
            steps {
                echo 'Running Maven build'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy WAR to Tomcat') {
            steps {
                echo 'Deploying WAR file to Tomcat'
                bat '''
                    if not exist "%TOMCAT_PATH%\\webapps" (
                        echo Tomcat webapps folder not found!
                        exit /b 1
                    )
                    for %%f in (target\\*.war) do (
                        echo Copying %%f to Tomcat
                        copy "%%f" "%TOMCAT_PATH%\\webapps\\%WAR_NAME%" /Y
                    )
                '''
            }
        }

        stage('Restart Tomcat') {
            steps {
                echo 'Restarting Tomcat server'
                bat '''
                    call "%TOMCAT_PATH%\\bin\\shutdown.bat"
                    timeout /t 5 > NUL
                    call "%TOMCAT_PATH%\\bin\\startup.bat"
                '''
            }
        }

        stage('Build Spring Boot Docker Image') {
            steps {
                bat '''
                    docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} -f "%WORKSPACE%\\Dockerfile" .
                '''
            }
        }

        stage('Run Spring Boot Docker Container') {
            steps {
                bat '''
                docker rm -f springbootapp-run || echo "Container not found, skipping removal"
                docker run -d --name springbootapp-run --network myapp-net -p 8091:8080 ${DOCKER_IMAGE_NAME}:${DOCKER_TAG}
                '''
            }
        }

        stage('Push Spring Boot Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        docker.image("${DOCKER_IMAGE_NAME}:${DOCKER_TAG}").push()
                    }
                }
            }
        }

        stage('Push SQL Server Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        // Tag local SQL Server image
                        bat """
                            docker tag ${SQL_IMAGE_LOCAL} ${SQL_IMAGE_REMOTE}:${SQL_TAG}
                        """
                        // Push to Docker Hub
                        docker.image("${SQL_IMAGE_REMOTE}:${SQL_TAG}").push()
                    }
                }
            }
        }
    }
}
