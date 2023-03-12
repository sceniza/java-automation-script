FROM maven:3.8.5-openjdk-17

WORKDIR /app
RUN microdnf install yum
RUN curl -O  https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
RUN yum install google-chrome-stable_current_x86_64.rpm -y
RUN yum -y install php-common libnss3.so libnssutil3.so libxcb

COPY src ./src
COPY pom.xml ./pom.xml

RUN mvn clean

ENTRYPOINT ["/app/entrypoint.sh"]
CMD ["mvn", "clean", "install"]