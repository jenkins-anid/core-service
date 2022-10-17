pipeline {
        agent any
        triggers {
            pollSCM('H/2 * * * *')
        }
        tools {
 //               jdk 'jdk-17.0.4.1'
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
            }
            }
                
            stage('Docker Build'){
            steps{
                script {
                         def pomVersion = sh(script: 'mvn -q -Dexec.executable=\'echo\' -Dexec.args=\'${project.version}\' --non-recursive exec:exec', returnStdout: true).trim()
                         def dockerVersion = 'ghcr.io/jenkins-anid/core-service:'+pomVersion+'-latest'
                         sh 'docker build -t ${dockerVersion} -f docker/Dockerfile .'
//                            sh 'docker tag rspm-core-service:1.0.0 registry/microservices/rspm-core-service:1.0.0'
                         sh 'echo ${env.DOCKER_PAT} | docker login ghcr.io -u jenkins-anid --password-stdin'
                         sh 'docker push ${dockerVersion}'
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
