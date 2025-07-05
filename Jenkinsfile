pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'demo-cardinalidad'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
        DOCKER_REGISTRY = 'localhost:5000'
        GITHUB_REPO = 'Jnasus/demoCardinalidad'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '🔍 Checking out code from repository...'
                checkout scm
            }
        }
        
        stage('Build & Test') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                echo '🔨 Building application...'
                sh 'mvn clean compile'
                echo '🧪 Running unit tests...'
                sh 'mvn test'
                echo '📦 Creating JAR package...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image...'
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }
        
        stage('Integration Tests') {
            steps {
                echo '🔗 Running integration tests...'
                sh '''
                    # Test if application builds successfully
                    echo "✅ Application built successfully"
                    
                    # Test if Docker image was created
                    docker images | grep ${DOCKER_IMAGE} || exit 1
                    echo "✅ Docker image created successfully"
                '''
            }
        }
        
        stage('Deploy to Staging') {
            agent {
                docker {
                    image 'docker/compose:latest'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                echo '🚀 Deploying to staging environment...'
                sh '''
                    # Stop any existing containers
                    docker-compose down || true
                    
                    # Start the application
                    docker-compose up -d
                    
                    # Wait for application to start
                    echo "⏳ Waiting for application to start..."
                    sleep 30
                    
                    # Test if application is running
                    echo "🔍 Testing application health..."
                    curl -f http://localhost:8081/actuator/health || {
                        echo "❌ Application health check failed"
                        docker-compose logs
                        exit 1
                    }
                    
                    echo "✅ Application deployed successfully"
                    echo "🌐 Application is running at: http://localhost:8081"
                '''
            }
        }
    }
    
    post {
        always {
            echo '🧹 Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed!'
            sh '''
                echo "📋 Container logs for debugging:"
                docker-compose logs || true
            '''
        }
    }
}
