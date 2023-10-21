package com.pinku.validation.controller;

import com.pinku.validation.entity.Person;
import com.pinku.validation.exception.NoDataFoundException;
import com.pinku.validation.repository.PersonRepository;
import com.pinku.validation.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {PersonControllerTest.class})
class PersonControllerTest {
    @Mock
    PersonService personService;
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonController personController;
    @Mock
    private Logger log;


    List<Person> personList;

    @Captor
    ArgumentCaptor<Long> argumentCaptor;

    Person p1 = new Person(1L,"Pinku","Gouda", LocalDate.of(1995,05,23),"pin@gmail.com");
    Person p2 = new Person(2L,"Rinku","Rouda", LocalDate.of(1994,06,24),"rin@gmail.com");
    Person p3 = new Person(3L,"Tinku","Touda", LocalDate.of(1993,07,25),"tin@gmail.com");
    Person p4 = new Person(4L,"Pinku","Gouda", LocalDate.of(1996,05,23),"pin@gmail.com");
    Person p5 = new Person(1L,"Krishna","Shyam", LocalDate.of(1997,07,03),"kir@gmail.com");

    @Test
    void testStorePersonDetails(){

        when(personService.storePersonDetails(p1)).thenReturn(p1);
        ResponseEntity<Person> response = personController.storePersonDetails(p1);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertThat(response.getBody().getFirstName(),Is.is("Pinku"));
    }

    @Test
    void testGetAllPersonDetailsWhenListNotEmpty() {

        personList = Arrays.asList(p1,p2,p3);

        when(personService.getAllPersonDetails()).thenReturn(personList);

        ResponseEntity<List<Person>> response = personController.getAllPersonDetails();

        assertEquals(HttpStatus.FOUND,response.getStatusCode());
        assertEquals(personList,response.getBody());
    }

    @Test
    public void testGetAllPersonDetailsException() {
        // Mock the behavior of personService to throw an exception
        when(personService.getAllPersonDetails()).thenThrow(new NoSuchElementException("No Person Found"));
        // Use assertThrows to verify that the method throws the expected exception
        assertThrows(NoSuchElementException.class, () -> personController.getAllPersonDetails());

    }

    @Test
    void testGetUniquePersonDetailsWhenListNotEmpty() {

        personList = Arrays.asList(p1,p4);

        when(personService.getAllUniquePersonDetails("Pinku")).thenReturn(personList);

        ResponseEntity<List<Person>> response = personController.getUniquePersonDetails("Pinku");
        assertEquals(HttpStatus.FOUND,response.getStatusCode());
    }

    @Test
    public void testGetUniquePersonDetailsException() {
        // Mock the behavior of personService to throw an exception
        when(personService.getAllUniquePersonDetails("Pinku")).thenThrow(new NoDataFoundException("No Person Found"));
        // Use assertThrows to verify that the method throws the expected exception
        assertThrows(NoDataFoundException.class, () -> personController.getUniquePersonDetails("Pinku"));
    }
@Test
 void testUpdatePersonDetails(){

      Long id = 1L;
     Optional<Person> optionalPerson = Optional.ofNullable(p1);

     when(personRepository.findById(id)).thenReturn(optionalPerson);
     when(personService.updatePersonDetails(id,p5)).thenReturn(p5);
     ResponseEntity<Person> response = personController.updatePersonDetails(id,p5);

     //assertThrows(NoSuchElementException.class,()->personController.updatePersonDetails(id,p5));
    assertEquals(HttpStatus.OK,response.getStatusCode());
 }
    @Test
    void testDeletePersonDetails() {

        Long id = 2L;

        personController.deletePersonDetails(id);

        verify(personService,times(1)).deletePersonDetails(argumentCaptor.capture());


    }
}