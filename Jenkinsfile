pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {

                checkout scm
            }
        }

        stage('Build & Test') {
            steps {

                sh './mvnw clean test'
            }
        }

        stage('Build Docker Images') {
            steps {

                sh 'docker build -t time-gateway-service ./gateway-service'
                sh 'docker build -t time-auth-service ./auth-service'
                sh 'docker build -t time-flight-service ./flight-service'
                sh 'docker build -t time-booking-service ./booking-service'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {

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