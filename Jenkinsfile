pipeline {
    agent any

    environment {
        TOMCAT_PATH = 'D:\\apache-tomcat-11.0.7'
        WAR_NAME = 'JavaWebFinal.war'


        LANG = 'en_US.UTF-8'
        LC_ALL = 'en_US.UTF-8'
		DOCKERHUB_CREDENTIALS = 'hubdocker'  // ID credentials
        IMAGE_NAME = 'nha311205/springbootapp '  // name of image on Docker Hub -- create repo on hub.docker
		DOCKER_IMAGE_NAME = 'nha311205/springbootapp'  //  Docker image name
        DOCKER_TAG = 'lastest'  // Tag cho Docker image
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


        stage('Build Docker Image') {
            steps {
                bat '''
                    docker build -t springbootapp:latest -f "%WORKSPACE%\\Dockerfile" .
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                bat '''
                docker rm -f springbootapp-run || echo "Container not found, skipping removal"
                docker run -d --name springbootapp-run --network myapp-net -p 8091:8080 springbootapp:latest
                '''
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    // login Docker Hub to push image
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        // login Docker Hub credentials
                    }
                }
            }
        }
		 
        stage('Push Docker Image') {
            steps {
				 
                script {
                    // push Docker image to Docker Hub
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        docker.image("${DOCKER_IMAGE_NAME}:${DOCKER_TAG}").push()
                    }
                }
            }
        }
    }
    
}

