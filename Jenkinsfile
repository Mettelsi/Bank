pipeline {
 agent any
 stages {
 stage('Build') {
 steps {
 sh 'echo "Building.."'
 sh './gradlew build clean -x test'
 }
 }

 }
}
