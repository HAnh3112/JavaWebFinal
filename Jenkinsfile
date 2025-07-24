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
        echo 'Restarting Tomcat server...'
bat '''
    rem Kill any process listening on port 8080
    for /f "tokens=5" %%a in ('netstat -aon ^| find ":8080" ^| find "LISTENING"') do (
        echo Checking PID %%a  
        tasklist /FI "PID eq %%a" | find /I "tomcat" >nul  
        if %%ERRORLEVEL%% EQU 0 (
            echo Killing Tomcat PID %%a  
            taskkill /F /PID %%a 
        ) else (
            echo Skipping PID %%a (not Tomcat)
        )
    )

    rem Clean temp and work directories
    if not exist "D:\\apache-tomcat-11.0.7\\temp" mkdir "D:\\apache-tomcat-11.0.7\\temp"
    rmdir /S /Q "D:\\apache-tomcat-11.0.7\\work" >nul 2>&1
    rmdir /S /Q "D:\\apache-tomcat-11.0.7\\temp" >nul 2>&1
    mkdir "D:\\apache-tomcat-11.0.7\\temp"

    rem Start Tomcat safely using detached cmd
    cmd /c start "" "D:\\apache-tomcat-11.0.7\\bin\\startup.bat"

    timeout /t 10 >nul
'''


    }
}






        stage('Verify Deployment') {
            steps {
                echo 'Checking if Tomcat is up on http://localhost:8080...'
                bat 'powershell -Command "try { (Invoke-WebRequest -Uri http://localhost:8080 -UseBasicParsing).StatusCode } catch { Write-Host \\"Tomcat not responding\\"; exit 1 }"'
            }
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
