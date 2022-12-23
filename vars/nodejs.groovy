def call() {
  if (!env.SONAR_OPTS) {
      env.SONAR_OPTS = ""
  }
    node {
        try {
            common.checkout()
            common.codeQuality()
            common.testCases("nodejs")
          if(binding.hasVariable('TAG_NAME')) {
            common.release()
          }
        } catch (e) {
            common.mail()
        }
    }
}
