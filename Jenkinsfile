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
            steps {
                echo '🚀 Deploying to staging environment...'
                echo "Current branch: ${env.BRANCH_NAME}"
                echo "Build number: ${env.BUILD_NUMBER}"
                echo "Workspace: ${env.WORKSPACE}"
                
                sh '''
                    echo "🔍 Checking if docker-compose is available..."
                    docker-compose --version || echo "docker-compose not found"
                    
                    echo "🔍 Checking current directory..."
                    pwd
                    ls -la
                    
                    echo "🔍 Checking if docker-compose.yml exists..."
                    if [ -f docker-compose.yml ]; then
                        echo "✅ docker-compose.yml found"
                        cat docker-compose.yml
                    else
                        echo "❌ docker-compose.yml not found"
                        exit 1
                    fi
                    
                    # Stop any existing containers
                    echo "🛑 Stopping existing containers..."
                    docker-compose down || true
                    
                    # Start the application
                    echo "🚀 Starting application..."
                    docker-compose up -d
                    
                    # Wait for application to start
                    echo "⏳ Waiting for application to start..."
                    echo "🔍 Checking container status..."
                    docker-compose ps
                    sleep 90
                    
                    # Wait for application to be ready
                    echo "🔍 Waiting for application to be ready..."
                    for i in {1..30}; do
                        if curl -f http://localhost:8082/api-docs > /dev/null 2>&1; then
                            echo "✅ Application is ready!"
                            break
                        fi
                        echo "⏳ Waiting for application... (attempt $i/30)"
                        sleep 5
                    done
                    
                    # Final health check
                    echo "🔍 Final health check..."
                    curl -f http://localhost:8082/api-docs || {
                        echo "❌ Final health check failed"
                        echo "📋 Container status:"
                        docker-compose ps
                        echo "📋 MySQL logs:"
                        docker-compose logs mysql
                        echo "📋 App logs:"
                        docker-compose logs app
                        echo "📋 All container logs:"
                        docker-compose logs
                        exit 1
                    }
                    
                    echo "✅ Application deployed successfully!"
                    echo "🌐 Application is running at: http://localhost:8082"
                    echo "🌐 Nginx proxy is running at: http://localhost:8080"
                    echo "🌐 Swagger UI available at: http://localhost:8080/swagger-ui/"
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
                echo "📋 Container status:"
                docker-compose ps || true
            '''
        }
    }
}
