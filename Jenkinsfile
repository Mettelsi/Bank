pipeline{
 agent any
 stages {
 stage('Build'){
 steps {
 sh 'echo "Building.."'
 sh './gradlew build -x test'
 }
post{
success{
archive " Bankprojekt.jar"
 }
}
}
 stage('Test'){
 steps{
 sh 'echo "Testing..."'
 sh './gradlew test'
 }
}
 stage('Javadoc'){
 steps{
 sh 'echo "create Javadoc .."'
 sh './gradlew javadoc'
 } 
}
 stage('Deployment Zip'){
steps {
                
                sh 'echo test > archive/test.txt'
                zip zipFile: 'test3.zip', archive: true, dir: 'archive'
                archiveArtifacts artifacts: 'test3.zip', fingerprint: true
            }
} 
}
}

