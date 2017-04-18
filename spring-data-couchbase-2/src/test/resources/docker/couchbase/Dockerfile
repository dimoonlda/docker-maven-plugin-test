FROM couchbase:latest
ENV BUCKET_NAME ${BUCKET_NAME:-test}
COPY configure.sh /opt/couchbase
RUN mkdir /opt/couchbase/views
COPY views/*.json /opt/couchbase/views/
RUN chmod +x /opt/couchbase/configure.sh
RUN chmod +x /entrypoint.sh
CMD ["/opt/couchbase/configure.sh"]