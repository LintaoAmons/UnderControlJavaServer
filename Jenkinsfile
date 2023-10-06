pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        disableConcurrentBuilds()
    }

    environment {
        imagePrefix = 'oatnil.top:8083/undercontrol-server'
        CRED = credentials('docker-registry')
    }

    stages {
        stage('MvnBuildJar') {
            steps {
                sh("./mvnw clean package -s .mvn/wrapper/settings.xml -Dmaven.test.skip=true")
            }
        }

        stage('BuildImage') {
            steps {
                echo("Image of this build: $imagePrefix:$BUILD_NUMBER")
                sh("docker build -t $imagePrefix:$BUILD_NUMBER .")
            }
        }

        stage('Push Image') {
            steps {
                sh "docker login -u $CRED_USR -p $CRED_PSW oatnil.top:8083"
                sh "docker push $imagePrefix:$BUILD_NUMBER"
            }
        }

        stage('Deploy') {
            steps {
                script {
                    build(job: "Undercontrol/deployment",
                            parameters: [string(name: "BACKEND_IMAGE_TAG", value: "$BUILD_NUMBER"),
                                         string(name: 'SERVICE', value: 'BACKEND')]
                    )
                }
            }
        }

    }
}
