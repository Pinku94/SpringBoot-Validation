package com.pinku.validation.repository;

import com.pinku.validation.entity.Person;
import jakarta.transaction.Transactional;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void testSave() {
        // Create a sample Person entity
        Person person = new Person(1L,"Pinku","Gouda", LocalDate.of(1995,05,23),"pin@gmail.com");

        // Save the entity to the database
        personRepository.save(person);
        assertThat(person.getFirstName(), Is.is("Pinku"));
    }

    @Test
    @Order(2)
    void testGetAllPersonDetails(){
        // Find all persons in the repository
        List<Person> allPersons = personRepository.findAll();
        // Verify that the list contains the saved person
        assertThat(allPersons.size(),Is.is(1));
    }

    @Test
    @Order(3)
    public void testFindDistinctPersonByFirstName() {

        List<Person> person = personRepository.findDistinctPersonByFirstName("Pinku");

        // Verify that the list contains two persons with the firstName "John"
        assertEquals(1, person.size());

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void testUpdatePerson(){

        Person person = personRepository.findById(1L).get();
        person.setEmail("pinku@gmail.com");
        Person p1 = personRepository.save(person);
        assertThat(p1.getEmail(),Is.is("pinku@gmail.com"));
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void testDeleteById() {

        // Get the ID of the saved person
        Long personId = 1L;

        // Delete the person by ID
        personRepository.deleteById(personId);

        // Try to find the deleted person
        Optional<Person> deletedPerson = personRepository.findById(personId);

        // Verify that the person is not found (Optional is empty)
        assertTrue(((Optional<?>) deletedPerson).isEmpty());
    }
}
