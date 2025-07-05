pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'demo-cardinalidad'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
        DOCKER_REGISTRY = 'localhost:5000'
        GITHUB_REPO = 'yourusername/demoCardinalidad'
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
                stage('SonarQube Analysis') {
                    when {
                        expression { env.SONAR_TOKEN != null }
                    }
                    steps {
                        echo '🔍 Running SonarQube analysis...'
                        withSonarQubeEnv('SonarQube') {
                            sh 'mvn clean verify sonar:sonar'
                        }
                    }
                }
                
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
        
        stage('Docker Security Scan') {
            steps {
                echo '🔒 Scanning Docker image for vulnerabilities...'
                sh 'docker run --rm -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image ${DOCKER_IMAGE}:${DOCKER_TAG}'
            }
        }
        
        stage('Push to Registry') {
            when {
                branch 'main'
            }
            steps {
                echo '📤 Pushing Docker image to registry...'
                script {
                    docker.withRegistry("http://${DOCKER_REGISTRY}", 'docker-registry-credentials') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push('latest')
                    }
                }
            }
        }
        
        stage('Deploy to Staging') {
            when {
                branch 'main'
            }
            steps {
                echo '🚀 Deploying to staging environment...'
                sh '''
                    cd /path/to/staging
                    docker-compose down
                    docker-compose pull
                    docker-compose up -d
                '''
            }
        }
        
        stage('Integration Tests') {
            when {
                branch 'main'
            }
            steps {
                echo '🔗 Running integration tests...'
                sh '''
                    # Wait for application to be ready
                    sleep 30
                    
                    # Run integration tests
                    curl -f http://localhost:8081/actuator/health || exit 1
                    
                    # Test API endpoints
                    curl -f http://localhost:8081/api/v1/categoria || exit 1
                    curl -f http://localhost:8081/api/v1/producto || exit 1
                '''
            }
        }
        
        stage('Deploy to Production') {
            when {
                branch 'main'
                beforeAgent true
            }
            input {
                message 'Deploy to production?'
                ok 'Deploy'
            }
            steps {
                echo '🚀 Deploying to production...'
                sh '''
                    cd /path/to/production
                    docker-compose down
                    docker-compose pull
                    docker-compose up -d
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
            emailext (
                subject: "Pipeline Successful: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Pipeline completed successfully. Build: ${env.BUILD_URL}",
                to: 'your-email@example.com'
            )
        }
        failure {
            echo '❌ Pipeline failed!'
            emailext (
                subject: "Pipeline Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Pipeline failed. Build: ${env.BUILD_URL}",
                to: 'your-email@example.com'
            )
        }
    }
} 