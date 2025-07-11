pipeline {
    agent any

    git credentialsId: 'github-pat', url: 'https://github.com/HAnh3112/JavaWebFinal.git'


    tools {
        maven 'Maven 3.9.9'   // Replace with the name of your configured Maven installation in Jenkin
        jdk 'JDK 21'          // Replace with your JDK version name from Jenkins Global Tools config
    }

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code...'
                git branch: 'main', url: 'https://github.com/HAnh3112/JavaWebFinal'
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
