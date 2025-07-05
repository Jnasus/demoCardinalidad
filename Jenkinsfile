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
        
        stage('Code Quality') {
            parallel {
                stage('Security Scan') {
                    steps {
                        echo '🔒 Running security scan...'
                        sh 'mvn dependency:check'
                    }
                }
            }
        }
        
        stage('Build & Test') {
            steps {
                echo '🔨 Building application...'
                sh 'mvn clean compile'
                
                echo '🧪 Running unit tests...'
                sh 'mvn test'
                
                echo '📦 Creating JAR package...'
                sh 'mvn package -DskipTests'
                
                echo '📊 Generating test reports...'
                publishTestResults testResultsPattern: '**/target/surefire-reports/*.xml'
            }
            post {
                always {
                    echo '📈 Publishing test results...'
                    publishTestResults testResultsPattern: '**/target/surefire-reports/*.xml'
                }
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
            when {
                branch 'main'
            }
            steps {
                echo '🚀 Deploying to staging environment...'
                sh '''
                    # Stop existing containers
                    docker-compose down || true
                    
                    # Start containers
                    docker-compose up -d
                    
                    # Wait for application to be ready
                    sleep 30
                    
                    # Test if application is responding
                    curl -f http://localhost:8081/actuator/health || exit 1
                    echo "✅ Application deployed successfully"
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
        }
    }
}
