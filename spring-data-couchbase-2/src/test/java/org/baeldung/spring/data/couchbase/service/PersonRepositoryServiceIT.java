package org.baeldung.spring.data.couchbase.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import org.baeldung.spring.data.couchbase.MyCouchbaseConfig;
import org.baeldung.spring.data.couchbase.model.Person;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryServiceIT extends PersonServiceIntegrationTest {

    @Autowired
    @Qualifier("PersonRepositoryService")
    public void setPersonService(PersonService service) {
        this.personService = service;
    }

    @BeforeClass
    public static void setupBeforeClass() {
        final Cluster cluster = CouchbaseCluster.create(MyCouchbaseConfig.NODE_LIST);
        final Bucket bucket = cluster.openBucket(MyCouchbaseConfig.BUCKET_NAME, MyCouchbaseConfig.BUCKET_PASSWORD);
        bucket.upsert(JsonDocument.create(johnSmithId, jsonJohnSmith));
        bucket.upsert(JsonDocument.create(foobarId, jsonFooBar));
        bucket.close();
        cluster.disconnect();
    }

    @Test
    public void whenFindingPersonByJohnSmithId_thenReturnsJohnSmith() {
        final Person actualPerson = personService.findOne(johnSmithId);
        assertNotNull(actualPerson);
        assertNotNull(actualPerson.getCreated());
        assertEquals(johnSmith, actualPerson);
    }

    @Test
    @Ignore
    public void whenFindingAllPersons_thenReturnsTwoOrMorePersonsIncludingJohnSmithAndFooBar() {
        final List<Person> resultList = personService.findAll();
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());
        assertTrue(resultContains(resultList, johnSmith));
        assertTrue(resultContains(resultList, foobar));
        assertTrue(resultList.size() >= 2);
    }

    @Test
    public void whenFindingByFirstNameJohn_thenReturnsOnlyPersonsNamedJohn() {
        final String expectedFirstName = john;
        final List<Person> resultList = personService.findByFirstName(expectedFirstName);
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());
        assertTrue(allResultsContainExpectedFirstName(resultList, expectedFirstName));
    }

    @Test
    public void whenFindingByLastNameSmith_thenReturnsOnlyPersonsNamedSmith() {
        final String expectedLastName = smith;
        final List<Person> resultList = personService.findByLastName(expectedLastName);
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());
        assertTrue(allResultsContainExpectedLastName(resultList, expectedLastName));
    }

    private boolean resultContains(List<Person> resultList, Person person) {
        boolean found = false;
        for (final Person p : resultList) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private boolean allResultsContainExpectedFirstName(List<Person> resultList, String firstName) {
        boolean found = false;
        for (final Person p : resultList) {
            if (p.getFirstName().equals(firstName)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private boolean allResultsContainExpectedLastName(List<Person> resultList, String lastName) {
        boolean found = false;
        for (final Person p : resultList) {
            if (p.getLastName().equals(lastName)) {
                found = true;
                break;
            }
        }
        return found;
    }

}
