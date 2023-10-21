package com.pinku.validation.controller;


import com.pinku.validation.entity.Person;
import com.pinku.validation.exception.NoDataFoundException;
import com.pinku.validation.exception.UserPersonException;
import com.pinku.validation.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import  com.pinku.validation.exception.PersonGlobalException;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @PostMapping("/storePerson")
    public ResponseEntity<Person>  storePersonDetails(@Valid @RequestBody Person person)
    {
        Person p =personService.storePersonDetails(person);

        if(p.getFirstName()== null){
            log.error("Person details are not saved");
            throw new UserPersonException("Person details are not saved");
        }
        else {
            log.info("store the Person::{}",p);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        }
    }

    @GetMapping("/getAllPersonDetails")
    public ResponseEntity<List<Person>> getAllPersonDetails(){
        List<Person> person = personService.getAllPersonDetails();
        if (person.isEmpty()){
            log.error("No person available");

            //throw new NoDataFoundException("No Person Found");
            throw new NoSuchElementException();
        }
        else{
            return new ResponseEntity<List<Person>>(person,HttpStatus.FOUND);
        }
    }

    @GetMapping("/getUniquePersonDetails/{name}")
    public ResponseEntity<List<Person>> getUniquePersonDetails(@PathVariable String name){
        List<Person> person = personService.getAllUniquePersonDetails(name);
        if (person.isEmpty()){
            log.error("No unique name");
            throw new NoDataFoundException("No Person details available in DB");
        }
        else{
            return new ResponseEntity<List<Person>>(person,HttpStatus.FOUND);
        }
    }

    @PutMapping("/updatePersonDetails/{id}")
    public ResponseEntity<Person> updatePersonDetails(@PathVariable Long id, @RequestBody Person person) {

      if (id!=null && id>=0){

          Person person1= personService.updatePersonDetails(id,person);
          if(null== person1){
             throw new NoSuchElementException("person details are not found");
          }
          else {
              return new ResponseEntity<Person>(person1, HttpStatus.OK);
          }
      }
      else {

          throw new NullPointerException();
      }
    }

    @DeleteMapping("/deletePerson/{id}")
    public void deletePersonDetails(@PathVariable Long id){

       personService.deletePersonDetails(id);

    }
}
