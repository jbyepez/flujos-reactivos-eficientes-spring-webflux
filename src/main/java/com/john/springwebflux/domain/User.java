package com.john.springwebflux.domain;

import java.time.LocalDate;

public class User {
    private Integer id;
    private Character documentType;
    private String documentNumber;
    private String firstName;
    private String secondName;
    private String lastName;
    private String lastNameHistory;
    private LocalDate birthDate;
    private Integer age;
    private Integer expressionNumber;
    private Integer lifePathNumber;

    public Integer getId() {
        return id;
    }

    public Character getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLastNameHistory() {
        return lastNameHistory;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getExpressionNumber() {
        return expressionNumber;
    }

    public Integer getLifePathNumber() {
        return lifePathNumber;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setDocumentType(Character documentType) {
        this.documentType = documentType;
        return this;
    }

    public User setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setLastNameHistory(String lastNameHistory) {
        this.lastNameHistory = lastNameHistory;
        return this;
    }

    public User setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public User setExpressionNumber(Integer expressionNumber) {
        this.expressionNumber = expressionNumber;
        return this;
    }

    public User setLifePathNumber(Integer lifePathNumber) {
        this.lifePathNumber = lifePathNumber;
        return this;
    }
}
