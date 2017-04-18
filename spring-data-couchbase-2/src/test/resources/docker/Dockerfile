FROM couchbase:latest
ENV BUCKET_NAME ${BUCKET_NAME:-baeldung}
COPY configure.sh /opt/couchbase
COPY *.ddoc /opt/couchbase
RUN chmod +x /opt/couchbase/configure.sh
RUN chmod +x /entrypoint.sh
CMD ["/opt/couchbase/configure.sh"]