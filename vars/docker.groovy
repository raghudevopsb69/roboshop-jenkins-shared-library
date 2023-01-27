def call() {
  node {
    sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 739561048503.dkr.ecr.us-east-1.amazonaws.com'
    sh 'docker build -t ${COMPONENT} .'
    sh 'docker tag ${COMPONENT}:latest 739561048503.dkr.ecr.us-east-1.amazonaws.com/${COMPONENT}:${TAG_NAME}'
    sh 'docker push 739561048503.dkr.ecr.us-east-1.amazonaws.com/${COMPONENT}:${TAG_NAME}'
  }
}
