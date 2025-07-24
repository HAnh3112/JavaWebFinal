pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 21'
    }

    environment {
        TOMCAT_PATH = 'D:\\apache-tomcat-11.0.7'
        WAR_NAME = 'JavaWebFinal.war'
        CATALINA_HOME = "${env.TOMCAT_PATH}"
    }

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code from GitHub...'
                git credentialsId: 'github-pat', branch: 'main', url: 'https://github.com/HAnh3112/JavaWebFinal'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project with Maven...'
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                bat 'mvn test'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying WAR to Tomcat...'
                bat """
                    if not exist target\\%WAR_NAME% (
                        echo WAR file not found!
                        exit /b 1
                    )

                    del "%TOMCAT_PATH%\\webapps\\%WAR_NAME%" >nul 2>&1
                    rmdir /S /Q "%TOMCAT_PATH%\\webapps\\JavaWebFinal" >nul 2>&1
                    copy target\\%WAR_NAME% "%TOMCAT_PATH%\\webapps\\" /Y
                """
            }
        }

        stage('Restart Tomcat') {
            steps {
                echo 'Restarting Tomcat server'
                bat '''
                    call "%TOMCAT_PATH%\\bin\\shutdown.bat"
                    timeout /t 5
                    call "%TOMCAT_PATH%\\bin\\startup.bat"
                '''
            }
        }

    post {
        success {
            echo 'Deployment completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Please check the logs for details.'
        }
    }
}
