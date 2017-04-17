package org.baeldung.spring.data.couchbase.service;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Ignore
public class PersonTemplateServiceIntegrationTest extends PersonServiceIntegrationTest {

    @Autowired
    @Qualifier("PersonTemplateService")
    public void setPersonService(PersonService service) {
        this.personService = service;
    }
}
