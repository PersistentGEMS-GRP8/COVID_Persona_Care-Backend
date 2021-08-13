package com.covidpersona.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Admin.class, name = "admin"), @Type(value = HospitalAdmin.class, name = "hospitalAdmin"),
		@Type(value = Manager.class, name = "manager"), @Type(value = Doctor.class, name = "doctor"),
		@Type(value = Manager.class, name = "manager"), @Type(value = Patient.class, name = "patient") })
public abstract class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Size(min = 3, message = "Name should be atlease 3 characters")
	@Column(nullable = false)
	private String name;

	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank
	@Size(min = 10, max = 12, message = "Contact no should be between 10 to 12 characters")
	@Column(name = "contact_no", nullable = false)
	private String contactNo;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", unique = true)
	@JsonIgnore
	private PersonaUser userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public PersonaUser getUserId() {
		return userId;
	}

	public void setUserId(PersonaUser userId) {
		this.userId = userId;
	}

}
