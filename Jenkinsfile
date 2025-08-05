pipeline {
    agent any

    environment {
        TOMCAT_PATH = 'D:\\apache-tomcat-11.0.7'
        WAR_NAME = 'JavaWebFinal.war'
    }

    tools {
        maven 'Maven 3.9.9' // Make sure this matches your Jenkins Maven installation name
        jdk 'JDK 21'   //'JDK 24'      // Or whatever version your Jenkins has set up 
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

    
        // stage('Build WAR with Maven') {
        //     steps {
        //         bat 'mvn clean package -DskipTests'
        //     }
        // }

        // stage('Build Docker Image') {
        //     steps {
        //         bat '''
        //             docker build -t springbootapp:latest -f "%WORKSPACE%\\Dockerfile" .
        //         '''
        //     }
        // }

        // stage('Run Docker Container') {
        //     steps {
        //         bat '''
        //         docker rm -f springbootapp-run || echo "Container not found, skipping removal"
        //         docker run -d --name springbootapp-run -p 8091:8080 springbootapp:latest
        //         '''
        //     }
        // 
    }
}

