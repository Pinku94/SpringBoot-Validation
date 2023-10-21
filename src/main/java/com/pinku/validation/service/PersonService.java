package com.pinku.validation.service;


import com.pinku.validation.repository.PersonRepository;
import com.pinku.validation.entity.Person;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    @Modifying
    public Person storePersonDetails(Person person) {

        return personRepository.save(person);
    }

    public List<Person> getAllPersonDetails() {

        return personRepository.findAll();
    }

    public List<Person> getAllUniquePersonDetails(String name) {

        return  personRepository.findDistinctPersonByFirstName(name);
    }

    @Transactional
    @Modifying
    public Person updatePersonDetails(Long id, Person person) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        Person p = null;
        if (optionalPerson.isPresent()) {
            Person person1 = optionalPerson.get();

            if (null !=person.getFirstName() && null != person.getLastName() && null != person.getDob() && null != person.getEmail())
            {

                person1.setFirstName(person.getFirstName());
                person1.setLastName(person.getLastName());
                person1.setDob(person.getDob());
                person1.setEmail(person.getEmail());
                p = personRepository.save(person);
            }
        } else {
            throw new NoSuchElementException("Person not found");
        }
        return p;
    }

    @Transactional
    @Modifying
    public void deletePersonDetails(Long id) {
        personRepository.deleteById(id);
    }
}
