pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'   // Make sure this matches your Jenkins global tool config
        jdk 'JDK 21'          // Same here
    }

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code...'
                git credentialsId: 'github-pat', branch: 'main', url: 'https://github.com/HAnh3112/JavaWebFinal'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project with Maven...'
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }
    }
}
