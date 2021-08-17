package com.covidpersona.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.covidpersona.dto.DoctorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.covidpersona.entity.Appointment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@ToString
@NamedNativeQuery(name = "find_doctor_hospital", query = "select d.id as id, d.name as name, d.email as email, d.contact_no as contactNo, sp.id as specializationId, sp.name as specialization from hospital_doctors hd"
		+ " inner join hospital h on hd.hospital_id = h.h_id" + " inner join doctors d on hd.doctor_id = d.id"
		+ " inner join specializations sp on sp.id = d.specialization_id"
		+ " where hd.hospital_id = ?1 and d.name like '%' ?2 '%'", resultSetMapping = "doctor_dto")
@SqlResultSetMapping(name = "doctor_dto", classes = @ConstructorResult(targetClass = DoctorDto.class, columns = {
		@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "name", type = String.class),
		@ColumnResult(name = "email", type = String.class), @ColumnResult(name = "contactNo", type = String.class),
		@ColumnResult(name = "specializationId", type = Long.class),
		@ColumnResult(name = "specialization", type = String.class) }))
public class Doctor extends Person {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false, name = "specialization_id")
	@JsonIgnore
	private Specialization specialization;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "doctors")
//	@JsonIgnore
	private Set<Hospital> hospitals = new HashSet<>();

	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	private List<Appointment> appointments;

}
