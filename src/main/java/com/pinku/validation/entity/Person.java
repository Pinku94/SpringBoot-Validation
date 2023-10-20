package com.pinku.validation.entity;

import com.pinku.validation.annotation.ValidName;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import java.time.LocalDate;

@Entity
@Table(name = "Person_Table")
public class Person {

    @Id
    @GeneratedValue
    @Column(name="Person_Id")
    private Long id;

    @Column(name="Person_FirstName")
   // @Pattern(regexp = "^(?!.*m).*$", message = "First name can't contains 'm'")
    @ValidName
    private String firstName;

    //@Pattern(regexp = "^(?!.*n).*$", message = "Last name should not contains 'n'")
    @Column(name="Person_LastName")
    @ValidName
    private String lastName;

    @Column(name="Person_Dob")
    @Future(message = "DOB can't be a future date")
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    @Email(message = "invalid email")
    private String email;

    public Person(Long id, String firstName, String lastName, LocalDate dob, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
