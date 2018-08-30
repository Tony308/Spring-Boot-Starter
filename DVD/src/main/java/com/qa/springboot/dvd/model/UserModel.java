package com.qa.springboot.dvd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate, lastModified"}, allowGetters = true)
public class UserModel implements Serializable{

	public UserModel(@NotBlank String firstName, @NotBlank String lastName, Date doB, Date createDate, Date lastModified) {
		this.firstName = firstName;
		this.lastName = lastName;
		DoB = doB;
		this.createDate = createDate;
		this.lastModified = lastModified;
	}

	public UserModel() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat
	private Date DoB;

	@Column(updatable = false, nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createDate;

	@Column(updatable = false, nullable = false)
	@Temporal( value = TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModified;

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

	public Date getDoB() {
		return DoB;
	}

	public void setDoB(Date doB) {
		DoB = doB;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
