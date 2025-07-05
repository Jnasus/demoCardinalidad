pipeline {
    agent any

    environment {
        IMAGE_NAME = "demo-cardinalidad"
        TAG = "latest"
    }

    stages {
        stage('🛠️ Compilar con Maven') {
            agent {
                docker {
                    image 'maven:3.9.4-eclipse-temurin-17'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                echo 'Ejecutando: mvn clean install -DskipTests=true'
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('🧪 Ejecutar pruebas') {
            agent {
                docker {
                    image 'maven:3.9.4-eclipse-temurin-17'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                echo 'Ejecutando: mvn test'
                sh 'mvn test'
            }
        }

        stage('🐳 Build Docker & Deploy') {
            steps {
                echo 'Construyendo imagen Docker'
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."
                echo 'Desplegando imagen localmente'
                sh "docker run -d -p 8080:8080 ${IMAGE_NAME}:${TAG}"
            }
        }
    }
}
