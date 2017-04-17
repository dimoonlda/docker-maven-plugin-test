FROM couchbase:latest
ENV BUCKET_NAME ${BUCKET_NAME:-baeldung}
COPY configure.sh /opt/couchbase
CMD ["/opt/couchbase/configure.sh"]