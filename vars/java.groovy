def call() {
  node {
    try {
      common.checkout()
      common.compile("java")
      common.codeQuality()
      common.release()
    } catch (e) {
      mail bcc: '', body: "<h1>Pipeline Failure</h1><br>Project Name: ${COMPONENT}\nURL = ${BUILD_URL}", cc: '', charset: 'UTF-8', from: 'raghudevopsb69@gmail.com', mimeType: 'text/html', replyTo: 'raghudevopsb69@gmail.com', subject: "ERROR CI: Component Name - ${COMPONENT}", to: "raghudevopsb69@gmail.com";
    }
  }
}
