FROM mysql:5.7
MAINTAINER Takima

ENV MYSQL_DATABASE=SchoolOrganisation \
    MYSQL_ROOT_PASSWORD=takima \
    MYSQL_USER=takima \
    MYSQL_PASSWORD=takipassword

ADD ./scripts/ /docker-entrypoint-initdb.d
EXPOSE 3306

# Entrypoint overload to catch the ctrl+c and stop signals
ENTRYPOINT ["/bin/bash", "/run.sh"]
CMD ["mysqld"]

COPY run.sh /run.sh

