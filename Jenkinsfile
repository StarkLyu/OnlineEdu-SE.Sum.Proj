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
	    	sh "cd code/backend/online-edu && mvn -DskipTests tomcat7:redeploy"
	        }
	    }
    }
}
