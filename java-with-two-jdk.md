### 1. Install Multiple JDKs in Jenkins
Go to:
Manage Jenkins → Global Tool Configuration → JDK

Click Add JDK twice (for each version)

Uncheck "Install automatically" if you have it installed already

Give each a name, e.g.:

jdk8 → Path: /usr/lib/jvm/java-8-openjdk

jdk11 → Path: /usr/lib/jvm/java-11-openjdk

### 2. Reference JDKs in Jenkins Pipeline
In a Declarative Pipeline:
```sh
pipeline {
    agent any

    tools {
        jdk 'jdk8'  // or 'jdk11'
    }

    stages {
        stage('Build') {
            steps {
                sh 'java -version'
                sh './gradlew build' // or mvn package
            }
        }
    }
}

```