## Step-by-Step: Store DockerHub Secrets in Jenkins

###  1. **Generate DockerHub Credentials**

* DockerHub username and password (or a **Personal Access Token**, recommended).
* You can create a token at: [https://hub.docker.com/settings/security](https://hub.docker.com/settings/security)

---

### 🔧 2. **Store Credentials in Jenkins**

#### 🛠 Go to:

> Jenkins → **Manage Jenkins** → **Credentials** → (select scope: Global or folder-specific)

#### ➕ Add a New Credential:

* **Kind**: `Username with password`
* **Username**: your Docker Hub username
* **Password**: your Docker password or access token
* **ID**: `dockerhub-creds` *(or a name you’ll reference in the pipeline)*
* **Description**: DockerHub login credentials

---

##  3. **Use Secrets in Jenkins Pipeline**

###  Example: Declarative Jenkinsfile

```groovy
pipeline {
  agent any

  environment {
    DOCKER_IMAGE = 'yourusername/your-image'
  }

  stages {
    stage('Login to DockerHub') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh """
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
          """
        }
      }
    }

    stage('Build and Push Image') {
      steps {
        sh """
          docker build -t $DOCKER_IMAGE:latest .
          docker push $DOCKER_IMAGE:latest
        """
      }
    }
  }
}
```

---

## Security Notes:

* Always use `withCredentials {}` block to **scope secret usage**.
* Do **not echo** or log passwords/tokens.
* Use **access tokens** instead of real passwords when possible.
* You can also use **docker config.json** as a **Secret File credential** if needed.

---

