package com.covidpersona.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String email;
	private long contactNo;

}
