package com.covidpersona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDto {

	private long id;
	private long vaccineId;
	private String name;
	private int count;
}
