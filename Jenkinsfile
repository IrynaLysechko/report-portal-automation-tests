pipeline {
     agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: 'feature-add-jenkins-file-1.0']],
                          doGenerateSubmoduleConfigurations: false,
                          extensions: [],
                          submoduleCfg: [],
                          userRemoteConfigs: [[url: 'https://github.com/IrynaLysechko/report-portal-automation-tests.git']]])
            }
        }

        stage('Run API Tests') {
            steps {
                script {
                    echo 'Running API Tests'
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'ReportPortal', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh './gradlew clean test -Drp.user.name=$USERNAME -Drp.user.password=$PASSWORD -Dgroups=api'
                    }
                }
            }
        }

        stage('Run UI Tests') {
            when {
                expression {
                    currentBuild.resultIsBetterOrEqualTo('SUCCESS')
                }
            }
            steps {
                script {
                    echo 'Running UI Tests'

                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'ReportPortal', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh './gradlew clean test -Drp.user.name=$USERNAME -Drp.user.password=$PASSWORD -Dgroups=ui -Dbrowser=chrome'
                    }
                }
            }
        }

        stage('Upload Allure Result') {
            steps {
                script {
                    // Your artifact upload steps go here
                    echo 'Uploading Artifact'
                    // Example command: cp target/test-reports/*.xml $JENKINS_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/archive/
                    sh 'cp target/test-reports/*.xml $JENKINS_HOME/jobs/$JOB_NAME/builds/$BUILD_NUMBER/archive/'
                }
            }
        }
    }
}