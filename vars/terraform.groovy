def call() {
  pipeline {

    agent any

    stages {

      stage('Terraform Plan') {
        steps {
          sh '''
            terraform plan 
          '''
        }
      }


    }

  }
}
