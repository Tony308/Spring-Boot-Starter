package com.qa.springboot.dvd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dvd")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "lastModified"}, allowGetters = true)
public class DvdModel implements Serializable {

    public DvdModel(@NotBlank String title, Boolean checkedOut, String reference, Date timeStamp) {
        this.title = title;
        this.checkedOut = checkedOut;
        this.reference = reference;
        this.timeStamp = timeStamp;
    }

    public DvdModel() {
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	private Boolean checkedOut;
	
	private String reference;

	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat
	private Date timeStamp;
	
	public Boolean getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(Boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


}
