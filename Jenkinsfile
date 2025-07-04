pipeline {
    agent any

    environment {
        IMAGE_NAME = "demo-cardinalidad"
        TAG = "latest"
    }

    stages {
        stage('🛠️ Compilar con Maven') {
            steps {
                echo 'Ejecutando: mvn clean install -DskipTests=true'
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('🧪 Ejecutar pruebas') {
            steps {
                echo 'Ejecutando: mvn test'
                sh 'mvn test'
            }
        }

        stage('🐳 Build Docker & Deploy') {
            steps {
                echo 'Construyendo imagen Docker'
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."

                echo 'Ejecutando contenedor Docker'
                sh "docker run -d -p 8080:8080 ${IMAGE_NAME}:${TAG}"
            }
        }
    }
}
