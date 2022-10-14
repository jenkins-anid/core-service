pipeline {
        agent any
        triggers {
            pollSCM('H/2 * * * *')
        }
        tools {
                jdk 'jdk-17.0.4.1'
                maven 'apache-maven'
        }
        stages{
            stage('Compile'){
            steps{
                sh 'mvn clean install'
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
