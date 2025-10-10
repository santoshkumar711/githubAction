pipeline {
    agent any

    environment {
        IMAGE_NAME = "santoshkumar711/github-action"
        IMAGE_TAG = "v1"
    }

    stages {
        stage('Checkout SCM') {
            steps {
                git branch: 'master', url: 'https://github.com/santoshkumar711/githubAction.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $IMAGE_NAME:$IMAGE_TAG ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh "docker stop $IMAGE_NAME || true"
                sh "docker rm $IMAGE_NAME || true"
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -d --name $IMAGE_NAME -p 8080:8080 $IMAGE_NAME:$IMAGE_TAG"
            }
        }

        stage('Docker Login & Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'PASSWORD'
                )]) {
                    sh "docker login -u $USERNAME -p $PASSWORD"
                    sh "docker tag $IMAGE_NAME:$IMAGE_TAG $USERNAME/$IMAGE_NAME:$IMAGE_TAG"
                    sh "docker push $USERNAME/$IMAGE_NAME:$IMAGE_TAG"
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline finished."
        }
        failure {
            echo "Pipeline failed. Check logs."
        }
    }
}