pipeline {
        agent any
        triggers {
            pollSCM('H/2 * * * *')
        }
        tools {
 //               jdk 'jdk-17.0.4.1'
                dockerTool 'docker-tool'
                maven 'apache-maven'
        }
        
        stages{
            stage('git repo & clean'){
            steps{
                sh 'if [ -d core-service ]; then rm -Rf core-service; fi'
                sh 'git clone https://github.com/jenkins-anid/core-service.git'
                sh 'mvn -version'
                sh 'mvn clean -f core-service'
            }
            }
            stage('compile'){
            steps{
                sh 'mvn compile -f core-service'
            }
            }
                
            stage('Install'){
            steps{
//                sh 'psql -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'rspm_core_db'" | grep -q 1 | psql -U postgres -c "CREATE DATABASE rspm_core_db"'
                sh 'mvn install -f core-service -DskipTests'
                sh 'cp core-service/target/*.jar /opt/rspm/app/core/'
            }
            }
                
            stage('Docker Build'){
            steps{
                script {
                         def pomVersion = sh(script: 'mvn -q -Dexec.executable=\'echo\' -Dexec.args=\'${project.version}\' --non-recursive exec:exec', returnStdout: true).trim()
                         def dockerVersion = 'ghcr.io/jenkins-anid/core-service:'+pomVersion+'-latest'
                         echo "${pomVersion}"
                         //sh 'docker build -t ${dockerVersion} -f docker/Dockerfile .'
                         sh 'ls -l'
                         sh 'docker build -t core-service:0.0.1 -f docker/Dockerfile .'
                         sh 'docker tag core-service:0.0.1 ghcr.io/jenkins-anid/core-service:0.0.1-latest'
                         sh "docker login ghcr.io -u jenkins-anid -p ${env.DOCKER_PAT}"
                         sh 'docker push ghcr.io/jenkins-anid/core-service:0.0.1-latest'
                  }
               }
        }
        }
       

//        stage('Kubernetes Deploy'){
//              steps{
//                  script{
//                          sh 'helm upgrade --install rspm-core-service-helm ${WORKSPACE}/rspm-core-service-helm --namespace=test'
//                        }
//                   }
//         }

//         stage('Clean'){
//            steps{
//              script{
//                  try{
//                    sh 'docker rmi -f $(docker images -q -f dangling=true)'
//                  } catch(Exception e){
//                    echo 'No dangling images found. '
//                  }
//              }
//            }
//         }
}
