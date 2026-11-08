pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Obtiene el código fuente más reciente del repositorio Git
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Compila y ejecuta pruebas en los servicios utilizando Maven Wrapper
                sh './mvnw clean test'
            }
        }

        stage('Build Docker Images') {
            steps {
                // Construye las imágenes Docker para cada microservicio usando los Dockerfiles
                sh 'docker build -t time-gateway-service ./gateway-service'
                sh 'docker build -t time-auth-service ./auth-service'
                sh 'docker build -t time-flight-service ./flight-service'
                sh 'docker build -t time-booking-service ./booking-service'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                // Levanta o actualiza los servicios contenedorizados de forma automatizada
                sh 'docker-compose down'
                sh 'docker-compose up -d --build'
            }
        }
    }

    post {
        success {
            echo '¡Pipeline ejecutado con éxito! El ecosistema está actualizado y corriendo.'
        }
        failure {
            echo 'El pipeline ha fallado. Revisa los logs de compilación o pruebas.'
        }
    }
}