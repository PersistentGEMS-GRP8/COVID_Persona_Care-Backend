package com.covidpersona.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
//@JsonTypeName("admin")
public class Admin extends Person {
}
