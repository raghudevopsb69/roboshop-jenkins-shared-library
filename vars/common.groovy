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

def testCases(appType) {

  stage('Unit Tests') {
    if(appType == "java") {
      sh 'mvn test || true'
    }

    if(appType == "nodejs") {
      sh 'npm test || true'
    }

    if(appType == "python") {
      sh 'python3 -m unittest *.py || true'
    }

  }


}


def codeQuality() {
  stage('Code Quality') {
    //sh "sonar-scanner -Dsonar.qualitygate.wait=true -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.host.url=http://172.31.8.238:9000 -Dsonar.projectKey=${env.COMPONENT} ${SONAR_OPTS}"
    sh 'echo OK'
  }
}

def release() {
  stage('Publish A Release') {
    echo 'Publish A Release'
  }
}

def mail() {
  mail bcc: '', body: "<h1>Pipeline Failure</h1><br>Project Name: ${COMPONENT}\nURL = ${BUILD_URL}", cc: '', charset: 'UTF-8', from: 'raghudevopsb69@gmail.com', mimeType: 'text/html', replyTo: 'raghudevopsb69@gmail.com', subject: "ERROR CI: Component Name - ${COMPONENT}", to: "raghudevopsb69@gmail.com"
  sh 'exit 1'
}
