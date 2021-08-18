package com.covidpersona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
	private long id;
	private String name;
	private String email;
	private String contactNo;
	private long specializationId;
	private String specialization;
}
