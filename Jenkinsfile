node ('master'){
	stage('back-maven'){
	checkout scm
	
	sh 'mvn -f server/pom.xml clean package'
	}
}
