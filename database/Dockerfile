FROM postgres:9.6-alpine

ADD ./scripts/ /docker-entrypoint-initdb.d

VOLUME /home/alex/Bureau/devops-cpe/tp-docker/database/data:/var/lib/postgresql/data

ENV POSTGRES_DB=db \
    POSTGRES_USER=usr \
    POSTGRES_PASSWORD=pwd
