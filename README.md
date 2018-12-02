# sample-application [![Build Status](https://travis-ci.org/takima-training/sample-application.svg?branch=master)](https://travis-ci.org/takima-training/sample-application)
This is a sample application exposing a simple REST API

## Local development

### Start the database

```bash
$ cd database
$ docker build -t school_db .
$ docker run -p 3306:3306 school_db
```
