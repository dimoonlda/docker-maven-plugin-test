package org.baeldung.spring.data.couchbase.service;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Ignore
public class StudentRepositoryServiceIntegrationTest extends StudentServiceIntegrationTest {

    @Autowired
    @Qualifier("StudentRepositoryService")
    public void setStudentService(StudentService service) {
        this.studentService = service;
    }
}
