pipeline {
	agent any 
	stages {
		stage('Checkout') {
			steps{ checkout scm }
		}
		
		stage("Biuld with Maven") {
			steps {
				script {
					def mvn = isUnix()? 'mvn' : 'mvn.cmd'
					if(isUnix) {
						sh "${mvn} -B clean verify"
					} else {
						bat "${mvn} -B clean verify"
					}
				}
			}
		}
	}
}