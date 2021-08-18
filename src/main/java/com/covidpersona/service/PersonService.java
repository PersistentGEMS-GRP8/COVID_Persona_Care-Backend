package com.covidpersona.service;

import com.covidpersona.entity.Person;

public abstract class PersonService<T extends Person> {
	abstract T getPersonByUserId(long id);
}
