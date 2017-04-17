package org.baeldung.spring.data.couchbase.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.baeldung.spring.data.couchbase.IntegrationTest;
import org.baeldung.spring.data.couchbase.MyCouchbaseConfig;
import org.baeldung.spring.data.couchbase.model.Person;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public abstract class PersonServiceIntegrationTest extends IntegrationTest {

    static final String typeField = "_class";
    static final String john = "John";
    static final String smith = "Smith";
    static final String johnSmithId = "person:" + john + ":" + smith;
    static final Person johnSmith = new Person(johnSmithId, john, smith);
    static final JsonObject jsonJohnSmith = JsonObject.empty().put(typeField, Person.class.getName()).put("firstName", john).put("lastName", smith).put("created", DateTime.now().getMillis());

    static final String foo = "Foo";
    static final String bar = "Bar";
    static final String foobarId = "person:" + foo + ":" + bar;
    static final Person foobar = new Person(foobarId, foo, bar);
    static final JsonObject jsonFooBar = JsonObject.empty().put(typeField, Person.class.getName()).put("firstName", foo).put("lastName", bar).put("created", DateTime.now().getMillis());

    PersonService personService;

}
