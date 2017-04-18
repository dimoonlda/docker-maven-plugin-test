#!/bin/bash
set -x
set -m
echo "docker host ip = " $DOCKER_HOST_IP
/entrypoint.sh couchbase-server &
echo "sleeping..."
sleep 20
echo "Start configuring env by calling REST endpoints"
curl -v -X POST http://localhost:8091/pools/default -d memoryQuota=300 -d indexMemoryQuota=300
curl -v -X POST http://localhost:8091/node/controller/setupServices -d services=kv%2Cindex%2Cn1ql
curl -i -u Administrator:password -X POST http://127.0.0.1:8091/settings/indexes -d 'storageMode=memory_optimized'
curl -v -X POST http://localhost:8091/settings/web -d port=8091 -d username=Administrator -d password=password
echo "bucket set up start"
echo "bucket name = " $BUCKET_NAME
curl -v -X POST http://127.0.0.1:8091/pools/default/buckets -u Administrator:password -d "name="$BUCKET_NAME"" -d 'ramQuotaMB=200' -d 'authType=none' -d 'proxyPort=11222'
sleep 5
echo "bucket set up done"
curl -v http://127.0.0.1:8093/query/service -d "statement=CREATE PRIMARY INDEX ON $BUCKET_NAME"
cd /opt/couchbase
#https://developer.couchbase.com/documentation/server/4.6/rest-api/rest-ddocs-create.html
curl -X PUT -H "Content-Type: application/json" http://Administrator:password@127.0.0.1:8092/baeldung/_design/dev_byfield -d @DeviceIdPhoneToContact.ddoc
fg 1