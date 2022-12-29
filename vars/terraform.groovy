def call() {
  node {

    properties([
        parameters([
            [$class: 'ChoiceParameterDefinition',
             choices: '\ndev\nprod\n',
             name: 'ENVIRONMENT',
             description: "Choose Environment"
            ],
        ]),
        parameters([
            [$class: 'ChoiceParameterDefinition',
             choices: '\napply\ndestroy\n',
             name: 'ACTION',
             description: "Choose Environment"
            ],
        ]),
    ])

    common.checkout()

    if ( ! ENVIRONMENT ) {
      env.ENVIRONMENT = "${env.ENVIRONMENT_DEFAULT}"
    }

    if ( ! ACTION ) {
      env.ACTION = "${env.ACTION_DEFAULT}"
    }

    stage('Terraform init') {
      addShortText background: '#FFFF00', borderColor: '#FFFF00', color: '', link: '', text: "Env : ${ENVIRONMENT} | Action : ${ACTION}"
      sh '''
        terraform init -backend-config=env/${ENVIRONMENT}-backend.tfvars
      '''
    }

    stage('Terraform Plan') {
      sh '''
        terraform plan -var-file=env/${ENVIRONMENT}.tfvars
      '''
    }

    stage('Terraform Apply') {
      sh '''
        terraform apply -auto-approve -var-file=env/${ENVIRONMENT}.tfvars
      '''
    }

  }
}
