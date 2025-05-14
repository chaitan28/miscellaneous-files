pipeline {
    agent { label 'build' }
    parameters {
        string(name: 'IMAGETAG', defaultValue: '1', description: 'Enter the Image Tag to Deploy')
    }
    stages {
        stage('Deploy') {
            steps { 
                git branch: 'main', credentialsId: 'GitlabCred', url: 'https://gitlab.com/chaitan28/spingboot-cd-pipeline.git'
                
                dir ("./kubernetes") {
                    sh "sed -i 's|image: .*|image: chaitan28/kubegame:$IMAGETAG|g' deployment.yml" 
                }

                sh 'git config --global user.email "n.chaitanya2803@gmail.com"'
                sh 'git config --global user.name "Chaitan28"'

                withCredentials([string(credentialsId: 'GitlabToken', variable: 'GITLAB_PAT')]) {
                    sh 'git commit -a -m "New deployment for Build $IMAGETAG"'
                    sh 'git push https://gitlab-ci-token:${GITLAB_PAT}@gitlab.com/chaitan28/spingboot-cd-pipeline.git'

                }
            }
        }
    }
}
