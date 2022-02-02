FROM registry.access.redhat.com/ubi8/ubi-minimal:8.4
WORKDIR /work/
RUN chown :root /work \
    && chmod "g+rwX" /work \
    && chown :root /work
COPY target/*-runner.jar /work/application && chown :root /work/application

EXPOSE 8080

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
