pipeline {
 agent any
 stages {
 stage('Build') {
 steps {
 sh 'echo "Building.."'
 sh './gradlew build -x test'
 }
 }
 Stage('Test'){
 steps{
 sh 'echo "Testing..."'
 }
 }
 }
}
