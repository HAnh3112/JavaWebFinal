pipeline {
    agent any
//

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 21'
    }

    environment {
        TOMCAT_PATH = 'D:\\apache-tomcat-11.0.7'  // adjust as needed
        WAR_NAME = 'JavaWebFinal.war'             // or your actual war name
        CATALINA_HOME = 'D:\\apache-tomcat-11.0.7'
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
                    rem Ensure WAR exists
                    if not exist target\\%WAR_NAME% (
                        echo WAR file not found!
                        exit /b 1
                    )

                    rem Remove previous WAR and folder (optional clean)
                    del "%TOMCAT_PATH%\\webapps\\%WAR_NAME%" >nul 2>&1
                    rmdir /S /Q "%TOMCAT_PATH%\\webapps\\JavaWebFinal" >nul 2>&1

                    rem Copy new WAR
                    copy target\\%WAR_NAME% "%TOMCAT_PATH%\\webapps\\" /Y
                """
            }
        }

stage('Restart Tomcat') {
    steps {
        script {
            def jdkPath = tool name: 'JDK 21', type: 'hudson.model.JDK'
            echo "Resolved JDK path: ${jdkPath}"

            bat """
                set "JAVA_HOME=${jdkPath}"
                set "PATH=%JAVA_HOME%\\bin;%PATH%"

                rem Try to stop Tomcat
                call "%CATALINA_HOME%\\bin\\shutdown.bat"
                timeout /t 5 /nobreak

                rem Start Tomcat
                call "%CATALINA_HOME%\\bin\\startup.bat"
            """
        }
    }
}



    }
}
