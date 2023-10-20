package com.pinku.validation.service;


import com.pinku.validation.repository.PersonRepository;
import com.pinku.validation.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person storePersonDetails(Person person) {

        return personRepository.save(person);
    }
}
