package com.pinku.validation.controller;


import com.pinku.validation.entity.Person;
import com.pinku.validation.exception.userPersonException;
import com.pinku.validation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/storePerson")
    public ResponseEntity<Person>  storePersonDetails(@Valid @RequestBody Person person)
    {
        Person p =personService.storePersonDetails(person);

        if(p.getFirstName()== null){
            throw new userPersonException("Person details are not saved");
        }
        else {
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        }
    }

    @GetMapping("/message")
    public String getMessage(){

        return "This is SpringBoot project";
    }
}
