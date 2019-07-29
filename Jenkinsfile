pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "cd code/backend/online-edu && mvn -B -DskipTests clean package"
            }
        }
	stage('Test') {
            steps {
                sh "cd code/backend/online-edu && mvn -Dspring.profiles.active=test test"
            }
        }
	stage('Deploy') {
	    steps {
	    	sh "cd code/backend/online-edu/target && curl -v -u uilil:fwznhyn -T online-edu-0.0.1-SNAPSHOT.war 'http://10.0.0.95:8080/manager/text/deploy?path=/online-edu&update=true'"
	        }
	    }
    }
}
