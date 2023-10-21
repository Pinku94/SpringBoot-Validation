package com.pinku.validation.service;

import com.pinku.validation.entity.Person;
import com.pinku.validation.repository.PersonRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {PersonServiceTest.class})
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
     PersonService personService;

    Person p1 = new Person(1L,"Pinku","Gouda", LocalDate.of(1995,05,23),"pin@gmail.com");
    Person p2 = new Person(2L,"Rinku","Rouda", LocalDate.of(1994,06,24),"rin@gmail.com");
    Person p3 = new Person(3L,"Tinku","Touda", LocalDate.of(1993,07,25),"tin@gmail.com");

    Person p4 = new Person(4L,"Pinku","Gouda", LocalDate.of(1996,05,23),"pin@gmail.com");
    Person p5 = new Person(1L,"Krishna","Shyam", LocalDate.of(1997,07,03),"kir@gmail.com");

    List<Person> person;

    @BeforeEach
    void setUp(){
        person = new ArrayList<>();

    }
    @AfterEach
    void tearDown(){

    }

    @Test
    void storePersonDetails() {
    }

    @Test
    void getAllPersonDetails() {
         person = Arrays.asList(p1,p2,p3);

        when(personRepository.findAll()).thenReturn(person);
        assertEquals(3,personService.getAllPersonDetails().size());
    }

    @Test
    void getAllUniquePersonDetails() {

        person = Arrays.asList(p1,p4);
        when(personRepository.findDistinctPersonByFirstName("Pinku")).thenReturn(person);

        //assertEquals(2,personService.getAllUniquePersonDetails("Pinku").size());
        assertThat(personService.getAllUniquePersonDetails("Pinku").size(), Is.is(2));
        assertThat(personService.getAllUniquePersonDetails("Pinku"), hasSize(2));
    }

    @Test
    void testStorePersonDetails() {

        when(personRepository.save(p1)).thenReturn(p1);

        assertThat(personService.storePersonDetails(p1).getFirstName(),is("Pinku"));
    }

    @Test
    void testUpdatePersonDetails(){
        Long id = 1L;
        Optional<Person> optionalPerson = Optional.ofNullable(p1);
        when(personRepository.findById(id)).thenReturn(optionalPerson);
        when(personRepository.save(p5)).thenReturn(p5);
        Person p = personService.updatePersonDetails(id,p5);
        assertThat(p.getFirstName(),is("Krishna"));
    }

    @Test
    void testDeletePersonDetails(){
        Long id = 1l;

        personService.deletePersonDetails(id);

        verify(personRepository,times(1)).deleteById(id);
    }
}