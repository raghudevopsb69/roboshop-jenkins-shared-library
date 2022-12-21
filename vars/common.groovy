def checkout() {
  stage('Checkout Code') {
    cleanWs()
    git branch: 'main', url: "${env.REPO_URL}"
  }
}

def compile(appType) {

  stage('Compile Code') {
    if(appType == "java") {
      sh 'mvn clean compile'
    }

    if(appType == "golang") {
      sh 'go mod init'
    }
  }


}


def codeQuality() {
  stage('Code Quality') {
    sh "sonar-scanner -Dsonar.qualitygate.wait=true -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.host.url=http://172.31.8.238:9000 -Dsonar.projectKey=${env.COMPONENT}"
  }
}

def release() {
  stage('Publish A Release') {
    echo 'Publish A Release'
  }
}
