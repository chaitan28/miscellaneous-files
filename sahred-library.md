# Shared Library
### Jenkins Shared Library:
- A Jenkins Shared Library lets you create reusable pipeline code (functions, variables, etc.) that can be shared across multiple Jenkins pipelines <br>
- It is concept of having a common pipeline code in the version control system that can be used by any number of pipelines just by refering it. Multiple teams can use the same shared library for their teams

### Folder Structure (Git Repo Example)
```sh
(jenkins-shared-library)
├── vars/
│   └── sayHello.groovy            # simple global function
├── src/
│   └── org/
│       └── myorg/
│           └── MyHelper.groovy    # reusable class or logic
├── resources/                     # static files/templates
└── README.md
```

### 1. vars/sayHello.groovy
```sh
def call(String name = 'World') {
    echo "Hello, ${name}!"
}
```

### 2.src/org/myorg/MyHelper.groovy
```sh
package org.myorg

class MyHelper implements Serializable {
    def steps

    MyHelper(steps) {
        this.steps = steps
    }

    def greet(String name) {
        steps.echo "Greetings, ${name}!"
    }
}
```

### 3. How to Load It in a Pipeline
```sh
@Library('jenkins-shared-library') _

pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sayHello('Chaitanya') // from vars/sayHello.groovy

                script {
                    def helper = new org.myorg.MyHelper(this)
                    helper.greet('DevOps Engineer')
                }
            }
        }
    }
}
```

### 4. Configure in Jenkins:
- Go to Manage Jenkins → Configure System → Global Pipeline Libraries. <br>

- Add a new library:<br>

      Name: jenkins-shared-library (or your custom name)<br>
      Source: Git repo URL <br>
      Default version: main or master <br>