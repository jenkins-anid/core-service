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
                sh 'rm -R core-service'
                sh 'git clone https://github.com/jenkins-anid/core-service.git'
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
                sh 'mvn install -f core-service'
            }
            }
        }
//        stage('Docker Build'){
//             steps{
//                 script {
//                            sh 'docker build -t rspm-core-service:1.0.0 -f ${WORKSPACE}/Dockerfile .'
//                            sh 'docker tag rspm-core-service:1.0.0 registry/microservices/rspm-core-service:1.0.0'
//                            sh 'docker login --username=username --password=password registry'
////                            sh 'docker push registry/microservices/rspm-core-service:1.0.0'
//                   }
//                }
//        }

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
