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
    $ travis encrypt <TOKEN>
    ```
 - **OPTIONAL :** Add your organisation into the `.travis.yml` file
 - Add this token into the `.travis.yml` file 