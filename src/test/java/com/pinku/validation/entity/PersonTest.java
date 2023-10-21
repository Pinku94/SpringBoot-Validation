package com.pinku.validation.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

//    private Validator validator;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        validator = Validation.buildDefaultValidatorFactory().getValidator();
//    }

//
//    @Test
//    public void testValidPerson() {
//        Person person = new Person();
//        person.setId(1L);
//        person.setFirstName("Pinku");
//        person.setLastName("Kumar");
//        person.setEmail("pinku@example.com");
//        person.setDob(LocalDate.of(2002,04,21));
//
//        Set<ConstraintViolation<Person>> violations = validator.validate(person);
//
//        assertTrue(violations.isEmpty());
//    }
//
//    @Test
//    public void testInvalidPerson() {
//        Person person = new Person();
//        person.setId(1L);
//        person.setEmail("pin@#av");
//        person.setDob(LocalDate.of(2040,05,20));
//
//        Set<ConstraintViolation<Person>> violations = validator.validate(person);
//
//        assertThat(violations).hasSize(2); // one constraints violated: email, dob
//    }

}