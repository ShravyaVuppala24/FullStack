pipeline {
    agent any  // Runs on any available agent (master or slave)

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from Git repository
                git 'https://github.com/ShravyaVuppala24/FullStack'
            }
        }

        stage('Build') {
            steps {
                // Build the Maven project
                bat 'mvn clean package'
            }
        }

    }

    post {
        success {
            // Actions to perform when the pipeline succeeds
            echo 'Pipeline successfully completed!'
        }
        failure {
            // Actions to perform when the pipeline fails
            echo 'Pipeline failed! Check the build logs for details.'
        }
    }
}
