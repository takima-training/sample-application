# sample-application [![Build Status](https://travis-ci.org/takima-training/sample-application.svg?branch=master)](https://travis-ci.org/takima-training/sample-application)
This is a sample application exposing a simple REST API

## Local development

### Start the database

```bash
$ cd database
$ docker build -t school_db .
$ docker run -p 3306:3306 school_db
```

### Run the application

```bash
# Run unit tests
$ mvn test 
# Run integration tests
$ mvn verify
# Generate the .jar artifact
$ mvn install
```

### Configure SonarCloud

- Go to [https://sonarcloud.io]()
- My account > Security
- Generate a new token
- Encrypt it :
    ```bash
    $ gem install travis
    $ travis encrypt SONAR_TOKEN=<TOKEN>
    ```
 - Edit the `.travis.yml` such as :
    ```yml
    env:
      global:
        secure: <ENCRYPTED_TOKEN>
    
    #...
    
    script:
      - |
        mvn clean install sonar:sonar \
        -Dsonar.projectKey=<SONAR_PROJECT_KEY> \
        -Dsonar.organization=<SONAR_ORGANISATION_KEY> \
        -Dsonar.host.url=https://sonarcloud.io \
        -Dsonar.login=$SONAR_TOKEN
    ```
    
    ### Push the image to the Docker Hub
    
    - Connect to Docker Hub
    - Create a repository like `<login>/sample-application`
    - Update the image name in the `.travis.yml`
    - Encrypt the Docker Hub login and password, and add it to the global variables in the `.travis.yml`
    ```bash
    $ travis encrypt DOCKER_USER=<docker_user>
    $ travis encrypt DOCKER_PASS=<docker_pass>
    ```